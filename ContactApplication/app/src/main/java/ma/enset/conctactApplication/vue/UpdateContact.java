package ma.enset.conctactApplication.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ma.enset.conctactApplication.Interface.ApiContact;
import ma.enset.conctactApplication.model.ContactModel;
import ma.enset.contactApplication.R;
import retrofit2.Call;
import retrofit2.Response;

public class UpdateContact  extends AppCompatActivity {
    // Cette classe a pour but de modifier un contact à partir de remplir un formulaire et de le modifier le  dans les bases de données.
    private FloatingActionButton fab;
    private TextInputLayout first_name,last_name,job,phone,email;
    public static int contactId;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);
        fab = findViewById(R.id.fab);
        setAllViews();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateContact.this,MainActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Retour à la liste des contacts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Gson gson = new Gson();
        ContactModel contact = gson.fromJson(getIntent().getStringExtra("contactJson"), ContactModel.class);
        contactId = contact.getId();
        first_name.getEditText().setText(contact.getFirst_name());
        last_name.getEditText().setText(contact.getLast_name());
        phone.getEditText().setText(contact.getPhone());
        job.getEditText().setText(contact.getJob());
        email.getEditText().setText(contact.getEmail());
        if (contact.getPhoto() != null)
            Picasso.get().load("file:///android_asset/" + contact.getPhoto()).into(imageView,
                    new Callback() {
                        @Override
                        public void onSuccess() {
                        }
                        @Override
                        public void onError(Exception e) {
                            Log.e("Error "," opening image"+"file:///android_asset/" + contact.getPhoto());
                        }
                    });
    }

    private void setAllViews() {
        first_name = findViewById(R.id.first_name);
        last_name= findViewById(R.id.last_name);
        job = findViewById(R.id.job);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        imageView = findViewById(R.id.profil_pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.check:
                //get Updated person from edit text
                String first_nameTxt=first_name.getEditText().getText().toString().trim();
                String last_nameTxt=last_name.getEditText().getText().toString().trim();
                String jobTxt=job.getEditText().getText().toString().trim();
                String phoneTxt=phone.getEditText().getText().toString().trim();
                String emailTxt=email.getEditText().getText().toString().trim();
                //update person in DB
                Call<ContactModel> data = RetrofitContact.getRetrofitInstance().create(ApiContact.class).updateContact(contactId, first_nameTxt,last_nameTxt,jobTxt,emailTxt,phoneTxt);
                data.enqueue(new retrofit2.Callback<ContactModel>() {
                   @Override
                   public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {

                   }

                   @Override
                   public void onFailure(Call<ContactModel> call, Throwable t) {
                       Toast.makeText(UpdateContact.this, "Vous avez modifier un contact numéro "+ contactId, Toast.LENGTH_SHORT).show();                   }
               });
                Intent intent =new Intent(UpdateContact.this,MainActivity.class);
                startActivity(intent);
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
}
