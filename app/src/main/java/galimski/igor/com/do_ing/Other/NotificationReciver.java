package galimski.igor.com.do_ing.Other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Managers.TaskManager;

public class NotificationReciver extends BroadcastReceiver
{
    public static String TASK_ID = "taskId";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        int taskId = intent.getParcelableExtra(TASK_ID);

        Task task = TaskManager.GetTask(taskId);
        task.NotificationShown = true;
    }
}
