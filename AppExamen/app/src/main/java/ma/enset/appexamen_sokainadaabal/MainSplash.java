package ma.enset.appexamen_sokainadaabal;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ma.enset.appexamen.R;

public class MainSplash extends AppCompatActivity {
    // variable of splashscreen
    private static int SPLASH_TIME_OUT=5000;

    // variables
    ImageView image;
    TextView logo,slogan;

    // variables for Animation
    Animation toAnim, bottomAnim;

    private String TAG="SplashScreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        setContentView(R.layout.splash_screen);
        Log.e(TAG,"OnCreate vient  d'être appelée!");
        Toast.makeText(this,"OnCreate vient  d'être appelée!",Toast.LENGTH_SHORT).show();
        //Animations
        toAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_annimation);
        // hooks
        image = findViewById(R.id.image);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        image.setAnimation(toAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(()->{
            //  This methode will  be executed one the timer is over
            // start your app  login activity
            Intent i = new Intent(MainSplash.this,MainActivity.class);
            //startActivity(i);
            // close this Activity you can use function  finish
            Pair[] pairs = new Pair[2];
            pairs[0]=new Pair<View,String>(image,"logo_image");
            pairs[1]=new Pair<View,String>(logo,"logo_text");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainSplash.this,pairs);
            startActivity(i,options.toBundle());
        }, SPLASH_TIME_OUT);
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
