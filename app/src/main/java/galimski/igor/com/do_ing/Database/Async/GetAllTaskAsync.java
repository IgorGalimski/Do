package galimski.igor.com.do_ing.Database.Async;

import android.os.AsyncTask;

import java.util.ArrayList;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.DatabaseHelper;

public class GetAllTaskAsync extends AsyncTask<Void, Integer, ArrayList<Task>>
{
    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Task> doInBackground(Void... voids)
    {
        return DatabaseHelper.GetAllTasks();
    }
}

