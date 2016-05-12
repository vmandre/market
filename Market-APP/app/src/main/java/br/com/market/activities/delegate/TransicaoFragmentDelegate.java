package br.com.market.activities.delegate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.io.Serializable;

import br.com.market.R;
import br.com.market.fragment.VagasDisponiveisFragment;

/**
 * Interface responsavel pela transicao de framentos da Activity Main.
 * @author TIVIT
 * @since 28/04/2016.
 */
public interface TransicaoFragmentDelegate {

    void alteraFragment(final TransicaoFragment transicao);

    enum TransicaoFragment implements Serializable {
        VAGAS_DISPONIVEIS {
            public void executa(final FragmentActivity activity) {
                iniciaTrafment(activity, new VagasDisponiveisFragment());
            }
        };
        /*NOVO_INCIDENTE {
            public void executa(final FragmentActivity activity) {
                iniciaTrafment(activity, new NovoIncidenteFragment());
            }
        },
        MINHAS_SOLICITACOES {
            public void executa(final FragmentActivity activity) {
                iniciaTrafment(activity, new MinhasSolicitacoesFragment());
            }
        },
        SOLICITACAO {
            public void executa(final FragmentActivity activity) {
                iniciaTrafment(activity, new SolicitacaoFragment());
            }
        };*/

        public abstract void executa(final FragmentActivity activity);
        void iniciaTrafment(final FragmentActivity activity, final Fragment fragment) {
            FragmentTransaction t =  activity.getSupportFragmentManager().beginTransaction();
            t.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            t.replace(R.id.fragment_principal, fragment);
            t.commit();
        }
    }
}
