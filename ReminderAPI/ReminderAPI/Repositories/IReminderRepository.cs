using ReminderAPI.Models;

namespace ReminderAPI.Repositories
{
    public interface IReminderRepository
    {
        Task<IEnumerable<Reminder>> Get();
        Task<Reminder> Get(int id);
        Task<Reminder> Create(Reminder reminder);
        Task Update (Reminder reminder);
        Task Delete (int id);
    }
}
