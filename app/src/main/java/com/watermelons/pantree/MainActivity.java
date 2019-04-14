package com.watermelons.pantree;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView pantryView;
    private RecyclerView.Adapter pAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Food> groceryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        groceryList = new ArrayList<Food>();
        readGrocery();
        groceryList.add(new Food("Bread"));
        groceryList.add(new Food("Eggs"));
        groceryList.add(new Food("Almond Milk"));

        TextView count = this.findViewById(R.id.textView);
        count.setText("You have " + Integer.toString(groceryList.size()) + " items in your pantry");

        pantryView = (RecyclerView) findViewById(R.id.pantry_recycler);
        pantryView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        pantryView.setLayoutManager(layoutManager);

        pAdapter = new PantryAdapter(groceryList, getApplicationContext());
        pantryView.setAdapter(pAdapter);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void writeGrocery(){
        try {
            FileOutputStream fos = openFileOutput("GroceryList.txt", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Food f: groceryList){
                oos.writeObject(f);
            }
            oos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readGrocery(){
        File dir = this.getFilesDir();
        File toDo = new File(dir, "GroceryList.txt");
        try {
            FileInputStream fis = new FileInputStream(toDo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            groceryList = new ArrayList<Food>();
            try{
                while(true) {
                    Food f = (Food) ois.readObject();
                    groceryList.add(f);
                }
            } catch (EOFException e) { }
        } catch (IOException e) {
            e.printStackTrace();
            groceryList = new ArrayList<Food>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
