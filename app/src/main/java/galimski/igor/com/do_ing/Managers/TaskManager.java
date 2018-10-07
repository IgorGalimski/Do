package galimski.igor.com.do_ing.Managers;

import java.util.ArrayList;
import java.util.Date;

import galimski.igor.com.do_ing.Database.Async.*;
import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.Database.Data.TaskPriority;
import galimski.igor.com.do_ing.View.Activites.MainActivity;

public class TaskManager
{
    private static ArrayList<Task> _tasks = new ArrayList<Task>();

    public TaskManager()
    {

    }

    public static ArrayList<Task> GetTasks()
    {
       if(_tasks.size() == 0)
       {
           try
           {
                GetAllTaskAsync getAllTaskAsync = new GetAllTaskAsync();
                getAllTaskAsync.execute();

               _tasks = getAllTaskAsync.get();
           }
           catch (Exception exp)
           {
                MainActivity.ShowMessage(exp.getMessage());
           }
       }

       return _tasks;
    }

    public static void AddTask(String shortDescription, String fullDescription, TaskPriority taskPriority, Date completitionDate, Boolean addNotification)
    {
        Task newTask = new Task(shortDescription, fullDescription, taskPriority, completitionDate);
        _tasks.add(newTask);

        if(addNotification)
        {
            MainActivity.GetInstance().DelayNotification(MainActivity.GetInstance().CreateNotification(newTask), newTask.CompletionDate.getTime(), newTask.Id);
        }

        AddTaskAsync addTaskAsync = new AddTaskAsync();
        addTaskAsync.execute(newTask);
    }

    public static void DeleteTask(Task task)
    {
        _tasks.remove(task);

        DeleteTaskAsync deleteTaskAsync = new DeleteTaskAsync();
        deleteTaskAsync.execute(task);
    }
}
