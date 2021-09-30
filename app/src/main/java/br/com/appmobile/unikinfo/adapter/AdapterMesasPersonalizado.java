package br.com.appmobile.unikinfo.adapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import br.com.appmobile.unikinfo.apprestauranteunik.R;
import br.com.appmobile.unikinfo.entidade.Mesas;

public class AdapterMesasPersonalizado extends BaseAdapter {

    private String imagemcomerro;
    private final List<Mesas> mesasLst;
    private final Activity act;
    private Handler handler = new Handler();
    private String caminhoImagens;


    public AdapterMesasPersonalizado(List<Mesas> mesasLst, Activity act) {
        this.mesasLst = mesasLst;
        this.act = act;
        imagemcomerro = "";
        caminhoImagens = "";
    }

    @Override
    public int getCount() {
        return mesasLst.size();
    }

    @Override
    public Object getItem(int position) {
        return mesasLst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.lista_mesas_personalizada, parent, false);
        Mesas mesas = mesasLst.get(position);

//        if(produto.getTpProduto().equals("CARDAPIOGRUPO")) {
//            view = act.getLayoutInflater().inflate(R.layout.lista_grupoproduto_personalizada, parent, false);
//        }

        TextView numMesa     = (TextView) view.findViewById(R.id.numMesa);
        TextView descMesa    = (TextView) view.findViewById(R.id.descMesa);
        TextView statusMesa  = (TextView) view.findViewById(R.id.statusMesa);
        ImageView imagemMesa = (ImageView) view.findViewById(R.id.imagemMesa);

        numMesa.setText(mesas.getNumeroMesa());
        descMesa.setText(mesas.getDescricao());
        statusMesa.setText(mesas.getStatus());

        if(mesas.getStatus().equals("Livre")) {
            imagemMesa.setImageResource(R.mipmap.ic_mesalivrex);
        } else {
            imagemMesa.setImageResource(R.mipmap.ic_mesaocupadax);
        }

//        if(!produto.getPathImagem().isEmpty()) {
//            File imgLocal = new File( act.getFilesDir().getPath() + "/"+ produto.getPathImagem()); //Environment.getExternalStorageDirectory().getAbsolutePath()
//            if (imgLocal.exists()) {
//                Bitmap img = BitmapFactory.decodeFile(act.getFilesDir().getPath() + "/"+ produto.getPathImagem());
//                imagem.setImageBitmap(img);
//            } else {
//                DownloadImageTask dit = new DownloadImageTask();
//                dit.setImagem(imagem);
//                dit.setProduto(produtoLst.get(position));
//                String vArquivo = "http://server28.integrator.com.br:12685/unikarquivos/arquivos/" + "imagens/" + produto.getPathImagem();
//                String vArquivo2 = "http://server28.integrator.com.br:12685/unikarquivos/arquivos/" + "imagens/logo-suco-bagaco.jpg";
//                dit.execute(vArquivo, vArquivo2);
//            }
//        }

        return view;
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private Mesas mesas;
        private ImageView imagem;

        public Mesas getMesas() {
            return mesas;
        }

        public void setMesas(Mesas mesas) {
            this.mesas = mesas;
        }

        public ImageView getImagem() {
            return imagem;
        }

        public void setImagem(ImageView imagem) {
            this.imagem = imagem;
        }

        /**
         * The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute()
         */
        protected Bitmap doInBackground(String... urls) {
            Bitmap ret = loadImageFromNetwork(urls[0]);
            if (ret == null) {
                File imgLocal = new File( act.getFilesDir().getPath() + "/"+ "logo-suco-bagaco.jpg");
                if (imgLocal.exists()) {
                    ret = BitmapFactory.decodeFile(act.getFilesDir().getPath() + "/"+ "logo-suco-bagaco.jpg");
                } else {
                    ret = loadImageFromNetwork(urls[1]);
                }
            }
            return ret;
        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                this.imagem.setImageBitmap(result);
                //saveImageToInternalStorage(result, mesas.getPathImagem()); //caminhoImagens+"/"+
            }
        }

        public boolean saveImageToInternalStorage(Bitmap image, String arquivoNome) {
            File imgLocal = new File(act.getFilesDir().getPath() + "/"+ arquivoNome);
            if (!imgLocal.exists()) {
                try {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                    byte[] bytes = stream.toByteArray();;
                    String nomeArquivo = act.getFilesDir().getPath() + "/"+ arquivoNome; // Environment.getExternalStorageDirectory().getCanonicalPath()

                    FileOutputStream fos = new FileOutputStream(nomeArquivo);
                    fos.write(bytes);
                    fos.close();
                } catch (Exception e) {
                    System.out.println("Erro ao gravar local: "+e.getMessage());
                    e.printStackTrace();
                }
            }
            return true;
        }


        private Bitmap loadImageFromNetwork(String url) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                return bitmap;
            } catch (Exception e) {
            }
            return null;
        }
    }

    public String getCaminhoImagens() {
        return caminhoImagens;
    }

    public void setCaminhoImagens(String caminhoImagens) {
        this.caminhoImagens = caminhoImagens;
    }
}