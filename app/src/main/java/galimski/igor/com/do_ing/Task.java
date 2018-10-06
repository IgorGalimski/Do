package galimski.igor.com.do_ing;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Task
{
    @PrimaryKey(autoGenerate = true)
    public int Id;

    @TypeConverters({TaskConverter.class})
    public Date CreatedDate;
    @TypeConverters({TaskConverter.class})
    public Date CompletionDate;

    @TypeConverters({TaskConverter.class})
    public TaskPriority TaskPriority;

    @ColumnInfo(name = "_shortDescription")
    public String ShortDescription;
    @ColumnInfo(name = "_fullDescription")
    public String FullDescription;

    @TypeConverters({TaskConverter.class})
    public boolean _notificationShown;

    public Task()
    {

    }

    public Task(String shortDescription, String fullDescription, TaskPriority taskPriority, Date completionDate)
    {
        ShortDescription = shortDescription;
        FullDescription = fullDescription;

        TaskPriority = taskPriority;

        CreatedDate = Calendar.getInstance().getTime();
        CompletionDate = completionDate;
    }
}
