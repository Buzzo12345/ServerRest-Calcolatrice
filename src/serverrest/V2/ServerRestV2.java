/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package serverrest.V2;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;



/**
 * Server REST per la calcolatrice
 * 
 * @author delfo
 */
public class ServerRestV2 {

    /**
     * Avvia il server REST sulla porta specificata
     * 
     * @param porta la porta su cui avviare il server
     */
    public static void avviaServer(int porta) {
        try {
            // Crea il server sulla porta specificata
            HttpServer server = HttpServer.create(new InetSocketAddress(porta), 0);
            
            // Endpoint versione V2
            server.createContext("/api/v2/calcola/post", new PostHandlerV2());
            server.createContext("/api/v2/calcola/get", new GetHandlerV2());
            
            // Endpoint di benvenuto
            server.createContext("/", ServerRestV2::gestisciBenvenuto);
            
            // Avvia il server
            server.setExecutor(null); // Usa il default executor
            server.start();
            
            // Messaggi di conferma
            System.out.println("==============================================");
            System.out.println("  Server REST con GSON avviato!");
            System.out.println("==============================================");
            System.out.println("Porta: " + porta);
            System.out.println();
            System.out.println("Endpoint disponibili:");
            System.out.println("  - POST V2: http://localhost:" + porta + "/api/v2/calcola/post");
            System.out.println("  - GET  V2: http://localhost:" + porta + "/api/v2/calcola/get");
            System.out.println("  - Info: http://localhost:" + porta + "/");
            System.out.println();
            System.out.println("Operatori supportati:");
            System.out.println("  SOMMA, SOTTRAZIONE, MOLTIPLICAZIONE, DIVISIONE, POTENZA, RADICE, MODULO");
            System.out.println();
            System.out.println("Premi Ctrl+C per fermare il server");
            System.out.println("==============================================");
            
        } catch (IOException e) {
            System.err.println("Errore nell'avvio del server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Gestisce l'endpoint di benvenuto che fornisce informazioni sull'API
     * 
     * @param exchange l'oggetto HttpExchange per gestire la richiesta/risposta
     * @throws IOException in caso di errori durante la comunicazione
     */
    private static void gestisciBenvenuto(HttpExchange exchange) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        Map info = new HashMap<>();
        info.put("messaggio", "Benvenuto alla Calcolatrice REST API");
        info.put("versione", "2.0.0");
        info.put("tecnologia", "Java + GSON");
        
        Map endpoints = new HashMap<>();
        Map v2 = new HashMap<>();
        v2.put("POST", "/api/v2/calcola/post");
        v2.put("GET", "/api/v2/calcola/get?operando1=X&operando2=Y&operatore=OP");
        endpoints.put("v2", v2);
        info.put("endpoints", endpoints);
        
        Map operatori = new HashMap<>();
        operatori.put("somma", "SOMMA o +");
        operatori.put("sottrazione", "SOTTRAZIONE o -");
        operatori.put("moltiplicazione", "MOLTIPLICAZIONE o * o X");
        operatori.put("divisione", "DIVISIONE o /");
        operatori.put("potenza", "POTENZA o ^");
        operatori.put("radice", "RADICE o √ (indice = operando2)");
        operatori.put("modulo", "MODULO o %");
        info.put("operatori_supportati", operatori);
        
        String jsonRisposta = gson.toJson(info);
        
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] bytes = jsonRisposta.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.getResponseBody().close();
    }
}