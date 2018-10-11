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
import java.util.GregorianCalendar;

import galimski.igor.com.do_ing.Database.Data.TaskPriority;
import galimski.igor.com.do_ing.Managers.TaskLifecycleManager;
import galimski.igor.com.do_ing.View.Activites.MainActivity;
import galimski.igor.com.do_ing.Managers.TaskManager;
import galimski.igor.com.do_ing.R;

public class AddTaskFragment extends Fragment
{
    private Spinner _prioritySpinner;

    private View _view;

    TextView _shortDescriptionView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        _view = inflater.inflate(R.layout.fragment_create_task, container, false);

        _prioritySpinner = _view.findViewById(R.id.priority_spinner);
        _prioritySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, TaskPriority.values()));

        _shortDescriptionView = _view.findViewById(R.id.short_description_text);

        Button submitButton = _view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask();
            }
        });

        return _view;
    }

    private void AddTask()
    {
        if(TextUtils.isEmpty(_shortDescriptionView.getText()))
        {
            _shortDescriptionView.setError(getResources().getString(R.string.requare_field));

            return;
        }

        TextView fullDescriptionView = _view.findViewById(R.id.full_description_text);

        DatePicker datePicker = _view.findViewById(R.id.date);
        TimePicker timePicker = _view.findViewById(R.id.time);

        CheckBox showNotificationCheckbox = _view.findViewById(R.id.add_notification_checkbox);

        String shortDescription = _shortDescriptionView.getText().toString();
        String fullDescription = fullDescriptionView.getText().toString();

        TaskPriority taskPriority = (TaskPriority) _prioritySpinner.getSelectedItem();

        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        Date completionDate = calendar.getTime();

        Boolean showNotification = showNotificationCheckbox.isChecked();

        TaskManager.AddTask(shortDescription, fullDescription, taskPriority, completionDate, showNotification);
        TaskLifecycleManager.OnTaskCreate();

        Toast toast = Toast.makeText(getContext(), R.string.task_added, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        MainActivity.GetInstance().UpdateFragment(MainActivity.GetInstance().TodayFragment);
    }
}
