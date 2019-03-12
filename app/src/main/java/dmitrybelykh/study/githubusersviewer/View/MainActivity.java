package dmitrybelykh.study.githubusersviewer.View;

import androidx.appcompat.app.AppCompatActivity;
import dmitrybelykh.study.githubusersviewer.R;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new UsersFragment(), UsersFragment.class.getName())
                    .commit();
        }
    }
}
