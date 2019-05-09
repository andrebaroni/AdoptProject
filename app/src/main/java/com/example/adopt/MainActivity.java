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

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText email, password;
    TextView cadastrar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;



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
        email = (EditText)findViewById(R.id.email);
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
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Email ou senha estão errados!", Toast.LENGTH_SHORT).show();
                            } else {
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

        /**loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString();
                String senha = password.getText().toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                //apenas para teste:
                if(emailUser.equals("andrebaroni") && senha.equals("12345")){
                    alert.setMessage("Login realizado");
                    alert.show();
                    //mudar para outra tela
                    openPaginaPrincipal();
                    finish();
                    return;
                }else{
                    alert.setMessage("Usuário ou senha incorretos");
                    alert.show();
                }
            }
        });**/
    }

    public void openPaginaPrincipal(){
        Intent intent = new Intent(this, paginaprincipal.class);
        startActivity(intent);
    }

    public void openRegistracao(){
        Intent intent = new Intent(this, registracao.class);
        startActivity(intent);
    }
}
