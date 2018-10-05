package galimski.igor.com.do_ing.sampledata;

import android.os.AsyncTask;

import java.util.ArrayList;

import galimski.igor.com.do_ing.MainActivity;
import galimski.igor.com.do_ing.Task;

public class DatabaseTask extends AsyncTask<Void, Integer, ArrayList<Task>>
{

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Task> doInBackground(Void... voids) {

        return MainActivity.GetInstance().GetDatabaseHepler().GetAllTasks();
    }
}
