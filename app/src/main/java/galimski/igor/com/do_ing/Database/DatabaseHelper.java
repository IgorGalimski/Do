package galimski.igor.com.do_ing.Database;

import android.arch.persistence.room.Room;
import android.util.Log;

import java.util.ArrayList;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.View.Activites.MainActivity;

public class DatabaseHelper {

    private static final String TAG = "SQLite";

    private static final String DATABASE_NAME = "TaskDatabaseRoom";

    private static TaskDao db;

    public static void Init()
    {
        db =  Room.databaseBuilder(MainActivity.GetInstance().getApplicationContext(), AppDatabase.class, DATABASE_NAME).build().taskDao();
    }

    public static void AddTask(Task task)
    {
        Log.i(TAG, "MyDatabaseHelper.addTask ... ");

        db.insertTask(task);
    }

    public static Task GetTask(int id) {
        Log.i(TAG, "MyDatabaseHelper.getTask ... " + id);

        return db.findTaskById(id);
    }


    public static ArrayList<Task> GetAllTasks() {
        Log.i(TAG, "MyDatabaseHelper.getAllTasks ... " );

        return new ArrayList<>(db.getAllTasks());
    }

    public static void DeleteTask(Task task)
    {
        Log.i(TAG, "MyDatabaseHelper.deleteTask ... ");

        db.deleteTask(task);
    }

    public static void AddTaskLifecycle(TaskLifecycle taskLifecycle)
    {
        Log.i(TAG, "MyDatabaseHelper.AddTaskLifecycle ... ");

        db.insertTaskLifecycle(taskLifecycle);
    }

    public static ArrayList<TaskLifecycle> GetAllTaskLifecycles()
    {
        Log.i(TAG, "MyDatabaseHelper.GetAllTaskLifecycles ... ");

        return new ArrayList<>(db.getAllTasksLifecycles());
    }
}
