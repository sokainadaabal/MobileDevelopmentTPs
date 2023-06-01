package ma.enset.appexamen_sokainadaabal.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.enset.appexamen.R;
import ma.enset.appexamen_sokainadaabal.BD.MainData;
import ma.enset.appexamen_sokainadaabal.BD.RoomDB;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //Create constructor
    public MainAdapter(Activity context, List<MainData> dataList)
    {
        this.context=context;
        this.dataList=dataList;
        notifyDataSetChanged();
    }
    {

    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Initialize view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // Initialize main data
        MainData data=dataList.get(position);

        // Initialize database
        database=RoomDB.getInstance(context);

        // Set text on text view
        holder.textView.setText(data.getText());
        holder.textView1.setText(data.getNb());
        holder.textView2.setText(data.getCapitale());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize main data
                MainData d=dataList.get(holder.getAdapterPosition());

                // Get id
                final int sID=d.getID();

                // Get text
                String sText=d.getText();
                String sText1=d.getNb();
                String sText2=d.getCapitale();

                // create dialog
                final Dialog dialog=new Dialog(context);

                // set content view
                dialog.setContentView(R.layout.dialog_update);

                //Initialize width
                int width= WindowManager.LayoutParams.MATCH_PARENT;

                //Initialize height
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                //Set layout
                dialog.getWindow().setLayout(width,height);

                //show dialog
                dialog.show();

                //Initialize and assign variable
                final EditText editText=dialog.findViewById(R.id.edit_text);
                final EditText editText1=dialog.findViewById(R.id.edit_text1);
                final EditText editText2=dialog.findViewById(R.id.edit_text2);
                Button btUpdate=dialog.findViewById(R.id.bt_update);

                // Set text on edit text
                editText.setText(sText);
                editText1.setText(sText1);
                editText2.setText(sText2);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss Dialog
                        dialog.dismiss();

                        //Get Updated text from edit text
                        String uText=editText.getText().toString().trim();
                        String uText1=editText1.getText().toString().trim();
                        String uText2=editText2.getText().toString().trim();
                        // Update text in database
                        database.mainDao().update(sID, uText,uText1,uText2);
                        Toast.makeText(context, "Les données de "+uText+ "  est changé sur la liste.", Toast.LENGTH_SHORT).show();
                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize main data
                MainData d=dataList.get(holder.getAdapterPosition());
                // Get id
                final int sID=d.getID();

                AlertDialog.Builder builder =  new AlertDialog.Builder(context);
                builder.setTitle("Suppression");
                builder.setMessage("Vous êtes sûr que vous voulez le supprimer ?");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.ic_delete);

                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        database.mainDao().delete(sID);

                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                        Toast.makeText(context, "Un pays est retirée de la liste.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.create().show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView textView,textView1,textView2;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable
            textView=itemView.findViewById(R.id.text_view);
            textView1=itemView.findViewById(R.id.text_view1);
            textView2=itemView.findViewById(R.id.text_view2);
            btEdit=itemView.findViewById(R.id.bt_edit);
            btDelete=itemView.findViewById(R.id.bt_delete);
        }
    }
}

