package com.example.adopt;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button)findViewById(R.id.loginButton);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = username.getText().toString();
                String senha = password.getText().toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                //apenas para teste:
                if(usuario.equals("andrebaroni") && senha.equals("12345")){
                    alert.setMessage("Login realizado");
                    alert.show();
                    //mudar para outra tela
                }else{
                    alert.setMessage("Usuário ou senha incorretos");
                    alert.show();
                }
            }
        });
    }
}
