package com.example.adopt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class paginaprincipal extends AppCompatActivity {

    Button logoutButton;
    TextView userName;
    public ArrayList<String> al;
    private ArrayAdapter arrayAdapter;
    private int i;
    private FirebaseAuth mAuth;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_principal);
        logoutButton = (Button)findViewById(R.id.logoutButton);
        userName = (TextView) findViewById(R.id.userName);

        al = new ArrayList<>();
        al.add("Cleita");//nome dos cards
        al.add("Murila");
        al.add("Leticia");
        al.add("Juju Blackhole");
        al.add("Karla");
        al.add("Marina");
        al.add("Felipe Neto");
        al.add("Uinderson");
        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );


        mAuth = FirebaseAuth.getInstance();

        //String name = ref.child("Users").child("Uid").child(mAuth.getUid()).child("nome").toString(); // salva o caminho no nome: https://adopt-680ed.firebaseio.com/Users/Uid/GAB4uCTgdTPy2FlPRmsWabdBhDG2/nome
        //mas nao salva o proprio nome
        //userName.setText(ref.child("Users").child("Uid").child(mAuth.getUid()).child("nome").toString());
        userName.setText(mAuth.getCurrentUser().getEmail());
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                openLogin();
            }
        });

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(paginaprincipal.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(paginaprincipal.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("Jooj ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(paginaprincipal.this, "Click!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
