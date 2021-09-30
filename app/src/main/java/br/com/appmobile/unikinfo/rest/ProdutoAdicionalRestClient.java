package br.com.appmobile.unikinfo.rest;
// Uses AsyncTask to create a task away from the main UI thread(For Avoid
// NetworkOnMainThreadException). This task takes a
// URL string and uses it to create an HttpUrlConnection. Once the
// connection
// has been established, the AsyncTask downloads the contents of the data as
// an InputStream. Than, the InputStream is converted into a string, which
// is
// displayed in the TextView by the AsyncTask's onPostExecute method. Which
// called after doInBackgroud Complete

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.appmobile.unikinfo.adapter.AdapterAdicionaisPersonalizado;
import br.com.appmobile.unikinfo.apprestauranteunik.Utility;
import br.com.appmobile.unikinfo.entidade.Empresa;
import br.com.appmobile.unikinfo.entidade.ProdutoAdicional;


public class ProdutoAdicionalRestClient extends ListActivity {
    private List<ProdutoAdicional> produtoAdicionalLst;
    private ProdutoAdicional produtoadicional;
    private String dados;
    private Activity activ;
    private String metodo;
    private Empresa empresa;
    private String caminhoRest;
    private String retornoProdutos;
    private ListView listViewAdicionais;
    private Long idProdutoSelected;


    public ProdutoAdicionalRestClient(Activity pAct) {
        super();
        activ = pAct;
        produtoadicional = new ProdutoAdicional();
        produtoAdicionalLst = new ArrayList<ProdutoAdicional>();
    }

    public List<ProdutoAdicional> getProdutoAdicionalLst() {
        return this.produtoAdicionalLst;
    }
    public Empresa getempresa() {
        return empresa;
    }

    public String getCaminhoRest() {
        return caminhoRest;
    }

    public void setCaminhoRest(String caminhoRest) {
        this.caminhoRest = caminhoRest;
    }

    public Long getIdProdutoSelected() {
        return idProdutoSelected;
    }

    public void setIdProdutoSelected(Long idProdutoSelected) {
        this.idProdutoSelected = idProdutoSelected;
    }

    public ListView getListViewAdicionais() {
        return listViewAdicionais;
    }

    public void setListViewAdicionais(ListView listViewAdicionais) {
        this.listViewAdicionais = listViewAdicionais;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void consultar() {
        metodo = "GET";
        new DownloadJsonAsyncTask()
        .execute(caminhoRest + "produtoadicional/produtoorigem/"+idProdutoSelected);
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
                    dados = downloadDataFromUrl(urls[0], metodo); //"UTF-8");
                }
                return dados;
            } catch (IOException e) {
                return "Erro: " + e.getMessage();
            }
        }

        private void carregaClasse(String result) {
            Gson gprodutoadicional = new Gson();

            if (!result.isEmpty()) {
                try {
                    JSONObject json = null;
                    String result2 = result;
                    if (result2.charAt(0) == '{') {
                        result2 = "[" + result + "]";
                    }
                    JSONArray jsonArray = new JSONArray(result2);
                    if (jsonArray.length() > 0) {
                        for (int it = 0; it < jsonArray.length(); it++) {
                            json = (JSONObject) jsonArray.get(it);
                            Reader reader = new StringReader(json.toString());
                            ProdutoAdicional vcf = gprodutoadicional.fromJson(reader, ProdutoAdicional.class);
                            produtoAdicionalLst.add(vcf);
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
            carregaClasse(result);
            AdapterAdicionaisPersonalizado adapter = new AdapterAdicionaisPersonalizado(activ, produtoAdicionalLst);
            listViewAdicionais.setAdapter(adapter);
        }
    }

    // Faz a requisicao ao WebService Rest
    @SuppressLint("LongLogTag")
    public String downloadDataFromUrl(String myurl, String metodo) throws IOException {
        InputStream is = null;
        String contentAsString = "";
        try {
            URL url;
            url = new URL(myurl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000); // time in milliseconds
            conn.setConnectTimeout(15000); // time in milliseconds
            conn.setRequestMethod(metodo); // request method GET OR POST
            conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1");
            conn.setRequestProperty("Accept", "application/json; charset=ISO-8859-1");
            conn.setDoInput(true);

            conn.connect(); // calling the web address
            String response = conn.getResponseCode() + "->> " + conn.getResponseMessage();
            Log.d(Utility.getTAG(), "Resposta do Servidor: " + response);
            is = conn.getInputStream();

            // Converte o InputStream em string
            contentAsString = Utility.readInputStream(is);
        } catch (Exception e) {
            Log.i("Produto Adicional Erro>>>>: ", e.toString());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return contentAsString;
    }
    public String getRetornoProdutos() {
        return retornoProdutos;
    }

    public void setRetornoProdutos(String retornoProdutos) {
        this.retornoProdutos = retornoProdutos;
    }
}

