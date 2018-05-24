package br.com.cliente.ftp;

import java.io.File;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import br.com.servidor.ftp.Requisicao;
import br.com.servidor.ftp.Resposta;

public class Cliente {
	public static void main(String[] args) {
		
		Socket socket;
		Scanner teclado = new Scanner(System.in);
		
		try {
			
			socket = new Socket("localhost",40000);
			ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("CLIENT: Digite o nome do arquivo");
			String nomeArquivo = teclado.nextLine();
			Requisicao requisicao = new Requisicao();
			requisicao.setTipoMensagem(Requisicao.REQUSICAO_NOMEARQUIVO);
			requisicao.setConteudoMensagem(nomeArquivo);
			
			saida.writeObject(requisicao);
			Resposta resposta = (Resposta) entrada.readObject();
			if(resposta.getCodigoResposta() == Resposta.ARQUIVO_EXISTE) {
				System.out.println("Sucesso - Arquivo existe no servidor... pronto para iniciar o download\nPressione <enter> para continuar...");
				teclado.nextLine();
				
				FileWriter escritor = new FileWriter(new File(nomeArquivo));
				System.out.println("Realizando download");
				do {
					requisicao = new Requisicao();
					requisicao.setTipoMensagem(Requisicao.REQUISICAO_CONTEUDOARQUIVO);
					saida.writeObject(requisicao);
					resposta = (Resposta) entrada.readObject();
					if(resposta.getCodigoResposta() == Resposta.CONTEUDO_DISPONIVEL) {
						System.out.print("=");
						escritor.write(resposta.getConteudoResposta()+"\n");
					}
					
				} while (resposta.getCodigoResposta() != Resposta.FIM_DO_ARQUIVO);
				System.out.println("\nConcluído!");
				escritor.close();
				
			}else if(resposta.getCodigoResposta() == Resposta.ARQUIVO_NAO_ENCONTRADO){
				System.out.println("ERRO - Arquivo nao encontrado no servidor");
			}
			entrada.close();
			saida.close();
			socket.close();
			
		} catch (Exception e) {
			
			System.err.println("Erro no cliente");
			e.printStackTrace();
		}
	}
}
