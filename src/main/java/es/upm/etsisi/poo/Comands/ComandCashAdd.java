package es.upm.etsisi.poo.Comands;

import java.util.ArrayList;
import es.upm.etsisi.poo.Cash;
import es.upm.etsisi.poo.persistence.CashDAO;
import es.upm.etsisi.poo.TiendaUPMExcepcion;

public class ComandCashAdd extends ComandCash {
    private String name;
    private String email;
    private String id;
    private ArrayList<Cash> cashers;


    public ComandCashAdd(String id, String name, String email, ArrayList<Cash> cashers) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.cashers = cashers;
    }
    public ComandCashAdd(String name, String email, ArrayList<Cash> cashers) {
        this.name = name;
        this.email = email;
        this.cashers = cashers;
        this.id = generateIDCash();
    }
    /**
     * Comprueba que no esiste un cajero con el mismo nombre y si no esiste crea
     * un cajero y o añade al arrayList de cajeros
     */
    public void apply() throws TiendaUPMExcepcion {
        boolean found = false;
        int i = 0;
        if (this.id != null && id.matches("^UW\\d{7}$")) {
            while (!found && i < cashers.size()) {
                if (cashers.get(i).getCashId().equals(id)) {
                    found = true;
                }
                i++;
            }
            if (!found) {
                Cash cash = new Cash(id, name
                        , email);
                cashers.add(cash);
                System.out.println(cash.toString());
                CashDAO.save(cash);
                System.out.println("cash add: ok");
            } else {
                throw new TiendaUPMExcepcion("The id of the casher is already created", "ERR_CASHER");
            }
        } else {
            throw new TiendaUPMExcepcion("The id format is not correct : UWxxxxxxx (x un digito 0-9) .", "ERR_CASHERID");
        }


    }
    /**
     * Busca el mayor id para crear un nuevo el cual será mayorId + 1 el cual sabemos
     * que no va a exitir
     */
    private String generateIDCash(){
        int max = 0;
        for (int i = 0; i < cashers.size(); i++) {
            String id = cashers.get(i).getCashId();
            int num = Integer.parseInt(id.substring(2));
            if (num > max) {
                max = num;
            }
        }
        int new_1 = max + 1;
        return "UW" + String.format("%07d", new_1);
    }
}
