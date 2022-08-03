package sg.edu.rp.c346.id21018193.p11psmovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movielist.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_MOVIE = "movie_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_GENRE + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_RATING +" TEXT ) ";
        db.execSQL(createNoteTableSql);

        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("ALTER TABLE " + TABLE_MOVIE + " ADD COLUMN  module_name TEXT ");
        onCreate(db);
    }

    public long insertMovie(String title, String genre, int year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Movies> getAllMovie() {
        ArrayList<Movies> notes = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null,
                null, null, COLUMN_RATING, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = Integer.parseInt(cursor.getString(3));
                String ratings = cursor.getString(4);

                Movies movie = new Movies(id, title, genre, year, ratings);
                notes.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public ArrayList<Movies> getFilterMovie(String filterText) {
        ArrayList<Movies> notes = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String condition = COLUMN_RATING + " Like ?";
        String[] args = { "%" +  filterText + "%"};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null,
                null, null, COLUMN_RATING, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = Integer.parseInt(cursor.getString(3));
                String ratings = cursor.getString(4);

                Movies movie = new Movies(id, title, genre, year, ratings);
                notes.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public ArrayList<Movies> getFilterTitle(String filterText) {
        ArrayList<Movies> notes = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String condition = COLUMN_TITLE + " Like ?";
        String[] args = { "%" +  filterText + "%"};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null,
                null, null, COLUMN_RATING, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = Integer.parseInt(cursor.getString(3));
                String ratings = cursor.getString(4);

                Movies movie = new Movies(id, title, genre, year, ratings);
                notes.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

        public int updateMovie(Movies data){//replace data with the value in dbh.updateSong(value);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_YEAR, data.getYear());
            values.put(COLUMN_GENRE, data.getGenre());
            values.put(COLUMN_TITLE, data.getTitle());
            values.put(COLUMN_RATING, data.getRating());
            String condition = COLUMN_ID + "= ?";
            String[] args = {String.valueOf(data.getId())};
            int result = db.update(TABLE_MOVIE, values, condition, args);
            db.close();
            return result;
        }

        public int deleteMovie(int id){
            SQLiteDatabase db = this.getWritableDatabase();
            String condition = COLUMN_ID + "= ?";
            String[] args = {String.valueOf(id)};
            int result = db.delete(TABLE_MOVIE, condition, args);

            db.close();
            return result;
        }
    }
