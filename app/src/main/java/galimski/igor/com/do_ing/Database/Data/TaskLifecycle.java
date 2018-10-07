package galimski.igor.com.do_ing.Database.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import galimski.igor.com.do_ing.Database.TaskConverter;

@Entity
public class TaskLifecycle
{
    @PrimaryKey(autoGenerate = true)
    public int Id;

    @TypeConverters({TaskConverter.class})
    public Date Date;

    @TypeConverters({TaskConverter.class})
    public TaskLifecycleState TaskLifecycleState;
}
