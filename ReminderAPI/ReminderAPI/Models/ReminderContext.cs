using Microsoft.EntityFrameworkCore;

namespace ReminderAPI.Models
{
    public class ReminderContext : DbContext
    {
        public ReminderContext(DbContextOptions<ReminderContext> options)
            : base(options) 
        {
            Database.EnsureCreated();
        }
        public DbSet<Reminder> Reminders { get; set; }
    }
    
}
