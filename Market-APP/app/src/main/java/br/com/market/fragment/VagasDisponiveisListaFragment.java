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

import java.util.List;

import br.com.market.R;
import br.com.market.activities.MainActivity;
import br.com.market.adapter.VagasAdapter;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.Funcionario;
import br.com.market.models.Vaga;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_vagas_disponiveis_lista)
public class VagasDisponiveisListaFragment extends Fragment {

    private static final String TAG = "VagasDisponiveisLista";
    public static final Integer MINHA_LOJA = new Integer(1);
    public static final Integer OUTRAS_LOJAS = new Integer(2);

    @RestService
    MarketRestService marketService;

    private View view;
    private Integer tipoPesquisa;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vagas_disponiveis_lista, container, false);
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

   private void initScreen(final View view) {
       Log.i(TAG, "METHOD: initScreen");

       Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
               ParametrosAplicacao.getParametro(getActivity().getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);

       //Recupera as listas de vagas do serviço e popula as list views.
       if (MINHA_LOJA.equals(tipoPesquisa)) {
           consultarListaVagasLoja(funcionarioLogado.getLoja().getCod());
       } else if (OUTRAS_LOJAS.equals(tipoPesquisa)) {
           consultarListaVagasDiferenteLoja(funcionarioLogado.getLoja().getCod());
       }

       ListView vagas = (ListView) view.findViewById(R.id.lvVagas);
       vagas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Vaga vaga = (Vaga) parent.getAdapter().getItem(position);

               VagaDetalhesFragment_ detalheFragmant = new VagaDetalhesFragment_();
               ((MainActivity)getActivity()).iniciarFragment(getActivity(), detalheFragmant);
               ((MainActivity)getActivity()).alterarTituloDetalhesActivity(((MainActivity)getActivity()).
                       getSupportActionBar(), getString(R.string.detalhes_vaga));

               detalheFragmant.setVaga(vaga);
           }
       });
    }

    @Background
    public void consultarListaVagasLoja(Long codLoja) {
        List<Vaga> resposta = null;

        try {
            resposta = marketService.consultarVagasPorLoja(codLoja);
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta de vagas.");
            return;
        }

        createListaVagasMeuLocal(resposta);
    }

    @Background
    public void consultarListaVagasDiferenteLoja(Long idLoja) {
        List<Vaga> resposta = null;

        try {
            resposta = marketService.consultarVagasDiferenteLoja(idLoja);
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta de vagas de outras loja.");
            return;
        }

        createListaVagasDiferenteLocal(resposta);
    }

    @UiThread
    void createListaVagasMeuLocal(List<Vaga> vagas) {
        Log.i(TAG, "METHOD: createNoticia");
        ListView lvVagas = (ListView) view.findViewById(R.id.lvVagas);
        lvVagas.setAdapter(new VagasAdapter(getActivity(), vagas, Boolean.FALSE));
    }

    @UiThread
    void createListaVagasDiferenteLocal(List<Vaga> vagas) {
        Log.i(TAG, "METHOD: createListaNoticiasDiferenteLocal");
        ListView lvVagas = (ListView) view.findViewById(R.id.lvVagas);
        lvVagas.setAdapter(new VagasAdapter(getActivity(), vagas, Boolean.TRUE));
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
