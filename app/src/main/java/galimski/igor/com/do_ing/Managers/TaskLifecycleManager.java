package galimski.igor.com.do_ing.Managers;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.Calendar;

import galimski.igor.com.do_ing.Database.Async.AddTaskLifecycleAsync;
import galimski.igor.com.do_ing.Database.Async.GetAllTasksLifecyclesAsync;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycleState;

public class TaskLifecycleManager
{
    private static ArrayList<TaskLifecycle> _tasksLifecycles = new ArrayList<TaskLifecycle>();

    public static ArrayList<TaskLifecycle> GetTasksLifecycles()
    {
        if(_tasksLifecycles.size() == 0)
        {
            try
            {
                GetAllTasksLifecyclesAsync getAllTasksLifecyclesAsync = new GetAllTasksLifecyclesAsync();
                getAllTasksLifecyclesAsync.execute();

                _tasksLifecycles = getAllTasksLifecyclesAsync.get();
            }
            catch (Exception exp)
            {
                Crashlytics.log(exp.getMessage());
            }
        }

        return _tasksLifecycles;
    }

    public static void OnTaskCreate()
    {
        TaskLifecycle taskLifecycle = new TaskLifecycle(Calendar.getInstance().getTime(), TaskLifecycleState.CREATED);

        _tasksLifecycles.add(taskLifecycle);

        AddTaskLifecycleAsync addTaskLifecycleAsync = new AddTaskLifecycleAsync();
        addTaskLifecycleAsync.execute(taskLifecycle);
    }

    public static void OnTaskUpdate()
    {

    }

    public static void OnTaskComplete()
    {

    }

    public static void OnTaskDelete()
    {

    }
}
