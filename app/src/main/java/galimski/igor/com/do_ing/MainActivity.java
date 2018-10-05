package galimski.igor.com.do_ing;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import galimski.igor.com.do_ing.sampledata.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private static MainActivity Instance = null;

    public static MainActivity GetInstance()
    {
        return Instance;
    }

    private DatabaseHelper _datebaseHelper;

    private BottomNavigationView _bottomNavigationView;
    FragmentManager _fragmentManager;

    TodayFragment _todayFragment;
    AddTaskFragment _addTaskFragment;

    private FloatingActionButton _addButton;

    public DatabaseHelper GetDatabaseHepler()
    {
        return _datebaseHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Instance = this;

        ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.WAKE_LOCK}, 123 );

        _todayFragment = new TodayFragment();
        _addTaskFragment = new AddTaskFragment();

        _bottomNavigationView = findViewById(R.id.bottomNavigationView);
        _bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_today:

                                UpdateFragment(_todayFragment);

                                break;
                            case R.id.action_add:

                                UpdateFragment(_addTaskFragment);

                                break;
                            case R.id.action_feed:

                                //UpdateFragment(null);

                                break;
                        }
                        return true;
                    }
                });

        _datebaseHelper = new DatabaseHelper(this);
    }

    private void UpdateFragment(Fragment fragment)
    {
        _fragmentManager = this.getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = _fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLyout, fragment);
        fragmentTransaction.commit();
    }

    public Notification CreateNotification(Task task)
    {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(task.GetShortDescription())
                        .setContentText(task.GetFullDescription());

        Notification notification = builder.build();

        return notification;
    }

    //@RequiresApi(api = Build.VERSION_CODES.M)
    public void DelayNotification(Notification notification, long futureInMillis, int id){

        AtomicInteger requestCodeCounter = new AtomicInteger(0);

        Intent notificationIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, id);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCodeCounter.incrementAndGet(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }

    public static void ShowMessage(String message)
    {
        Toast.makeText(GetInstance(), message, Toast.LENGTH_LONG).show();
    }
}
