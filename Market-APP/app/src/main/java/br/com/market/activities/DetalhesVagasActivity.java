package br.com.market.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Timer;
import java.util.TimerTask;

import br.com.market.R;
import br.com.market.models.Vaga;

@EActivity
public class DetalhesVagasActivity extends Activity {

    @ViewById(R.id.tv_id_cargo)
    TextView textCargo;

    @ViewById(R.id.tv_id_unidade)
    TextView textUnidade;

    @ViewById(R.id.tv_id_data_inclusao)
    TextView textDataInclusao;

    @ViewById(R.id.tv_id_data_expiracao)
    TextView textDataExpiracao;

    @ViewById(R.id.tv_id_remuneracao)
    TextView textRemuneracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_vaga);

        Intent i = getIntent();
        Vaga vaga = (Vaga) i.getSerializableExtra("VAGA");
        textCargo.setText(vaga.getCargo().getDescricao());
        textUnidade.setText(vaga.getLoja().getNome());
        //textDataInclusao.setText(vaga.getDataAbertura().getDate().toString());
        //textDataExpiracao.setText(vaga.getDataAbertura().getDate());
        //textRemuneracao.setText(vaga.getDataAbertura().getDate());

    }

}
