package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        //Layout Variables
        Button grp1Button = (Button) findViewById(R.id.grp1Button);
        Button grp2Button = (Button) findViewById(R.id.grp2Button);

        //Event Triggers
        grp1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.blankFrameLayout, Group1Fragment.class, null)
                        .setReorderingAllowed(true)
                        .disallowAddToBackStack()
                        .commit();
            }
        });

        grp2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.blankFrameLayout, Group2Fragment.class, null)
                        .setReorderingAllowed(true)
                        .disallowAddToBackStack()
                        .commit();
            }
        });
    }
}