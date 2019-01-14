package galimski.igor.com.do_ing.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;

@Database(entities = {Task.class, TaskLifecycle.class}, version = 1, exportSchema = false)
@TypeConverters({TaskConverter.class})
public abstract class AppDatabase extends RoomDatabase
{
    public abstract TaskDao taskDao();
}
