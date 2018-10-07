package galimski.igor.com.do_ing.Database.Async;

import android.os.AsyncTask;

import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.Database.DatabaseHelper;

public class AddTaskLifecycleAsync extends AsyncTask<TaskLifecycle, Void, Void>
{
    @Override
    protected Void doInBackground(TaskLifecycle... taskLifecycles)
    {
        for (TaskLifecycle taskLifecycle : taskLifecycles)
        {
            DatabaseHelper.AddTaskLifecycle(taskLifecycle);
        }

        return null;
    }
}
