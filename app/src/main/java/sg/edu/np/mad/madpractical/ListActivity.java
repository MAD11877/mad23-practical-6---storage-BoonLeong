package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    static ArrayList<User> testUserList;
    static UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Layout Variables
        RecyclerView userListRV = findViewById(R.id.userList_RV);

        //Initialization
        testUserList = User.createRdTestUserList(20);

        /*
        Gson gson = new Gson();
        String json;

        SharedPreferences userData = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = userData.edit();
        for (int i = 0; i < testUserList.size(); i++) {
            User user = testUserList.get(i);
            json = gson.toJson(user);
            editor.putString("userId" + user.getId(), json);
        }
        editor.apply();
         */

        userListAdapter = new UserListAdapter(testUserList);
        LinearLayoutManager userListLayoutManager = new LinearLayoutManager(this);
        userListRV.setLayoutManager(userListLayoutManager);
        userListRV.setItemAnimator(new DefaultItemAnimator());
        userListRV.setAdapter(userListAdapter);
    }

    /*
    protected void onResume() {
        super.onResume();
        // Retrieve the value from SharedPreferences
        SharedPreferences userData = getSharedPreferences("userData", MODE_PRIVATE);
        String objectValue = userData.getString("object_value", null);

        // Update the text based on the retrieved value
        if (objectValue != null) {
            textView.setText(objectValue);
        }
    }

     */

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            User user = data.getParcelableExtra("user");
            int index = testUserList.indexOf(user);
            if (index >= 0) {
                testUserList.set(index, user);
                userListAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}