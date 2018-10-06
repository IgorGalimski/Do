package galimski.igor.com.do_ing;

import android.os.AsyncTask;

public class AddTaskAsync extends AsyncTask<Task, Void, Void>
{
    @Override
    protected Void doInBackground(Task... tasks) {

        for (Task task : tasks){
            DatabaseHelper.AddTask(task);
        }

        return null;
    }
}