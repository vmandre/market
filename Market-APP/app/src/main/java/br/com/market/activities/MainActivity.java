package br.com.market.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.market.R;
import br.com.market.fragment.HoleriteFragment_;
import br.com.market.fragment.HomeFragment_;
import br.com.market.fragment.MeusDadosFragment_;
import br.com.market.fragment.NoticiasFragment_;
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

        createActionBar();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void createActionBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
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
        } else {
            iniciarFragment(this, new VagasDisponiveisFragment_());
            alterarTituloActivity(getSupportActionBar(), getString(R.string.vagas_disponiveis));

            Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(myChildToolbar);
            createActionBar();
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
                alterarTituloActivity(getSupportActionBar(), item.getTitle().toString());
                break;
            case R.id.nav_meus_dados:
                iniciarFragment(this, new MeusDadosFragment_());
                alterarTituloActivity(getSupportActionBar(), item.getTitle().toString());
                break;
            case R.id.nav_holerite:
                iniciarFragment(this, new HoleriteFragment_());
                alterarTituloActivity(getSupportActionBar(), item.getTitle().toString());
                break;
            case R.id.nav_solicitacao_ferias:
                iniciarFragment(this, new SolicitarFeriasFragment_());
                alterarTituloActivity(getSupportActionBar(), item.getTitle().toString());
                break;
            case R.id.nav_lista_noticias:
                iniciarFragment(this, new NoticiasFragment_());
                alterarTituloActivity(getSupportActionBar(), item.getTitle().toString());
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        //return super.onOptionsItemSelected(item);
    }

    public void iniciarFragment(final FragmentActivity activity, final Fragment fragment) {
        FragmentTransaction t =  activity.getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        t.replace(R.id.fragment_principal, fragment);
        t.addToBackStack(null);
        t.commit();
    }

//    public void iniciarFragmentComVoltar(final FragmentActivity activity, final Fragment fragment) {
//        FragmentTransaction t =  activity.getSupportFragmentManager().beginTransaction();
//        t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//
//        if (t.isAddToBackStackAllowed()) {
//            Log.i(TAG, "%%%%%%%%%%%% PODE VOLTAR %%%%%%%%%%%%%%%%%%%");
//        }
//        t.replace(R.id.fragment_principal, fragment);
//        t.commit();
//    }

    public void alterarTituloActivity(ActionBar bar, String titulo) {
        bar.setDisplayHomeAsUpEnabled(false); //Set true para alterar o icone do hamburguer pela flecha
        alterarTituloBarraActivity(bar, titulo);
    }

    public void alterarTituloDetalhesActivity(ActionBar bar, String titulo) {
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);

        bar.setDisplayHomeAsUpEnabled(true);
        //bar.setDisplayShowHomeEnabled(true);
        alterarTituloBarraActivity(bar, titulo);


    }

    private void alterarTituloBarraActivity(ActionBar bar, String titulo) {
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayShowTitleEnabled(true);
        bar.setTitle(titulo);
        bar.setDisplayUseLogoEnabled(false);
    }

    private void alterarHomeActivity(ActionBar bar) {
        bar.setDisplayHomeAsUpEnabled(false); //Set true para alterar o icone do hamburguer pela flecha
        bar.setDisplayShowHomeEnabled(false);
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