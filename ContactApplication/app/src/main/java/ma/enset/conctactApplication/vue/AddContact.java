package ma.enset.conctactApplication.vue;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import ma.enset.conctactApplication.Interface.ApiContact;
import ma.enset.conctactApplication.model.ContactModel;
import ma.enset.contactApplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContact extends AppCompatActivity {

    // Cette classe a pour but d'ajouter un nouveau contact à partir de remplir un formulaire et de l'insérer dans les bases de données.
    private TextInputLayout first_name,last_name,job,phone,email;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        fab = findViewById(R.id.fab);
        setAllViews();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddContact.this,MainActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Retour à la liste des contacts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }
        private void setAllViews() {
            first_name = findViewById(R.id.first_name);
            last_name= findViewById(R.id.last_name);
            job = findViewById(R.id.job);
            phone = findViewById(R.id.phone);
            email = findViewById(R.id.email);
        }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.add_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save:
                if (first_name.getEditText().getText().toString().equals("") ||first_name.getEditText().getText().toString().equals("") || job.getEditText().getText().toString().equals("") ||
                        phone.getEditText().getText().toString().equals("") || email.getEditText().getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //Setting message manually and performing action on button click
                    builder.setMessage("Un ou plusieurs de vos chmaps est vide!!!\nVeuillez remplir tous les champs.")
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                    Toast.makeText(AddContact.this, "Vous avez quitter l'pération d'ajout d'un contqct", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"Continuer le remplissage des champs",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Attention!");
                    alert.setIcon(R.drawable.ic_warn);
                    alert.show();
                }else {
                    ContactModel contact=new ContactModel();
                    contact.setFirst_name(first_name.getEditText().getText().toString());
                    contact.setLast_name(last_name.getEditText().getText().toString());
                    contact.setJob(job.getEditText().getText().toString());
                    contact.setPhone(phone.getEditText().getText().toString());
                    contact.setEmail(email.getEditText().getText().toString());
                    int nbr = getRandomNumber(1,6);
                    contact.setPhoto("photo"+nbr+".jpg");

                    //insert person in db
                    Call<ContactModel> data = RetrofitContact.getRetrofitInstance().create(ApiContact.class).addContact(contact.getFirst_name(), contact.getLast_name(), contact.getJob(),contact.getEmail(), contact.getPhone(), contact.getPhoto());
                    data.enqueue(new Callback<ContactModel>() {
                        @Override
                        public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {
                            Toast.makeText(AddContact.this, "Vous avez ajouter un contact avec le nom "+contact.getFirst_name(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<ContactModel> call, Throwable t) {
                            Toast.makeText(AddContact.this, "échec au niveau de serveur, rien ajouter", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent intent =new Intent(AddContact.this,MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.refresh:
                refreshViews();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshViews() {
        first_name.getEditText().setText("");
        last_name.getEditText().setText("");
        job.getEditText().setText("");
        email.getEditText().setText("");
        phone.getEditText().setText("");
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
