package br.com.market.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.market.R;
import br.com.market.models.Vaga;

/**
 * Adapter responsavel por exibir a lista vaga - tela inicial
 */
public class VagasAdapter extends BaseAdapter {

    private Context context;
    private List<Vaga> vagas;
    private Boolean exibeLocalizacao;

    public VagasAdapter(final Context context, final List<Vaga> vagas, Boolean exibeLocalizacao) {
        this.context = context;
        this.vagas = vagas;
        this.exibeLocalizacao = exibeLocalizacao;
    }

    @Override
    public int getCount() {
        return vagas.size();
    }

    @Override
    public Object getItem(int position) {
        return vagas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Vaga vaga = (Vaga) getItem(position);

        ViewHolder holder = null;
        View linha = null;

        if (convertView == null) {
            linha = LayoutInflater.from(context).inflate(R.layout.list_view_vagas, parent, false);
            holder = new ViewHolder(linha);
            linha.setTag(holder);
        } else {
            linha = convertView;
            holder = (ViewHolder) linha.getTag();
        }

        holder.tvVaga.setText(vaga.getCargo().getDescricao());
        if (exibeLocalizacao.booleanValue()) {
            holder.tvLocalizacao.setText(vaga.getLoja().getNome());
        }

        return linha;
    }

    class ViewHolder {
        TextView tvVaga;
        TextView tvLocalizacao;
        ViewHolder(View view) {
            this.tvVaga = (TextView) view.findViewById(R.id.tv_vaga);
            this.tvLocalizacao = (TextView) view.findViewById(R.id.tv_localizacao);
        }
    }
}
