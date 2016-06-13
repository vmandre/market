package br.com.market.activities;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.market.R;
import br.com.market.fragment.HoleriteFragment_;
import br.com.market.fragment.HomeFragment_;
import br.com.market.fragment.MeusDadosFragment_;
import br.com.market.fragment.SolicitarFeriasFragment_;
import br.com.market.fragment.VagasDisponiveisFragment_;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.Funcionario;

@EActivity
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @ViewById(R.id.textNomeFuncionario)
    TextView textNomeFuncionario;
    @ViewById(R.id.textMatriculaFuncionario)
    TextView textMatriculaFuncionario;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;
    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
                ParametrosAplicacao.getParametro(getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);

        if (funcionarioLogado == null) {
            //TODO NAO ENCOTROU O USUARIO, SOLICITAR LOGIN
        } else {
            //View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
            View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
            navigationView.removeHeaderView(header);
            navigationView.addHeaderView(header);

            textNomeFuncionario = (TextView) header.findViewById(R.id.textNomeFuncionario);
            textMatriculaFuncionario = (TextView) header.findViewById(R.id.textMatriculaFuncionario);
            textNomeFuncionario.setText(funcionarioLogado.getNome());
            textMatriculaFuncionario.setText("Matrícula: " + funcionarioLogado.getMatricula().toString());
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(getLogo());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "METHOD: onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "METHOD: onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "METHOD: onOptionsItemSelected");
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent it = new Intent(MainActivity.this, LoginActivity_.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.i(TAG, "METHOD: onNavigationItemSelected");
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                iniciarFragment(this, new HomeFragment_());
                alterarHomeActivity(getSupportActionBar());
                break;
            case R.id.nav_lista_vagas:
                iniciarFragment(this, new VagasDisponiveisFragment_());
                alterarTituloActivity(getSupportActionBar(), R.string.vagas_disponiveis);
                break;
            case R.id.nv_meus_dados:
                iniciarFragment(this, new MeusDadosFragment_());
                alterarTituloActivity(getSupportActionBar(), R.string.meus_dados);
                break;
            case R.id.nav_holerite:
                iniciarFragment(this, new HoleriteFragment_());
                alterarTituloActivity(getSupportActionBar(), R.string.holerite);
                break;
            case R.id.nav_solicitacao_ferias:
                iniciarFragment(this, new SolicitarFeriasFragment_());
                alterarTituloActivity(getSupportActionBar(), R.string.solicitacao_ferias);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }

    public void iniciarFragment(final FragmentActivity activity, final Fragment fragment) {
        FragmentTransaction t =  activity.getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        t.replace(R.id.fragment_principal, fragment);
        t.addToBackStack(null);
        t.commit();
    }

    public void iniciarFragmentComVoltar(final FragmentActivity activity, final Fragment fragment) {
        FragmentTransaction t =  activity.getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        if (t.isAddToBackStackAllowed()) {
            Log.i(TAG, "%%%%%%%%%%%% PODE VOLTAR %%%%%%%%%%%%%%%%%%%");
        }
        t.replace(R.id.fragment_principal, fragment);
        t.commit();
    }

    private void alterarTituloActivity(ActionBar bar, int titulo) {
        bar.setDisplayHomeAsUpEnabled(false); //Set true para alterar o icone do hamburguer pela flecha
        bar.setDisplayShowHomeEnabled(false);
        alterarTituloBarraActivity(bar, titulo);
    }

    public void alterarTituloDetalhesActivity(ActionBar bar, int titulo) {
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        alterarTituloBarraActivity(bar, titulo);
    }

    private void alterarTituloBarraActivity(ActionBar bar, int titulo) {
        bar.setDisplayShowHomeEnabled(false);
        bar.setTitle(titulo);
        bar.setDisplayUseLogoEnabled(false);
    }

    private void alterarHomeActivity(ActionBar bar) {
        bar.setDisplayHomeAsUpEnabled(false); //Set true para alterar o icone do hamburguer pela flecha
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayUseLogoEnabled(true);
        bar.setIcon(getLogo());
        bar.setDisplayShowTitleEnabled(false);
    }

    private Drawable getLogo() {
        Drawable dr = getResources().getDrawable(R.drawable.logo_transparente);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        return new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 200, 50, true));
    }
}