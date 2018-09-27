package galimski.igor.com.do_ing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView _bottomNavigationView;
    FrameLayout _frameLayout;
    FragmentManager _fragmentManager;

    TodayFragment _todayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _todayFragment = new TodayFragment();

        _bottomNavigationView = findViewById(R.id.bottomNavigationView);
        _bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_today:

                                UpdateFragment(_todayFragment);

                                break;
                            case R.id.action_schedules:

                                //UpdateFragment(null);

                                break;
                            case R.id.action_feed:

                                //UpdateFragment(null);

                                break;
                        }
                        return true;
                    }
                });
    }

    private void UpdateFragment(Fragment fragment)
    {
        _fragmentManager = this.getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = _fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLyout, fragment);
        fragmentTransaction.commit();
    }
}
