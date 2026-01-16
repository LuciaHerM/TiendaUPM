package es.upm.etsisi.poo;

public class TiendaUPMExcepcion extends Exception{
    private String codigoError;
    private String descripcionError;

    public TiendaUPMExcepcion(String descripcionError, String codigoError) {
        super(descripcionError);
        this.codigoError = codigoError;

    }

    public String getMensaje() {
        return descripcionError;
    }
    public String getError() {
        return codigoError;
    }

}
