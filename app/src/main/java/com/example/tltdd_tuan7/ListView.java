package com.example.tltdd_tuan7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tltdd_tuan7.Adapter.Adapter;
import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.Items;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class ListView extends AppCompatActivity {
    private android.widget.ListView listView;
    ArrayList<Items> items = new ArrayList<>();
    ImageView imgthem ;
    Adapter adapter;
    Uri uriimgt,urii;
    Boolean kt=false;
    int j=0;
    Bitmap bitmap,bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        listView = (android.widget.ListView) findViewById(R.id.list_view);
//        Button btthem = (Button) findViewById(R.id.bt_them);
//        Button btca = (Button) findViewById(R.id.btchonanh);
//        imgthem = (ImageView) findViewById(R.id.imgthem);
//        EditText item_ten = (EditText) findViewById(R.id.edt_them);
//        EditText item_mota = (EditText) findViewById(R.id.edt_hint);
//        items.add(new Items("Quảng Trị","Quê",R.drawable.img,bm)) ;
//        items.add(new Items("Hà Nội","Thủ đô nước CHXHCN Việt Nam",R.drawable.img_1,bm)) ;
//        items.add(new Items("Đà Nẵng","Thành phố đáng sống",R.drawable.img_2,bm)) ;
//        items.add(new Items("TP Hồ Chí Minh","Thành phố trực thuộc trung ương",R.drawable.img_3,bm)) ;
//        item_mota.setText(String.valueOf(BTP.items.size()));
//        if (BTP.items.size()==0) {
//            BTP.items=items;
//        }
//
//        btthem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BTP.items.add(new Items(item_ten.getText().toString(),item_mota.getText().toString(),0,bitmap));
//                item_ten.setText(uriimgt.toString());
//                item_mota.setText(imgthem.getResources().toString());
//                adapter.notifyDataSetChanged();
//            }
//        });
//        btca.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CapQuyenCA();
//            }
//        }));
//
//        adapter = new Adapter(ListView.this,BTP.items);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ListView.this, Detail.class);
//                intent.putExtra("dulieu",items.get(i).getTen());
//                if (kt!=true)
//                    startActivity(intent);
//                kt=false;
//            }
//        });
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                kt=true;
//                Xacnhanxoa(i);
//                return false;
//            }
//        });
//    }
//
//    private void CapQuyenCA() {
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                OpenImagePicker();
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(ListView.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//            }
//        };
//        TedPermission.create()
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .check();
//
////        if (ContextCompat.checkSelfPermission(
////                getActivity(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
////            ActivityCompat.requestPermissions(getActivity(),new String[]{""+
////                    "android.permission.CAMERA"},1002);
////        }
//
//    }
//
//    private void OpenImagePicker() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Title"),1);
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1){
//            uriimgt=data.getData();
//            bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(ListView.this.getContentResolver(), uriimgt);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            imgthem.setImageBitmap(bitmap);
//        }
//    }
//
//
//    public void Xacnhanxoa(final int pos){
//        AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(ListView.this);
//        alertDiaLog.setTitle("Thông báo");
//        alertDiaLog.setIcon(R.mipmap.ic_launcher);
//        alertDiaLog.setMessage("Bạn có muốn xóa "+items.get(pos).getTen()+" ?"    );
//        alertDiaLog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                items.remove(pos);
//                adapter.notifyDataSetChanged();
//            }
//        });
//        alertDiaLog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        alertDiaLog.show();


    }
}