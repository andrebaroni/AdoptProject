package com.example.adopt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CadastroPet extends AppCompatActivity {

    EditText txtIdade, txtNome, txtEndereco, txtRaca;
    Button seguinte;
    RadioGroup sexo;
    RadioButton porteP, porteM, porteG;
    ImageButton uploadButton;
    public static final int GET_FROM_GALLERY = 3;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_pet);

        txtIdade = (EditText) findViewById(R.id.txtIdade);
        txtNome = (EditText) findViewById(R.id.txtNomePet);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtRaca = (EditText) findViewById(R.id.txtRaca);
        seguinte = (Button) findViewById(R.id.cadastroPet);
        uploadButton = (ImageButton) findViewById(R.id.uploadButton);

        seguinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, registracao.class);
                //startActivity(intent);
            }
        });


        // Faz upload das imagens da galeria
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });

        /*@Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);


            //Detects request codes
            if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/


    };


}
