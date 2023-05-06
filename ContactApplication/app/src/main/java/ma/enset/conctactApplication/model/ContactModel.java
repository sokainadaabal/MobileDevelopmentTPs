package ma.enset.conctactApplication.model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ContactModel {
    // les attributs
    private int id;

    private String first_name;

    private String last_name;

    private String job;
    private String email;
    private String phone;

    private String photo;

    // les constructeurs
    public ContactModel() {
    }

    public ContactModel(int id, String first_name, String last_name, String job, String email, String phone,String photo) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.job = job;
        this.email = email;
        this.phone = phone;
        this.photo=photo;

    }

    // Getters et Setters
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Conversion de contact
     * @return
     */
    public JSONArray convertionToJsonArray()
    {
        List list= new ArrayList<>();
        list.add(id);
        list.add(first_name);
        list.add(last_name);
        list.add(job);
        list.add(email);
        list.add(phone);
        list.add(photo);
        return  new JSONArray(list);
    }
}
