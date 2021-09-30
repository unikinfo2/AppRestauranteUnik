package br.com.appmobile.unikinfo.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.appmobile.unikinfo.entidade.Produtos;
import br.com.appmobile.unikinfo.entidade.ProdutoAdicional;
import br.com.appmobile.unikinfo.apprestauranteunik.R;

public class AdapterAdicionaisPersonalizado  extends BaseAdapter {

    private final Context context;
    private NumberFormat currency;
    private final List<ProdutoAdicional> produtosadd;
    private CheckBox checkBox;
    private TextView tvItem;
    private String concatItensName;
    private double somaItens;

    public AdapterAdicionaisPersonalizado(Context context, List<ProdutoAdicional> produtosAdd) {
        this.context = context;
        currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        this.produtosadd = produtosAdd;
    }

    @Override
    public int getCount() {
        return produtosadd.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lista_adicionais_personalizada, parent, false);

        ProdutoAdicional produtosadd = (ProdutoAdicional) this.produtosadd.get(position);

        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        tvItem = (TextView) view.findViewById(R.id.tvItem);
        //tvItem.setText(produtosadd.getIdProdutoAdicionavel().getDescricao()+" - "+currency.format(produtosadd.getIdProdutoAdicionavel().getPrecoVenda()));
        return view;
    }



    public String getConcatItensName() {
        return concatItensName;
    }

    public void setConcatItensName(String concatItensName) {
        this.concatItensName = concatItensName;
    }

    public double getSomaItens() {
        return somaItens;
    }

    public void setSomaItens(double somaItens) {
        this.somaItens = somaItens;
    }
}