using Microsoft.EntityFrameworkCore;
using ReminderAPI.Models;

namespace ReminderAPI.Repositories
{
    public class ReminderRepository : IReminderRepository
    {
        private readonly ReminderContext _context;
        public ReminderRepository(ReminderContext context)
        {
            _context = context;

        }
        public async Task<Reminder> Create(Reminder reminder)
        {
            _context.Reminders.Add(reminder);
            await _context.SaveChangesAsync();
            return reminder;
        }

        public async Task Delete(int id)
        {
            var reminderToDelete = await _context.Reminders.FindAsync(id);
            _context.Reminders.Remove(reminderToDelete);
            await _context.SaveChangesAsync();
        }

        public async Task<IEnumerable<Reminder>> Get()
        {
            return await _context.Reminders.ToListAsync();
        }

        public async Task<Reminder> Get(int id)
        {
            return await _context.Reminders.FindAsync(id);
        }

        public async Task Update(Reminder reminder)
        {
            _context.Entry(reminder).State = EntityState.Modified;
            await _context.SaveChangesAsync();
        }
    }
}
