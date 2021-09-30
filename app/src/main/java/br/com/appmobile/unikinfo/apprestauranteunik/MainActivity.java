package br.com.appmobile.unikinfo.apprestauranteunik;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;

import br.com.appmobile.unikinfo.rest.ImagemClient;
import br.com.appmobile.unikinfo.rest.UsuariosRestClient;

public class MainActivity extends AppCompatActivity {
    private EditText edtEmail;
    private UsuariosRestClient usuario;
    private ImageView iView;
    private ProgressBar proBar;
    private EditText edtSenha;
    private EditText edtHost;
    private String url =  "http://192.168.1.200:8080/webServiceRestaurante/rest/";
    //private String url =  "http://192.168.1.102:8080/webServiceRestaurante/rest/";
    //private String url =  "http://server28.integrator.com.br:12685/NeosWebService/rest/"; //"http://www.androidstation.info/people.json";
    //private String url =  "http://192.168.0.106:8080/NeosWebService/rest/"; //"http://www.androidstation.info/people.json";
    //private String url =  "http://192.168.0.103:8080/NeosERP/rest/clifor/"; //"http://www.androidstation.info/people.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        edtEmail = (EditText)findViewById(R.id.editTextUsuario);
        edtSenha = (EditText) findViewById(R.id.editTextSenha);
        proBar = (ProgressBar) findViewById(R.id.pBar);
        ImageView imagemLogo = (ImageView) findViewById(R.id.imageViewLogo);
        edtHost =  (EditText) findViewById(R.id.editTextHost);

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        edtHost.setText(preferences.getString("ServerName", "servidor:8080"));

        edtEmail.setText("admin");
        edtSenha.setText("123");
        usuario = new UsuariosRestClient(this);
        imagemLogo.setImageResource(R.mipmap.ic_logoappx);
    }



    public void consultar(View view) throws IOException {
        usuario.setUrlBase(url); //+"clifor/email/"+edtEmail.getText()
        usuario.consultar();
        /*String sUrl = "http://server28.integrator.com.br:12685/NeosERP/arquivos/logo-suco-bagaco.png";
        iView.setImageBitmap(null);
        iView.setTop(100);;
        iView.setScaleType(ImageView.ScaleType.FIT_XY);
        new ImagemClient(sUrl, iView).execute();*/

        //Intent intent = new Intent(this, PedidoActivity.class);
        //intent.putExtra("urlBase", url);
        //intent.putExtra("urlBase", url);
        //startActivity(intent);

    }

    public void validar(View view) throws IOException {
        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ServerName", edtHost.getText().toString().trim());
        editor.commit();

        url =  "http://"+edtHost.getText().toString().trim()+"/webServiceRestaurante/rest/";
        usuario.setUrlBase(url);
        usuario.setEdtSenha(edtSenha);
        usuario.consultar();
    }

    public void chamaCadastro(View view){
//        Intent intent = new Intent(this, CadastroActivity.class);
//        startActivity(intent);
    }

    public void alterar(View view) throws IOException {
        //cf.setCaminhoRest(url+clifor);
        //cf.alterar();
        //Intent intent = new Intent(this, PedidoActivity.class);
        //startActivity(intent);
        String sUrl = "http://server28.integrator.com.br:12685/NeosERP/arquivos/food.png";
        iView.setImageBitmap(null);
        iView.setTop(50);;
        iView.setScaleType(ImageView.ScaleType.FIT_XY);
        new ImagemClient(sUrl, iView).execute();

        //new ImagemClient(this).execute(IMAGE_URL);

        //iView.setImageBitmap(getbmpfromURL(sUrl));
    }

}
