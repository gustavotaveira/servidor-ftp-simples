package br.com.servidor.ftp;

import java.io.Serializable;

public class Requisicao implements Serializable {

	public static final int REQUSICAO_NOMEARQUIVO = 0;
	public static final int REQUISICAO_CONTEUDOARQUIVO = 1;

	private int tipoMensagem;
	private String conteudoMensagem;

	public int getTipoMensagem() {
		return tipoMensagem;
	}

	public void setTipoMensagem(int tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}


	public String getConteudoMensagem() {
		return conteudoMensagem;
	}

	public void setConteudoMensagem(String conteudoMensagem) {
		this.conteudoMensagem = conteudoMensagem;
	}
}
