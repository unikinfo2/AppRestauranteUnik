package br.com.appmobile.unikinfo.rest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.appmobile.unikinfo.adapter.AdapterPedidosPersonalizado;
import br.com.appmobile.unikinfo.apprestauranteunik.PedidoConfirmadoActivity;
import br.com.appmobile.unikinfo.apprestauranteunik.R;
import br.com.appmobile.unikinfo.apprestauranteunik.Utility;
import br.com.appmobile.unikinfo.entidade.MesasAbertas;
import br.com.appmobile.unikinfo.entidade.MesasItens;


public class MesasItensRestClient extends ListActivity {
    private ProgressBar pb;
    private String caminhoRest;
    private String dados;
    private JSONObject jObj;
    private String metodo;
    private List<MesasItens> mesasItensLst;
    private MesasItens mesasItens;
    private Activity activ;
    private TextView tvTotal;
    private NumberFormat currency;
    private Double totalMesa;
    private Integer idMesa;
    private Integer idMesaAberta;

    public MesasItensRestClient(Activity pAct) {
        super();
        activ = pAct;
        mesasItensLst = new ArrayList<MesasItens>();
        mesasItens    = new MesasItens();
        tvTotal       = (TextView) activ.findViewById(R.id.tvTotal);
        currency      = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        idMesa        = 0;
        idMesaAberta  = 0;
    }

    public String getCaminhoRest() {
        return caminhoRest;
    }

    public void setCaminhoRest(String caminhoRest) {
        this.caminhoRest = caminhoRest;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String pDados) {
        this.dados = pDados;
    }

    public ProgressBar getPb() {
        return pb;
    }

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

    public List<MesasItens> getMesasItensLst() {
        return mesasItensLst;
    }

    public void setMesasItensLst(List<MesasItens> mesasItensLst) {
        this.mesasItensLst = mesasItensLst;
    }

    public MesasItens getMesasItens() {
        return mesasItens;
    }

    public void setMesasItens(MesasItens mesasItens) {
        this.mesasItens = mesasItens;
    }

    public Activity getActiv() {
        return activ;
    }

    public void setActiv(Activity activ) {
        this.activ = activ;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void consultar(Integer idMesaAberta) {
        metodo = "GET";
        totalMesa = 0.00;
        System.out.println("caminho: "+caminhoRest + "mesasitens/mesa/"+idMesaAberta);
        new DownloadJsonAsyncTask()
                    .execute(caminhoRest + "mesasitens/mesa/"+idMesaAberta);
    }

    public void alterar() {
        //exibirProgress(true);
        metodo = "PUT";
        new DownloadJsonAsyncTask()
                .execute(caminhoRest);
    }

    public void inserir() {
        //exibirProgress(true);
        metodo = "POST";
        new DownloadJsonAsyncTask()
                .execute(caminhoRest+"mesasitens/");
    }

    public class DownloadJsonAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                if (metodo.equals("GET")) {
                    dados = downloadDataFromUrl(urls[0], metodo); //"UTF-8");Transacao Itens
                    carregaClasse(dados);
                }
                if (metodo.equals("PUT")) {
                    if (mesasItensLst.size() > 0) {
                        //pedido = pedidoItens.get(0);
                        dados = downloadDataFromUrl(urls[0], metodo);
                    }
                }

                if (metodo.equals("POST")) {
                    dados = downloadDataFromUrl(urls[0], metodo);
                }
                return dados;
            } catch (IOException e) {
                return "Erro: " + e.getMessage();
            }
        }

        private void carregaClasse(String result) {
            Gson gpedidoitem = new Gson();
            //System.out.println("result carregaclasse: "+result);
            if (!result.isEmpty()) {
                try {
                    JSONObject json = null;
                    String result2 = result;
                    if (result2.charAt(0) == '{') {
                        result2 = "[" + result2 + "]";
                    }
                    JSONArray jsonArray = new JSONArray(result2);
                    if (jsonArray.length() > 0) {
                        for (int it = 0; it < jsonArray.length(); it++) {
                            json = (JSONObject) jsonArray.get(it);
                            Reader reader = new StringReader(json.toString());
                            MesasItens vcf = gpedidoitem.fromJson(reader, MesasItens.class);
                            mesasItensLst.add(vcf);
                            if(vcf.getPrecoTotal() != null) {
                                totalMesa = totalMesa + vcf.getPrecoTotal();
                            }
                        }
                        dados = result;
                    }

                } catch (JSONException e) {
                    System.out.println("Erro CarregaClasse: "+e.getMessage());;
                }
            }
        }

        // O método a seguir é chamado após o resultado da requisição
        @Override
        protected void onPostExecute(String result) {
            if(metodo.equals("GET")) {
                tvTotal.setText(currency.format(totalMesa));
                ArrayAdapter adapter = new AdapterPedidosPersonalizado(activ, getMesasItensLst());
                ListView lvItens = (ListView) activ.findViewById(R.id.listaItensPedido);
                lvItens.setAdapter(adapter);

                /*List<String> ped = new ArrayList<>();
                for (Transacaoitem pedIt : getpedido().getTransacaoitemCollection()) {
                    ped.add(pedIt.getIdProduto().getDescricao() + "........ R$" + pedIt.getTotalItem());
                }*/
            }

            if(metodo.equals("POST")) {
//                Intent intent = new Intent(activ, PedidoConfirmadoActivity.class);
//                intent.putExtra("idPedido", mesasItens.getId());
//                activ.startActivity(intent);
            }

            //retornoProdutos = ped.toString();
            //AdapterPedidosPersonalizado adapter = new AdapterPedidosPersonalizado(pedidoItens, activ);
            //listViewPedidoItens.setAdapter(adapter);

        }

    }

    // Faz a requisicao ao WebService Rest
    @SuppressLint("LongLogTag")
    public String downloadDataFromUrl(String myurl, String metodo) throws IOException {
        InputStream is = null;
        String contentAsString = "";
        try {
            URL url;
            if (metodo.equals("PUT")) {
                url = new URL(myurl + "/" + mesasItens.getIdMesa());
            } else {
                url = new URL(myurl);
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000); // time in milliseconds
            conn.setConnectTimeout(15000); // time in milliseconds
            conn.setRequestMethod(metodo); // request method GET OR POST
            conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1");
            conn.setRequestProperty("Accept", "application/json; charset=ISO-8859-1");
            conn.setDoInput(true);

            if (metodo.equals("PUT")) {
                JSONObject jParam = new JSONObject();
                jParam.put("idproduto", mesasItens.getIdProduto());
                jParam.put("idmesa", mesasItens.getIdMesa());
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(jParam.toString());
                wr.flush();
                wr.close();
            }

            String response = "";
            if (metodo.equals("POST")) {
                JSONObject jParam = new JSONObject();
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                Gson gTr = new Gson();
                Log.i("json -> ", gTr.toJson(mesasItens));
                wr.writeBytes(gTr.toJson(mesasItens));
                wr.flush();
                wr.close();
                conn.connect(); // calling the web address
                response = ""+conn.getResponseCode();
                is = conn.getInputStream();
                contentAsString = Utility.readInputStream(is);
                if(response.equals("OK")) {
                }
            } else {
                conn.connect(); // calling the web address
                is = conn.getInputStream();
                response = conn.getResponseCode() + "->> " + conn.getResponseMessage();
                contentAsString = Utility.readInputStream(is);
            }
            //Log.d(Utility.getTAG(), "Resposta do Servidor: " + response);
            // Converte o InputStream em string

        } catch (Exception e) {
            Log.i("TransacaoItem Erro>>>>: ", e.toString());
            Log.i("myUrl -> ", myurl);
            Log.i("idMesa -> ", ""+mesasItens.getIdMesa());
            Log.i("metodo -> ", ""+metodo);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return contentAsString;
    }
}

