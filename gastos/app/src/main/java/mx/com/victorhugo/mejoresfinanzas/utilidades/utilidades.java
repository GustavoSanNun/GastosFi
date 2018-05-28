package mx.com.victorhugo.mejoresfinanzas.utilidades;

public class utilidades {
    //Constante campos tabla transaccion
    public static final String TABLA_TRANSACCION = "transacciones";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_MONTO = "monto";
    public static final String CAMPO_TIPO = "tipo";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_CONCEPTO = "concepto";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String[] meses = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};




    public static final String CREAR_TABLA_TRANSACCIONES="CREATE TABLE "+TABLA_TRANSACCION+" ("+CAMPO_ID+" INTEGER, "+CAMPO_MONTO+" INTEGER, "+CAMPO_TIPO+" TEXT, "+CAMPO_FECHA+" TEXT, "+CAMPO_CONCEPTO+" TEXT, "+CAMPO_DESCRIPCION+" TEXT)";

}
