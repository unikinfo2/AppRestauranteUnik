package br.com.appmobile.unikinfo.rest;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.appmobile.unikinfo.adapter.AdapterProdutosPersonalizado;
import br.com.appmobile.unikinfo.apprestauranteunik.Utility;
import br.com.appmobile.unikinfo.entidade.Empresa;
import br.com.appmobile.unikinfo.entidade.Mesas;
import br.com.appmobile.unikinfo.entidade.Produtos;


public class ProdutosRestClient extends ListActivity {
    private ProgressBar pb;
    private String caminhoRest;
    private List<Produtos> produtoLst;
    private Produtos produto;
    private String dados;
    private JSONObject jObj;
    private Activity activ;
    private EditText edtProduto;
    private String metodo;
    private String retornoProdutos;
    private ImageView iView;
    private ListView listViewProdutos;
    private Mesas mesas;
    private String caminhoImagens;

    public ProdutosRestClient(Activity pAct) {
        super();
        activ = pAct;
        produto = new Produtos();
        produtoLst = new ArrayList<Produtos>();
        caminhoImagens = "";
    }

    public String getCaminhoRest() {
        return caminhoRest;
    }

    public void setCaminhoRest(String caminhoRest) {
        this.caminhoRest = caminhoRest;
    }

    public List<Produtos> getProdutoLst() {
        return this.produtoLst;
    }

    public void setProdutoLst(List<Produtos> produtoLst) {
        this.produtoLst = produtoLst;
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

    public EditText getEdtProduto() {
        return edtProduto;
    }

    public String getRetornoProdutos() {
        return retornoProdutos;
    }

    public void setRetornoProdutos(String retornoProdutos) {
        this.retornoProdutos = retornoProdutos;
    }

    public void setEdtProduto(EditText edtProduto) {
        this.edtProduto = edtProduto;
    }

    public Produtos getproduto() {
        return produto;
    }

    public void setproduto(Produtos produto) {
        this.produto = produto;
    }

    public Mesas getmesas() {
        return mesas;
    }

    public void setMesas(Mesas mesas) {
        this.mesas = mesas;
    }

    public ListView getListViewProdutos() {
        return listViewProdutos;
    }

    public void setListViewProdutos(ListView listViewProdutos) {
        this.listViewProdutos = listViewProdutos;
    }

    public String getCaminhoImagens() {
        return caminhoImagens;
    }

    public void setCaminhoImagens(String caminhoImagens) {
        this.caminhoImagens = caminhoImagens;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void consultar() {
        //exibirProgress(true);
        metodo = "GET";
        new DownloadJsonAsyncTask()
                .execute(caminhoRest + "produtos/cardapio");
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
                .execute(caminhoRest);
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
                    if (getProdutoLst().size() > 0) {
                        produto = getProdutoLst().get(0);
                        //getProdutoLst().get(0).setRazaoSocial(edtRazao.getText().toString());
                        dados = downloadDataFromUrl(urls[0], metodo);
                    }
                }

                if (metodo.equals("POST")) {
                    produto = new Produtos();
                    //produto.setRazaoSocial(edtRazao.getText().toString());
                    dados = downloadDataFromUrl(urls[0], metodo);
                }
                return dados;
            } catch (IOException e) {
                return "Erro: " + e.getMessage();
            }
        }

        private void carregaClasse(String result) {
            Gson gproduto = new Gson();

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

                            Produtos vcf = gproduto.fromJson(reader, Produtos.class);

                            if((vcf.getPathImagem() != null) && (!vcf.getPathImagem().isEmpty())) {
                                File imgLocal = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ vcf.getPathImagem());
                                if (imgLocal.exists()) {
                                    Bitmap img = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ vcf.getPathImagem());
                                    //vcf.setImagem(img);
                                }
                            } else {
//                                File imgLocal = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ "logo-suco-bagaco.jpg");
//                                if (imgLocal.exists()) {
//                                    Bitmap img = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ "logo-suco-bagaco.jpg");
//                                    //vcf.setImagem(img);
//                                }
                            }
                            getProdutoLst().add(vcf);
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
            List<String> prod = new ArrayList<>();
            for (int i = 0; i < getProdutoLst().size(); i++) {
                prod.add(getProdutoLst().get(i).getDescricao()+"........ R$"+getProdutoLst().get(i).getPrecoVenda());
            }
            retornoProdutos = prod.toString();
            AdapterProdutosPersonalizado adapter = new AdapterProdutosPersonalizado(produtoLst, activ);
            adapter.setCaminhoImagens(caminhoImagens);
            listViewProdutos.setAdapter(adapter);
        }

        // Faz a requisicao ao WebService Rest
        public String downloadDataFromUrl(String myurl, String metodo) throws IOException {
            InputStream is = null;
            String contentAsString = "";
            try {
                URL url;
                if (metodo.equals("PUT")) {
                    url = new URL(myurl + "/" + getproduto().getId());
                } else {
                    url = new URL(myurl);
                }
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(30000); // time in milliseconds
                conn.setConnectTimeout(30000); // time in milliseconds
                conn.setRequestMethod(metodo); // request method GET OR POST
                conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1");
                conn.setRequestProperty("Accept", "application/json; charset=ISO-8859-1");
                conn.setDoInput(true);

                if (metodo.equals("PUT")) {
                    JSONObject jParam = new JSONObject();
                    jParam.put("idproduto", getproduto().getId());
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(jParam.toString());
                    wr.flush();
                    wr.close();
                }

                if (metodo.equals("POST")) {
                    JSONObject jParam = new JSONObject();
                    conn.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(jParam.toString());
                    wr.flush();
                    wr.close();
                } else {
                    conn.connect(); // calling the web address
                    String response = conn.getResponseCode() + "->> " + conn.getResponseMessage();
                    Log.d(Utility.getTAG(), "Resposta do Servidor: " + response);
                    is = conn.getInputStream();
                    // Converte o InputStream em string
                    contentAsString = Utility.readInputStream(is);
                    carregaClasse(contentAsString);
                }

            } catch (Exception e) {
                Log.i("Produto Erro>>>>: ", e.toString());
            } finally {
                if (is != null) {
                    is.close();
                }
            }
            return contentAsString;
        }
    }
}

