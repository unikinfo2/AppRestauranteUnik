package br.com.appmobile.unikinfo.apprestauranteunik;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.appmobile.unikinfo.adapter.AdapterMesasPersonalizado;
import br.com.appmobile.unikinfo.adapter.AdapterProdutosPersonalizado;
import br.com.appmobile.unikinfo.entidade.Mesas;
import br.com.appmobile.unikinfo.rest.MesasAbertasRestClient;
import br.com.appmobile.unikinfo.rest.MesasRestClient;
import br.com.appmobile.unikinfo.rest.UsuariosRestClient;

public class ActivityMesas extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private Mesas mesas;

    private String urlBase;

    private EditText etPesquisa;
    private List<Mesas> pesquisaMesas = new ArrayList<Mesas>();
    private List<Mesas> lstMesas;
    private Activity activ;
    private File dir;
    private MesasRestClient mesasRest;
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);

        Bundle extras = getIntent().getExtras();
        urlBase   = extras.getString("urlbase");
        pesquisaMesas  = new ArrayList<Mesas>();
        lista = (ListView) findViewById(R.id.listaMesa);
        lista.setOnItemClickListener(this); // Clique no item
        lista.setOnItemLongClickListener(this); // Pressão sobre o item

        mesasRest = new MesasRestClient(this);
        mesasRest.setUrlBase(urlBase);
        mesasRest.setListViewMesas(lista);
        mesasRest.setMesasLst(pesquisaMesas);
//        AdapterMesasPersonalizado adapter = new AdapterMesasPersonalizado(pesquisaMesas, activ);
//        adapter.setCaminhoImagens("");
//        lista.setAdapter(adapter);


        //lista.setAdapter(new ArrayAdapter<Mesas>(this, android.R.layout.simple_list_item_1, mesasLst));
        //etPesquisa = (EditText) findViewById(R.id.edtPesquisaMesa);

        dir = this.getDir("imagens", Context.MODE_PRIVATE);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        mesasRest.consultar();

    }
    public void atualizar(View view) {
       //refazer a consulta das mesas
        pesquisaMesas.clear(); //  = new ArrayList<Mesas>();
        mesasRest = new MesasRestClient(this);
        mesasRest.setUrlBase(urlBase);
        mesasRest.setListViewMesas(lista);
        mesasRest.setMesasLst(pesquisaMesas);
        System.out.println("mesasLst: "+pesquisaMesas);
        AdapterMesasPersonalizado adapter = new AdapterMesasPersonalizado(pesquisaMesas, this);
        adapter.setCaminhoImagens("");
        lista.setAdapter(adapter);

        mesasRest.consultar();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
        Mesas ms;
        ms = pesquisaMesas.get(position);
        if(ms.getIdMesaAberta() == null){
            System.out.println("Mesa aberta nula : "+this);
            new AlertDialog.Builder(this)
                    .setTitle("Deseja abrir a mesa")
                    .setMessage("Esta mesa não esta aberta")
                    .setPositiveButton("Abrir Mesa", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.out.println("botão abrir mesa ! " + urlBase);
                            Mesas ms;
                            ms = pesquisaMesas.get(position);
                            MesasAbertasRestClient ma = new MesasAbertasRestClient(activ);
                            ma.setCaminhoRest(urlBase);
                            ma.setActiv(activ);
                            ma.setMesas(ms);
                            ma.inserir();
                            atualizar(view);
                        }
                    })
                    .setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.out.println("Voltar");
                            return;
                        }
                    })
                    .show();
            ms = pesquisaMesas.get(position);
            if(ms.getIdMesaAberta() == null){
                return;
            }
        }

        System.out.println("Clique mesa ....."+ms.getIdMesaAberta());

        Intent intent = new Intent(this, ProdutosActivity.class);
        intent.putExtra("idmesa", ms.getId());
        intent.putExtra("idmesaaberta", ms.getIdMesaAberta());
        intent.putExtra("descmesa", ms.getDescricao());
        intent.putExtra("nummesa", ms.getNumeroMesa());
        intent.putExtra("status", ms.getStatus());
        intent.putExtra("urlbase", urlBase);
        startActivity(intent);
        try{
        }catch(Exception e){
            System.out.println(">>>>else e = "+e);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
