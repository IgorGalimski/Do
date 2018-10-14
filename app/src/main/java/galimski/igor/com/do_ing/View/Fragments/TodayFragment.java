package galimski.igor.com.do_ing.View.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import galimski.igor.com.do_ing.Database.Data.Task;
import galimski.igor.com.do_ing.Database.Data.TaskLifecycle;
import galimski.igor.com.do_ing.Managers.TaskLifecycleManager;
import galimski.igor.com.do_ing.Managers.TaskManager;
import galimski.igor.com.do_ing.R;
import galimski.igor.com.do_ing.View.Activites.MainActivity;
import galimski.igor.com.do_ing.View.RecyclerTouchListener;
import galimski.igor.com.do_ing.View.SwipeController;
import galimski.igor.com.do_ing.View.SwipeControllerActions;
import galimski.igor.com.do_ing.View.TaskRecyclerAdapter;

public class TodayFragment extends Fragment
{
    private View _view;

    private RecyclerView _recyclerView;
    private TaskRecyclerAdapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        _view = inflater.inflate(R.layout.fragment_today, container, false);

        return _view;
    }

    @Override
    public void onStart() {

        super.onStart();

        SetCurrentDate();
        ShowTasks();
    }

    private void SetCurrentDate(){
        String timeStamp = GetFormattedDate(Calendar.getInstance().getTime(), false);

        TextView _todayTextView = (TextView)_view.findViewById(R.id.todayText);
        _todayTextView.setText(timeStamp);
    }

    private String GetFormattedDate(Date date, boolean withTime)
    {
        if(withTime)
        {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
        }

        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public void ShowTasks()
    {
        _recyclerView = (RecyclerView) _view.findViewById(R.id.taskRecyclerView);

        _layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        _recyclerView.setLayoutManager(_layoutManager);

        final ArrayList<Task> tasks = new ArrayList<>();
        for(Task task: TaskManager.GetTasks())
        {
            if(isToday(task.CompletionDate))
            {
                tasks.add(task);
            }
        }

        _adapter = new TaskRecyclerAdapter(tasks);
        _recyclerView.setAdapter(_adapter);

        final SwipeController swipeController = new SwipeController
                (new SwipeControllerActions()
        {
            @Override
            public void onLeftClicked(int position)
            {
            }

            @Override
            public void onRightClicked(int position)
            {
                Task task = _adapter.getTasks().get(position);
                TaskManager.DeleteTask(task);
                TaskLifecycleManager.OnTaskDelete();

                _adapter.getTasks().remove(task);

                _adapter.notifyItemRemoved(position);
                _adapter.notifyItemRangeChanged(position, tasks.size());
            }
        });

        _recyclerView.addOnItemTouchListener(new RecyclerTouchListener(_recyclerView.getContext(), _recyclerView, new RecyclerTouchListener.ClickControllerActions() {
            @Override
            public void onClick(int position)
            {
                //if(!swipeController.IsButtonShowing())
                {
                    ShowTaskDialog(_adapter.getTasks().get(position), position);
                }
            }

            @Override
            public void onLongClick(int position)
            {
            }
        }));
;
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(_recyclerView);

        _recyclerView.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state)
            {
                swipeController.onDraw(c);
            }
        });
    }

    private void ShowTaskDialog(final Task task, final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.ic_task_done);
        builder.setTitle(task.ShortDescription);
        builder.setMessage(task.FullDescription);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });

        if(!task.IsTimeCome())
        {
            String shownString = task.NotificationShown ? getResources().getString(R.string.mark_task_not_shown) : getResources().getString(R.string.mark_task_shown);
            builder.setNegativeButton(shownString, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id)
                {
                    if (!task.NotificationShown)
                    {
                        MainActivity.GetInstance().CancelNotificatiion(task.Id);
                    } else {
                        MainActivity.GetInstance().DelayNotification(MainActivity.GetInstance().CreateNotification(task), task.CompletionDate.getTime(), task.Id);
                    }

                    task.NotificationShown = !task.NotificationShown;

                    _adapter.notifyItemChanged(position);
                }
            });
        }

        builder.show();
    }

    public static boolean isToday(Date date)
    {
        Calendar today = Calendar.getInstance();
        Calendar specifiedDate  = Calendar.getInstance();
        specifiedDate.setTime(date);

        return today.get(Calendar.DAY_OF_MONTH) == specifiedDate.get(Calendar.DAY_OF_MONTH)
                &&  today.get(Calendar.MONTH) == specifiedDate.get(Calendar.MONTH)
                &&  today.get(Calendar.YEAR) == specifiedDate.get(Calendar.YEAR);
    }
}
