import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String json , valorDeBase="", valorDeBusca="";
        String url;
        float valor, valorConvertido;

        Scanner input = new Scanner(System.in);
        Gson gson = new Gson();
        int option;
        Requisicao requisicao = new Requisicao();

        while(true){
            System.out.println("Seja bem-vindo ao conversor de moedas...");
            System.out.println("1) Dolar ==> Peso argentino");
            System.out.println("2) Peso argentino ==> Dolar  ");
            System.out.println("3) Dolar ==> Real Brasileiro");
            System.out.println("4) Real Brasileiro ==> Dolar");
            System.out.println("5) Dolar ==> Peso colombiano");
            System.out.println("6) Peso colombiano ==> Dolar  ");
            System.out.println("7) Sair");
            option = input.nextInt();
            switch (option){
                case 1:
                    valorDeBase = "USD";
                    valorDeBusca = "ARS";
                    break;
                case 2:
                    valorDeBase = "ARS";
                    valorDeBusca = "USD";
                    break;
                case 3:
                    valorDeBase = "USD";
                    valorDeBusca = "BRL";
                    break;
                case 4:
                    valorDeBase = "BRL";
                    valorDeBusca = "USD";
                    break;
                case 5:
                    valorDeBase = "USD";
                    valorDeBusca = "COP";
                    break;
                case 6:
                    valorDeBase = "COP";
                    valorDeBusca = "USD";
                    break;
                case 7:
                    valorDeBase = "BRL";
                    valorDeBusca = "ARS";
                    break;
                default:
                    System.out.println("Digite uma opção válida...");
                    option = 0;
                    break;
            }
            if(option!=0){
                if(option==7){
                    System.out.println("Encerrando...");
                    break;
                }else{
                    url ="https://v6.exchangerate-api.com/v6/3c3d35bc62d45b0b977e59a2/pair/"+valorDeBase+"/"+valorDeBusca;
                    json = requisicao.conecta(url);
                    TaxaDeConversao taxa = gson.fromJson(json, TaxaDeConversao.class);
                    System.out.println("Informe o valor a ser convertido ");
                    valor = input.nextFloat();
                    valorConvertido = valor*taxa.conversion_rate();
                    System.out.println("Valor "+valor+"["+valorDeBase+"]"+"corresponde ao valor final de: "+valorConvertido+"["+valorDeBusca+"]\n");
                }
            }

        }
    }
}
