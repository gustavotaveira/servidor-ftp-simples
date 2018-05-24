## Servidor ftp simples
Esse código representa um servidor ftp simples que transfere arquivos de texto copiando-os linha a linha e 
armazenando em outro arquivo de mesmo nome no lado do cliente. É utilizada a API de sockets do java,
mais precisamente a classe ServerSocket para ser o servidor e Socket para cliente. 
É utilizada as classes ObjectInputStream e ObjectOutputStream para realizar a comunicação de fato, 
provavelmente usando o protocolo tcp. Para os arquivos são utilizadas
as classes File(cria novo arquivo), FileWriter (escritor), FileReader (leitor), BufferedReader (executa a leitura de fato).
Alem do servidor, estao presentes o projeto cliente e mensageria(apenas modelo das classes de requisicao e resposta).
Para o servidor utiliza o Spring Boot, para rodar bastar rodar como uma aplicação java tradicional pois o servidor de aplicação Tomcat
ja vem embutido.É necessário ter o plugin do Spring Boot no eclipse.
