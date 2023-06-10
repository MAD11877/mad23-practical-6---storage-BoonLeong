package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout Components
        Button followButton = (Button) findViewById(R.id.btnFollow);
        Button msgButton = (Button) findViewById(R.id.btnMessage);
        TextView pfPage_userName = (TextView) findViewById(R.id.pfPage_userName);
        TextView prfPage_userDesc = (TextView) findViewById(R.id.pfPage_userDesc);

        //Variables for Sending and Receiving Data across Activities
        Intent MessageGroup = new Intent(MainActivity.this, MessageGroup.class);
        Intent receivingEnd = getIntent();

        //Initializing User Data Received from ListActivity through UserListAdapter
        int currentUserID = receivingEnd.getIntExtra("userID", 0);
        UserDBHandler userDBHandler = new UserDBHandler(this, null, null, 1);
        User currentUser = userDBHandler.findUserByID(currentUserID);

        //Follow Button Mechanism Set-up
        followButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentUser.changeFollowStatus();
                userDBHandler.updateUser(currentUser);

                if (currentUser.isFollowed()) {
                    followButton.setText(R.string.button_text_unfollow);
                    Toast.makeText(getApplicationContext(), R.string.toast_followed, Toast.LENGTH_SHORT).show();
                }
                else {
                    followButton.setText(R.string.button_text_follow);
                    Toast.makeText(getApplicationContext(), R.string.toast_unfollowed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Message Button Mechanism Set-up
        msgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(MessageGroup);
            }
        });

        //Inserting User Data into Profile Page
        pfPage_userName.setText(currentUser.getName());
        prfPage_userDesc.setText(currentUser.getDescription());
        if (currentUser.isFollowed()) {
            followButton.setText(R.string.button_text_unfollow);
        }
        else {
            followButton.setText(R.string.button_text_follow);
        }
    }
}