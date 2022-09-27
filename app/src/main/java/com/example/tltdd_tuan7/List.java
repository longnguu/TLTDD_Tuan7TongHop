package com.example.tltdd_tuan7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tltdd_tuan7.Class.Adapter;
import com.example.tltdd_tuan7.Class.Items;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    private ListView listView;
    ArrayList<Items> items = new ArrayList<>();
    Adapter adapter;
    Boolean kt=false;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list_view);
        items.add(new Items("Item 1","Information of item 1", "6",R.drawable.item1)) ;
        items.add(new Items("Item 2","Information of item 2", "6",R.drawable.item2)) ;
        items.add(new Items("Item 3","Information of item 3", "6",R.drawable.img)) ;
        items.add(new Items("Item 4","Information of item 4", "6",R.drawable.img_1)) ;
        items.add(new Items("Item 5","Information of item 5", "6",R.drawable.img_2)) ;

        adapter = new Adapter(List.this,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List.this,Detail.class);
                intent.putExtra("dulieu",items.get(i).getTen());
                if (kt!=true)
                    startActivity(intent);
                kt=false;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                kt=true;
                Xacnhanxoa(i);
                return false;
            }
        });
    }
    public void Xacnhanxoa(final int pos){
        AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(List.this);
        alertDiaLog.setTitle("Thong bao");
        alertDiaLog.setIcon(R.mipmap.ic_launcher);
        alertDiaLog.setMessage("Ban co muon xoa");
        alertDiaLog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                items.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
        alertDiaLog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDiaLog.show();

    }
}