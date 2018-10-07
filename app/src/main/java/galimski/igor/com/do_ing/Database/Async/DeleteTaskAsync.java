package galimski.igor.com.do_ing.Database.Async;

import android.os.AsyncTask;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.DatabaseHelper;

public class DeleteTaskAsync extends AsyncTask<Task, Void, Void>
{
    @Override
    protected Void doInBackground(Task... tasks)
    {
        for (Task task : tasks)
        {
            DatabaseHelper.DeleteTask(task);
        }

        return null;
    }
}
