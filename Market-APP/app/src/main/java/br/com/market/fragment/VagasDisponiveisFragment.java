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

import java.util.List;

import br.com.market.R;
import br.com.market.activities.MainActivity;
import br.com.market.adapter.VagasAdapter;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.Funcionario;
import br.com.market.models.Vaga;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_vagas_disponiveis)
public class VagasDisponiveisFragment extends Fragment {

    private static final String TAG = "VagasDisponiveis";

    @RestService
    MarketRestService marketService;

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vagas_disponiveis, container, false);
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
       consultarListaVagasLoja(funcionarioLogado.getLoja().getCod());
       consultarListaVagasDiferenteLoja(funcionarioLogado.getLoja().getCod());

       TextView txtMaisVagasMinhaLoja = (TextView) view.findViewById(R.id.txtMaisVagasMinhaLoja);
       txtMaisVagasMinhaLoja.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               VagasDisponiveisListaFragment_ vagasFragmant = new VagasDisponiveisListaFragment_();
               vagasFragmant.setTipoPesquisa(VagasDisponiveisListaFragment.MINHA_LOJA);

               ((MainActivity)getActivity()).iniciarFragment(getActivity(), vagasFragmant);
               ((MainActivity)getActivity()).alterarTituloActivity(((MainActivity)getActivity()).
                       getSupportActionBar(), getString(R.string.label_vagas_minha_loja));
           }
       });

       ListView suaUnidade = (ListView) view.findViewById(R.id.lvMinhaLoja);
       suaUnidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

       TextView txtMaisVagasOutrasLojas = (TextView) view.findViewById(R.id.txtMaisVagasOutrasLojas);
       txtMaisVagasOutrasLojas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               VagasDisponiveisListaFragment_ vagasFragmant = new VagasDisponiveisListaFragment_();
               vagasFragmant.setTipoPesquisa(VagasDisponiveisListaFragment.OUTRAS_LOJAS);

               ((MainActivity)getActivity()).iniciarFragment(getActivity(), vagasFragmant);
               ((MainActivity)getActivity()).alterarTituloActivity(((MainActivity)getActivity()).
                       getSupportActionBar(), getString(R.string.label_vagas_outras_lojas));
           }
       });

       ListView outrasUnidade = (ListView) view.findViewById(R.id.lvOutrasLojas);
       outrasUnidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            resposta = marketService.consultaRapidaVagasPorLoja(codLoja, new Integer(3));
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
            resposta = marketService.consultaRapidaVagasDiferenteLoja(idLoja, new Integer(3));
        }catch (Exception e) {
            erroServico("Não foi possível fazer a consulta de vagas de outras loja.");
            return;
        }

        createListaVagasDiferenteLocal(resposta);
    }

    @UiThread
    void createListaVagasMeuLocal(List<Vaga> vagas) {
        Log.i(TAG, "METHOD: createNoticia");
        ListView lvSuaUnidade = (ListView) view.findViewById(R.id.lvMinhaLoja);
        lvSuaUnidade.setAdapter(new VagasAdapter(getActivity(), vagas, Boolean.FALSE));
    }

    @UiThread
    void createListaVagasDiferenteLocal(List<Vaga> vagas) {
        Log.i(TAG, "METHOD: createListaNoticiasDiferenteLocal");
        ListView lvOutrasUnidade = (ListView) view.findViewById(R.id.lvOutrasLojas);
        lvOutrasUnidade.setAdapter(new VagasAdapter(getActivity(), vagas, Boolean.TRUE));
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText( getActivity().getApplicationContext(), mensagem, duration).show();
    }
}
