package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.text.SimpleDateFormat;

import br.com.market.R;
import br.com.market.listeners.DataHoleriteListener;
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

    private View view;

    public SolicitarFeriasFragment() {
    }

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

//        Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
//                ParametrosAplicacao.getParametro(getActivity().getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");

        //dataHolerite.setText(sdf.format(new Date()));
    }

    private void initFields() {
        if (dataInicio == null) {
            dataInicio = (Button) view.findViewById(R.id.btnInicio);
        }
        dataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DataHoleriteListener();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        if (dataFim == null) {
            dataFim = (Button) view.findViewById(R.id.btnFim);
        }
        dataFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DataHoleriteListener();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText(getActivity().getApplicationContext(), mensagem, duration).show();
    }
}
