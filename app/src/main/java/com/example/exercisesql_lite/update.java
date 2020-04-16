package com.example.exercisesql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class update extends AppCompatActivity {

    EditText nm, pn, email, almt;
    String mode="",idUser="0";
    Button btnSimpan, btnKembali;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();

                    return true;
            }
        }
        return false;

    }

    private static  String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nm =(EditText) findViewById(R.id.editTextNama);
        pn =(EditText) findViewById(R.id.editTextNoTelp);
        email =(EditText) findViewById(R.id.editTextEmail);
        almt =(EditText) findViewById(R.id.editTextAlamat);

        btnSimpan=(Button) findViewById(R.id.btnsimpan);
        btnKembali=(Button) findViewById(R.id.btnkembali);

        Intent in = this.getIntent();
        mode= in.getStringExtra("mode");

        if(mode.equals("Edit")){
            idUser =  in.getStringExtra("id_user");

            DBHelper db = new DBHelper(getApplicationContext());
            ModKontak modKontak = db.getUserById(idUser);

            nm.setText(modKontak.getNama());
            pn.setText(modKontak.getPhone());
            email.setText(modKontak.getEmail());
            almt.setText(modKontak.getAlamat());

        }
    }
    public View Simpan(View v){
        if(nm.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Data Harus Diisi Semua !.",Toast.LENGTH_SHORT).show();
        }else if(pn.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Data Harus Diisi Semua !.",Toast.LENGTH_SHORT).show();
        }else if(email.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Data Harus Diisi Semua !.",Toast.LENGTH_SHORT).show();
        }else if(almt.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "Data Harus Diisi Semua !.",Toast.LENGTH_SHORT).show();
        }else if(!email.getText().toString().matches(EMAIL_REGEX)){
            Toast.makeText(getApplicationContext(),
                    "Format Email Salah !.",Toast.LENGTH_SHORT).show();
        }else{
            ModKontak modKontak = new ModKontak();
            modKontak.setNama(nm.getText().toString());
            modKontak.setPhone(pn.getText().toString());
            modKontak.setEmail(email.getText().toString());
            modKontak.setAlamat(almt.getText().toString());

            DBHelper db = new DBHelper(getApplicationContext());


            if(mode.equals("Edit")){
                modKontak.setIdUser(idUser);
                int status = db.UpdateData(modKontak);
                if(status   >0){
                    Toast.makeText(update.this, "Data Berhasil Diubah ",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(update.this, "Data Gagal Diubah ",Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();


            }else{
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }

        }

        return v;
    }

    public View Kembali(View v){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
        return v;
    }
}
