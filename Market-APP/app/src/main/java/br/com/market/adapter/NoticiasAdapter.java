package br.com.market.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.market.R;
import br.com.market.models.Noticia;

/**
 * Adapter responsavel por exibir a lista de notícias - tela inicial
 */
public class NoticiasAdapter extends BaseAdapter {

    private Context context;
    private List<Noticia> noticias;

    public NoticiasAdapter(final Context context, final List<Noticia> noticias) {
        this.context = context;
        this.noticias = noticias;
    }

    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int position) {
        return noticias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Noticia noticia = (Noticia) getItem(position);

        ViewHolder holder = null;
        View linha = null;

        if (convertView == null) {
            linha = LayoutInflater.from(context).inflate(R.layout.list_view_noticias, parent, false);
            holder = new ViewHolder(linha);
            linha.setTag(holder);
        } else {
            linha = convertView;
            holder = (ViewHolder) linha.getTag();
        }

        holder.txtNoticiaTitulo.setText(noticia.getTitulo());
        //holder.txtNoticiaCategoria.setText(Noticia.Categoria.INFORME_GERAL.getValue());
        holder.txtNoticiaCategoria.setText(noticia.getCategoria().getValue());

        return linha;
    }

    class ViewHolder {
        TextView txtNoticiaTitulo;
        TextView txtNoticiaCategoria;
        ViewHolder(View view) {
            this.txtNoticiaTitulo = (TextView) view.findViewById(R.id.txtNoticiaTitulo);
            this.txtNoticiaCategoria = (TextView) view.findViewById(R.id.txtNoticiaCategoria);
        }
    }
}
