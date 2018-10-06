package galimski.igor.com.do_ing.sampledata;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import galimski.igor.com.do_ing.AppDatabase;
import galimski.igor.com.do_ing.MainActivity;
import galimski.igor.com.do_ing.Task;
import galimski.igor.com.do_ing.TaskDao;


public class DatabaseHelper extends SQLiteOpenHelper {

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

    private TaskDao db;

    public DatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        //String path = context.getDatabasePath(DATABASE_NAME).getPath();

        //database = SQLiteDatabase.openDatabase(path, null, CREATE_IF_NECESSARY | OPEN_READWRITE);

        db =  Room.databaseBuilder(MainActivity.GetInstance().getApplicationContext(), AppDatabase.class, DATABASE_NAME).build().taskDao();
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

        db.insert(task);

    }

    public Task GetTask(int id) {
        Log.i(TAG, "MyDatabaseHelper.getTask ... " + id);

        return db.findById(id);
    }


    public ArrayList<Task> GetAllTasks() {
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

    public void DeleteTask(Task task) {
        Log.i(TAG, "MyDatabaseHelper.deleteTask ... ");

        db.delete(task);
    }
}
