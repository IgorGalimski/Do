package galimski.igor.com.do_ing.Database.Async;

import android.os.AsyncTask;

import java.util.ArrayList;

import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.Database.DatabaseHelper;

public class GetAllTasksLifecyclesAsync extends AsyncTask<Void, Integer, ArrayList<TaskLifecycle>>
{
    @Override
    protected ArrayList<TaskLifecycle> doInBackground(Void... voids)
    {
        return DatabaseHelper.GetAllTaskLifecycles();
    }
}
