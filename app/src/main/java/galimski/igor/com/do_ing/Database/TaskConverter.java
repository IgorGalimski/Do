package galimski.igor.com.do_ing.Database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

import galimski.igor.com.do_ing.Database.Data.TaskLifecycleState;
import galimski.igor.com.do_ing.Database.Data.TaskPriority;

public class TaskConverter
{
    @TypeConverter
    public static String fromDate(Date date)
    {
        return date.toString();
    }

    @TypeConverter
    public static Date fromDate(String dateString)
    {
        return new Date(dateString);
    }

    @TypeConverter
    public static String fromTaskPriority(TaskPriority taskPriority)
    {
        return taskPriority.toString();
    }

    @TypeConverter
    public static TaskPriority fromTaskPriority(String taskPriorityString)
    {
        return TaskPriority.valueOf(taskPriorityString);
    }

    @TypeConverter
    public static String fromBolean(boolean value)
    {
        return String.valueOf(value);
    }

    @TypeConverter
    public static boolean fromBoleanString(String value)
    {
        return true;
    }

    @TypeConverter
    public static String fromTaskLifecycleState(TaskLifecycleState taskLifecycleState)
    {
        return taskLifecycleState.toString();
    }

    @TypeConverter
    public static TaskLifecycleState fromTaskLifecycleStateString(String taskLifecycleState)
    {
        return TaskLifecycleState.valueOf(taskLifecycleState);
    }
}
