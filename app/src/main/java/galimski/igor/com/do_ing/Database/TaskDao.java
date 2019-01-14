package galimski.igor.com.do_ing.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;

@Dao
public interface TaskDao
{
    @Query("SELECT * FROM task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM task WHERE Id LIKE :id LIMIT 1")
    Task findTaskById(int id);

    @Insert
    void insertAllTasks(List<Task> tasks);

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM tasklifecycle")
    List<TaskLifecycle> getAllTasksLifecycles();

    @Insert
    void insertTaskLifecycle(TaskLifecycle taskLifecycle);

    @Update
    void updateTaskLifecycle(TaskLifecycle taskLifecycle);

    @Delete
    void deleteTaskLifecycle(TaskLifecycle taskLifecycle);
}
