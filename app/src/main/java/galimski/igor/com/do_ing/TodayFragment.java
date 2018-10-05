package galimski.igor.com.do_ing;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

        SetCurrentDate();
        ShowTasks();

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        TaskManager.GetInstance().PutTestTasks();

        ShowTasks();
    }

    private void SetCurrentDate(){
        String timeStamp = GetFormattedDate(Calendar.getInstance().getTime(), false);

        TextView _todayTextView = (TextView)view.findViewById(R.id.todayText);
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

        TableLayout tableLayout = view.findViewById(R.id.taskTableLayout);
        tableLayout.removeAllViews();

        for(final Task task: TaskManager.GetInstance().GetTasks())
        {
            TableRow tableRow = new TableRow(getContext());

            TextView taskNameTextView = new TextView(getContext());
            taskNameTextView.setText(task.GetFullDescription());

            tableRow.addView(taskNameTextView, 0);

            ImageView priorityImage = new ImageView(getContext());
            priorityImage.setImageResource(R.drawable.ic_action_name);

            tableRow.addView(priorityImage, 1);

            tableLayout.addView(tableRow);

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setTitle(task.GetShortDescription());
                    builder.setMessage(task.GetFullDescription());

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

                    builder.setNegativeButton(getResources().getText(R.string.delete_notification), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            TaskManager.GetInstance().DeleteTask(task);

                            ShowTasks();

                        }
                    });

                    builder.show();
                }
            });
        }
    }
}
