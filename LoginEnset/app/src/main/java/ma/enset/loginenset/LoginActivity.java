package ma.enset.loginenset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.net.InetSocketAddress;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout username,password;
    private String TAG="LoginScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.e(TAG,"OnCreate vient  d'être appelée!");
        Toast.makeText(this,"OnCreate vient  d'être appelée!",Toast.LENGTH_SHORT).show();

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
    }
    public void envoyer(View view){
        Intent intent = new Intent(LoginActivity.this,Profile.class);
        String usernameString = this.username.getEditText().getText().toString();
        String passwordString= this.password.getEditText().getText().toString();
        if (passwordString.isEmpty() || usernameString.isEmpty()){
            Toast.makeText(this,getResources().getString(R.string.fill_in),Toast.LENGTH_LONG).show();
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("username",username.getEditText().getText().toString().trim());
            bundle.putString("password",password.getEditText().getText().toString().trim());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart vient d'être appelée !");
        Toast.makeText(this, "onStart vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart vient d'être appelée !");
        Toast.makeText(this, "onRestart vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume vient d'être appelée !");
        Toast.makeText(this, "onResume vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause vient d'être appelée !");
        Toast.makeText(this, "onPause vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop vient d'être appelée !");
        Toast.makeText(this, "onStop vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy vient d'être appelée !");
        Toast.makeText(this, "onDestroy vient d'être appelée !", Toast.LENGTH_SHORT).show();
    }
}