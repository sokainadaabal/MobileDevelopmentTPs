package ma.enset.loginenset;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView username,password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        username = findViewById(R.id.usernameTextView);
        password = findViewById(R.id.passwordText);
        Bundle bundle = getIntent().getExtras();

        username.setText(bundle.getString("username"));
        password.setText(bundle.getString("password"));
    }

    public void retour(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}