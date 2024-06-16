package com.example.chatbot;

/*Projeto (PSC) – A3: 1º Semestre/2024
 *Desenvolvimento de Chatbot em Java com Vetores
 *Instituiçāo: UAM Mooca
 *UC: Programa de Soluçōes Computacionais
 *Prof. Raul Bastos
 *Alunos: 
 -Alex Mota Almeida                        - RA: 12524115198 - ADS - 1º semestre
 -Eduardo Oliveira dos Santos              - RA: 12524119946 - Ciência da Computação - 1º semestre 
 -Vinicius Alves Frabetti                  - RA: 12524110973 - Ciência da Computação - 1º semestre
 -Darllyson Juan Batista Araujo            - RA: 12524141685 - ADS - 1º semestre
 -João Vitor Bione Ribeiro de Sousa        - RA: 12524129408 - ADS - 1º semestre
 -Matheus Cardoso Curvelo                  - RA: 12524143790
 -Mateus Victor Franco de Souza            - RA: 12524129538 - ADS - 1º semestre
 -Saulo Silva Conceição                    - RA: 12524149047 - ADS - 1º Semestre
 -Vittor Mateus Rodrigues                  - RA: 1252419997  - ADS - 1º semestre    
 Versão 1.4
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

public class ChatBot {
   public static void main(String[] args) throws Exception {
       String input;
       String nome;
       Scanner sc = new Scanner(System.in);
       boolean helloThere = true;

       // Boas vindas e identificação
       do {
           System.out.println("Inicie o chat: ");
           input = sc.nextLine().toLowerCase();
           if (input.contains("oi") || input.contains("ola") || input.contains("bom dia") || input.contains("boa tarde") || input.contains("boa noite")) {
               System.out.println("Olá, seja bem vindo! Primeiramente, me diga seu nome:");
               helloThere = true;
           } else {
               helloThere = false;
           }
       } while (!helloThere);

       nome = sc.nextLine().toLowerCase();

       // rotina do chat
       System.out.println("Como posso te ajudar " + nome + "? (favor, não utilize acentos)");
       input = sc.nextLine().toLowerCase();
       do {
           if (input.contains("calcular")||input.contains("calculadora")) {
               System.out.println("Abrindo a calculadora...");
            //Caso o input seja "calcular" o programa abrira um novo caminho (thread), sem fechar o caminho "principal"
               new Thread(() -> Calculadora.main(new String[]{})).start();               
           } else if (input.contains("jogo da forca")||input.contains("forca")) {
               System.out.println("Iniciando jogo da forca");
               new Thread(() -> JogoDaForca.main(new String[]{})).start();
           } else {
               String output = bancoRespostas(input);
               System.out.println("\nResp: " + output);              
           }
           System.out.println("\nEm que mais eu poderia te ajudar? (Ou digite 'encerrar' para encerrar o atendimento)");
           input = sc.nextLine().toLowerCase();
       } while (!input.equals("encerrar"));

       // Finalização do programa
       System.out.println("Espero ter te ajudado " + nome + ". Volte sempre!!!");
       sc.close();
   }


    //banco de respostas
    public static String bancoRespostas(String input){
        String output="Desculpe, resposta não encontrada";
        String cidade=null;
        String clima=null;
        String time=time();
        Scanner sc= new Scanner(System.in);
        if (input.contains("tempo")||input.contains("clima")||input.contains("temperatura")){
            System.out.println("Por gentileza, informe a cidade desejada:");
            cidade=sc.nextLine().toLowerCase();
            clima=getWeather(cidade);
     }


        HashMap<String, String> bancoRespostas = new HashMap<String, String>();
        bancoRespostas.put("data",time);
        bancoRespostas.put("hora",time);
        bancoRespostas.put("horario",time);
        bancoRespostas.put("tempo",clima);
        bancoRespostas.put("clima",clima);
        bancoRespostas.put("temperatura",clima);
        bancoRespostas.put("proposito","Este projeto tem como objetivo proporcionar aos estudantes a oportunidade de aplicar seus conhecimentos e habilidades no desenvolvimento de uma aplicação Java, incorporando princípios de lógica de programação.");
        bancoRespostas.put("projeto","Este projeto tem como objetivo proporcionar aos estudantes a oportunidade de aplicar seus conhecimentos e habilidades no desenvolvimento de uma aplicação Java, incorporando princípios de lógica de programação.");
        bancoRespostas.put("participantes","O número máximo de participantes por grupo é de 6 ou mais conforme combinado com o professor.");
        bancoRespostas.put("grupo","O número máximo de participantes por grupo é de 6 ou mais conforme combinado com o professor.");
        bancoRespostas.put("pai da computacao","Alan Turing é frequentemente considerado o pai da ciência da computação, devido aos seus trabalhos fundamentais sobre computação, criptografia e inteligência artificial.");
        bancoRespostas.put("ciencia da computacao","Alan Turing é frequentemente considerado o pai da ciência da computação, devido aos seus trabalhos fundamentais sobre computação, criptografia e inteligência artificial.");
        bancoRespostas.put("alan turing","Alan Turing é frequentemente considerado o pai da ciência da computação, devido aos seus trabalhos fundamentais sobre computação, criptografia e inteligência artificial.");
        bancoRespostas.put("primeira programadora","Ada Lovelace é frequentemente reconhecida como a primeira programadora da história, tendo desenvolvido algoritmos para a máquina analítica de Charles Babbage no século XIX.");
        bancoRespostas.put("ada lovelace","Ada Lovelace é frequentemente reconhecida como a primeira programadora da história, tendo desenvolvido algoritmos para a máquina analítica de Charles Babbage no século XIX.");
        bancoRespostas.put("algoritmo","Um algoritmo é um conjunto finito de passos bem definidos e ordenados que levam à solução de um problema ou realização de uma tarefa específica.");
        bancoRespostas.put("primeiro computador","O ENIAC (Electronic Numerical Integrator and Computer) foi o primeiro computador eletrônico digital, concluído em 1946.");
        bancoRespostas.put("computador eletronico","O ENIAC (Electronic Numerical Integrator and Computer) foi o primeiro computador eletrônico digital, concluído em 1946.");
        bancoRespostas.put("computador digital","O ENIAC (Electronic Numerical Integrator and Computer) foi o primeiro computador eletrônico digital, concluído em 1946.");
        bancoRespostas.put("eniac","O ENIAC (Electronic Numerical Integrator and Computer) foi o primeiro computador eletrônico digital, concluído em 1946.");
        bancoRespostas.put("virus de computador","Um vírus de computador é um programa malicioso que se replica e se espalha entre os computadores, muitas vezes causando danos aos sistemas ou roubando informações. Eles podem se espalhar através de redes, dispositivos USB infectados e e-mails maliciosos.");
        bancoRespostas.put("linguagem de programacao","Uma linguagem de programação de alto nível é uma linguagem que possui abstração de detalhes da máquina e é mais compreensível para os humanos. Exemplos incluem Python, Java e C++.");
        bancoRespostas.put("maior codigo","Código fonte do Google ultrapassa a marca de 2 bilhões de linhas.");
        bancoRespostas.put("linguagens de programacao","Aproximadamente o número de linguagens de programação existentes no mundo ultrapassa 1300");
        bancoRespostas.put("primeira capital do brasil","Salvador, na Bahia, foi a primeira capital do Brasil, de 1549 até 1763.");
        bancoRespostas.put("primeira universidade do brasil","A Universidade de São Paulo (USP), fundada em 1934, é considerada a primeira universidade do Brasil.");
        bancoRespostas.put("medalhas olimpicas","O Brasil possui até hoje 151 medalhas:\n37 de Ouro\n42 de Prata\n72 de Bronze");
        bancoRespostas.put("maria da penha","Maria da Penha Maia Fernandes é uma ativista brasileira nascida em 1º de dezembro de 1945 em Fortaleza, Ceará, que se tornou um símbolo na luta contra a violência doméstica no Brasil.");
        bancoRespostas.put("napoleao bonaparte","Napoleão Bonaparte foi um líder militar e político francês que se tornou imperador dos franceses e dominou a maior parte da Europa no início do século XIX.");
        bancoRespostas.put("william shakespeare","William Shakespeare foi um renomado dramaturgo e poeta inglês do século XVI e XVII, conhecido por suas peças teatrais como 'Romeu e Julieta' e 'Hamlet'.");
        bancoRespostas.put("martin luther king","Martin Luther King Jr. foi um líder dos direitos civis nos Estados Unidos, conhecido por sua luta pacífica contra a segregação racial e pela promoção da igualdade.");
        bancoRespostas.put("isaac newton","Isaac Newton foi um cientista inglês do século XVII, famoso por suas descobertas em física e matemática, incluindo as leis do movimento e a lei da gravitação universal.");
        bancoRespostas.put("galileu galilei","Galileu Galilei foi um cientista italiano do século XVI e XVII, conhecido por suas contribuições para a astronomia, física e desenvolvimento do telescópio.");
        bancoRespostas.put("vr","NÃO ACEITAMOS VR!!!");
     
        for(String c: bancoRespostas.keySet()){
            if(input.contains(c)){
                output=bancoRespostas.get(c);
            }
        }
        return output;
    }




    //Função de data/hora
    public static String time(){
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraFormatada = agora.format(formatoBrasileiro);
        String time= "Data e hora atual: " + dataHoraFormatada;
        return time;
    }

    public static String getWeather(String cidade) {
        String apiKey = "VR8WAF473S3W4JRHCJNE6L85D";
        String urlString = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + cidade + "?unitGroup=metric&key=" + apiKey + "&contentType=json";
  
        try {
           URL url = new URL(urlString);
           HttpURLConnection conn = (HttpURLConnection)url.openConnection();
           conn.setRequestMethod("GET");
           int responseCode = conn.getResponseCode();
           if (responseCode == 200) {
              BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
              StringBuffer response = new StringBuffer();
  
              String inputLine;
              while((inputLine = in.readLine()) != null) {
                 response.append(inputLine);
              }
  
              in.close();
            //JSONObjects sao usados para converter a resposta do site, que vem em formato JSON, para strings.
              JSONObject jsonResponse = new JSONObject(response.toString());
              JSONObject condicoesAtuais = jsonResponse.getJSONObject("currentConditions");
              double temperatura = condicoesAtuais.getDouble("temp");
              double temperaturaTermica = condicoesAtuais.getDouble("feelslike");
              String descricaoDoClima = condicoesAtuais.getString("conditions");
              if (descricaoDoClima.equals("Clear")) {
                 descricaoDoClima = "Claro";
              } else if (descricaoDoClima.equals("Cloudy")) {
                 descricaoDoClima = "Nublado";
              } else if (descricaoDoClima.equals("Partly Cloudy")) {
                 descricaoDoClima = "Parcialmente Nublado";
              } else if (descricaoDoClima.equals("Rain")) {
                 descricaoDoClima = "Chovendo";
              } else if (!descricaoDoClima.equals("Showers") && !descricaoDoClima.equals("Drizzle")) {
                 if (descricaoDoClima.equals("Showers")) {
                    descricaoDoClima = "Chuviscando";
                 }
              } else {
                 descricaoDoClima = "Chuviscando";
              }
  
              return "O tempo em " + cidade + " está " + descricaoDoClima + " com temperatura de " + temperatura + "°C e sensação térmica de " + temperaturaTermica;

           }
        } catch (Exception var16) {
           var16.printStackTrace();
        }
  
        return "Erro ao obter o tempo para a cidade " + cidade;
     }

     
}
