package com.example.tanush.aloenainvoice;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewInvoiceActivity extends AppCompatActivity {

    private EditText title, description, priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invoice);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Invoice");
        title = findViewById(R.id.edit_text_title);
        description = findViewById(R.id.edit_text_description);
        priority = findViewById(R.id.priority);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_invoice, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String Title = title.getText().toString();
        String Description = description.getText().toString();
        int Priority = Integer.parseInt(priority.getText().toString());
        String p = priority.getText().toString();
        if (Title.trim().isEmpty() || p.trim().isEmpty()) {
            Toast.makeText(this, "Enter Company Name/ Invoice No.", Toast.LENGTH_SHORT).show();
            return;
        }
        CollectionReference invoiceRef = FirebaseFirestore.getInstance()
                .collection("Invoices");

        invoiceRef.add(new Invoice(Title, Description, Priority));
        Toast.makeText(this, "Invoice Added", Toast.LENGTH_SHORT).show();
        finish();


    }
}
