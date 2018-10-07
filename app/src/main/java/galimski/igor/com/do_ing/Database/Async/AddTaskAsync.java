package galimski.igor.com.do_ing.Database.Async;

import android.os.AsyncTask;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.DatabaseHelper;

public class AddTaskAsync extends AsyncTask<Task, Void, Void>
{
    @Override
    protected Void doInBackground(Task... tasks)
    {
        for (Task task : tasks)
        {
            DatabaseHelper.AddTask(task);
        }

        return null;
    }
}