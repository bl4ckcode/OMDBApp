package carlos.weatherapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import carlos.weatherapp.util.Constantes;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context contexto) {
        super(contexto, Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FilmesResumidos(" +
                "imdbId varchar NOT NULL, " +
                "Title varchar NOT NULL, " +
                "Year INTEGER NOT NULL," +
                "Poster varchar NOT NULL, " +
                "CONSTRAINT PK_OMBD_ID PRIMARY KEY (imdbId));");

        db.execSQL("CREATE TABLE Filmes(" +
                "imdbId varchar NOT NULL, " +
                "Title varchar NOT NULL, " +
                "Year INTEGER NOT NULL," +
                "Released varchar(15) NOT NULL, " +
                "Runtime varchar(15) NOT NULL, " +
                "Genre varchar NOT NULL, " +
                "Director varchar NOT NULL, " +
                "Actors varchar NOT NULL, " +
                "Plot varchar NOT NULL, " +
                "Awards varchar NOT NULL, " +
                "Poster varchar NOT NULL, " +
                "CONSTRAINT PK_OMBD_ID PRIMARY KEY (imdbId));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
