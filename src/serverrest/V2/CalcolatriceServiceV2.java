/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

/**
 *
 * @author delfo
 */
public class CalcolatriceServiceV2 {
    
    /**
     * Esegue l'operazione matematica richiesta
     * 
     * @param operando1 Il primo operando
     * @param operando2 Il secondo operando
    * @param operatore L'operatore (SOMMA, SOTTRAZIONE, MOLTIPLICAZIONE, DIVISIONE, POTENZA, RADICE, MODULO)
     * @return Il risultato dell'operazione
     * @throws IllegalArgumentException se l'operatore non è valido o divisione per zero
     */
    public static double calcola(double operando1, double operando2, String operatore) 
            throws IllegalArgumentException {
        
        if (operatore == null || operatore.trim().isEmpty()) {
            throw new IllegalArgumentException("Operatore non può essere vuoto");
        }
        
        // Convertiamo l'operatore in maiuscolo per gestire input case-insensitive
        String op = operatore.toUpperCase().trim();
        
        switch (op) {
            case "SOMMA":
            case "+":
                return operando1 + operando2;
                
            case "SOTTRAZIONE":
            case "-":
                return operando1 - operando2;
                
            case "MOLTIPLICAZIONE":
            case "*":
            case "X":
                return operando1 * operando2;
                
            case "DIVISIONE":
            case "/":
                if (operando2 == 0) {
                    throw new IllegalArgumentException("Divisione per zero non consentita");
                }
                return operando1 / operando2;
                
            case "POTENZA":
            case "^":
                return Math.pow(operando1, operando2);

            case "RADICE":
            case "√":   
                if (operando2 == 0) {
                    throw new IllegalArgumentException("Radice con indice zero non consentita");
                }
                if (operando1 < 0) {
                    throw new IllegalArgumentException("Radice quadrata di un numero negativo non consentita");
                }
                return Math.pow(operando1, 1.0 / operando2);

            case "MODULO":
            case "%":
                if (operando2 == 0) {
                    throw new IllegalArgumentException("Modulo per zero non consentito");
                }
                return operando1 % operando2;
                
            default:
                throw new IllegalArgumentException(
                    "Operatore non valido: " + operatore + 
                    ". Operatori consentiti: SOMMA, SOTTRAZIONE, MOLTIPLICAZIONE, DIVISIONE, POTENZA, RADICE, MODULO"
                );
        }
    }
}
