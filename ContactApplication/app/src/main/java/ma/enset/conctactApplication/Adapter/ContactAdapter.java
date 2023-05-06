package ma.enset.conctactApplication.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
import ma.enset.conctactApplication.model.ContactModel;
import ma.enset.contactApplication.R;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements Filterable {
    // initialiser les variables
    private Activity context;
    private  List<ContactModel> contactsListAll;
    private List<ContactModel> contacts;
    private ItemClickListener itemClickListener;

    // Création  de constructor
    public ContactAdapter(Activity context, List<ContactModel> contacts, ItemClickListener itemClickListener) {
        this.context = context;
        this.contacts= contacts;
        this.contactsListAll = new ArrayList<>(contacts);
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }
    public void updateDataList(List<ContactModel> dataList) {
        contacts = dataList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // initialiser View
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contact = contacts.get(position);
        holder.name.setText(contact.getFirst_name() +" "+ contact.getLast_name() );
        holder.job.setText(contact.getJob());
        holder.email.setText(contact.getEmail());
        holder.phone.setText(contact.getPhone());
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(contacts.get(position));
        } );
        holder.itemView.setOnLongClickListener(view -> {
            itemClickListener.onItemLongClick(contacts.get(position));
            return true;
        } );
        holder.call.setTag(contact);
        holder.msg.setTag(contact);
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactModel contact = (ContactModel) view.getTag();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
                context.startActivity(intent);
            }
        });
        holder.msg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contact.getPhone(), null)));
            }
        });
        CircleImageView photo = holder.photo;
        if (contact.getPhoto() != null)
            Picasso.get().load("file:///android_asset/" + contact.getPhoto()).into(photo);

    }

    @Override
    public Filter getFilter() {
        return filter ;
    }
    Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ContactModel> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(contactsListAll);
            }
            else {
                for (ContactModel c :contactsListAll){
                    if(c.getFirst_name().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(c);
                    }
                }
            }
            FilterResults filterResults= new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            contacts.clear();
            contacts.addAll((Collection<? extends ContactModel>) filterResults.values);
            notifyDataSetChanged();
        }
    };




    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface ItemClickListener{
        public void onItemClick(ContactModel contact);
        public void onItemLongClick(ContactModel contact);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,job,email,phone;
        Button call, msg;
        CircleImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            job=itemView.findViewById(R.id.job);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            call = itemView.findViewById(R.id.call);
            msg = itemView.findViewById(R.id.msg);
            photo = itemView.findViewById(R.id.photo);

        }
    }
}
