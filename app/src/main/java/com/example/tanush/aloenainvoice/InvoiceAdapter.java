package com.example.tanush.aloenainvoice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class InvoiceAdapter extends FirestoreRecyclerAdapter<Invoice, InvoiceAdapter.InvoiceHolder> {

    public InvoiceAdapter(@NonNull FirestoreRecyclerOptions<Invoice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull InvoiceHolder holder, int position, @NonNull Invoice model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.priority.setText(String.valueOf(model.getPriority()));
    }

    @NonNull
    @Override
    public InvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_item, parent, false);
        return new InvoiceHolder(v);
    }

    public void deleteInvoice(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    class InvoiceHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView priority;

        public InvoiceHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}
