package galimski.igor.com.do_ing.sampledata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;

import galimski.igor.com.do_ing.MainActivity;
import galimski.igor.com.do_ing.Task;
import galimski.igor.com.do_ing.TaskPriority;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "TaskDatabase.db";

    private static final String TABLE_TASK = "Task";

    private static final String COLUMN_TASK_ID ="Id";
    private static final String COLUMN_TASK_SHORT ="ShortDescription";
    private static final String COLUMN_TASK_FULL = "FullDescription";

    private static final String COLUMN_TASK_CREATIONDATE = "CreatedDate";
    private static final String COLUMN_TASK_COMPLETIONDATE = "CompletionDate";

    private static final String COLUMN_TASK_PRIORITY = "Priority";

    private static final String COLUMN_TASK_SHOWN= "Shown";

    public DatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");

        //        String script = "CREATE TABLE " + TABLE_NOTE + "("
        //                + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY," + COLUMN_NOTE_TITLE + " TEXT,"
        //                + COLUMN_NOTE_CONTENT + " TEXT" + ")";
        //String script = "CREATE TABLE `Task` ( `Id` INTEGER NOT NULL, `ShortDescription` TEXT NOT NULL, `FullDescription` TEXT, `CreatedDate` TEXT NOT NULL, `CompletionDate` TEXT NOT NULL, `Priority` TEXT NOT NULL, PRIMARY KEY(`Id`) )";

        String script = "CREATE TABLE " + TABLE_TASK + "("
                        + COLUMN_TASK_ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_TASK_SHORT + " TEXT,"
                        + COLUMN_TASK_CREATIONDATE + " TEXT,"
                        + COLUMN_TASK_COMPLETIONDATE + " TEXT,"
                        + COLUMN_TASK_PRIORITY + " TEXT,"
                        + COLUMN_TASK_SHOWN + " TEXT" + ")";

        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
            onCreate(db);
        }

    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void AddTask(Task task) {
        Log.i(TAG, "MyDatabaseHelper.addTask ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TASK_SHORT, task.GetShortDescription());
        values.put(COLUMN_TASK_FULL, task.GetFullDescription());

        values.put(COLUMN_TASK_CREATIONDATE, task.GetCreatedDate().toString());
        values.put(COLUMN_TASK_COMPLETIONDATE, task.GetCompletionDate().toString());

        values.put(COLUMN_TASK_PRIORITY, task.GetTaskPriority().toString());

        db.insert(TABLE_TASK, null, values);

        db.execSQL("INSERT INTO " + TABLE_TASK
                + " (" + task.GetShortDescription() + ","
                + task.GetFullDescription() + ","
                + task.GetCreatedDate().toString() + ","
                + task.GetCompletionDate().toString() + ","
                + task.GetCompletionDate());

        MainActivity.ShowMessage(String.valueOf(DatabaseUtils.queryNumEntries(db, TABLE_TASK)));

        db.close();
    }


    public Task GetTask(int id) {
        Log.i(TAG, "MyDatabaseHelper.getTask ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASK,
                new String[] { COLUMN_TASK_ID, COLUMN_TASK_SHORT, COLUMN_TASK_FULL }, COLUMN_TASK_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task();
        task.SetShortDescription(cursor.getString(1));
        task.SetFullDescription(cursor.getString(2));

        task.SetCreatedDate(Date.valueOf(cursor.getString(3)));
        task.SetCompletionDate(Date.valueOf(cursor.getString(4)));

        task.SetTaskPriority(TaskPriority.valueOf(cursor.getString(5)));

        task.SetNotificationShown(Boolean.parseBoolean(cursor.getString(6)));

        cursor.close();

        return task;
    }


    public ArrayList<Task> GetAllTasks() {
        Log.i(TAG, "MyDatabaseHelper.getAllTasks ... " );

        ArrayList<Task> taskList = new ArrayList<Task>();

        String selectQuery = "SELECT * FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.SetShortDescription(cursor.getString(1));
                task.SetFullDescription(cursor.getString(2));

                task.SetCreatedDate(Date.valueOf(cursor.getString(3)));
                task.SetCompletionDate(Date.valueOf(cursor.getString(4)));

                task.SetTaskPriority(TaskPriority.valueOf(cursor.getString(5)));

                task.SetNotificationShown(Boolean.parseBoolean(cursor.getString(6)));

                taskList.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return taskList;
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

    public void DeleteTask(Task task) {
        Log.i(TAG, "MyDatabaseHelper.deleteTask ... ");

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, COLUMN_TASK_ID + " = ?", new String[] { String.valueOf(task.GetId()) });
        db.close();
    }
}
