package galimski.igor.com.do_ing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TaskManager
{
    private static final TaskManager Instance = new TaskManager();

    public static TaskManager GetInstance()
    {
        return Instance;
    }

    private ArrayList<Task> _tasks = new ArrayList<Task>();

    public void PutTestTasks()
    {
        Date completionDate = Calendar.getInstance().getTime();
        Task test1 = new Task("Task1", "FullTask1", TaskPriority.Immediate, completionDate);

        for (int i = 0; i < 100; i++)
        {
            MainActivity.GetInstance().GetDatabaseHepler().AddTask(test1);
            //_tasks.add(test1);
        }

        //GetTasks();

        _tasks = MainActivity.GetInstance().GetDatabaseHepler().GetAllTasks();
        //MainActivity.ShowMessage(String.valueOf(_tasks.size()));

    }

    public ArrayList<Task> GetTasks()
    {
       return _tasks;
    }

    public void AddTask(String shortDescription, String fullDescription, TaskPriority taskPriority, Date completitionDate, Boolean addNotification)
    {
        Task newTask = new Task(shortDescription, fullDescription, taskPriority, completitionDate);
        _tasks.add(newTask);

        if(addNotification)
        {
            MainActivity.GetInstance().DelayNotification(MainActivity.GetInstance().CreateNotification(newTask), newTask.GetCompletionDate().getTime(), newTask.GetId());
        }

        MainActivity.GetInstance().GetDatabaseHepler().AddTask(newTask);
    }

    public void DeleteTask(Task task)
    {
        if(_tasks.contains(task))
        {
            _tasks.remove(task);
        }

        MainActivity.GetInstance().GetDatabaseHepler().DeleteTask(task);
    }
}
