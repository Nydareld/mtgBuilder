package com.theoguerin.mtgbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 26/01/17.
 */

public class InterractionBD extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mtgBuilder.db";

    public final String DECK_TABLE_NAME = "deck";
    public final String DECK_COLUMN_NAME_ID = "id";
    public final String DECK_COLUMN_NAME_NOM = "nom";
    public final String CARTE_TABLE_NAME = "carte";
    public final String CARTE_COLUMN_NAME_ID = "id";
    public final String CARTE_COLUMN_NAME_NOM = "nom";
    public final String CARTE_COLUMN_NAME_COULEUR= "couleur";
    public final String CARTE_COLUMN_NAME_MANA = "mana";
    public final String CARTE_COLUMN_NAME_RARETE = "rarete";
    public final String APPARTIENT_TABLE_NAME = "appartient";
    public final String APPARTIENT_COLUMN_NAME_IDDECK = "idDeck";
    public final String APPARTIENT_COLUMN_NAME_IDCARTE = "idCarte";

    private final String SQL_CREATE_ENTRIES_APPARTIENT =
            "CREATE TABLE " + APPARTIENT_TABLE_NAME + " (" +
                    APPARTIENT_COLUMN_NAME_IDDECK + " INTEGER," +
                    APPARTIENT_COLUMN_NAME_IDCARTE + " INTEGER," +
                    "FOREIGN KEY(" + APPARTIENT_COLUMN_NAME_IDDECK + ") REFERENCES " + DECK_TABLE_NAME + "(" + DECK_COLUMN_NAME_ID + ")," +
                    "FOREIGN KEY(" + APPARTIENT_COLUMN_NAME_IDCARTE + ") REFERENCES " + CARTE_TABLE_NAME + "(" + CARTE_COLUMN_NAME_ID + ")";

    private final String SQL_DELETE_ENTRIES_APPARTIENT =
            "DROP TABLE IF EXISTS " + APPARTIENT_TABLE_NAME;

    private final String SQL_CREATE_ENTRIES_CARTE =
            "CREATE TABLE " + CARTE_TABLE_NAME + " (" +
                    CARTE_COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    CARTE_COLUMN_NAME_NOM + " TEXT," +
                    CARTE_COLUMN_NAME_COULEUR + " TEXT," +
                    CARTE_COLUMN_NAME_MANA + " TEXT," +
                    CARTE_COLUMN_NAME_RARETE + " TEXT)";

    private final String SQL_DELETE_ENTRIES_CARTE =
            "DROP TABLE IF EXISTS " + CARTE_TABLE_NAME;

    private final String SQL_CREATE_ENTRIES_DECK =
            "CREATE TABLE " + DECK_TABLE_NAME + " (" +
                    DECK_COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DECK_COLUMN_NAME_NOM + " TEXT)";

    private final String SQL_DELETE_ENTRIES_DECK =
            "DROP TABLE IF EXISTS " + DECK_TABLE_NAME;



    public InterractionBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_DECK);
        db.execSQL(SQL_CREATE_ENTRIES_CARTE);
        db.execSQL(SQL_DELETE_ENTRIES_APPARTIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_APPARTIENT);
        db.execSQL(SQL_DELETE_ENTRIES_CARTE);
        db.execSQL(SQL_DELETE_ENTRIES_DECK);
        onCreate(db);
    }

    public void addDeck(String nom){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DECK_COLUMN_NAME_NOM, nom);
        long newRowId = bd.insert(DECK_TABLE_NAME, null, values);
        bd.close();
    }

    public ArrayList<Deck> getDecks(){
        SQLiteDatabase bd = this.getReadableDatabase();
        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                DECK_COLUMN_NAME_NOM,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = "";
        String[] selectionArgs = { "" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                "";

        Cursor cursor = bd.query(
                DECK_TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        ArrayList<Deck> listeDecks = new ArrayList<Deck>();
        while(cursor.moveToNext()) {
            String nom = cursor.getString(0);
            listeDecks.add(new Deck(nom));
        }
        cursor.close();
        bd.close();
    return listeDecks;
    }

    public void deleteDeck(Deck deck){
        SQLiteDatabase bd = this.getWritableDatabase();
        String selection = DECK_COLUMN_NAME_NOM + " LIKE ?";
        String[] selectionArgs = { deck.getNom() };
        bd.delete(DECK_TABLE_NAME, selection, selectionArgs);
    }
}
