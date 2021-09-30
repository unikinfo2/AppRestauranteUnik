package br.com.appmobile.unikinfo.rest;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import br.com.appmobile.unikinfo.adapter.AdapterMesasPersonalizado;
import br.com.appmobile.unikinfo.apprestauranteunik.ActivityMesas;
import br.com.appmobile.unikinfo.entidade.Mesas;
import br.com.appmobile.unikinfo.apprestauranteunik.R;
import br.com.appmobile.unikinfo.apprestauranteunik.Utility;

public class MesasRestClient extends ListActivity {
    private ProgressBar pb;
    private String urlBase;
    private List<Mesas> mesasLst;
    private Mesas mesas;
    private String dados;
    private JSONObject jObj;
    private Activity activ;
    private String metodo;
    private boolean resultado;
    private ListView listViewMesas;

    public MesasRestClient(Activity pAct) {
        super();
        activ     = pAct;
        mesas     = new Mesas();
        mesasLst  = new ArrayList<Mesas>();
        pb        = (ProgressBar) activ.findViewById(R.id.pBar);
        resultado = false;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public List<Mesas> getMesasLst() {
        return mesasLst;
    }

    public void setMesasLst(List<Mesas> mesasLst) {
        this.mesasLst = mesasLst;
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

    public Mesas getMesas() {
        return mesas;
    }

    public void setClifor(Mesas mesas) {
        this.mesas = mesas;
    }

    private void exibirProgress(boolean exibir) {
        //pb.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    public ListView getListViewMesas() {
        return listViewMesas;
    }

    public void setListViewMesas(ListView listViewMesas) {
        this.listViewMesas = listViewMesas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void consultar() {
        exibirProgress(true);
        metodo = "GET";
        new DownloadJsonAsyncTask()
                .execute(urlBase+"mesas");
    }

    public void alterar() {
        exibirProgress(true);
        metodo = "PUT";
        new DownloadJsonAsyncTask()
                .execute(urlBase+"mesas");
    }

    public void inserir() {
        exibirProgress(true);
        metodo = "POST";
        new DownloadJsonAsyncTask()
                .execute(urlBase+"mesas");
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
                if (metodo.equals("PUT")) {
//                    if(getMesasLst().size() > 0) {
//                        mesas = getMesasLst().get(0);
//                        getMesasLst().get(0).setNumeroMesa(); //(edtSenha.getText().toString());
//                        dados = downloadDataFromUrl(urls[0], metodo);
//                    }
                }

                if (metodo.equals("POST")) {
                    System.out.println("urls[0] = "+urls[0]);
                    dados = downloadDataFromUrl(urls[0], metodo);
                }
                return dados;
            } catch (IOException e) {
                return "Erro: "+e.getMessage();
            }
        }

        private void carregaClasse(String result){
            Gson gMesa = new Gson();

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
                            Mesas vcf = gMesa.fromJson(reader, Mesas.class);
                            getMesasLst().add(vcf);
                        }
                        dados = result;
                    }

                } catch (JSONException e) {
                    System.out.println("Ocorreu um erro ao carregar as mesas: "+e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Pesquisa das mesas não retornou dados, verifique se o servidor Rest esta ativo.");
            }
        }
        // O método a seguir é chamado após o resultado da requisição
        @Override
        protected void onPostExecute(String result) {

            if (metodo.equals("GET")) {
                AdapterMesasPersonalizado adapter = new AdapterMesasPersonalizado(getMesasLst(), activ);
                adapter.setCaminhoImagens("");
                listViewMesas.setAdapter(adapter);
            }
            exibirProgress(false);

        }

        // Faz a requisicao ao WebService Rest
        public String downloadDataFromUrl(String myurl, String metodo) throws IOException {
            InputStream is = null;
            String contentAsString = "";
            try {
                URL url;
                if(metodo.equals("PUT")) {
                    url = new URL(myurl+"/"+getMesas().getId());
                } else {
                    url = new URL(myurl);
                }
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(30000); // time in milliseconds
                conn.setConnectTimeout(30000); // time in milliseconds
                conn.setRequestMethod(metodo); // request method GET OR POST
                conn.setRequestProperty("Content-Type","application/json; charset=ISO-8859-1");
                conn.setRequestProperty("Accept","application/json; charset=ISO-8859-1");
                conn.setDoInput(true);

                if(metodo.equals("PUT")) {
                    JSONObject jParam = new JSONObject();
                    //Colocar os campos para alterar um registro, não escrecer de informar o ID do registro a ser alterado
                    jParam.put("idmesa", getMesas().getId());
                    jParam.put("descricaomesa",getMesas().getDescricao());
                    // Send post request
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(jParam.toString());
                    wr.flush();
                    wr.close();
                }
                String response = "";
                if(metodo.equals("POST")) {
                    JSONObject jParam = new JSONObject();
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    Gson gCli = new Gson();
                    wr.writeBytes(gCli.toJson(mesas));
                    wr.flush();
                    wr.close();
                    conn.connect(); // calling the web address
                    response = conn.getResponseMessage();
                    is = conn.getInputStream();
                    contentAsString = Utility.readInputStream(is);
                    if(response.equals("OK")) {
                        resultado = true;
                        System.out.println("ENTROU OK");
                        mesas = gCli.fromJson(contentAsString, Mesas.class);
                    }
                } else {
                    conn.connect(); // calling the web address
                    is = conn.getInputStream();
                    response = conn.getResponseCode() + "->> " + conn.getResponseMessage();
                    String resultadostr = Utility.readInputStream(is);
                    if(conn.getResponseMessage().equals("OK")) {
                        carregaClasse(resultadostr);
                        resultado = true;
                    }
                    contentAsString = resultadostr;
                }

                //conn.connect(); // calling the web address
                //String response = conn.getResponseCode() + "->> " +conn.getResponseMessage();
                //Log.d(Utility.getTAG(), "Resposta do Servidor: " + response);
                //is = conn.getInputStream();

                // Converte o InputStream em string
                //contentAsString = Utility.readInputStream(is);
            } catch (Exception e){
                Log.i("MSG de Erro: " , e.getMessage());
            } finally {
                if (is != null) {
                    is.close();
                }
            }
            return contentAsString;
        }
    }

}

