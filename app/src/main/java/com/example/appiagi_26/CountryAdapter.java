package com.example.appiagi_26;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private ArrayList<String> countries;
    private Context context;

    public CountryAdapter(ArrayList<String> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        String country = countries.get(position);
        holder.textViewCountryName.setText(country);

        holder.buttonEdit.setOnClickListener(v -> showEditDialog(position));
        holder.buttonDelete.setOnClickListener(v -> showDeleteDialog(position));
        holder.itemView.setOnClickListener(v -> Toast.makeText(context, country, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    private void showEditDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Country");

        final EditText input = new EditText(context);
        input.setText(countries.get(position));
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                countries.set(position, newName);
                notifyItemChanged(position);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Country");
        builder.setMessage("Are you sure you want to delete " + countries.get(position) + "?");

        builder.setPositiveButton("Delete", (dialog, which) -> {
            countries.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, countries.size());
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCountryName;
        ImageButton buttonEdit, buttonDelete;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCountryName = itemView.findViewById(R.id.textViewCountryName);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
