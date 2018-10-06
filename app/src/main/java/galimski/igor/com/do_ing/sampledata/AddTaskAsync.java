package galimski.igor.com.do_ing.sampledata;

import android.os.AsyncTask;

import galimski.igor.com.do_ing.MainActivity;
import galimski.igor.com.do_ing.Task;

public class AddTaskAsync extends AsyncTask<Task, Void, Void>
{

    @Override
    protected Void doInBackground(Task... tasks) {

        for (Task task : tasks){

            MainActivity.GetInstance().GetDatabaseHepler().AddTask(task);

        }

        return null;
    }
}