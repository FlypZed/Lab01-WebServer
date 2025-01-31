package app;

import java.net.*;
import java.io.*;
import java.util.HashMap;

import app.services.Services;

/**
 * Levantar un servicio web que va a correr por el puerto 35000
 */
public class HttpServer {

    private static HttpServer instance;

    public static HttpServer getInstance() {
        if (instance == null){
            instance = new HttpServer();
        }
        return instance;
    }

    /**
     * Metodo para iniciar el programa
     * @param args main
     * @throws IOException exception
     */
    public void run (String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine="";

            boolean first_line = true;
            String request = "/simple";

            while ((inputLine = in.readLine()) != null) {
                if (first_line) {
                    request = inputLine.split(" ")[1];
                    first_line = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            if (request.startsWith("/app")) {
                outputLine = executeService(request.substring(5));
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Ejecutar un servicio indicado por el path /apps/
     * @param serviceName El nombre del servicio o recurso a ejecutar
     * @return EL Header y Body del recurso solicitado, en caso de no encontrarse el recurso se le dirigirá a un 404
     */
    private String executeService(String serviceName) {
        Services ps = Services.getInstance();
        try {
            String type = serviceName.split("\\.")[1];
            String header = ps.getHeader(type, "200 OK");
            String body = ps.getResponse("src/main/resources/" + serviceName);
            return header + body;
        }
        catch (RuntimeException e){
            String header = ps.getHeader("html", "404 Not Found");
            String body = ps.getResponse("src/main/resources/404.html");
            return header + body;
        }
    }

    /**
     * Entregar el index de la página principal
     * @return index en formato de String del HTML del inicio de la Página
     */
    private static String index(){
        return "<!DOCTYPE html>\n" +
                "</html>";
    }
}