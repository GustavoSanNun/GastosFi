package mx.com.victorhugo.mejoresfinanzas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.com.victorhugo.mejoresfinanzas.utilidades.utilidades;

public class Conexionsqlitehelper extends SQLiteOpenHelper {




    public Conexionsqlitehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.CREAR_TABLA_TRANSACCIONES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS transacciones");
        onCreate(db);

    }
}
