package gr.hua.dit.android.geofence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbProvider extends ContentProvider {

    private UriMatcher uriMatcher;

    private String AUTHORITY =  "gr.hua.dit.android.geofence";
    private static final String CENTER_REGIONS_PATH = "center_regions";
    private static final String POINT_REGIONS_PATH = "point_regions";

    private static final int CENTER_REGIONS_CODE = 1;
    private static final int POINT_REGIONS_CODE = 2;
    private RegionsDB dbHelper;

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(uriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"center_regions",1);
        uriMatcher.addURI(AUTHORITY,"point_regions",2);

        dbHelper = new RegionsDB(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case CENTER_REGIONS_CODE:
                cursor = db.query(db.query(iuyhoi, projection, selection, selectionArgs, null, null, sortOrder);

         }
        return null;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
            switch (uriMatcher.match(uri)) {
                case CENTER_REGIONS_CODE:
                    return "vnd.android.cursor.dir/" + AUTHORITY + "." + CENTER_REGIONS_PATH;
                case POINT_REGIONS_CODE:
                    return "vnd.android.cursor.dir/" + AUTHORITY + "." + POINT_REGIONS_PATH;
                default:
                    throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id;

        switch (uriMatcher.match(uri)) {
            case CENTER_REGIONS_CODE:
                id = db.insert("center_regions", null, values); // Replace "center_regions_table" with your actual table name
                break;
            case POINT_REGIONS_CODE:
                id = db.insert("point_regions", null, values); // Replace "point_regions_table" with your actual table name
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(uri + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

//    @Nullable
//    @Override
//    public String getType(@NonNull Uri uri) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//        return null;
//    }
////
//    @Override
//    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
//            return dbHelper.deleteAllCenterRegions();
//    }

//    @Override
//    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
//        return 0;
//    }
}
