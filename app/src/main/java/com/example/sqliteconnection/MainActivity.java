package com.example.sqliteconnection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sqliteconnection.adapter.TemanAdapter;
import com.example.sqliteconnection.database.DBController;
import com.example.sqliteconnection.database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private RecyclerView recyclerView;
    private TemanAdapter adapter;
//    untuk menampung data teman
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nm,tlp;
    private FloatingActionButton fab;

//    menu
    Bundle bundle = new Bundle();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);

        bacaData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(intent);
            }
        });

//        set show menu
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pm = new PopupMenu(getApplicationContext(),view);
                pm.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) MainActivity.this);
                pm.inflate(R.menu.popup_menu);
                pm.show();
            }
        });

//        masuk ke pemampilan data
        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                Intent intent = new Intent(MainActivity.this,MenampilanData.class);
                startActivity(intent);

                return false;
            }
        });

    }

    public void bacaData(){
        ArrayList<HashMap<String,String>> daftarTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();
//        memindahkan dari hasil query kedalam teman
        for (int i = 0; i<daftarTeman.size();i++){
            Teman teman = new Teman();

            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setId(daftarTeman.get(i).get("nama").toString());
            teman.setId(daftarTeman.get(i).get("telpon").toString());

//          pindahkan dari teman ke dalam Arraylist teman di adapter
            temanArrayList.add(teman);

        }

    }

//    menu item click masuk ke edit data
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.mnedit:
                intent = new Intent(getApplicationContext(),EditData.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.mndelete:
                Toast.makeText(getApplicationContext(),"Ini untuk delete kontak",
                        Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }
}