package galimski.igor.com.do_ing.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.R;
import galimski.igor.com.do_ing.View.Activites.MainActivity;

public class TaskLifecycleRecyclerAdapter extends RecyclerView.Adapter<TaskLifecycleRecyclerAdapter.ViewHolder>
{
    private TaskLifecycle[] _tasksLifecycles;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView _taskLifecycleAction;
        public TextView _taskLifecycleDate;
        public TextView _taskLifecycleTime;

        public ViewHolder(View view)
        {
            super(view);

            _taskLifecycleAction = (TextView) view.findViewById(R.id.taskLifecycleAction);
            _taskLifecycleDate = (TextView) view.findViewById(R.id.taskLifecycleDate);
            _taskLifecycleTime = (TextView) view.findViewById(R.id.taskLifecycleTime);
        }
    }

    public TaskLifecycleRecyclerAdapter(ArrayList<TaskLifecycle> taskLifecycles)
    {
        _tasksLifecycles = new TaskLifecycle[taskLifecycles.size()];
        _tasksLifecycles = taskLifecycles.toArray(_tasksLifecycles);
    }

    @Override
    public TaskLifecycleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);

        TaskLifecycleRecyclerAdapter.ViewHolder vh = new TaskLifecycleRecyclerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskLifecycleRecyclerAdapter.ViewHolder holder, int position)
    {
        TaskLifecycle taskLifecycle = _tasksLifecycles[position];

        String action = MainActivity.GetInstance().getResources().getString(taskLifecycle.TaskLifecycleState.GetLifecycleString(taskLifecycle.TaskLifecycleState));
        holder._taskLifecycleAction.setText(action);

        holder._taskLifecycleTime.setText(GetTime(taskLifecycle.Date));
        holder._taskLifecycleDate.setText(GetDate(taskLifecycle.Date));
    }

    private String GetTime(Date date)
    {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(date);
    }

    private String GetDate(Date date)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        return df.format(date);
    }

    @Override
    public int getItemCount()
    {
        return _tasksLifecycles.length;
    }
}
