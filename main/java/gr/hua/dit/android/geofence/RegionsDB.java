package gr.hua.dit.android.geofence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RegionsDB extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "RegionsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CENTER_REGIONS = "center_regions";
    private static final String TABLE_POINT_REGIONS = "point_regions";
    private static final String ID = "session_id";

    private static final String REGION_NAME = "region_name";
    private static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ENTER_TIME = "enter_time";
    private static final String EXIT_TIME = "exit_time";

    public RegionsDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CenterQuery = "CREATE TABLE " + TABLE_CENTER_REGIONS + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REGION_NAME + "TEXT,"
                + START_TIME + " TEXT, "
                + END_TIME + " TEXT, "
                + LATITUDE + " REAL, "
                + LONGITUDE + " REAL)";
        db.execSQL(CenterQuery);
        String PointQuery = "CREATE TABLE " + TABLE_POINT_REGIONS + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REGION_NAME + "TEXT,"
                + START_TIME + " TEXT, "
                + END_TIME + " TEXT, "
                + LATITUDE + " REAL, "
                + LONGITUDE + " REAL,"
                + ENTER_TIME + "TEXT,"
                + EXIT_TIME + "TEXT)";
        db.execSQL(PointQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CENTER_REGIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINT_REGIONS);
        onCreate(db);
    }

    public long addCenter_Regions(ContentValues values) {
        long res; //Var to keep insert query result
        SQLiteDatabase db = this.getWritableDatabase(); //Get Writable Db instance
        res = db.insert(TABLE_CENTER_REGIONS, null, values); //Exec insert query and save result
        db.close(); // Closing database connection
        return res; //Return Query Result
    }
    public long addPoint_Regions(ContentValues values) {
        long res; //Var to keep insert query result
        SQLiteDatabase db = this.getWritableDatabase(); //Get Writable Db instance
        res = db.insert(TABLE_POINT_REGIONS, null, values); //Exec insert query and save result
        db.close(); // Closing database connection
        return res; //Return Query Result
    }


    public int deleteAllCenterRegions() {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_CENTER_REGIONS, null, null);
        db.close();
        return i;
    }
    public int deleteAllPointRegions() {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_CENTER_REGIONS, null, null);
        db.close();
        return i;
    }
}
