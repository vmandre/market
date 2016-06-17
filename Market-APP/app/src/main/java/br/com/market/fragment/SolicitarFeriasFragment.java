package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.market.R;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.listeners.DataPickerListener;
import br.com.market.models.AplicacaoVaga;
import br.com.market.models.Ferias;
import br.com.market.models.Funcionario;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_solicitar_ferias)
public class SolicitarFeriasFragment extends Fragment {

    private static final String TAG = "SolicitarFeriasFragment";

    @RestService
    MarketRestService marketService;
    @ViewById(R.id.btnInicio)
    public Button dataInicio;
    @ViewById(R.id.btnFim)
    public Button dataFim;
    @ViewById(R.id.edtObs)
    public EditText edtObs;
    @ViewById(R.id.btnSolicitar)
    public Button btnSolicitar;

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_solicitar_ferias, container, false);
        //Inicia os campos necessários caso não tenha sido injetado.
        initFields();
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

    private void initScreen(final View view) {
        Log.i(TAG, "METHOD: initScreen");

        dataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DataPickerListener();
                ((DataPickerListener)newFragment).setButtonRetorno(dataInicio);
                newFragment.show(getFragmentManager(), "dataInicioPicker");
            }
        });

        dataFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DataPickerListener();
                ((DataPickerListener)newFragment).setButtonRetorno(dataFim);
                newFragment.show(getFragmentManager(), "dataFimPicker");
            }
        });

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
                        ParametrosAplicacao.getParametro(getActivity().getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);

                SimpleDateFormat sdf = new SimpleDateFormat(Utils.DATA_FORMATO);

                Ferias ferias = new Ferias();
                ferias.setDataSolicitacao(new Date());
                try {
                    ferias.setDataInicio(sdf.parse(dataInicio.getText().toString()));
                    ferias.setDataFim(sdf.parse(dataFim.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ferias.setObservacao(edtObs.getText().toString());
                ferias.setFuncionario(funcionarioLogado);
                ferias.setStatus(Ferias.Status.AGUARDANDO_APROVACAO);

                solicitaFerias(ferias);
            }
        });
    }

    private void initFields() {
        if (dataInicio == null) {
            dataInicio = (Button) view.findViewById(R.id.btnInicio);
        }

        if (dataFim == null) {
            dataFim = (Button) view.findViewById(R.id.btnFim);
        }

        if (edtObs == null) {
            edtObs = (EditText) view.findViewById(R.id.edtObs);
        }

        if (btnSolicitar == null) {
            btnSolicitar = (Button) view.findViewById(R.id.btnSolicitar);
        }
    }

    @Background
    public void solicitaFerias(Ferias ferias) {
        Ferias resposta = null;

        try {
            resposta = marketService.solicitarFerias(ferias);
        }catch (HttpClientErrorException e) {
            erroServico(Utils.validaErroServicoHttpClient(e,
                    "Erro na solicitação de férias, favor tentar mais tarde."));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            erroServico("Erro na solicitação de férias, favor tentar mais tarde.");
            return;
        }

        verificarResposta(resposta);
    }

    @UiThread
    void verificarResposta(Ferias ferias) {
        Log.i(TAG, "METHOD: verificarResposta");
        exibirToast("Solicitação efetuada com sucesso", Toast.LENGTH_LONG);
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText(getActivity().getApplicationContext(), mensagem, duration).show();
    }
}
