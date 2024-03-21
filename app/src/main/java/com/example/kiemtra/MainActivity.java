    package com.example.kiemtra;

    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.Toast;

    import androidx.activity.EdgeToEdge;
    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.PopupMenu;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import java.io.Serializable;
    import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {


        private static final int ADD_ITEM_REQUEST = 1;
        private static final int UPDATE_ITEM_REQUEST =2 ;
        private ListAdapter adapter;
        private ListView listView;
        private ArrayList<ObjectModel> models;
        @Override
        protected void onStart() {
            super.onStart();



        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            models = new ArrayList<>();

            ObjectModel model = new ObjectModel(1, "Tin");
            ObjectModel model1 = new ObjectModel(2, "Lan");
            Log.e("model", model.name + " " + model.quantities );
           models.add(model);
            models.add(model1);

            Log.e("models", models.size() + "");
            adapter = new ListAdapter(models);
            listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Lấy view của item được click
                    Log.e("TAG", "onItemClick: 000" );

                   showPopup(view, position);


                }
            });

        }

        private void showPopup(View view, int position) {
            View itemView = view;

            // Hiển thị Popup Menu
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, itemView);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.action_edit) {
                        // Xử lý hành động sửa
                        Toast.makeText(MainActivity.this, "edt", Toast.LENGTH_LONG).show();
                        updateObjectModel(models.get(position));
                        return true;
                    } else if (item.getItemId() == R.id.action_delete) {
                        // Xử lý hành động xóa
                        Toast.makeText(MainActivity.this, "delte", Toast.LENGTH_LONG).show();
                        deleteObjectModels(models.get(position));
                        return true;
                    }
                    else {
                        Toast.makeText(MainActivity.this, "add", Toast.LENGTH_LONG).show();
                        addObjectModels(models);
                        return true;
                    }

                }
            });

            popupMenu.inflate(R.menu.menu_popup); // Inflate menu resource
            popupMenu.show();
        }

        private void addObjectModels(ArrayList<ObjectModel> objectModels) {
            Intent intent = new Intent(this, AddItemActivity.class);
            startActivityForResult(intent, ADD_ITEM_REQUEST);
        }

        private void deleteObjectModels(ObjectModel objectModel) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa mục này?");
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Xóa mục khỏi danh sách và cập nhật ListView
                    models.remove(objectModel);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Đã xóa mục", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        private void updateObjectModel(ObjectModel objectModel) {
            Log.e("objectModel", objectModel.name.toString() );

            Intent intent = new Intent(this, UpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("update", (Serializable) objectModel); // Đặt key là tên bạn muốn để truy cập object trong Activity đích
            intent.putExtras(bundle);

    // Khởi chạy Activity mới
            startActivityForResult(intent, UPDATE_ITEM_REQUEST);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK && data != null) {
                // Nhận dữ liệu mới từ Intent
                ObjectModel newModel = (ObjectModel) data.getSerializableExtra("newModel");
                Log.e("newModel", "onActivityResult: " + newModel.name);
                // Thêm dữ liệu mới vào danh sách models
                models.add(newModel);

                // Cập nhật ListView
                Log.e("models_ Result", models.size() + "");
                adapter.notifyDataSetChanged();
            } else if (requestCode == UPDATE_ITEM_REQUEST && resultCode == RESULT_OK && data != null) {
                ObjectModel updatedModel = (ObjectModel) data.getSerializableExtra("updatedModel");
                Log.e("updatedModel", updatedModel.name );
                if (updatedModel != null) {

                    Log.e("models 2", models.size() + "" );

                    for (int i = 0; i < models.size(); i++) {
                        if (models.get(i).quantities ==  updatedModel.quantities) { // Sử dụng phương thức getId() hoặc thuộc tính tương tự
                            models.set(i, updatedModel);
                            adapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }

        }}

