package com.example.adopt;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText email, password;
    TextView cadastrar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Usuario userInfo = new Usuario();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(MainActivity.this, paginaprincipal.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        loginButton = (Button)findViewById(R.id.loginButton);
        email = (EditText)findViewById(R.id.txtName);
        password = (EditText)findViewById(R.id.password);
        cadastrar = (TextView) findViewById(R.id.cadastrar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUsuario = email.getText().toString();
                String senha = password.getText().toString();
                if(!emailUsuario.equals("") && !senha.equals("")) {
                    mAuth.signInWithEmailAndPassword(emailUsuario, senha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    //showData(dataSnapshot);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            //Boolean isAnimal = ref.child("Users").child("Uid").child(mAuth.getUid()).child("animal");
                            //Boolean isAnimal = userInfo.getAnimal();
                            //Toast.makeText(MainActivity.this, isAnimal.toString(), Toast.LENGTH_SHORT).show();
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Email ou senha estão errados!", Toast.LENGTH_SHORT).show();
                            }//else if( isAnimal == true){ //se for animal, transfere para tela da leticia
                                //Toast.makeText(MainActivity.this, "pagina animal", Toast.LENGTH_SHORT).show();
                            //}
                            else {//if( isAnimal == false){
                                openPaginaPrincipal();
                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "Digite email e senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistracao();
            }
        });
    }

    public void openPaginaPrincipal(){
        Intent intent = new Intent(this, paginaprincipal.class);
        startActivity(intent);
    }

    public void openRegistracao(){
        Intent intent = new Intent(this, registracao.class);
        startActivity(intent);
    }


    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            userInfo.setNome(ds.child("Uid").child(mAuth.getUid()).getValue(Usuario.class).getNome());
            userInfo.setEmail(ds.child("Uid").child(mAuth.getUid()).getValue(Usuario.class).getEmail());
            userInfo.setAnimal(ds.child("Uid").child(mAuth.getUid()).getValue(Usuario.class).getAnimal());
            Boolean isAnimal = userInfo.getAnimal();
        }
    }
}
