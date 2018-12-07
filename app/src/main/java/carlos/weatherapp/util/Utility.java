package carlos.weatherapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import carlos.weatherapp.database.DatabaseHelper;
import carlos.weatherapp.interfaces.Colunas;
import carlos.weatherapp.models.MovieModel;
import carlos.weatherapp.models.ShortMovieModel;

public class Utility {
    public static void insert(String tableName, ContentValues contentValues, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d("INSERT OPERATION CODE: ", "" + database.insertOrThrow(tableName, null, contentValues));
        database.close();
    }

    public static void remove(String whereClause, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d("DELETE OPERATION CODE: ", "" + database.delete(Constantes.TABLE_FILMES_RESUMIDOS, whereClause, null));
        Log.d("DELETE OPERATION CODE: ", "" + database.delete(Constantes.TABLE_FILMES, whereClause, null));
        database.close();
    }

    public static ArrayList<ShortMovieModel> buscarFilmesResumidos(Context context) {
        ArrayList<ShortMovieModel> shortMovieModels = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_FILMES_RESUMIDOS, null,
                null, null, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            shortMovieModels.add(new ShortMovieModel(cursor.getString(cursor.getColumnIndex(Colunas.TITLE)),
                    cursor.getString(cursor.getColumnIndex(Colunas.YEAR)),
                    cursor.getString(cursor.getColumnIndex(Colunas.IMDB_ID)),
                    cursor.getString(cursor.getColumnIndex(Colunas.POSTER))));
        }

        cursor.close();
        database.close();

        return shortMovieModels;
    }

    public static MovieModel buscarFilme(Context context, String imdbId) {
        MovieModel cidade = null;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_FILMES, null,
                Colunas.IMDB_ID + " = " + imdbId, null, null,
                null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            cidade = new MovieModel(cursor.getString(cursor.getColumnIndex(Colunas.IMDB_ID)),
                    cursor.getString(cursor.getColumnIndex(Colunas.TITLE)),
                    cursor.getString(cursor.getColumnIndex(Colunas.YEAR)),
                    cursor.getString(cursor.getColumnIndex(Colunas.RELEASED)),
                    cursor.getString(cursor.getColumnIndex(Colunas.RUNTIME)),
                    cursor.getString(cursor.getColumnIndex(Colunas.GENRE)),
                    cursor.getString(cursor.getColumnIndex(Colunas.DIRECTOR)),
                    cursor.getString(cursor.getColumnIndex(Colunas.ACTORS)),
                    cursor.getString(cursor.getColumnIndex(Colunas.PLOT)),
                    cursor.getString(cursor.getColumnIndex(Colunas.AWARDS)),
                    cursor.getString(cursor.getColumnIndex(Colunas.POSTER)));
        }

        cursor.close();
        database.close();

        return cidade;
    }
}
