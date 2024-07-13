import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String API_KEY = "b8c662de35218250694e4a89";
        if(API_KEY.equals("TU-API-KEY")){
            System.out.println("Debe cambiar el valor de API_KEY por su api key en la linea 13 de la clase Main");
            return;
        }
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/"+API_KEY+"/latest/USD"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var jsonBody = response.body();

            Gson gson = new Gson();

            RazonesConversionUSD razonesConversionUSD = gson.fromJson(jsonBody, RazonesConversionUSD.class);

            convertidor(razonesConversionUSD);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertidor(RazonesConversionUSD razonesConversionUSD){

        double ratioArs = Double.parseDouble(razonesConversionUSD.conversion_rates().ARS());
        double ratioBrl = Double.parseDouble(razonesConversionUSD.conversion_rates().BRL());
        double ratioCop = Double.parseDouble(razonesConversionUSD.conversion_rates().COP());

        Scanner lectura = new Scanner(System.in);
        boolean isActive = true;
        while(isActive){
            System.out.println("****************************************");
            System.out.println("Sea bienvenido al Conversor de Moneda :]");
            System.out.println("\n");
            System.out.println("1) Dólar =>> Peso Argentino");
            System.out.println("2) Peso Argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("****************************************");

            var opcion = lectura.nextLine();
            double valor = 0;
            boolean entradaValida = false;

            switch (opcion){
                case "7": isActive = false;
                    break;
                case "1":
                    while (!entradaValida) {
                        try {
                            System.out.println("Ingrese el valor que desee convertir");
                            valor = lectura.nextDouble();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, introduce un número entero o decimal.");
                            lectura.next();
                        }
                    }

                    System.out.println(valor * ratioArs);
                    lectura.nextLine();
                    break;
                case "2":
                    while (!entradaValida) {
                        try {
                            System.out.println("Ingrese el valor que desee convertir");
                            valor = lectura.nextDouble();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, introduce un número entero o decimal.");
                            lectura.next();
                        }
                    }
                    System.out.println(valor / ratioArs);
                    lectura.nextLine();
                    break;
                case "3":
                    while (!entradaValida) {
                        try {
                            System.out.println("Ingrese el valor que desee convertir");
                            valor = lectura.nextDouble();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, introduce un número entero o decimal.");
                            lectura.next();
                        }
                    }
                    System.out.println(valor * ratioBrl);
                    lectura.nextLine();
                    break;
                case "4":
                    while (!entradaValida) {
                        try {
                            System.out.println("Ingrese el valor que desee convertir");
                            valor = lectura.nextDouble();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, introduce un número entero o decimal.");
                            lectura.next();
                        }
                    }
                    System.out.println(valor / ratioBrl);
                    lectura.nextLine();
                    break;
                case "5":
                    while (!entradaValida) {
                        try {
                            System.out.println("Ingrese el valor que desee convertir");
                            valor = lectura.nextDouble();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, introduce un número entero o decimal.");
                            lectura.next();
                        }
                    }
                    System.out.println(valor * ratioCop);
                    lectura.nextLine();
                    break;
                case "6":
                    while (!entradaValida) {
                        try {
                            System.out.println("Ingrese el valor que desee convertir");
                            valor = lectura.nextDouble();
                            entradaValida = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, introduce un número entero o decimal.");
                            lectura.next();
                        }
                    }
                    System.out.println(valor / ratioCop);
                    lectura.nextLine();
                    break;
            }
        }
    }
}