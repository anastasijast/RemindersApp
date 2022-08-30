using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ReminderAPI.Models;
using ReminderAPI.Repositories;

namespace ReminderAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ReminderController : ControllerBase
    {
        private readonly IReminderRepository _reminderRepository;
        public ReminderController(IReminderRepository reminderRepository)
        {
            _reminderRepository = reminderRepository;
        }
        [HttpGet]
        public async Task<IEnumerable<Reminder>> GetReminders()
        {
            return await _reminderRepository.Get();
        }
        [HttpGet("{id}")]
        public async Task<ActionResult<Reminder>> GetReminders(int id)
        {
            return await _reminderRepository.Get(id);
        }
        [HttpPost]
        public async Task<ActionResult<Reminder>> PostReminders([FromBody] Reminder reminder)
        {
            var newReminder = await _reminderRepository.Create(reminder);
            return CreatedAtAction(nameof(GetReminders), new { id = newReminder.Id }, newReminder);
        }
        [HttpPut]
        public async Task<ActionResult> PutReminders(int id, [FromBody] Reminder reminder)
        {
            if(id != reminder.Id)
            {
                return BadRequest();
            }
            await _reminderRepository.Update(reminder);
            return NoContent(); 
        }
        [HttpDelete("{id}")]
        public async Task<ActionResult> Delete (int id)
        {
            var reminderToDelete = await _reminderRepository.Get(id);
            if(reminderToDelete == null)
            {
                return NotFound();
            }
            await _reminderRepository.Delete(reminderToDelete.Id);
            return NoContent();
        }
    }
}
