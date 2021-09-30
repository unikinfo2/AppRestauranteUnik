package br.com.appmobile.unikinfo.rest;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

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
import java.util.ArrayList;
import java.util.List;

import br.com.appmobile.unikinfo.entidade.Empresa;
import br.com.appmobile.unikinfo.entidade.Mesas;
import br.com.appmobile.unikinfo.entidade.MesasAbertas;
import br.com.appmobile.unikinfo.entidade.MesasItens;
import br.com.appmobile.unikinfo.entidade.Produtos;
import br.com.appmobile.unikinfo.apprestauranteunik.R;
import br.com.appmobile.unikinfo.apprestauranteunik.Utility;


public class MesasAbertasRestClient extends ListActivity {
    private ProgressBar pb;
    private String caminhoRest;
    private String dados;
    private JSONObject jObj;
    private Activity activ;
    private String metodo;
    private ListView listViewPedidoItens;
    private Mesas mesas;
    private Empresa empresa;
	private MesasAbertas mesasAbertas;
    private Produtos produtos;
    private List<MesasItens> mesasItensLst;
    private List<MesasAbertas> mesasAbertasLst;

    public MesasAbertasRestClient(Activity pAct) {
        super();
        activ = pAct;
        mesasAbertas = new MesasAbertas();
        mesasAbertasLst = new ArrayList<MesasAbertas>();
    }

    public String getCaminhoRest() {
        return caminhoRest;
    }

    public void setCaminhoRest(String caminhoRest) {
        this.caminhoRest = caminhoRest;
    }

    public List<MesasAbertas> getMesasAbertasLst() {
        return this.mesasAbertasLst;
    }

    public void setPedidos(List<MesasAbertas> mesasAbertasLst) {
        this.mesasAbertasLst = mesasAbertasLst;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String pDados) {
        this.dados = pDados;
    }

    public Activity getActiv() {
        return activ;
    }

    public void setActiv(Activity activ) {
        this.activ = activ;
    }

    public ProgressBar getPb() {
        return pb;
    }

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

    public MesasAbertas getMesasAbertas() {
        return mesasAbertas;
    }

    public void setpedido(MesasAbertas mesasAbertas) {
        this.mesasAbertas = mesasAbertas;
    }

    public Mesas getMesas() {
        return mesas;
    }

    public Empresa getempresa() {
        return empresa;
    }

    public void setempresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setMesas(Mesas mesas) {
        this.mesas = mesas;
    }

    public ListView getListViewPedidoItens() {
        return listViewPedidoItens;
    }

    public void setListViewProdutos(ListView listViewPedidoItens) {
        this.listViewPedidoItens = listViewPedidoItens;
    }

    public List<MesasItens> getMesasItensLst() {
        return mesasItensLst;
    }

    public void setMesasItens(List<MesasItens> mesasItensLst) {
        this.mesasItensLst = mesasItensLst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listViewPedidoItens= (ListView) findViewById(R.id.listaItensPedido);
    }

    public void consultar() {
        metodo = "GET";
        if (this.getempresa() != null) {
            new DownloadJsonAsyncTask()
                    .execute(caminhoRest + "mesasabertas/");
        }
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
                .execute(caminhoRest+"mesasabertas/");

    }

    public class DownloadJsonAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            InputStream is = null;
            String contentAsString = "";
            try {
                URL url;
                if(metodo.equals("PUT")) {
                    url = new URL(urls[0]);
                } else {
                    url = new URL(urls[0]);
                }
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(30000); // time in milliseconds
                conn.setConnectTimeout(30000); // time in milliseconds
                conn.setRequestMethod(metodo); // request method GET OR POST
                conn.setRequestProperty("Content-Type","application/json; charset=ISO-8859-1");
                conn.setRequestProperty("Accept","application/json; charset=ISO-8859-1");
                conn.setDoInput(true);

                if (metodo.equals("GET")) {
                    dados = downloadDataFromUrl(urls[0], metodo); //"UTF-8");Transacao Itens
                    carregaClasse(dados);
                }
                if (metodo.equals("PUT")) {
                    if (getMesasAbertasLst().size() > 0) {
                        mesasAbertas = getMesasAbertasLst().get(0);
                        dados = downloadDataFromUrl(urls[0], metodo);
                    }
                }
                String response = "";
                if (metodo.equals("POST")) {
                    dados = downloadDataFromUrl(urls[0], metodo);
                    JSONObject jParam = new JSONObject();
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    Gson gTr = new Gson();
                    mesasAbertas.setIdMesa(mesas.getId());
                    wr.writeBytes(gTr.toJson(mesasAbertas));
                    wr.flush();
                    wr.close();
                    conn.connect(); // calling the web address
                    response = ""+conn.getResponseCode();
                    is = conn.getInputStream();
                    contentAsString = Utility.readInputStream(is);
                    if(response.equals("OK")) {
                    }
                }
                return dados;
            } catch (IOException e) {
                return "Erro: " + e.getMessage();
            }
        }

        private void carregaClasse(String result) {
            Gson gpedido = new Gson();

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
                            MesasAbertas vcf = gpedido.fromJson(reader, MesasAbertas.class);
                            getMesasAbertasLst().add(vcf);
                        }
                        dados = result;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        // O método a seguir é chamado após o resultado da requisição
        @Override
        protected void onPostExecute(String result) {
            if(metodo.equals("GET")) {
                /*List<String> ped = new ArrayList<>();
                for (Transacaoitem pedIt : getpedido().getTransacaoitemCollection()) {
                    ped.add(pedIt.getIdProduto().getDescricao() + "........ R$" + pedIt.getTotalItem());
                }*/
            }
            if(metodo.equals("POST")) {
//                MesasAbertasRestClient ma = new MesasAbertasRestClient(activ);
//                ma.setpedido(mesasAbertas);
//                ma.setpedidoItens(mesasItensLst);
//                ma.setCaminhoRest(caminhoRest);
//                ma.inserir();
            }

            //retornoProdutos = ped.toString();
            //AdapterPedidosPersonalizado adapter = new AdapterPedidosPersonalizado(pedidoItens, activ);
            //listViewPedidoItens.setAdapter(adapter);

        }

    }

    // Faz a requisicao ao WebService Rest
    public String downloadDataFromUrl(String myurl, String metodo) throws IOException {
        InputStream is = null;
        String contentAsString = "";
        try {
            URL url;
            if (metodo.equals("PUT")) {
                url = new URL(myurl + "/" + getMesasAbertas().getId());
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
                jParam.put("idproduto", getMesasAbertas().getId());
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
                wr.writeBytes(gTr.toJson(mesasAbertas));
                wr.flush();
                wr.close();
                conn.connect(); // calling the web address
                response = conn.getResponseMessage();
                is = conn.getInputStream();
                contentAsString = Utility.readInputStream(is);
                if(response.equals("OK")) {
//                    mesasAbertas = gTr.fromJson(contentAsString, MesasAbertas.class);
//                    for(int x=0 ; x < mesasItensLst.size(); x++){
//                        mesasItensLst.get(x).setId(mesasAbertas.getId());
//                    }
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
            Log.i("Transacao Erro>>>>: ", e.toString());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return contentAsString;
    }
}

