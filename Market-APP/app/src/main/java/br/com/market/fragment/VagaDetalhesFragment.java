package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.json.JSONStringer;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.market.R;
import br.com.market.activities.MainActivity;
import br.com.market.adapter.VagasAdapter;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.AplicacaoVaga;
import br.com.market.models.ErroMarket;
import br.com.market.models.Funcionario;
import br.com.market.models.Vaga;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_vaga_detalhes)
public class VagaDetalhesFragment extends Fragment {

    private static final String TAG = "VagaDetalhesFragment";

    @RestService
    MarketRestService marketService;
    @ViewById(R.id.txtVagaCargo)
    TextView txtVagaCargo;
    @ViewById(R.id.txtVagaLoja)
    TextView txtVagaLoja;
    @ViewById(R.id.txtVagaDataInclusao)
    TextView txtVagaDataInclusao;
    @ViewById(R.id.txtVagaDataExpiracao)
    TextView txtVagaDataExpiracao;
    @ViewById(R.id.txtVagaRemuneracao)
    TextView txtVagaRemuneracao;
    @ViewById(R.id.txtVagaRequisito)
    TextView txtVagaRequisito;
    @ViewById(R.id.btnAplicar)
    Button btnAplicar;

    private View view;
    private Vaga vaga;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vaga_detalhes, container, false);
        //Inicia os campos necessários caso não tenha sido injetado.
        initFields();
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

   private void initScreen(final View view) {
       Log.i(TAG, "METHOD: initScreen");
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

       txtVagaCargo.setText(vaga.getCargo().getDescricao());
       txtVagaLoja.setText(vaga.getLoja().getNome());
       txtVagaDataInclusao.setText(sdf.format(vaga.getDataAbertura()));
       txtVagaRequisito.setText(vaga.getDescricao());

       btnAplicar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
                       ParametrosAplicacao.getParametro(getActivity().getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);

               AplicacaoVaga aplicacaoVaga = new AplicacaoVaga();
               aplicacaoVaga.setVaga(vaga);
               aplicacaoVaga.setFuncionario(funcionarioLogado);

               consultarListaVagasLoja(aplicacaoVaga);
           }
       });
    }

    private void initFields() {
        if (txtVagaCargo == null) {
            txtVagaCargo = (TextView) view.findViewById(R.id.txtVagaCargo);
        }
        if (txtVagaLoja == null) {
            txtVagaLoja = (TextView) view.findViewById(R.id.txtVagaLoja);
        }
        if (txtVagaDataInclusao == null) {
            txtVagaDataInclusao = (TextView) view.findViewById(R.id.txtVagaDataInclusao);
        }
        if (txtVagaDataExpiracao == null) {
            txtVagaDataExpiracao = (TextView) view.findViewById(R.id.txtVagaDataExpiracao);
        }
        if (txtVagaRemuneracao == null) {
            txtVagaRemuneracao = (TextView) view.findViewById(R.id.txtVagaRemuneracao);
        }
        if (txtVagaRemuneracao == null) {
            txtVagaRemuneracao = (TextView) view.findViewById(R.id.txtVagaRemuneracao);
        }
        if (txtVagaRequisito == null) {
            txtVagaRequisito = (TextView) view.findViewById(R.id.txtVagaRequisito);
        }
        if (btnAplicar == null) {
            btnAplicar = (Button) view.findViewById(R.id.btnAplicar);
        }
    }

    @Background
    public void consultarListaVagasLoja(AplicacaoVaga aplicacaoVaga) {
        AplicacaoVaga resposta = null;

        try {
            resposta = marketService.aplicarVaga(aplicacaoVaga);
        }catch (HttpClientErrorException e) {
            if (HttpStatus.BAD_REQUEST.equals(e.getStatusCode())) {
                ErroMarket erroMarket = null;
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = e.getResponseBodyAsString();
                try {
                    erroMarket = mapper.readValue(jsonString, ErroMarket.class);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    erroServico("Erro na aplicação da vaga, favor tentar mais tarde.");
                }

                if (erroMarket != null) {
                    erroServico(erroMarket.getMensagem());
                }
            } else {
                erroServico("Erro na aplicação da vaga, favor tentar mais tarde.");
            }

            return;
        } catch (Exception e) {
            erroServico("Erro na aplicação da vaga, favor tentar mais tarde.");
            return;
        }

        verificarResposta(resposta);
    }

    @UiThread
    void verificarResposta(AplicacaoVaga aplicacaoVaga) {
        Log.i(TAG, "METHOD: verificarResposta");
        exibirToast("Aplicação com sucesso", Toast.LENGTH_LONG);
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText( getActivity().getApplicationContext(), mensagem, duration).show();
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
