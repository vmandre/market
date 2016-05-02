package br.com.market.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import br.com.market.services.MarketRestService;
import br.com.market.models.Funcionario;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    @RestService
    MarketRestService marketService;

    private static Context parent;

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

    private void exibirToast2(CharSequence mensagem, int duration) {
        Toast.makeText( parent , mensagem, duration).show();
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

        parent = this.getBaseContext();
        realizarLogin(funcionario);
    };

    @Background
    public void realizarLogin(Funcionario funcionario) {
        Funcionario resposta = marketService.login(funcionario);

        if (resposta == null) {
            erroLogin();
        } else {
            //TODO Adicionar usuario logado na sessao!
            Intent it = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(it);
        }
    }


    @UiThread
        // will be called on the main thread
    void erroLogin() {
        exibirToast("Usuário/Senha incorreto!", Toast.LENGTH_LONG);
    }

}
