package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
import br.com.market.activities.MainActivity;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.Funcionario;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_meus_dados)
public class MeusDadosFragment extends Fragment {

    private static final String TAG = "DadosFuncionario";

    @RestService
    MarketRestService marketService;
    @ViewById(R.id.txtNome)
    public TextView txtNome;
    @ViewById(R.id.txtSobrenome)
    public TextView txtSobrenome;
    @ViewById(R.id.txtMatricula)
    public TextView txtMatricula;
    @ViewById(R.id.txtLoja)
    public TextView txtLoja;
    @ViewById(R.id.txtCargo)
    public TextView txtCargo;
    @ViewById(R.id.txtCPF)
    public TextView txtCPF;
    @ViewById(R.id.txtDtNascimento)
    public TextView txtDtNascimento;
    @ViewById(R.id.txtcelular)
    public TextView txtcelular;
    @ViewById(R.id.txtEmail)
    public TextView txtEmail;

    private View view;

    public MeusDadosFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_meus_dados, container, false);
        //Inicia os campos necessários caso não tenha sido injetado.
        initFields();
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

    private void initScreen(final View view) {
        Log.i(TAG, "METHOD: initScreen");

        Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
                ParametrosAplicacao.getParametro(getActivity().getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int posicao = funcionarioLogado.getNome().indexOf(" ");

        if (posicao > 0) {
            txtNome.setText(funcionarioLogado.getNome().substring(0, posicao));
            txtSobrenome.setText(funcionarioLogado.getNome().substring(posicao + 1));
        } else {
            txtNome.setText(funcionarioLogado.getNome());
        }

        txtMatricula.setText(funcionarioLogado.getMatricula().toString());
        txtLoja.setText(funcionarioLogado.getLoja().getNome());
        txtCargo.setText(funcionarioLogado.getCargo().getDescricao());
        txtCPF.setText(funcionarioLogado.getCpf().toString());
        txtDtNascimento.setText(sdf.format(funcionarioLogado.getDataNascimento()));
        txtcelular.setText(funcionarioLogado.getCelular());
        txtEmail.setText(funcionarioLogado.getEmail());
    }

    private void initFields() {
        if (txtNome == null) {
            txtNome = (TextView) view.findViewById(R.id.txtNome);
        }
        if (txtSobrenome == null) {
            txtSobrenome = (TextView) view.findViewById(R.id.txtSobrenome);
        }
        if (txtMatricula == null) {
            txtMatricula = (TextView) view.findViewById(R.id.txtMatricula);
        }
        if (txtLoja == null) {
            txtLoja = (TextView) view.findViewById(R.id.txtLoja);
        }
        if (txtCargo == null) {
            txtCargo = (TextView) view.findViewById(R.id.txtCargo);
        }
        if (txtCPF == null) {
            txtCPF = (TextView) view.findViewById(R.id.txtCPF);
        }
        if (txtDtNascimento == null) {
            txtDtNascimento = (TextView) view.findViewById(R.id.txtDtNascimento);
        }
        if (txtcelular == null) {
            txtcelular = (TextView) view.findViewById(R.id.txtcelular);
        }
        if (txtEmail == null) {
            txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        }
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText(getActivity().getApplicationContext(), mensagem, duration).show();
    }
}
