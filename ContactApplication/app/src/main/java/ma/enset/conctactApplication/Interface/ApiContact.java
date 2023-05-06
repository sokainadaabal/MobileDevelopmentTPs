package ma.enset.conctactApplication.Interface;

import java.util.List;

import ma.enset.conctactApplication.model.ContactModel;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiContact {
    // Chacun Call créé peut faire une requête HTTP synchrone ou asynchrone au serveur web distant crée par php et liée a ma base de données Mysql.

    // function qui permet de retourner la liste des contacts
    @GET("getAllContacts.php")
    Call<List<ContactModel>> getAllContacts();
    // function qui permet de supprimer un contact
    @DELETE("deleteContact.php")
    Call<Void> deleteContact(@Query("id") int id);

    // function qui permet d'ajouter un nouveau contact
    @FormUrlEncoded
    @POST("addContact.php")
    Call<ContactModel> addContact(@Field("first_name") String first_name, @Field("last_name") String last_name,@Field("job") String job, @Field("email") String email, @Field("phone") String phone, @Field("photo") String photo);

    // function qui permet de modifier un contact
    @FormUrlEncoded
    @POST("updateContact.php")
    Call<ContactModel> updateContact(@Field("id") int id,@Field("first_name") String first_name, @Field("last_name") String last_name,@Field("job") String job, @Field("email") String email, @Field("phone") String phone);

}
