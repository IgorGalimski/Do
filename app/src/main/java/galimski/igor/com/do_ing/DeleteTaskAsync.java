package galimski.igor.com.do_ing;

import android.os.AsyncTask;

class DeleteTaskAsync extends AsyncTask<Task, Void, Void> {

    @Override
    protected Void doInBackground(Task... tasks) {

        for (Task task : tasks){

            DatabaseHelper.DeleteTask(task);

        }

        return null;
    }
}
