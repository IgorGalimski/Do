package galimski.igor.com.do_ing;

import java.util.Calendar;
import java.util.Date;

public class Task
{
    private int _id;

    private Date _createdDate;
    private Date _completionDate;

    private TaskPriority _taskPriority;

    private String _shortDescription;
    private String _fullDescription;

    private boolean _notificationShown;

    public Task()
    {

    }

    public Task(String shortDescription, String fullDescription, TaskPriority taskPriority, Date completionDate)
    {
        _shortDescription = shortDescription;
        _fullDescription = fullDescription;

        _taskPriority = taskPriority;

        _createdDate = Calendar.getInstance().getTime();
        _completionDate = completionDate;
    }

    public int GetId() { return _id; }
    public String GetShortDescription()
    {
        return _shortDescription;
    }
    public String GetFullDescription() { return _fullDescription; }
    public Date GetCreatedDate() { return _createdDate; }
    public Date GetCompletionDate() { return _completionDate; }
    public TaskPriority GetTaskPriority() { return _taskPriority; }
    public boolean GetNotificationShown() { return _notificationShown; }

    public void SetShortDescription(String shortDescription)
    {
        _shortDescription = shortDescription;
    }

    public void SetFullDescription(String fullDescription)
    {
        _fullDescription = fullDescription;
    }

    public void SetCreatedDate(Date createdDate)
    {
        _createdDate = createdDate;
    }

    public void SetCompletionDate(Date completionDateDate)
    {
        _completionDate = completionDateDate;
    }

    public void SetTaskPriority(TaskPriority taskPriority)
    {
        _taskPriority = taskPriority;
    }

    public void SetNotificationShown(boolean shown)
    {
        _notificationShown = true;
    }
}
