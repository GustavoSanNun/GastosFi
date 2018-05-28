package mx.com.victorhugo.mejoresfinanzas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.com.victorhugo.mejoresfinanzas.utilidades.utilidades;

public class MainActivity extends AppCompatActivity {


    ImageButton btnmenosmes;
    ImageButton btnmasmes;
    TextView tvmes,tvano;
    Calendar controlador_fecha1;
    SimpleDateFormat simpleDateFormat1;
    String valor_fecha;
    Integer int_mes,int_ano,int_dia;
    Integer[] auxiliar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Conexionsqlitehelper conn= new Conexionsqlitehelper(this,"bd_transacciones",null,1);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnmenosmes = (ImageButton)findViewById(R.id.btnmenosmes);
        btnmasmes = (ImageButton)findViewById(R.id.btnmasmes);
        tvmes = (TextView)findViewById(R.id.tvmes);
        controlador_fecha1 = Calendar.getInstance();
        simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        valor_fecha = simpleDateFormat1.format(controlador_fecha1.getTime());
        int_mes = Integer.parseInt(String.valueOf(valor_fecha.charAt(3) + String.valueOf(valor_fecha.charAt(4))));
        int_ano = Integer.parseInt(String.valueOf(valor_fecha.charAt(6) + String.valueOf(valor_fecha.charAt(7) + String.valueOf(valor_fecha.charAt(8)+String.valueOf(valor_fecha.charAt(9))))));
        int_dia = Integer.parseInt(String.valueOf(valor_fecha.charAt(0) + String.valueOf(valor_fecha.charAt(1))));
        tvano = (TextView)findViewById(R.id.tvano);
        mostrar_datos();
        }

    private void mostrar_datos() {
        tvmes.setText(utilidades.meses[calcularmes('-',int_mes, int_ano)[0]]);
        tvano.setText(String.valueOf(calcularmes('*',int_mes, int_ano)[1]));
    }

    private Integer[] calcularmes(char direccion,Integer mees,Integer anno) {

        switch (direccion){
            case '-':
                if (mees >= 1) {
                    mees--;

                } else {
                    mees = 11;
                    anno--;

                };
                break;
            case '+':
                if (mees == 11) {
                    mees=0;
                    anno++;


                } else {
                    mees++;


                };
                break;
            case '*':
                break;
        }
        Integer[] mesano = {mees, anno};
        return mesano;

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent vistasiguiente = null;
            switch (item.getItemId()) {
                case R.id.btnmasingreso:
                    vistasiguiente = new Intent(MainActivity.this, agregar_transaccion.class);
                    vistasiguiente.putExtra("tipodetransaccion","ingreso");
                    vistasiguiente.putExtra("fecha_de_alta",construye_fecha_de_alta());
                    break;
                case R.id.btnmasgastos:
                    vistasiguiente = new Intent(MainActivity.this, agregar_transaccion.class);
                    vistasiguiente.putExtra("tipodetransaccion","gasto");
                    vistasiguiente.putExtra("fecha_de_alta",construye_fecha_de_alta());
                    break;
                case R.id.btntransacciones:
                    //vistasiguiente = new Intent(MainActivity.this, ver_transacciones.class);
                    break;
            }
            startActivity(vistasiguiente);
            return false;
        }
    };

    private String construye_fecha_de_alta() {
        return String.valueOf(int_dia) +"-"+ String.valueOf(int_mes+1)+"-"+String.valueOf(int_ano);
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnmasmes:
                auxiliar = calcularmes('+',int_mes, int_ano);
                int_mes = auxiliar[0];
                int_ano= auxiliar[1];
                int_dia = 00;
                tvmes.setText(utilidades.meses[int_mes]);
                tvano.setText(String.valueOf(int_ano));
                break;
            case R.id.btnmenosmes:
                auxiliar= calcularmes('-',int_mes, int_ano);
                int_mes = auxiliar[0];
                int_ano= auxiliar[1];
                int_dia = 00;
                tvmes.setText(utilidades.meses[int_mes]);
                tvano.setText(String.valueOf(int_ano));
                break;

        }
    }
}
