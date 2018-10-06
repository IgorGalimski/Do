package galimski.igor.com.do_ing;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class TaskConverter {

    @TypeConverter
    public static String fromDate(Date date) {
        return date.toString();
    }

    @TypeConverter
    public static Date fromDate(String dateString) {
        return new Date(dateString);
    }

    @TypeConverter
    public static String fromTaskPriority(TaskPriority taskPriority) {
        return taskPriority.toString();
    }

    @TypeConverter
    public static TaskPriority fromTaskPriority(String taskPriorityString) {
        return TaskPriority.valueOf(taskPriorityString);
    }

    @TypeConverter
    public static String fromBolean(boolean value) {
        return String.valueOf(value);
    }

    @TypeConverter
    public static boolean fromBoleanString(String value) {
        return true;
    }

}
