package br.com.appmobile.unikinfo.apprestauranteunik;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.com.appmobile.unikinfo.entidade.MesasItens;
import br.com.appmobile.unikinfo.entidade.Produtos;
//import br.com.appmobile.unikinfo.rest.ProdutoAdicionalRestClient;

public class ProdutoSelecionadoActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private Context context;
    private ImageView iView;
    private EditText tvQuantidade;
    private EditText tvTitulo;
    private EditText tvDesc;
    private EditText tvDesc2;
    private EditText tvPreco;
    private Long produtoSelect;
    private ImageView imagem;
    private NumberFormat currency;
    private Double total;
    private Double totalAdic;
    private int qtd;
    private Button btnAdicionar;
    private Intent carrinhoActivity;
    private ArrayList<MesasItens> mesasItensLst;
    private String urlBase;
    private ListView listViewProdutos;
    private ListView listaAdicionais;
    private Integer idMesa;
    private Integer idMesaAberta;
    private String descMesa;
    private String numMesa;
    private String statusMesa;
    private Integer idProduto;
    private String tituloProduto;
    private String descProduto;
    private Double precoProduto;

    //private ProdutoAdicionalRestClient restAdicional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_selecionado);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        iView        = (ImageView) findViewById(R.id.ivProduto);
        tvQuantidade = (EditText) findViewById(R.id.tvQuantidade);
        tvTitulo     = (EditText) findViewById(R.id.tvTitulo);
        tvDesc       = (EditText) findViewById(R.id.tvDesc);
        tvPreco      = (EditText) findViewById(R.id.tvPreco);
        imagem       = (ImageView) findViewById(R.id.ivProduto);
        btnAdicionar = (Button)findViewById(R.id.btnAdicionar);
        listaAdicionais = (ListView) findViewById(R.id.listaAdicionais);

        listaAdicionais.setOnItemClickListener(this); // Clique no item
        listaAdicionais.setOnItemLongClickListener(this);


        Bundle extras    = getIntent().getExtras();
        idMesa           = extras.getInt("idmesa");
        idMesaAberta     = extras.getInt("idmesaaberta");
        descMesa         = extras.getString("descmesa");
        numMesa          = extras.getString("nummesa");
        statusMesa       = extras.getString("status");
        urlBase          = extras.getString("urlbase");
        idProduto        = extras.getInt("idproduto");
        tituloProduto    = extras.getString("tituloproduto");
        descProduto      = extras.getString("descproduto");
        precoProduto     = extras.getDouble("precoproduto");

        //imagem.setImageBitmap(img);

        //imagem.setImageBitmap(produtoSelected.getImagem());
        tvTitulo.setText(tituloProduto);
        tvDesc.setText(descProduto);

        currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        tvPreco.setText(currency.format(precoProduto));
        total = precoProduto;
        totalAdic = 0.00;
        qtd = 1;
        btnAdicionar.setText("Adicionar          "+currency.format(precoProduto));


//        restAdicional = new ProdutosAdicionalRestClient(this);
//        restAdicional.setCaminhoRest(urlBase);
//        restAdicional.setIdProdutoSelected(produtoSelected.getIdProduto());
//        restAdicional.setListViewAdicionais(listaAdicionais);
//        restAdicional.consultar();

    }

    public void aumentaQuantidade(View view){
        qtd = Integer.parseInt(tvQuantidade.getText().toString());
        if( qtd < 100){
            qtd ++;
            tvQuantidade.setText(Integer.toString(qtd));
            total = (precoProduto + totalAdic) * qtd;
            btnAdicionar.setText("Adicionar          "+ currency.format(total));
        }
    }

    public void diminuiQuantidade(View view){
        qtd = Integer.parseInt(tvQuantidade.getText().toString());
        if(qtd > 1){
            qtd --;
            tvQuantidade.setText(Integer.toString(qtd));
            total = (precoProduto + totalAdic) * qtd;
            btnAdicionar.setText("Adicionar          "+ currency.format(total));
        }
    }

    public void somaAdicional(Double valorInc){
        qtd = Integer.parseInt(tvQuantidade.getText().toString());
        tvQuantidade.setText(Integer.toString(qtd));

        totalAdic = totalAdic + (qtd * valorInc);

        btnAdicionar.setText("Adicionar          "+ currency.format(total + totalAdic));
    }

    public void subtraiAdicional(Double valorDec){
        qtd = Integer.parseInt(tvQuantidade.getText().toString());
        tvQuantidade.setText(Integer.toString(qtd));

        totalAdic = totalAdic - (qtd * valorDec);
        btnAdicionar.setText("Adicionar          "+ currency.format(total + totalAdic));
    }


    public void pegaValorCheckbox(View view){

    }

    public void adicionarCarrinho(View view) throws IOException {
        carrinhoActivity = new Intent(this, CarrinhoActivity.class);
        carrinhoActivity.putExtra("idmesa", idMesa);
        carrinhoActivity.putExtra("idmesaaberta", idMesaAberta);
        carrinhoActivity.putExtra("idproduto", idProduto);
        carrinhoActivity.putExtra("qtditem", qtd);
        carrinhoActivity.putExtra("precoitem", precoProduto + totalAdic);
        carrinhoActivity.putExtra("urlbase", urlBase);
        carrinhoActivity.putExtra("descmesa", descMesa);
        carrinhoActivity.putExtra("nummesa", numMesa);
        carrinhoActivity.putExtra("tituloproduto", tituloProduto);
        carrinhoActivity.putExtra("descproduto", descProduto);

        //startActivity(carrinhoActivity);
        String compl = "";

        total = precoProduto * qtd;
        totalAdic = 0.00;
        for(int x = 0; x < listaAdicionais.getCount(); x++){
            View view1 = listaAdicionais.getChildAt(x);
            CheckBox chk = (CheckBox) view1.findViewById(R.id.checkBox);
            if(chk.isChecked()){
                Double preco = 0.00; //restAdicional.getProdutoAdicionalLst().get(x).getIdProdutoAdicionavel().getPrecoVenda().doubleValue();
                totalAdic = totalAdic + (preco * qtd);
                TextView tv = (TextView) view1.findViewById(R.id.tvItem);
                if(!compl.isEmpty()){
                    compl = compl + ", ";
                }
                compl = compl + tv.getText();
            }

        }
        carrinhoActivity.putExtra("totalitem", total + totalAdic);
        carrinhoActivity.putExtra("complemento", compl);
        System.out.println("idMesaAberta: "+idMesaAberta);
        startActivityForResult(carrinhoActivity, 2);
    }

    public ListView getListViewProdutos() {
        return listViewProdutos;
    }

    public void setListViewProdutos(ListView listViewProdutos) {
        this.listViewProdutos = listViewProdutos;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Double preco = 0.00; //restAdicional.getProdutoAdicionalLst().get(position).getIdProdutoAdicionavel().getPrecoVenda().doubleValue();
        CheckBox ck = (CheckBox) view.findViewById(R.id.checkBox);
        TextView tvItem = (TextView) view.findViewById(R.id.tvItem);

        if(ck.isChecked()){
            ck.setChecked(false);
            subtraiAdicional(preco);
        }else{
            ck.setChecked(true);
            somaAdicional(preco);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}