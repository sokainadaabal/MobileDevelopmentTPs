package ma.enset.manipulationdesfichiers.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import ma.enset.manipulationdesfichiers.Model.ContactModel;
import ma.enset.manipulationdesfichiers.R;


public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<ContactModel> arrayList;

    public CustomAdapter(Context context, ArrayList<ContactModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_list,viewGroup,false);
        }
        TextView name,job,email,phone;
        ContactModel contact = arrayList.get(i);
        name=(TextView) view.findViewById(R.id.name);
        job=(TextView) view.findViewById(R.id.job);
        email=(TextView) view.findViewById(R.id.email);
        phone=(TextView) view.findViewById(R.id.phone);
        Button call = (Button) view.findViewById(R.id.call);
        name.setText(arrayList.get(i).getFirst_name() +" "+ arrayList.get(i).getLast_name() );
        job.setText(arrayList.get(i).getJob());
        email.setText(arrayList.get(i).getEmail());
        phone.setText(arrayList.get(i).getPhone());
        call.setTag(contact);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactModel contact = (ContactModel) view.getTag();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
                context.startActivity(intent);
            }
        });
        CircleImageView photo = view.findViewById(R.id.photo);
        if (contact.getPhoto() != null)
            Picasso.get().load("file:///android_asset/" + contact.getPhoto()).into(photo);
        return view;
    }
}
