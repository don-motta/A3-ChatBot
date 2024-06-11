package com.example.chatbot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

public class chatBot {
   public static void main(String[] args) throws Exception {
      String input;
      String nome;
      Scanner sc = new Scanner(System.in);
      boolean helloThere=true;
      //Boas vindas e identificação
      do{
          System.out.println("Inicie o chat: ");
          input=sc.nextLine().toLowerCase();
          if (input.contains("oi")||input.contains("ola")||input.contains("bom dia")||input.contains("boa tarde")||input.contains("boa noite")){
          System.out.println("Olá, seja bem vindo! Primeiramente, me diga seu nome:");
              helloThere=true;
          } else{
              helloThere=false;
          }
      }while(helloThere==false);
      nome=sc.nextLine().toLowerCase();
      //rotina do chat
      System.out.println("Como posso te ajudar "+nome+"? (favor, não utilize acentos)");
      input=sc.nextLine().toLowerCase();
      do{
          String output=bancoRespostas(input);
          System.out.println("\nResp: "+output);
          System.out.println("\nO que mais poderia te ajudar? (Ou digite 'encerrar' para encerrar o atendimento)");
          input=sc.nextLine().toLowerCase();
      }while(!input.equals("encerrar"));
      //Finalização do programa
      System.out.println("Espero ter te ajudado "+nome+". Volte sempre!!!");
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
        bancoRespostas.put("key1","resposta1");
        bancoRespostas.put("key2","resposta2");
        bancoRespostas.put("key3","resposta3");
        bancoRespostas.put("key4","resposta4");
        bancoRespostas.put("key5","resposta5");
        bancoRespostas.put("key6","resposta6");
        bancoRespostas.put("key7","resposta7");
        bancoRespostas.put("key8","resposta8");
        bancoRespostas.put("key9","resposta9");
        bancoRespostas.put("key10","resposta10");
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
  
              return "O tempo em " + cidade + " est\u00e1 " + descricaoDoClima + " com temperatura de " + temperatura + "\u00b0C e sensacao t\u00e9rmica de " + temperaturaTermica;
           }
        } catch (Exception var16) {
           var16.printStackTrace();
        }
  
        return "Erro ao obter o tempo para a cidade " + cidade;
     }
}