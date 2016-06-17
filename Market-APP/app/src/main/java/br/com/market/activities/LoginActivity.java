package br.com.market.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import br.com.market.R;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.Funcionario;
import br.com.market.services.MarketRestService;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    @RestService
    MarketRestService marketService;

    @ViewById(R.id.edtMatricula)
    public EditText edtMatricula;
    @ViewById(R.id.edtSenha)
    public EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * TODO - Adicionar na infra ou implementar um tipo de msg melhor.
     * @param mensagem
     */
    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText( getApplicationContext(), mensagem, duration).show();
    }

    @Click(R.id.btn_Login)
    public void login() {
        String matricula = edtMatricula.getText().toString();
        if (matricula == null || matricula.isEmpty()) {
            //TODO ERRO NO CAMPO
            exibirToast("Favor informar a matricula" , Toast.LENGTH_LONG);
            return;
        }

        String senha = edtSenha.getText().toString();
        if (senha == null || senha.isEmpty()) {
            //TODO ERRO NO CAMPO
            exibirToast("Favor informar a senha", Toast.LENGTH_LONG);
            return;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setMatricula(new Long(matricula));
        funcionario.setSenha(senha);

        realizarLogin(funcionario);
    };

    @Background
    public void realizarLogin(Funcionario funcionario) {
        Funcionario resposta = null;

        if (!Utils.isOnline((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))){
            erroLogin("Verifique sua conexão de internet.");
            return;
        }

        try {
            resposta = marketService.login(funcionario);
        }catch (Exception e) {
            erroLogin("Não foi possivel fazer o login, verifique sua conexão de internet.");
            return;
        }

        if (resposta == null) {
            erroLogin("Usuário/Senha incorreto!");
        } else {
            //TODO Adicionar usuario logado na sessao!
            ParametrosAplicacao.salvarParametro(getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO, Utils.objectToJson(resposta));

            Intent it = new Intent(LoginActivity.this, MainActivity_.class);
            startActivity(it);
        }
    }

    @UiThread
        // will be called on the main thread
    void erroLogin(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

}
