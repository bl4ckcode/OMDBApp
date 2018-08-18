package carlos.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import carlos.weatherapp.util.Constantes;

/**
 * Created by Carlos on 17/08/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context contexto) {
        super(contexto, Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Cidade(" +
                "idCidade INTEGER NOT NULL, " +
                "nomeCidade varchar(30) NOT NULL, " +
                "ehFavoritada INTEGER NOT NULL, " +
                "CONSTRAINT PK_Cidade PRIMARY KEY (idCidade));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
