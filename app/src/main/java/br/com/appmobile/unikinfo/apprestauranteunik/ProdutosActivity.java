package br.com.appmobile.unikinfo.apprestauranteunik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.appmobile.unikinfo.adapter.AdapterProdutosPersonalizado;
import br.com.appmobile.unikinfo.entidade.Produtos;
import br.com.appmobile.unikinfo.rest.ProdutosRestClient;

public class ProdutosActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private String urlBase;
    private Integer idMesa;
    private Integer idMesaAberta;
    private ProdutosRestClient prRest;
    private Activity activ;
    private Object selected;
    private ListView lista;
    private EditText etPesquisa;
    private Intent intentCarrinho;
    private List<Produtos> pesquisa = new ArrayList<Produtos>();
    private List<Produtos> lstProduto;
    private File dir;
    private String descMesa;
    private String numMesa;
    private String statusMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lstProduto = new ArrayList<Produtos>();

        dir = this.getDir("imagens", Context.MODE_PRIVATE);
        if (!dir.exists())
            dir.mkdirs();

        Bundle extras = getIntent().getExtras();
        idMesa           = extras.getInt("idmesa");
        idMesaAberta     = extras.getInt("idmesaaberta");
        urlBase          = extras.getString("urlbase");
        numMesa          = extras.getString("nummesa");
        descMesa         = extras.getString("descmesa");
        statusMesa       = extras.getString("status");

        prRest= new ProdutosRestClient(this);
        prRest.setCaminhoRest(urlBase);
        prRest.setProdutoLst(lstProduto);

        setContentView(R.layout.activity_produtos);
        activ = this;
        etPesquisa = (EditText) findViewById(R.id.edtPesquisa);
        lista = (ListView) findViewById(R.id.listaProdutos);
        lista.setOnItemClickListener(this); // Clique no item
        lista.setOnItemLongClickListener(this); // Pressão sobre o item

//        AdapterProdutosPersonalizado adapter = new AdapterProdutosPersonalizado(lstProduto, this);
//        adapter.setCaminhoImagens("");
//        lista.setAdapter(adapter);

        prRest.setListViewProdutos(lista);
        prRest.setCaminhoImagens(dir.getPath());
        prRest.consultar();


        Pesquisar();

        etPesquisa.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                Pesquisar();

                //lista.setAdapter(new ArrayAdapter<Produto>(activ, android.R.layout.simple_list_item_1, pesquisa));
//                AdapterProdutosPersonalizado adapter = new AdapterProdutosPersonalizado(pesquisa, this);
//                adapter.setCaminhoImagens(dir.getAbsolutePath());
//                lista.setAdapter(adapter);

            }
        });

    }

    public void Pesquisar() {
        int textlength = etPesquisa.getText().length();
        pesquisa.clear();

        for (int i = 0; i < lstProduto.size(); i++ ) {
            if (textlength <= lstProduto.get(i).getDescricao().length()) {
                if (etPesquisa.getText().toString().equalsIgnoreCase((String)lstProduto.get(i).getDescricao().subSequence(0, textlength))) {
                    pesquisa.add(lstProduto.get(i));
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Produtos pr;
        if(pesquisa.size() > 0) {
            pr = pesquisa.get(position);
        } else {
            pr = prRest.getProdutoLst().get(position);
        }
        System.out.println("Produtos - idmesaaberta: "+idMesaAberta);

        //if(!pr.getTpProduto().equals("CARDAPIOGRUPO")) {
            Intent intent = new Intent(activ, ProdutoSelecionadoActivity.class);
            intent.putExtra("idmesa", idMesa);
            intent.putExtra("idmesaaberta", idMesaAberta);
            intent.putExtra("nummesa", numMesa);
            intent.putExtra("descmesa", descMesa);
            intent.putExtra("urlbase", urlBase);

            intent.putExtra("idproduto", pr.getId());
            intent.putExtra("tituloproduto", pr.getDescricao());
            intent.putExtra("descproduto", pr.getDescricaoDetalhe());
            intent.putExtra("precoproduto", pr.getPrecoVenda());

            ImageView img = view.findViewById(R.id.imagem);
            if(img.getDrawable() != null) {
                //Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                //intent.putExtra("imgproduto", bitmap);
            } else {
                img.setImageResource(R.mipmap.ic_logoapp);
                //Bitmap bmpImg = ((BitmapDrawable) img.getDrawable()).getBitmap();
                //intent.putExtra("imgproduto", bmpImg);
            }
            System.out.println("idmesa: "+idMesa);
            System.out.println("idmesaaberta: "+idMesaAberta);
            startActivity(intent);
        //}

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