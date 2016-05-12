package br.com.market.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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
import br.com.market.fragment.VagasDisponiveisFragment;
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

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lista_vagas) {
            iniciaTrafment(this, new VagasDisponiveisFragment_());

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void iniciaTrafment(final FragmentActivity activity, final Fragment fragment) {
        FragmentTransaction t =  activity.getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        t.replace(R.id.fragment_principal, fragment);
        t.commit();
    }

}
