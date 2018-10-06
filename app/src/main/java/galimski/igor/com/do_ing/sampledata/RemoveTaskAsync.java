package galimski.igor.com.do_ing.sampledata;

import android.os.AsyncTask;

import galimski.igor.com.do_ing.MainActivity;
import galimski.igor.com.do_ing.Task;

class RemoveTaskAsync extends AsyncTask<Task, Void, Void> {

    @Override
    protected Void doInBackground(Task... tasks) {

        for (Task task : tasks){

            MainActivity.GetInstance().GetDatabaseHepler().DeleteTask(task);

        }

        return null;
    }
}
