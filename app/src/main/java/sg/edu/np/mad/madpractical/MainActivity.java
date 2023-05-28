package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout Variables
        Button followButton = (Button) findViewById(R.id.btnFollow);
        Button msgButton = (Button) findViewById(R.id.btnMessage);
        TextView pfPage_userName = (TextView) findViewById(R.id.pfPage_userName);
        TextView prfPage_userDesc = (TextView) findViewById(R.id.pfPage_userDesc);

        //Intent Variables
        Intent MessageGroup = new Intent(MainActivity.this, MessageGroup.class);
        Intent receivingEnd = getIntent();

        User currentUser = receivingEnd.getParcelableExtra("user");
        int currentUserTestUserListIndex = ListActivity.testUserList.indexOf(currentUser);

        //Initialization
        pfPage_userName.setText(currentUser.getName());
        prfPage_userDesc.setText(currentUser.getDescription());
        if (currentUser.isFollowed()) {
            followButton.setText(R.string.button_text_unfollow);
        }
        else {
            followButton.setText(R.string.button_text_follow);
        }

        followButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentUser.changeFollowStatus();

                Intent intent = new Intent();
                intent.putExtra("user", currentUser);
                setResult(RESULT_OK, intent);
                updateTestUserList(currentUserTestUserListIndex, currentUser);

                /*
                Gson gson = new Gson();
                String json = gson.toJson(currentUser);

                SharedPreferences userData = getSharedPreferences("userData", MODE_PRIVATE);
                SharedPreferences.Editor editor = userData.edit();
                editor.putString("userId" + currentUser.getId(), json);
                editor.apply();
                 */

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

        msgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(MessageGroup);
            }
        });
    }

    void updateTestUserList(int index, User user) {
        if (index >= 0) {
            ListActivity.testUserList.set(index, user);
            ListActivity.userListAdapter.notifyItemChanged(index);
        }
    }
}