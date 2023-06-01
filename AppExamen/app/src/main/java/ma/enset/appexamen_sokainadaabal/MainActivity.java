package ma.enset.appexamen_sokainadaabal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import ma.enset.appexamen.R;
import ma.enset.appexamen_sokainadaabal.BD.MainData;
import ma.enset.appexamen_sokainadaabal.BD.RoomDB;
import ma.enset.appexamen_sokainadaabal.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity {

   // Initialize variable

    TextInputLayout editText,editText1,editText2;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable

        editText=findViewById(R.id.edit_text);
        editText1=findViewById(R.id.edit_text1);
        editText2=findViewById(R.id.edit_text2);
        btAdd=findViewById(R.id.bt_add);
        btReset=findViewById(R.id.bt_reset);
        recyclerView=findViewById(R.id.recycler_view);

        // initialize database
        database= RoomDB.getInstance(this);

        // store database value in data list

        dataList=database.mainDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager=new LinearLayoutManager(this);

        // Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter=new MainAdapter(MainActivity.this,dataList);

        // set adapter

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get string from edit text
                String sText=editText.getEditText().getText().toString().trim();
                String sText1=editText1.getEditText().getText().toString().trim();
                String sText2=editText2.getEditText().getText().toString().trim();
                // check condition
                if(!sText.equals("") && !sText1.equals("") && !sText2.equals(""))
                {
                    // when text is not empty
                    // initialize main data

                    MainData data=new MainData();

                    //Set text on main data
                    data.setText(sText);
                    data.setNb(sText1);
                    data.setCapitale(sText2);

                    //Insert text in database
                    database.mainDao().insert(data);
                    Toast.makeText(MainActivity.this, data.getText()+ "  est ajouté sur la liste.", Toast.LENGTH_SHORT).show();
                    //Clear edit text
                    editText.getEditText().setText("");
                    editText1.getEditText().setText("");
                    editText2.getEditText().setText("");
                    //Notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();

                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editText.getEditText().setText("");
                editText1.getEditText().setText("");
                editText2.getEditText().setText("");
                //Notify when data is inserted
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}