package br.com.market.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import br.com.market.R;
import br.com.market.models.Noticia;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_noticia)
public class NoticiaFragment extends Fragment {

    private static final String TAG = "NoticiaFragment";

    @RestService
    MarketRestService marketService;
    @ViewById(R.id.txtNoticiaDetalheTitulo)
    TextView txtNoticiaDetalheTitulo;
    @ViewById(R.id.txtNoticiaTexto)
    TextView txtNoticiaTexto;

    private View view;
    private Long codNoticia;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_noticia, container, false);
        //Inicia os campos necessários caso não tenha sido injetado.
        initFields();
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

   private void initScreen(final View view) {
       Log.i(TAG, "METHOD: initScreen");
       consultarNoticia(codNoticia);
    }

    private void initFields() {
        if (txtNoticiaDetalheTitulo == null) {
            txtNoticiaDetalheTitulo = (TextView) view.findViewById(R.id.txtNoticiaDetalheTitulo);
        }
        if (txtNoticiaTexto == null) {
            txtNoticiaTexto = (TextView) view.findViewById(R.id.txtNoticiaTexto);
        }
    }

    @Background
    public void consultarNoticia(Long codNoticia) {
        Noticia resposta = null;

        try {
            resposta = marketService.consultarNoticia(codNoticia);
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta da noticias.");
            return;
        }

        createNoticia(resposta);
    }

    @UiThread
    void createNoticia(Noticia noticia) {
        Log.i(TAG, "METHOD: createNoticia");
        txtNoticiaDetalheTitulo.setText(noticia.getTitulo());
        txtNoticiaDetalheTitulo.setTypeface(null, Typeface.BOLD);
        txtNoticiaTexto.setText(Html.fromHtml(noticia.getTexto()));
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText( getActivity().getApplicationContext(), mensagem, duration).show();
    }

    public void setCodNoticia(Long codNoticia) {
        this.codNoticia = codNoticia;
    }
}
