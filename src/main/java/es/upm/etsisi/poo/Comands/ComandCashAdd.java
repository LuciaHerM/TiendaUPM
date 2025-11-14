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
        this.id = generarIDCash();
        this.cashers = cashers;
    }
    /**
     * Comprueba que no esiste un cajero con el mismo nombre y si no esiste crea
     * un cajero y o añade al arrayList de cajeros
     */
    public void apply(){
        boolean encontrado = false;
        int i = 0 ;
        while (!encontrado&&i<cashers.size()) {
            if (cashers.get(i).getId().equals(id)) {
                encontrado = true;
            }
            i++;
        }
        if(!encontrado) {
            Cash cash = new Cash(id, name, email);
            cashers.add(cash);
        }else {
            System.err.println("Ya existe un cajero con el mismo Id en la base de datos");
        }
    }
    /**
     * Busca el mayor id para crear un nuevo el cual será mayorId + 1 el cual sabemos
     * que no va a exitir
     */
    private String generarIDCash(){
        String nuevoId = "";
        int maximo = 0;
        for (int i = 0 ; i < cashers.size() ; i++){
            if ( maximo < Integer.parseInt(cashers.get(i).getId())){
                maximo = Integer.parseInt(cashers.get(i).getId());
            }
        }
        nuevoId = String.valueOf(maximo + 1);

        return nuevoId;
    }
}
