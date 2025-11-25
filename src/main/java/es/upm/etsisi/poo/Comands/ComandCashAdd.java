package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;
import es.upm.etsisi.poo.Cash;



public class ComandCashAdd extends ComandCash {
    private String name;
    private String email;
    private String id;
    private ArrayList<Cash> cashers;


    public ComandCashAdd(String name, String email, String id, ArrayList<Cash> cashers) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.cashers = cashers;
    }
    public ComandCashAdd(String name, String email, ArrayList<Cash> cashers) {
        this.name = name;
        this.email = email;
        this.cashers = cashers;
        this.id = generarIDCash();
    }
    /**
     * Comprueba que no esiste un cajero con el mismo nombre y si no esiste crea
     * un cajero y o añade al arrayList de cajeros
     */
    public void apply(){
        boolean encontrado = false;
        int i = 0 ;
        if(this.id !=  null && id.matches("^UW\\d{7}$")) {
            while (!encontrado && i < cashers.size()) {
                if (cashers.get(i).getId().equals(id)) {
                    encontrado = true;
                }
                i++;
            }
            if (!encontrado) {
                Cash cash = new Cash(id, name, email);
                cashers.add(cash);
                System.out.println(cash.toString());
                System.out.println("cash add: ok");
            } else {
                System.err.println("Ya existe un cajero con el mismo Id en la base de datos");
            }
        }
        else {
            System.err.println("El formato del id no es el correcto : UWxxxxxxx (x un digito 0-9) .");
        }


    }
    /**
     * Busca el mayor id para crear un nuevo el cual será mayorId + 1 el cual sabemos
     * que no va a exitir
     */
    private String generarIDCash(){
        int max = 0;
        for (int i = 0; i < cashers.size(); i++) {
            String id = cashers.get(i).getId();
            int num = Integer.parseInt(id.substring(2));
            if (num > max) {
                max = num;
            }
        }
        int nuevo = max + 1;
        return "UW" + String.format("%07d", nuevo);
    }
}
