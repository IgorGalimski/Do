package galimski.igor.com.do_ing.Managers;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import galimski.igor.com.do_ing.Database.Async.*;
import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.Database.Data.TaskPriority;
import galimski.igor.com.do_ing.View.Activites.MainActivity;

public class TaskManager
{
    private static ArrayList<Task> _tasks = new ArrayList<Task>();

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
               Crashlytics.log(exp.getMessage());
           }
       }

       return _tasks;
    }

    public static Task GetTask(int id)
    {
        for (Task task: _tasks)
        {
            if(task.Id == id)
            {
              return task;
            }
        }

        return null;
    }

    public static void AddTask(Task task, boolean addNotification)
    {
        _tasks.add(task);

        if(addNotification)
        {
            MainActivity.GetInstance().DelayNotification(MainActivity.GetInstance().CreateNotification(task), task.CompletionDate.getTime(), task.Id);
        }

        AddTaskAsync addTaskAsync = new AddTaskAsync();
        addTaskAsync.execute(task);
    }

    public static void DeleteTask(Task task)
    {
        _tasks.remove(task);

        MainActivity.GetInstance().CancelNotificatiion(task.Id);

        DeleteTaskAsync deleteTaskAsync = new DeleteTaskAsync();
        deleteTaskAsync.execute(task);
    }
}
