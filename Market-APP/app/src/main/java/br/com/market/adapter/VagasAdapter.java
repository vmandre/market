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
 * @author TIVIT
 * @since 29/04/2016.
 */
public class VagasAdapter extends BaseAdapter {

    private Context context;
    private List<Vaga> vagas;

    public VagasAdapter(final Context context, final List<Vaga> vagas) {
        this.context = context;
        this.vagas = vagas;
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

        return linha;
    }

    class ViewHolder {
        TextView tvVaga;

        ViewHolder(View view) {
            this.tvVaga = (TextView) view.findViewById(R.id.tv_vaga);
        }
    }
}
