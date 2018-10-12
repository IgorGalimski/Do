package galimski.igor.com.do_ing.View;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.R;

public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder>
{
    private ArrayList<Task> tasks;

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView shortDescrView;
        public TextView _timeView;

        public ImageView iconView;

        public ViewHolder(View view)
        {
            super(view);

            shortDescrView = (TextView) view.findViewById(R.id.short_description_text);
            _timeView = view.findViewById(R.id.time_text);

            iconView = (ImageView) view.findViewById(R.id.iconView);
        }
    }

    public TaskRecyclerAdapter(ArrayList<Task> tasksToShow)
    {        tasks = tasksToShow;
    }

    @Override
    public TaskRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Task task = tasks.get(position);

        holder.shortDescrView.setText(task.ShortDescription);
        if(!task.NotificationShown)
        {
            holder.iconView.setImageResource(R.drawable.ic_action_name);
            holder.iconView.setColorFilter(task.GetTaskPriorityColor());
        }
        else
        {
            holder.iconView.setImageResource(R.mipmap.ic_task_done);
        }

        holder._timeView.setText(task.GetTime());


    }

    @Override
    public int getItemCount()
    {
        return tasks.size();
    }
}

