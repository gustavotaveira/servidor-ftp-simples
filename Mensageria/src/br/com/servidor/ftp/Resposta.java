package br.com.servidor.ftp;

import java.io.Serializable;

public class Resposta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ARQUIVO_EXISTE = 1;
	public static final int ARQUIVO_NAO_ENCONTRADO = 2;
	public static final int CONTEUDO_DISPONIVEL = 3;
	public static final int FIM_DO_ARQUIVO = 4;

	private int codigoResposta;
	private String conteudoResposta;

	public int getCodigoResposta() {
		return codigoResposta;
	}

	public void setCodigoResposta(int codigoResposta) {
		this.codigoResposta = codigoResposta;
	}

	public String getConteudoResposta() {
		return conteudoResposta;
	}

	public void setConteudoResposta(String conteudoResposta) {
		this.conteudoResposta = conteudoResposta;
	}
}
