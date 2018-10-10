package galimski.igor.com.do_ing.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import galimski.igor.com.do_ing.Database.Data.TaskPriority;
import galimski.igor.com.do_ing.Managers.TaskLifecycleManager;
import galimski.igor.com.do_ing.View.Activites.MainActivity;
import galimski.igor.com.do_ing.Managers.TaskManager;
import galimski.igor.com.do_ing.R;

public class AddTaskFragment extends Fragment
{
    private Spinner _prioritySpinner;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_create_task, container, false);

        _prioritySpinner = view.findViewById(R.id.priority_spinner);
        _prioritySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, TaskPriority.values()));

        Button submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask();
            }
        });

        return view;
    }

    private void AddTask()
    {
        TextView shortDescriptionView = view.findViewById(R.id.short_description_text);

        if(TextUtils.isEmpty(shortDescriptionView.getText()))
        {
            shortDescriptionView.setError(getResources().getString(R.string.requare_field));

            return;
        }

        TextView fullDescriptionView = view.findViewById(R.id.full_description_text);

        DatePicker datePicker = view.findViewById(R.id.date);
        TimePicker timePicker = view.findViewById(R.id.time);

        CheckBox showNotificationCheckbox = view.findViewById(R.id.add_notification_checkbox);

        String shortDescription = shortDescriptionView.getText().toString();
        String fullDescription = fullDescriptionView.getText().toString();

        TaskPriority taskPriority = (TaskPriority) _prioritySpinner.getSelectedItem();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, datePicker.getYear() - 1900);
        cal.set(Calendar.MONTH, datePicker.getMonth() + 1);
        cal.set(Calendar.DATE, datePicker.getDayOfMonth());
        cal.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        cal.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date completionDate = cal.getTime();

        Boolean showNotification = showNotificationCheckbox.isSelected();

        TaskManager.AddTask(shortDescription, fullDescription, taskPriority, completionDate, showNotification);
        TaskLifecycleManager.OnTaskCreate();

        Toast toast = Toast.makeText(getContext(), R.string.task_added, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        MainActivity.GetInstance().UpdateFragment(MainActivity.GetInstance().TodayFragment);
    }
}
