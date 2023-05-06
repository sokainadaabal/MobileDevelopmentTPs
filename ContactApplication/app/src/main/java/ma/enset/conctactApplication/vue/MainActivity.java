package ma.enset.conctactApplication.vue;




import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.List;

import ma.enset.conctactApplication.Adapter.ContactAdapter;
import ma.enset.conctactApplication.Interface.ApiContact;
import ma.enset.conctactApplication.model.ContactModel;
import ma.enset.contactApplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ContactAdapter customAdapter;


    List<ContactModel> contactModelsList;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listeView);
        fab=findViewById(R.id.fab);
        ApiContact methods = RetrofitContact.getRetrofitInstance().create(ApiContact.class);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{
                        Manifest.permission.CALL_PHONE
                },
                1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddContact.class);
                startActivity(intent);
                Snackbar.make(view, "Ajouter Contact", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // getAll data
        Call<List<ContactModel>> data = methods.getAllContacts();
        //initialize & set linear layout manager in recyclerview
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        data.enqueue(new Callback <List<ContactModel>> () {
            @Override
            public void onResponse(Call <List<ContactModel>> call, Response <List<ContactModel>> response) {
                contactModelsList= response.body();
                //Adaptateur
                customAdapter= new ContactAdapter(MainActivity.this,contactModelsList,new ContactAdapter.ItemClickListener(){
                    @Override
                    public void onItemClick(ContactModel contact) {
                        Intent intent = new Intent(MainActivity.this,UpdateContact.class);
                        Gson gson = new Gson();
                        String contactJson = gson.toJson(contact);
                        intent.putExtra("contactJson", contactJson);
                        startActivity(intent);
                    }
                    @Override
                    public void onItemLongClick(ContactModel contact) {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                .setIcon(R.drawable.ic_warn)
                                .setTitle("Etes-vous sur de supprimer ce contact!")
                                .setMessage("Le contact va être supprimer pour toujours.\nAucun cache n'est disponible.")
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Call<Void> delete = methods.deleteContact(contact.getId());
                                        delete.enqueue(new Callback<Void>() {;
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                contactModelsList.clear();
                                                Call<List<ContactModel>> contact = methods.getAllContacts();
                                                contact.enqueue(new Callback<List<ContactModel>>() {
                                                    @Override
                                                    public void onResponse(Call<List<ContactModel>> call, Response<List<ContactModel>> response) {
                                                        contactModelsList=response.body();
                                                        customAdapter.updateDataList(contactModelsList);
                                                        Toast.makeText(getApplicationContext(),"vous avez supprimer un contact",Toast.LENGTH_LONG).show();
                                                    }
                                                    @Override
                                                    public void onFailure(Call<List<ContactModel>> call, Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                            }
                                        });
                                        customAdapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getApplicationContext(),"Rien execute",Toast.LENGTH_LONG).show();
                                    }
                                })
                                .show();

                    }
                });
                recyclerView.setAdapter(customAdapter);

            }
            @Override
            public void onFailure(Call<List<ContactModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erreur au niveau de serveur, aucun donnée afficher", Toast.LENGTH_LONG).show();
            }
        });


    }




}