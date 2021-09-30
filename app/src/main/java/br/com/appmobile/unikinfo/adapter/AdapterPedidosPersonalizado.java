package br.com.appmobile.unikinfo.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.appmobile.unikinfo.entidade.MesasItens;
import br.com.appmobile.unikinfo.apprestauranteunik.R;

public class AdapterPedidosPersonalizado extends ArrayAdapter<MesasItens> {

    private final Context context;
    private final List<MesasItens> mesasItens;
    private final NumberFormat currency;


    public AdapterPedidosPersonalizado(Context context, List<MesasItens> pedidoitens) {
        super(context, R.layout.lista_itenspedido_personalizada);
        this.context = context;
        this.mesasItens = pedidoitens;
        currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    }

    @Override
    public int getCount() {
        return mesasItens.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.lista_itenspedido_personalizada, parent, false);

        MesasItens mesasItens = (MesasItens) this.mesasItens.get(position);

        TextView descitem1 = (TextView) view.findViewById(R.id.descitem1);
        TextView descitem2 = (TextView) view.findViewById(R.id.descitem2);
        TextView totalitem = (TextView) view.findViewById(R.id.totalitem);
        TextView qtditem   = (TextView) view.findViewById(R.id.qtditem);

        descitem1.setText(mesasItens.getDescricao());
        descitem2.setText(mesasItens.getDescricaoDetalhe());
        totalitem.setText(currency.format(mesasItens.getPrecoTotal().doubleValue()));
        qtditem.setText(mesasItens.getQuant().toString());

        return view;
    }
}