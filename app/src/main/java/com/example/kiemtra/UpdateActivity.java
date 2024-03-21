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

public class UpdateActivity extends AppCompatActivity {
    private EditText edtQuantites;
    private EditText edtName;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSave = findViewById(R.id.btnSave);

        edtName = findViewById(R.id.edt_name);
        edtQuantites = findViewById(R.id.edt_Quantities);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // Trích xuất object từ Bundle
        ObjectModel model = (ObjectModel) bundle.getSerializable("update"); // key phải giống như key đã được sử dụng để đóng gói object
        edtName.setText(model.name);
        edtQuantites.setText(model.quantities +"");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();

                // Cập nhật thông tin của objectModel
                model.setName(name);

                // Tạo Intent để trả về MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedModel", model);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}