package br.com.market.models;

import java.util.Date;

public class Noticia extends AbstractModel {

    private String titulo;
    private String texto;
    private Date dataInicio;
    private Date dataFim;
    private Loja loja;
    private Categoria categoria;

    public static enum Categoria {
        INFORME_LOCAL("Informativo local"),
        INFORME_GERAL("Informe geral"),
        DICA("Dica"),
        ACAO("Ação"),
        NOVIDADES("Novidades");

        private String value;

        private Categoria(String value) {
            this.setValue(value);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
