package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import br.com.market.R;
import br.com.market.activities.MainActivity;
import br.com.market.adapter.NoticiasAdapter;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.Funcionario;
import br.com.market.models.Noticia;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_noticias_lista)
public class NoticiasListaFragment extends Fragment {

    private static final String TAG = "NoticiasListaFragment";
    public static final Integer MINHA_LOJA = new Integer(1);
    public static final Integer OUTRAS_LOJAS = new Integer(2);

    @RestService
    MarketRestService marketService;

    private View view;
    private Integer tipoPesquisa;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_noticias_lista, container, false);
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

   private void initScreen(final View view) {
       Log.i(TAG, "METHOD: initScreen");

       Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
               ParametrosAplicacao.getParametro(getActivity().getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);

       //Recupera as listas de noticias do serviço e popula as list views.
       if (MINHA_LOJA.equals(tipoPesquisa)) {
           consultarListaNoticiasLoja(funcionarioLogado.getLoja().getCod());
       } else if (OUTRAS_LOJAS.equals(tipoPesquisa)) {
           consultarListaNoticiasDiferenteLoja(funcionarioLogado.getLoja().getCod());
       }

       ListView noticias = (ListView) view.findViewById(R.id.lvNoticias);
       noticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Noticia noticia = (Noticia) parent.getAdapter().getItem(position);

               NoticiaFragment_ noticiaFragment = new NoticiaFragment_();
               noticiaFragment.setCodNoticia(noticia.getCod());

               ((MainActivity)getActivity()).iniciarFragment(getActivity(), noticiaFragment);
               ((MainActivity)getActivity()).alterarTituloDetalhesActivity(((MainActivity)getActivity()).
                       getSupportActionBar(), getString(R.string.noticia));
           }
       });
    }

    @Background
    public void consultarListaNoticiasLoja(Long codLoja) {
        Object[] resposta = null;

        try {
            resposta = marketService.consultarNoticiasPorLoja(codLoja);
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta de noticia.");
            return;
        }

        List<Noticia> noticias = respostaParaNoticias(resposta);

        createListaNoticiasMeuLocal(noticias);
    }

    @Background
    public void consultarListaNoticiasDiferenteLoja(Long idLoja) {
        Object[] resposta = null;

        try {
            resposta = marketService.consultarNoticiasDiferenteLoja(idLoja);
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta de noticias de outras loja.");
            return;
        }

        List<Noticia> noticias = respostaParaNoticias(resposta);

        createListaNoticiasDiferenteLocal(noticias);
    }

    private List<Noticia> respostaParaNoticias(Object[] resposta) {
        List<Noticia> noticias = new ArrayList<Noticia>();
        Noticia noticia = null;
        if (resposta != null) {
            for (int i = 0; i < resposta.length; i++) {
                noticia = new Noticia();
                noticia.setCod(new Long((Integer) ((ArrayList) resposta[i]).get(0)));
                noticia.setTitulo((String) ((ArrayList) resposta[i]).get(1));
                noticia.setCategoria(Noticia.Categoria.valueOf((String) ((ArrayList) resposta[i]).get(2)));

                noticias.add(noticia);
            }
        }

        return noticias;
    }

    @UiThread
    void createListaNoticiasMeuLocal(List<Noticia> noticias) {
        Log.i(TAG, "METHOD: createListaNoticiasMeuLocal");
        ListView lvNoticias = (ListView) view.findViewById(R.id.lvNoticias);
        lvNoticias.setAdapter(new NoticiasAdapter(getActivity(), noticias));
    }

    @UiThread
    void createListaNoticiasDiferenteLocal(List<Noticia> noticias) {
        Log.i(TAG, "METHOD: createListaNoticiasDiferenteLocal");
        ListView lvNoticias = (ListView) view.findViewById(R.id.lvNoticias);
        lvNoticias.setAdapter(new NoticiasAdapter(getActivity(), noticias));
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText( getActivity().getApplicationContext(), mensagem, duration).show();
    }

    public void setTipoPesquisa(Integer tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }
}
