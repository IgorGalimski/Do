package galimski.igor.com.do_ing;

import android.os.AsyncTask;

import java.util.ArrayList;

public class GetAllTaskAsync extends AsyncTask<Void, Integer, ArrayList<Task>>
{
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Task> doInBackground(Void... voids) {

        return DatabaseHelper.GetAllTasks();
    }
}

