package es.upm.etsisi.poo;

public class TiendaUPMExcepcion extends Exception{
    private String codigoError;

    public TiendaUPMExcepcion(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;

    }

    public String getCodigoError() {
        return codigoError;
    }

}
