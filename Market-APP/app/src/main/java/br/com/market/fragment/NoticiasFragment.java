package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

@EFragment(R.layout.fragment_noticias)
public class NoticiasFragment extends Fragment {

    private static final String TAG = "NoticiasFragment";

    @RestService
    MarketRestService marketService;

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_noticias, container, false);
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
       consultarListaNoticiasLoja(funcionarioLogado.getLoja().getCod());
       consultarListaNoticiasDiferenteLoja(funcionarioLogado.getLoja().getCod());

       TextView txtMaisNoticiasMinhaLoja = (TextView) view.findViewById(R.id.txtMaisNoticiasMinhaLoja);
       txtMaisNoticiasMinhaLoja.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               NoticiasListaFragment_ noticiasFragmant = new NoticiasListaFragment_();
               noticiasFragmant.setTipoPesquisa(NoticiasListaFragment.MINHA_LOJA);

               ((MainActivity)getActivity()).iniciarFragment(getActivity(), noticiasFragmant);
               ((MainActivity)getActivity()).alterarTituloActivity(((MainActivity)getActivity()).
                       getSupportActionBar(), getString(R.string.label_noticias_minha_loja));
           }
       });

       ListView suaLoja = (ListView) view.findViewById(R.id.lvNoticiasMinhaLoja);
       suaLoja.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

       TextView txtMaisNoticiasOutrasLojas = (TextView) view.findViewById(R.id.txtMaisNoticiasOutrasLojas);
       txtMaisNoticiasOutrasLojas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               NoticiasListaFragment_ noticiasFragmant = new NoticiasListaFragment_();
               noticiasFragmant.setTipoPesquisa(NoticiasListaFragment.OUTRAS_LOJAS);

               ((MainActivity)getActivity()).iniciarFragment(getActivity(), noticiasFragmant);
               ((MainActivity)getActivity()).alterarTituloActivity(((MainActivity)getActivity()).
                       getSupportActionBar(), getString(R.string.label_noticias_outras_lojas));
           }
       });

       ListView outrasUnidade = (ListView) view.findViewById(R.id.lvNoticiasOutrasLojas);
       outrasUnidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        Object[]  resposta = null;

        try {
            resposta = marketService.consultaRapidaNoticiasPorLoja(codLoja, new Integer(3));
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta de notícias.");
            return;
        }

        List<Noticia> noticias = respostaParaNoticias(resposta);

        createListaNoticiasMeuLocal(noticias);
    }

    @Background
    public void consultarListaNoticiasDiferenteLoja(Long idLoja) {
        Object[] resposta = null;

        try {
            resposta = marketService.consultaRapidaNoticiasDiferenteLoja(idLoja, new Integer(3));
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta de notícia de outras lojas.");
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
        Log.i(TAG, "METHOD: createNoticia");
        ListView lvSuaUnidade = (ListView) view.findViewById(R.id.lvNoticiasMinhaLoja);
        lvSuaUnidade.setAdapter(new NoticiasAdapter(getActivity(), noticias));
    }

    @UiThread
    void createListaNoticiasDiferenteLocal(List<Noticia> noticias) {
        Log.i(TAG, "METHOD: createListaNoticiasDiferenteLocal");
        ListView lvOutrasUnidade = (ListView) view.findViewById(R.id.lvNoticiasOutrasLojas);
        lvOutrasUnidade.setAdapter(new NoticiasAdapter(getActivity(), noticias));
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText( getActivity().getApplicationContext(), mensagem, duration).show();
    }
}
