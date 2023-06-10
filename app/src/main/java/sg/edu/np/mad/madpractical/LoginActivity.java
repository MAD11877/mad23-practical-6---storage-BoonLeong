package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Layout Components
        Button loginButton = (Button) findViewById(R.id.loginButton);
        EditText usernameField = (EditText) findViewById(R.id.usernameField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);

        //Initialize Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://madpracticaluseraccounts-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference usersRef = database.getReference("Users");

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String enteredUsername = usernameField.getText().toString();
                String enteredPassword = passwordField.getText().toString();

                if (TextUtils.isEmpty(enteredUsername) || TextUtils.isEmpty(enteredPassword)){
                    Toast.makeText(LoginActivity.this, "Please complete all the relevant fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference userRef = usersRef.child(enteredUsername);

                userRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DataSnapshot dataSnapshot = task.getResult();

                        if (dataSnapshot.exists()) {
                            String passwordFromDB = dataSnapshot.child("password").getValue(String.class);

                            if (enteredPassword.equals(passwordFromDB)) {
                                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                Intent ListActivity = new Intent(LoginActivity.this, ListActivity.class);
                                startActivity(ListActivity);
                                finish();
                            }

                            else {
                                Toast.makeText(LoginActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        else {
                            Toast.makeText(LoginActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else {
                        Toast.makeText(LoginActivity.this, "Error occurred: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}