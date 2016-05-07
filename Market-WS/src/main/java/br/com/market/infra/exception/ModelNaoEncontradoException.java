package br.com.market.infra.exception;

public class ModelNaoEncontradoException extends Exception {
	
	/**@serialField */
	private static final long serialVersionUID = 33744495595598962L;
	
	private Integer codigo;
	private String mensagem;

	public ModelNaoEncontradoException (Integer codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


}
