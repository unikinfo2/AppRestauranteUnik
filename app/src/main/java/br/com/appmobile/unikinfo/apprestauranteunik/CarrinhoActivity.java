package br.com.appmobile.unikinfo.apprestauranteunik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.appmobile.unikinfo.adapter.AdapterPedidosPersonalizado;
import br.com.appmobile.unikinfo.entidade.Mesas;
import br.com.appmobile.unikinfo.entidade.MesasItens;
import br.com.appmobile.unikinfo.entidade.Produtos;
import br.com.appmobile.unikinfo.rest.MesasItensRestClient;

public class CarrinhoActivity extends Activity {
    private Button btnConfirma;
    private ListView lvItens;
    private Mesas mesas;
    private Produtos produto;
    private Activity actCarrinho;
    private ArrayList<MesasItens> mesasItensLst;
    private String urlBase;
    private NumberFormat currency;
    private Double totalFinal;
    private Integer idMesa;
    private Integer idMesaAberta;
    private String descMesa;
    private String numMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        actCarrinho = this;

        btnConfirma     = (Button) findViewById(R.id.btnConfirma);
        lvItens         = (ListView) findViewById(R.id.listaItensPedido);
        TextView titulo = (TextView) findViewById(R.id.tvTitulo);

        currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        Bundle extras = getIntent().getExtras();
        idMesa                  = extras.getInt("idmesa");
        idMesaAberta            = extras.getInt("idmesaaberta");
        Integer idProduto       = extras.getInt("idproduto");
        int qtd                 = extras.getInt("qtditem");
        Double total            = extras.getDouble("totalitem");
        Double preco            = extras.getDouble("precoitem");
        urlBase                 = extras.getString("urlbase");
        String compl            = extras.getString("complemento");
        String tituloProduto    = extras.getString("tituloproduto");
        String descProduto      = extras.getString("descproduto");
        descMesa         = extras.getString("descmesa");
        numMesa          = extras.getString("nummesa");

        titulo.setText("Pedidos: "+descMesa+"/"+numMesa);
        mesasItensLst = new ArrayList<MesasItens>();

        MesasItens msIt = new MesasItens();
        msIt.setQuant(new Double(qtd));
        msIt.setPrecoUnit(preco);
        msIt.setPrecoTotal(total);
        msIt.setIdProduto(idProduto);
        msIt.setDescricao(tituloProduto);
        msIt.setDescricaoDetalhe(descProduto);
        msIt.setIdMesa(idMesaAberta);
        //trItemLst.add(msIt);

        MesasItensRestClient miRest = new MesasItensRestClient(this);
        miRest.setCaminhoRest(urlBase);
        miRest.setMesasItensLst(mesasItensLst);
        miRest.setMesasItens(msIt);
        miRest.setActiv(actCarrinho);
        miRest.setIdMesa(idMesaAberta);

//        ArrayAdapter adapter = new AdapterPedidosPersonalizado(actCarrinho, mesasItensLst);
//        lvItens.setAdapter(adapter);
        miRest.inserir();

        MesasItensRestClient miR = new MesasItensRestClient(actCarrinho);
        miR.setCaminhoRest(urlBase);
        miR.setMesasItensLst(mesasItensLst);
        miR.consultar(idMesaAberta);
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        setResult(1, intent);
        super.finish();
    }

    public void voltarListaProdutos(View view){
        Intent intent = new Intent(actCarrinho, ProdutosActivity.class);
        intent.putExtra("urlbase", urlBase);
        intent.putExtra("idmesa", idMesa);
        intent.putExtra("idmesaaberta", idMesaAberta);
        intent.putExtra("descmesa", descMesa);
        intent.putExtra("nummesa", numMesa);
        this.startActivity(intent);
    }

    public void selecionarOutraMesa(View view){
        Intent intent = new Intent(actCarrinho, ActivityMesas.class);
        intent.putExtra("urlbase", urlBase);
        intent.putExtra("idmesa", idMesa);
        intent.putExtra("idmesaaberta", idMesaAberta);
        this.startActivity(intent);
    }


    public String formatDate(Date pDt, String pFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pFormat);
        return dateFormat.format(pDt);
    }

    public String formatDate(Calendar pDt, String pFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pFormat);
        return dateFormat.format(pDt.getTime());
    }

    public Date strToDate(String pDt, String pFormat) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pFormat);
            return (Date) dateFormat.parse(pDt);
        } catch(ParseException e) {
            return null;
        }
    }

}