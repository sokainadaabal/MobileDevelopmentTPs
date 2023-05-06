package ma.enset.conctactApplication.vue;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitContact {

    // Retrofit transforme votre API HTTP en une interface Java.
    private static Retrofit retrofit;
    private static String BASE_URL="http://192.168.1.13/contact/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
         retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return  retrofit;
    }

}
