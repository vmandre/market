package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.text.SimpleDateFormat;

import br.com.market.R;
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
