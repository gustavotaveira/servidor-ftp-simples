package com.servidor.ftp.servidorftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.servidor.ftp.Requisicao;
import br.com.servidor.ftp.Resposta;

@SpringBootApplication
public class ServidorFtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorFtpApplication.class, args);
		ServerSocket servidorSocket;
		Socket cliente;
		Requisicao requisicao;
		try {
			servidorSocket = new ServerSocket(40000);
			System.out.println("Servidor iniciado na porta 40000");
			
			while(true) {
				cliente = servidorSocket.accept();
				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
				ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
				
				requisicao = (Requisicao) entrada.readObject();
				
				if(requisicao.getTipoMensagem() == Requisicao.REQUSICAO_NOMEARQUIVO) {
					File arquivo = new File(requisicao.getConteudoMensagem());
					Resposta resposta = new Resposta();
					if(arquivo.exists()) {
						resposta.setCodigoResposta(Resposta.ARQUIVO_EXISTE);
						saida.writeObject(resposta);
						
						FileReader leitor = new FileReader(arquivo);
						BufferedReader executaLeitura = new BufferedReader(leitor);
						String linha = null;
						do {
							requisicao =(Requisicao) entrada.readObject(); // aguardo o pedido do cliente
							if(requisicao.getTipoMensagem() == Requisicao.REQUISICAO_CONTEUDOARQUIVO) {
								linha = executaLeitura.readLine();
								resposta = new Resposta();
								if(linha != null) {
									resposta.setCodigoResposta(Resposta.CONTEUDO_DISPONIVEL);								
									resposta.setConteudoResposta(linha);
								}else {
									resposta.setCodigoResposta(Resposta.FIM_DO_ARQUIVO);
								}
								saida.writeObject(resposta);
							}
						}while(linha != null);
					}else {
						resposta.setCodigoResposta(Resposta.ARQUIVO_NAO_ENCONTRADO);
						saida.writeObject(resposta);
					}
					
				}
				entrada.close();
				saida.close();
				cliente.close();
			}
			
			
		} catch (Exception e) {
			System.err.print("Erro no servidor");
			e.printStackTrace();
		}
	}
}
