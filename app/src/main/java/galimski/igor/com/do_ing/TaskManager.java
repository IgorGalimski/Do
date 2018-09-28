package galimski.igor.com.do_ing;

import java.util.ArrayList;
import java.util.Date;

public class TaskManager
{
    private static final TaskManager Instance = new TaskManager();

    public static TaskManager getInstance()
    {
        return Instance;
    }

    private ArrayList<Task> _tasks = new ArrayList<Task>();

    public TaskManager()
    {
        Task test1 = new Task("Task1", "FullTask1", TaskPriority.Immediate, new Date(2018,1, 1));
        Task test2 = new Task("Task2", "FullTask2", TaskPriority.Hight, new Date(2018,2, 1));
        Task test3 = new Task("Task3", "FullTask3", TaskPriority.Normal, new Date(2018,3, 1));
        Task test4 = new Task("Task4", "FullTask4", TaskPriority.Low, new Date(2018,4, 1));

        _tasks.add(test1);
        _tasks.add(test2);
        _tasks.add(test3);
        _tasks.add(test4);
    }

    public ArrayList<Task> GetTasks()
    {
       return _tasks;
    }
}
