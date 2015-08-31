package com.example.fiap.mob8.demopersistencia;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fiap.mob8.demopersistencia.adapter.PessoaAdapter;
import com.example.fiap.mob8.demopersistencia.dao.PessoaDAO;
import com.example.fiap.mob8.demopersistencia.model.Pessoa;

import java.util.List;

/**
 * Created by Eduardo Arena rm49684 on 26/08/2015.
 */

public class MainActivity extends ActionBarActivity {

    private EditText etNome;
    private EditText etIdade;

    private ListView lvPessoas;
    private PessoaDAO listaPessoas;
    private PessoaAdapter pessoaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = (EditText) findViewById(R.id.etNome);
        etIdade = (EditText) findViewById(R.id.etIdade);
        lvPessoas = (ListView) findViewById(R.id.lvPessoas);

        lvPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String name= lvPessoas.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
            }
        });
    }

    //Implementar List view para listar
    public void cancelar (View v){ }

    public void salvar (View v){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(etNome.getText().toString());
        pessoa.setIdade(Integer.parseInt(etIdade.getText().toString()));
        PessoaDAO dao = new PessoaDAO(this);
        dao.salvar(pessoa);
        Toast.makeText(this, "Gravado com sucesso", Toast.LENGTH_LONG).show();
    }

    public void listar(View v){
        listaPessoas = new PessoaDAO(this);
        if(listaPessoas.listAll().size() > 0){
            pessoaAdapter = new PessoaAdapter(this,listaPessoas.listAll());
            lvPessoas.setAdapter(pessoaAdapter);
        }else{
            Toast.makeText(this,"",Toast.LENGTH_LONG).show();
        }
    }

    public void selecionar(View v){


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
}