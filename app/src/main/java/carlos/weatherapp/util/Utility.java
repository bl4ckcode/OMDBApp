package carlos.weatherapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import carlos.weatherapp.database.DatabaseHelper;
import carlos.weatherapp.interfaces.Colunas;
import carlos.weatherapp.models.Cidade;

/**
 * Created by Carlos on 17/08/2018.
 */

public class Utility {
    public static void insert(ContentValues contentValues, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d("INSERT OPERATION CODE: ", "" + database.insertOrThrow(Constantes.TABLE_CIDADE, null, contentValues));
        database.close();
    }

    public static void remove(String whereClause, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d("DELETE OPERATION CODE: ", "" + database.delete(Constantes.TABLE_CIDADE, whereClause, null));
        database.close();
    }

    public static Cidade obterCidade(Context context, int idCidade) {
        Cidade cidade = null;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_CIDADE, null,
                Colunas.ID_CIDADE + " = " + idCidade, null, null,
                null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            cidade = new Cidade(cursor.getInt(cursor.getColumnIndex(Colunas.ID_CIDADE)),
                    cursor.getString(cursor.getColumnIndex(Colunas.NOME_CIDADE)),
                    cursor.getString(cursor.getColumnIndex(Colunas.CLIMA)),
                    cursor.getString(cursor.getColumnIndex(Colunas.TEMPERATURA)));
        }

        cursor.close();
        database.close();

        return cidade;
    }

    public static ArrayList<Cidade> obterCidades(Context context) {
        ArrayList<Cidade> cidades = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_CIDADE, null, null, null, null,
                null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            cidades.add(new Cidade(cursor.getInt(cursor.getColumnIndex(Colunas.ID_CIDADE)),
                    cursor.getString(cursor.getColumnIndex(Colunas.NOME_CIDADE)),
                    cursor.getString(cursor.getColumnIndex(Colunas.CLIMA)),
                    cursor.getString(cursor.getColumnIndex(Colunas.TEMPERATURA))));
        }

        cursor.close();
        database.close();

        return cidades;
    }

    public static String converterCelsiusKelvin(double temperatura) {
        //Converter Kelvin para Celsius, K = -273.15C
        return temperatura - 273.15 + "c";
    }

    public static double converterKelvinCelsius(int temperatura) {
        //Converter Celsius para Kelvin, C = 273.15C
        return temperatura + 273.15;
    }
}
