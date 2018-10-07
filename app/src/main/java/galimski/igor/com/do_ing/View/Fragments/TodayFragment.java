package galimski.igor.com.do_ing.View.Fragments;

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
import java.util.Calendar;
import java.util.Date;

import galimski.igor.com.do_ing.Managers.TaskManager;
import galimski.igor.com.do_ing.R;
import galimski.igor.com.do_ing.View.SwipeController;
import galimski.igor.com.do_ing.View.SwipeControllerActions;
import galimski.igor.com.do_ing.View.TaskRecyclerAdapter;

public class TodayFragment extends Fragment
{
    private View _view;

    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
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
        Date date = Calendar.getInstance().getTime();

        _recyclerView = (RecyclerView) _view.findViewById(R.id.taskRecyclerView);

        _layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        _recyclerView.setLayoutManager(_layoutManager);

        _adapter = new TaskRecyclerAdapter(TaskManager.GetTasks());
        _recyclerView.setAdapter(_adapter);

        final SwipeController swipeController = new SwipeController(new SwipeControllerActions()
        {
            @Override
            public void onLeftClicked(int position)
            {

            }

            @Override
            public void onRightClicked(int position)
            {
                TaskManager.DeleteTask(TaskManager.GetTasks().get(position));

                _adapter.notifyItemRemoved(position);
                _adapter.notifyItemRangeChanged(position, _adapter.getItemCount());
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(_recyclerView);

        _recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }
}
