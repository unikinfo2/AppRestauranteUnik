package br.com.appmobile.unikinfo.apprestauranteunik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PedidoConfirmadoActivity extends Activity implements View.OnClickListener  {
    private TextView nrPedido;
    private Long idPedido;
    Button btnEmail;
    Button btnNovo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_confirmado);
        nrPedido = (TextView) findViewById(R.id.tvNumeroPedido);
        Bundle extras = getIntent().getExtras();
        idPedido  = extras.getLong("idPedido");
        nrPedido.setText(""+idPedido);

        btnNovo = (Button)findViewById(R.id.btnNovoPedido);

        btnEmail = (Button)findViewById(R.id.btnEmail);
        enviarEmail(btnEmail);
        //btnEmail.setOnClickListener(this);
        //btnEmail.callOnClick();
        /*btnEmail.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                enviarEmail();
            }
        });*/
        //enviarEmail();

    }

    public void novoPedido(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        //enviarEmail();
    }
    public TextView getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(TextView nrPedido) {
        this.nrPedido = nrPedido;
    }

    public boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! (" + ex.getMessage() + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void enviarEmail(View v){

        final String nome = "App Suco Bagaço";
        final String email = "unikinfo2@gmail.com";
        final String subject = "Seu pedido Suco Bagaço";
        final String body = "O seu pedido Suco Bagaço já esta a caminho sob o numero: " + idPedido;

        if(!isOnline()) {
            Toast.makeText(getApplicationContext(), "Não estava online para enviar e-mail!", Toast.LENGTH_SHORT).show();
            System.exit(0);
        }

        new Thread(new Runnable(){
            @Override
            public void run() {
//                Mail m = new Mail();
//
//                String[] toArr = {email};
//                m.setTo(toArr);
//
//                //m.setFrom("seunome@seuemail.com.br"); //caso queira enviar em nome de outro
//                m.setSubject(subject);
//                m.setBody(body);
//
//                try {
//                    //m.addAttachment("pathDoAnexo");//anexo opcional
//                    m.send();
//                }
//                catch(RuntimeException rex){ }//erro ignorado
//                catch(Exception e) {
//                    e.printStackTrace();
//                    System.exit(0);
//                }

                //Toast.makeText(getApplicationContext(), "Email enviado!", Toast.LENGTH_SHORT).show();
            }
        }).start();
    }
}