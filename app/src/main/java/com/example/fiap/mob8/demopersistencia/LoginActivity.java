package com.example.fiap.mob8.demopersistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private final String PREFERENCE = "DemoPersistencia";
    private final String PREF_LOGIN = "login";
    private final String PREF_PASS = "senha";
    private final String PREF_MANTER_CONECTADO = "manterconectado";

    private EditText etLogin;
    private EditText etSenha;
    private CheckBox cbConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        cbConectado = (CheckBox) findViewById(R.id.cbConectado);

        SharedPreferences configLogin = getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);

        etLogin.setText(configLogin.getString(PREF_LOGIN, ""));
        etSenha.setText(configLogin.getString(PREF_PASS,  ""));

        if(configLogin.getBoolean(PREF_MANTER_CONECTADO, false)){
            if(isUsuarioValido(etLogin.getText().toString(), etSenha.getText().toString())){
                abrirTela();
            }
        }

    }

    public void conectar(View v) {
        if (isUsuarioValido(
                etLogin.getText().toString(),
                etSenha.getText().toString())) {
            if (cbConectado.isChecked()) {
                SharedPreferences configLogin = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = configLogin.edit();

                editor.putString(PREF_LOGIN, etLogin.getText().toString());
                editor.putString(PREF_PASS, etSenha.getText().toString());
                editor.putBoolean(PREF_MANTER_CONECTADO, cbConectado.isChecked());
                editor.commit();
            }
            abrirTela();
        } else {
            Toast.makeText(this, getString(R.string.msg_usuario_invalido), Toast.LENGTH_LONG).show();
        }
    }

    private void abrirTela() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private boolean isUsuarioValido(String login, String senha) {
        if (login.equals("fiap") && senha.equals("123")) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
