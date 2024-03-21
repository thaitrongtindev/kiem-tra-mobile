package com.example.kiemtra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {

    private EditText edtQuantites;
    private EditText edtName;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSave = findViewById(R.id.btnSave);

        edtName = findViewById(R.id.edt_name);
        edtQuantites = findViewById(R.id.edt_Quantities);

            // Bây giờ bạn có thể sử dụng yourObject trong Activity này

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                int quantities = Integer.parseInt(edtQuantites.getText().toString().trim());
                ObjectModel newModel = new ObjectModel(quantities, name);
                Intent resultIntent = new Intent();

                resultIntent.putExtra("newModel", newModel);
                setResult(RESULT_OK, resultIntent);

               // startActivity(new Intent(AddItemActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}