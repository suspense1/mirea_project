package ru.mirea.ishutin.mireaproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InstitutionAdapter extends RecyclerView.Adapter<InstitutionAdapter.ViewHolder> {
    private ArrayList<Institution> institutions = new ArrayList<>();

    private Context context;

    public InstitutionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public InstitutionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.institution_item, parent, false);

        return new InstitutionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int pos = holder.getAdapterPosition();

        holder.topText.setText(institutions.get(pos).name);
        holder.bottomText.setText(institutions.get(pos).address);

        holder.bg.setOnClickListener(view -> {
            Intent intent = new Intent(context, MapActivity.class);
            intent.putExtra("latitude", institutions.get(pos).point.getLatitude());
            intent.putExtra("longitude", institutions.get(pos).point.getLongitude());

            intent.putExtra("institutionName", institutions.get(pos).name);
            intent.putExtra("institutionAddress", institutions.get(pos).address);
            intent.putExtra("institutionDesc", institutions.get(pos).description);

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return institutions.size();
    }

    public void setInstitutions(ArrayList<Institution> institutions) {
        this.institutions = institutions;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final CardView bg;
        private final TextView topText;
        private final TextView bottomText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bg = itemView.findViewById(R.id.parent);
            topText = itemView.findViewById(R.id.topText);
            bottomText = itemView.findViewById(R.id.bottomText);
        }
    }
}
