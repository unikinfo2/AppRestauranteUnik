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

import br.com.appmobile.unikinfo.apprestauranteunik.ActivityMesas;
import br.com.appmobile.unikinfo.entidade.Usuarios;
import br.com.appmobile.unikinfo.apprestauranteunik.R;
import br.com.appmobile.unikinfo.apprestauranteunik.Utility;

public class UsuariosRestClient extends ListActivity {
    private ProgressBar pb;
    private String urlBase;
    private List<Usuarios> usuariosLst;
    private Usuarios usuarios;
    private String dados;
    private JSONObject jObj;
    private Activity activ;
    private EditText edtSenha;
    private EditText edtUsuario;
    private String metodo;
    private boolean resultado;

    public UsuariosRestClient(Activity pAct) {
        super();
        activ       = pAct;
        usuarios    = new Usuarios();
        usuariosLst = new ArrayList<Usuarios>();
        edtSenha    = (EditText) activ.findViewById(R.id.editTextSenha);
        edtUsuario  = (EditText) activ.findViewById(R.id.editTextUsuario);
        pb          = (ProgressBar) activ.findViewById(R.id.pBar);
        resultado   = false;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public List<Usuarios> getUsuariosLst() {
        return usuariosLst;
    }

    public void setMesasLst(List<Usuarios> usuariosLst) {
        this.usuariosLst = usuariosLst;
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

    public EditText getEdtSenha() {
        return edtSenha;
    }

    public void setEdtSenha(EditText edtSenha) {
        this.edtSenha = edtSenha;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public EditText getEdtUsuario() {
        return edtUsuario;
    }

    public void setEdtUsuario(EditText edtUsuario) {
        this.edtUsuario = edtUsuario;
    }

    private void exibirProgress(boolean exibir) {
        //pb.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void consultar() {
        exibirProgress(true);
        metodo = "GET";
        new DownloadJsonAsyncTask()
                .execute(urlBase+"usuarios/usuario/"+edtUsuario.getText());
    }

    public void alterar() {
        exibirProgress(true);
        metodo = "PUT";
        new DownloadJsonAsyncTask()
                .execute(urlBase+"usuarios");
    }

    public void inserir() {
        exibirProgress(true);
        metodo = "POST";
        new DownloadJsonAsyncTask()
                .execute(urlBase+"usuarios");
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
                    dados = downloadDataFromUrl(urls[0], metodo);
                }
                return dados;
            } catch (IOException e) {
                return "Erro: "+e.getMessage();
            }
        }

        private void carregaClasse(String result){
            Gson gUsuario = new Gson();

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
                            Usuarios vcf = gUsuario.fromJson(reader, Usuarios.class);
                            getUsuariosLst().add(vcf);
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
//            if(!result.isEmpty()) {
//                carregaClasse(result);
//                if ((metodo.equals("GET")) && (getUsuariosLst().size() > 0)) {
//                    //edtRazao.setText(getCliforLst().get(0).getRazaoSocial()); // getCliforLst().get(0).getRazaoSocial()); getClifor().getRazaoSocial()
//                    if (getUsuariosLst().get(0).getSenha() != null) {
//                        if (edtSenha.getText().toString().equals(getUsuariosLst().get(0).getSenha())) {
//                            //Chamar activity
//                            Intent intent = new Intent(activ, PedidoActivity.class);
//                            intent.putExtra("urlBase", urlBase);
//                            intent.putExtra("usuarios", getUsuariosLst().get(0));
//                            activ.startActivity(intent);
//                        } else {
//                            System.out.println("Senha Errada.....");
//                        }
//                    } else {
//                        System.out.println("Não foi atribuido uma senha para o seu usuario, solicite a sua senha.....");
//                     }
//                }
//            }


            if(resultado){
                Intent intent = new Intent(activ, ActivityMesas.class);
                intent.putExtra("urlbase", urlBase);
                activ.startActivity(intent);
            }
            exibirProgress(false);
        }

    }

    // Faz a requisicao ao WebService Rest
    public String downloadDataFromUrl(String myurl, String metodo) throws IOException {
        InputStream is = null;
        String contentAsString = "";
        try {
            URL url;
            if(metodo.equals("PUT")) {
                url = new URL(myurl+"/"+getUsuarios().getId());
            } else {
                url = new URL(myurl);
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000); // time in milliseconds
            conn.setConnectTimeout(15000); // time in milliseconds
            conn.setRequestMethod(metodo); // request method GET OR POST
            conn.setRequestProperty("Content-Type","application/json; charset=ISO-8859-1");
            conn.setRequestProperty("Accept","application/json; charset=ISO-8859-1");
            conn.setDoInput(true);

            if(metodo.equals("PUT")) {
                JSONObject jParam = new JSONObject();
                //Colocar os campos para alterar um registro, não escrecer de informar o ID do registro a ser alterado
                jParam.put("idCliFor", getUsuarios().getId());
                jParam.put("razaoSocial",getUsuarios().getNomeUsuario());
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
                Gson gUsu = new Gson();
                wr.writeBytes(gUsu.toJson(usuarios));
                wr.flush();
                wr.close();
                conn.connect(); // calling the web address
                response = conn.getResponseMessage();
                is = conn.getInputStream();
                contentAsString = Utility.readInputStream(is);
                if(response.equals("OK")) {
                    resultado = true;
                    usuarios = gUsu.fromJson(contentAsString, Usuarios.class);
                }
            } else {
                conn.connect(); // calling the web address
                is = conn.getInputStream();
                response = conn.getResponseCode() + "->> " + conn.getResponseMessage();
                Log.i("UsuariosRestClient", "Resposta Servidor -> "+response.toString());
                resultado = conn.getResponseMessage().equals("OK");
                contentAsString = Utility.readInputStream(is);
            }
            contentAsString = Utility.readInputStream(is);
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

