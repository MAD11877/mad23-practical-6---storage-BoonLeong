package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        /*
        // Check if the user is authenticated
        if (!isUserAuthenticated()) {
            // User is not authenticated, redirect to LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optional: finish the LauncherActivity to prevent going back to it when pressing the back button from the LoginActivity
            return;
        }

        // User is authenticated, continue with the normal flow
         */

        //Layout Components
        RecyclerView userListRV = findViewById(R.id.userList_RV);

        //Test User Data Initialization
        UserDBHandler userDBHandler = new UserDBHandler(this, null, null, 1);
        if (userDBHandler.isDatabaseEmpty() == true) {
            ArrayList<User> testUserList = User.createRdTestUserList(20);
            for (User testUser : testUserList) {
                userDBHandler.addUser(testUser);
            }
        }

        ArrayList<User> testUserList = userDBHandler.getUsers();

        //RecyclerView Set-up
        UserListAdapter userListAdapter = new UserListAdapter(testUserList);
        LinearLayoutManager userListLayoutManager = new LinearLayoutManager(this);
        userListRV.setLayoutManager(userListLayoutManager);
        userListRV.setItemAnimator(new DefaultItemAnimator());
        userListRV.setAdapter(userListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);
        dbHandler.deleteAllUsers();
    }
}