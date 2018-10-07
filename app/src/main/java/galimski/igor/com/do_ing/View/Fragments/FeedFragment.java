package galimski.igor.com.do_ing.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import galimski.igor.com.do_ing.Managers.TaskLifecycleManager;
import galimski.igor.com.do_ing.R;
import galimski.igor.com.do_ing.View.TaskLifecycleRecyclerAdapter;

public class FeedFragment extends Fragment
{
    private View _view;

    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        _view = inflater.inflate(R.layout.fragment_fragment_feed, container, false);

        return _view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        ShowFeed();
    }

    private void ShowFeed()
    {
        _recyclerView = (RecyclerView) _view.findViewById(R.id.feedRecyclerView);

        _layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        _recyclerView.setLayoutManager(_layoutManager);

        _adapter = new TaskLifecycleRecyclerAdapter(TaskLifecycleManager.GetTasksLifecycles());
        _recyclerView.setAdapter(_adapter);
    }
}
