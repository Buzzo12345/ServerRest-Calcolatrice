/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package serverrest;

/**
 * Entry point dell'applicazione Calcolatrice REST
 * 
 * @author delfo
 */
public class AppV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Configurazione porta (default 8080)
        int porta = 8080;
        if (args.length > 0) {
            try {
                porta = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Porta non valida, uso porta default 8080");
            }
        }
        
        // Avvia il server REST
        ServerRestV2.avviaServer(porta);
    }
}
