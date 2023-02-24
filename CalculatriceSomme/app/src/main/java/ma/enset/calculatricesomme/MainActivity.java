package ma.enset.calculatricesomme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editNum;
        final EditText editNum1;
        final TextView editResultat;
        Button btn_rest;
        Button btn_somme;

        editNum = (EditText) findViewById(R.id.editNum);
        editNum1 = (EditText) findViewById(R.id.editNum1);
        editResultat = (TextView) findViewById(R.id.editResultat);
        btn_rest = (Button) findViewById(R.id.btn_rest);
        btn_somme = (Button) findViewById(R.id.btn_somme);

        btn_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editNum.setText("");
                editNum1.setText("");
                editResultat.setText("");
            }
        });

        btn_somme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a, b, c;
                a = Float.parseFloat(editNum.getText().toString());
                b = Float.parseFloat(editNum1.getText().toString());
                c = a + b;
                editResultat.setText(String.valueOf(c));
            }
        });
    }
}