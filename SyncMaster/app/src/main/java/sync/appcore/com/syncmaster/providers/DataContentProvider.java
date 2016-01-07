package sync.appcore.com.syncmaster.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;

public class DataContentProvider extends ContentProvider {

    private static final String TARGET_TABLE_NAME = "notes";
    public static final String AUTHORITY = "sync.appcore.com.syncmaster";
    private static final String TAG = "DataContentProvider";
    private static final UriMatcher sUriMatcher;
    private static HashMap<String, String> notesProjectionMap;
    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;

    private class DataDbHelper extends SQLiteOpenHelper {

        DataDbHelper(Context ctx) {
            super(ctx, "", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + TARGET_TABLE_NAME + " (" + Note.Notes.NOTE_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT," + Note.Notes.TITLE + " VARCHAR(255)," + Note.Notes.TEXT + " LONGTEXT" + ");");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TARGET_TABLE_NAME);
            onCreate(db);
        }
    }

    private DataDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DataDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case NOTES:
                return Note.Notes.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case NOTES:
                break;
            case NOTES_ID:
                where = where + "_id = " + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        int count = db.delete(TARGET_TABLE_NAME, where, whereArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    static {

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, TARGET_TABLE_NAME, NOTES);
        sUriMatcher.addURI(AUTHORITY, TARGET_TABLE_NAME + "/#", NOTES_ID);

        notesProjectionMap = new HashMap<String, String>();
        notesProjectionMap.put(Note.Notes.NOTE_ID, Note.Notes.NOTE_ID);
        notesProjectionMap.put(Note.Notes.TITLE, Note.Notes.TITLE);
        notesProjectionMap.put(Note.Notes.TEXT, Note.Notes.TEXT);
    }
}
