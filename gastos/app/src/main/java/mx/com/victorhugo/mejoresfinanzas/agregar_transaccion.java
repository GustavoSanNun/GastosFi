package mx.com.victorhugo.mejoresfinanzas;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Date;
import java.sql.SQLData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.com.victorhugo.mejoresfinanzas.utilidades.utilidades;

public class agregar_transaccion extends AppCompatActivity {
    TextView muestra_tipo ,tvfecha;
    DateFormat formatDateTime = new SimpleDateFormat("dd-MM-yyyy");
    Calendar dateTime= Calendar.getInstance();
    private ImageButton btn_date;

    //Calendar controlador_fecha;
    EditText etxmonto,etxnota;
    Spinner spconcepto;
    //SimpleDateFormat simpleDateFormat;
    String tipo_transaccion,fecha_de_alta;

    String[] conceptos_gastos = {"GASOLINA","COMIDA","RENTA","RESTAURANTE","CINE","FIESTA","CONSUMIBLES","MASCOTA","SERVICIOS","ESCUELA","SALUD"};
    String[] conceptos_ingresos = {"SUELDO","PRESTAMO","VENTA","BECA","REGALO","PENSION"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_transaccion);
        muestra_tipo = (TextView)findViewById(R.id.tvmuestratipo);
        etxmonto = (EditText) findViewById(R.id.etmonto);
        btn_date = (ImageButton) findViewById(R.id.select_fecha);
        spconcepto = (Spinner) findViewById(R.id.spiconcepto);


        etxnota= (EditText) findViewById(R.id.etnota);
        tvfecha = (TextView) findViewById(R.id.edfecha);
        btn_date = (ImageButton) findViewById(R.id.select_fecha);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });
        updateTextLabel();

        recibir_datos();

        if(tipo_transaccion.equals("gasto")) {
            ArrayAdapter<String> adaptador_lista = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conceptos_gastos);
            spconcepto.setAdapter(adaptador_lista);
        }
        else{
            ArrayAdapter<String> adaptador_lista = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conceptos_ingresos);
            spconcepto.setAdapter(adaptador_lista);
        };






        //establecer_fecha();




    }


   // private void establecer_fecha() {
       // tvfecha.setText(fecha_de_alta);
   // }

    public void recibir_datos(){
        Bundle extras = getIntent().getExtras();
        tipo_transaccion = extras.getString("tipodetransaccion");
        fecha_de_alta = extras.getString("fecha_de_alta");
        String Indica_tipo = "Captura el nuevo "+ tipo_transaccion;
        muestra_tipo.setText(Indica_tipo);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btncancelar:
                finish();
                break;
            case R.id.btnaceptar:
                validar_y_registrar();
                //registrarTransaccion();
                finish();
                break;
        }
        //Intent guardar_o_cancelar = new Intent(agregar_transaccion.this,MainActivity.class);

    }

    private void validar_y_registrar() {
        if(etxmonto.getText().toString().length() != 0 ){
            registrarTransaccion();
        }
    }

    private void registrarTransaccion() {
        Conexionsqlitehelper conn= new Conexionsqlitehelper(this,"bd_transacciones",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(utilidades.CAMPO_ID,1);
        values.put(utilidades.CAMPO_MONTO,etxmonto.getText().toString());
        values.put(utilidades.CAMPO_FECHA,fecha_de_alta);
        values.put(utilidades.CAMPO_CONCEPTO,spconcepto.getSelectedItem().toString());
        values.put(utilidades.CAMPO_TIPO,tipo_transaccion);
        values.put(utilidades.CAMPO_DESCRIPCION,etxnota.getText().toString());

        Long idresultante = db.insert(utilidades.TABLA_TRANSACCION,utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id_registro: "+idresultante,Toast.LENGTH_SHORT).show();
        db.close();

    }
    private void updateDate(){
        new DatePickerDialog(this,d,dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();

        }
    };


    private void updateTextLabel(){
        tvfecha.setText(formatDateTime.format(dateTime.getTime()));
    }
}
