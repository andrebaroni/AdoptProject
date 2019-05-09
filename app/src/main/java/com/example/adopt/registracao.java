package com.example.adopt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registracao extends AppCompatActivity {

    Button registerButton;
    EditText email, password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_usuario);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                /**final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(registracao.this, paginaprincipal.class);
                    startActivity(intent);
                    finish();
                    return;
                }**/
            }
        };

        registerButton = (Button)findViewById(R.id.loginButton);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUsuario = email.getText().toString();
                String senha = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(emailUsuario, senha).addOnCompleteListener(registracao.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(registracao.this, "E-mail não existe!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(registracao.this, "E-mail cadastrado!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                AlertDialog.Builder alert = new AlertDialog.Builder(registracao.this);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }

    public void openPaginaPrincipal(){
        Intent intent = new Intent(this, paginaprincipal.class);
        startActivity(intent);
        finish();
        return;
    }
}
