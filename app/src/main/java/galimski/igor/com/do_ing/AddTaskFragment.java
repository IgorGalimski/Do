package galimski.igor.com.do_ing;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.Date;

public class AddTaskFragment extends Fragment {

    private Spinner _prioritySpinner;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        Date completionDate = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getHour(), timePicker.getMinute());

        Boolean showNotification = showNotificationCheckbox.isSelected();

        TaskManager.GetInstance().AddTask(shortDescription, fullDescription, taskPriority, completionDate, showNotification);

        Toast toast = Toast.makeText(getContext(), R.string.task_added, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
