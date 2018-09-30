package galimski.igor.com.do_ing;

import java.util.ArrayList;
import java.util.Date;

public class TaskManager
{
    private static final TaskManager Instance = new TaskManager();

    public static TaskManager GetInstance()
    {
        return Instance;
    }

    private ArrayList<Task> _tasks;

    public TaskManager()
    {
        Task test1 = new Task("Task1", "FullTask1", TaskPriority.Immediate, new Date(2018,1, 1));
        MainActivity.GetInstance().GetDatabaseHepler().AddTask(test1);

        MainActivity.GetInstance().DelayNotification(MainActivity.GetInstance().CreateNotification(test1), test1.GetCompletionDate().getTime(), test1.GetId());

        _tasks = MainActivity.GetInstance().GetDatabaseHepler().GetAllTasks();
    }

    public ArrayList<Task> GetTasks()
    {
       if(_tasks == null)
       {
           _tasks = new ArrayList<>();
       }

       return _tasks;
    }

    public void AddTask(String shortDescription, String fullDescription, TaskPriority taskPriority, java.sql.Date completitionDate, Boolean addNotification)
    {
        Task newTask = new Task(shortDescription, fullDescription, taskPriority, completitionDate);
        _tasks.add(newTask);

        if(addNotification)
        {
            MainActivity.GetInstance().DelayNotification(MainActivity.GetInstance().CreateNotification(newTask), newTask.GetCompletionDate().getTime(), newTask.GetId());
        }
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
