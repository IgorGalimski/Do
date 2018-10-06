package galimski.igor.com.do_ing;

import android.arch.persistence.room.Room;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseHelper {

    private static final String TAG = "SQLite";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "TaskDatabaseRoom";

    private static final String TABLE_TASK = "Task";

    private static final String COLUMN_TASK_ID ="Id";
    private static final String COLUMN_TASK_SHORT ="ShortDescription";
    private static final String COLUMN_TASK_FULL = "FullDescription";

    private static final String COLUMN_TASK_CREATIONDATE = "CreatedDate";
    private static final String COLUMN_TASK_COMPLETIONDATE = "CompletionDate";

    private static final String COLUMN_TASK_PRIORITY = "Priority";

    private static final String COLUMN_TASK_SHOWN= "Shown";

    private static TaskDao db;

    public static void Init()
    {
        db =  Room.databaseBuilder(MainActivity.GetInstance().getApplicationContext(), AppDatabase.class, DATABASE_NAME).build().taskDao();
    }

    public static void AddTask(Task task) {
        Log.i(TAG, "MyDatabaseHelper.addTask ... ");

        db.insert(task);

    }

    public static Task GetTask(int id) {
        Log.i(TAG, "MyDatabaseHelper.getTask ... " + id);

        return db.findById(id);
    }


    public static ArrayList<Task> GetAllTasks() {
        Log.i(TAG, "MyDatabaseHelper.getAllTasks ... " );

        return new ArrayList<>(db.getAll());
    }

    /*public int updateNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());

        // updating row
        return db.update(TABLE_NOTE, values, COLUMN_NOTE_ID + " = ?",
                new String[]{String.valueOf(note.getNoteId())});
    }*/

    public static void DeleteTask(Task task) {
        Log.i(TAG, "MyDatabaseHelper.deleteTask ... ");

        db.delete(task);
    }
}
