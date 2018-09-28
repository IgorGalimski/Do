package galimski.igor.com.do_ing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodayFragment extends Fragment {

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_today, container, false);

        //SetCurrentDate();
        ShowTasks(Calendar.getInstance().getTime());

        return view;
    }

    private void SetCurrentDate(){
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        TextView _todayTextView = (TextView)view.findViewById(R.id.todayDateText);
        _todayTextView.setText(timeStamp);
    }

    private void ShowTasks(Date date)
    {
        TableLayout tableLayout = view.findViewById(R.id.taskTableLayout);
        tableLayout.removeAllViews();

        for(Task task: TaskManager.getInstance().GetTasks())
        {
            TableRow tableRow = new TableRow(getContext());

            TextView taskNameTextView = new TextView(getContext());
            taskNameTextView.setText(task.GetShortDescription());

            tableRow.addView(taskNameTextView, 0);

            TextView createdDate = new TextView(getContext());
            createdDate.setText(task.GetCreatedDate().toString());

            tableRow.addView(createdDate, 1);

            ImageView priorityImage = new ImageView(getContext());
            priorityImage.setImageResource(R.drawable.ic_action_name);

            tableRow.addView(priorityImage, 2);

            tableLayout.addView(tableRow);
        }
    }
}
