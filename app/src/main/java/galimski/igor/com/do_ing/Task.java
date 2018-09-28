package galimski.igor.com.do_ing;

import java.util.Calendar;
import java.util.Date;

public class Task
{
    private Date _createdDate;
    private Date _completionDate;

    private TaskPriority _taskPriority;

    private String _shortDescription;
    private String _fullDescription;

    public Task(String shortDescription, String fullDescription, TaskPriority taskPriority, Date completionDate)
    {
        _shortDescription = shortDescription;
        _fullDescription = fullDescription;

        _taskPriority = taskPriority;

        _createdDate = Calendar.getInstance().getTime();
        _completionDate = completionDate;
    }

    public String GetShortDescription()
    {
        return _shortDescription;
    }
    public Date GetCreatedDate() { return _createdDate; }
    public TaskPriority GetTaskPriority() { return _taskPriority; }
}
