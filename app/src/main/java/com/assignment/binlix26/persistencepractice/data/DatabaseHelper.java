package com.assignment.binlix26.persistencepractice.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assignment.binlix26.persistencepractice.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.assignment.binlix26.persistencepractice.data.BMCContract.AppointmentEntry;
import static com.assignment.binlix26.persistencepractice.data.BMCContract.CHECKOUT;
import static com.assignment.binlix26.persistencepractice.data.BMCContract.VisitorEntry;
import static com.assignment.binlix26.persistencepractice.data.BMCContract.StaffEntry;
import static com.assignment.binlix26.persistencepractice.data.BMCContract.CheckInEntry;


/**
 * Created by binlix26 on 11/06/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BMC.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_VISITOR_CREATE =
            "CREATE TABLE " + VisitorEntry.TABLE_NAME + " (" +
                    VisitorEntry._ID + " INTEGER PRIMARY KEY, " +
                    VisitorEntry.COLUMN_NAME + " TEXT, " +
                    VisitorEntry.COLUMN_BUSINESS_NAME + " TEXT, " +
                    VisitorEntry.COLUMN_PHONE + " TEXT, " +
                    VisitorEntry.COLUMN_DATE_CREATED + " datetime default CURRENT_TIMESTAMP" +
                    ")";

    private static final String TABLE_STAFF_CREATE =
            "CREATE TABLE " + StaffEntry.TABLE_NAME + " (" +
                    StaffEntry._ID + " INTEGER PRIMARY KEY, " +
                    StaffEntry.COLUMN_NAME + " TEXT, " +
                    StaffEntry.COLUMN_TITLE + " TEXT, " +
                    StaffEntry.COLUMN_DEPARTMENT + " TEXT, " +
                    StaffEntry.COLUMN_PHONE + " TEXT, " +
                    StaffEntry.COLUMN_PHOTO + " TEXT, " +
                    StaffEntry.COLUMN_DATE_CREATED + " datetime default CURRENT_TIMESTAMP" +
                    ")";

    private static final String TABLE_APPOINTMENT_CREATE =
            "CREATE TABLE " + AppointmentEntry.TABLE_NAME + " (" +
                    AppointmentEntry._ID + " INTEGER PRIMARY KEY, " +
                    AppointmentEntry.COLUMN_DESCRIPTION + " TEXT, " +
                    AppointmentEntry.COLUMN_DATETIME + " datetime , " +
                    AppointmentEntry.COLUMN_DATE_CREATED + " datetime default CURRENT_TIMESTAMP, " +
                    AppointmentEntry.COLUMN_VISITOR + " INTEGER NOT NULL, " +
                    AppointmentEntry.COLUMN_STAFF + " INTEGER NOT NULL, " +
                    " FOREIGN KEY( " + AppointmentEntry.COLUMN_VISITOR + ") REFERENCES " + VisitorEntry.TABLE_NAME + "(" + VisitorEntry._ID + "), " +
                    " FOREIGN KEY(" + AppointmentEntry.COLUMN_STAFF + ") REFERENCES " + StaffEntry.TABLE_NAME + "(" + StaffEntry._ID + ") " +
                    ")";

    private static final String TABLE_CHECK_IN_CREATE =
            "CREATE TABLE " + CheckInEntry.TABLE_NAME + " (" +
                    CheckInEntry._ID + " INTEGER PRIMARY KEY, " +
                    CheckInEntry.COLUMN_VISITOR + " INTEGER NOT NULL, " +
                    CheckInEntry.COLUMN_PURPOSE + " TEXT, " +
                    CheckInEntry.COLUMN_SIGN_IN + " datetime default CURRENT_TIMESTAMP, " +
                    CheckInEntry.COLUMN_SIGN_OUT + " datetime, " +
                    CheckInEntry.COLUMN_STATUS + " INTEGER default " + CHECKOUT + ", " +
                    " FOREIGN KEY( " + CheckInEntry.COLUMN_VISITOR + ") REFERENCES " + VisitorEntry.TABLE_NAME + "(" + VisitorEntry._ID + ")" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_VISITOR_CREATE);
        db.execSQL(TABLE_STAFF_CREATE);
        db.execSQL(TABLE_APPOINTMENT_CREATE);
        db.execSQL(TABLE_CHECK_IN_CREATE);
        // put some data
        initDB(db);
    }

    private void initDB(SQLiteDatabase db) {
        Log.i("DatabaseHelper", "init database");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        ContentValues values = new ContentValues();

        // visitor tables
        values.put(VisitorEntry.COLUMN_NAME, "Bin Li");
        values.put(VisitorEntry.COLUMN_BUSINESS_NAME, "Wintec");
        values.put(VisitorEntry.COLUMN_PHONE, "02108105762");
        values.put(VisitorEntry.COLUMN_DATE_CREATED, dateFormat.format(calendar.getTime()));

        db.insert(VisitorEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(VisitorEntry.COLUMN_NAME, "Sophie Yu");
        values.put(VisitorEntry.COLUMN_BUSINESS_NAME, "New Save");
        values.put(VisitorEntry.COLUMN_PHONE, "02029586631");
        values.put(VisitorEntry.COLUMN_DATE_CREATED, dateFormat.format(calendar.getTime()));

        db.insert(VisitorEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(VisitorEntry.COLUMN_NAME, "Fabiano Costa");
        values.put(VisitorEntry.COLUMN_BUSINESS_NAME, "New World");
        values.put(VisitorEntry.COLUMN_PHONE, "02399521230");

        db.insert(VisitorEntry.TABLE_NAME, null, values);
        values.clear();

        // staff
        values.put(StaffEntry.COLUMN_NAME, "John Green");
        values.put(StaffEntry.COLUMN_TITLE, "Manager");
        values.put(StaffEntry.COLUMN_DEPARTMENT, "ITB");
        values.put(StaffEntry.COLUMN_PHONE, "0249238559");
        values.put(StaffEntry.COLUMN_DATE_CREATED, dateFormat.format(calendar.getTime()));
        values.put(StaffEntry.COLUMN_PHOTO, R.drawable.staff);

        db.insert(StaffEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(StaffEntry.COLUMN_NAME, "Tom Stone");
        values.put(StaffEntry.COLUMN_TITLE, "Admin");
        values.put(StaffEntry.COLUMN_DEPARTMENT, "ITB");
        values.put(StaffEntry.COLUMN_PHONE, "1293985");
        values.put(StaffEntry.COLUMN_DATE_CREATED, dateFormat.format(calendar.getTime()));
        values.put(StaffEntry.COLUMN_PHOTO, R.drawable.staff);

        db.insert(StaffEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(StaffEntry.COLUMN_NAME, "Lucy Hamilton");
        values.put(StaffEntry.COLUMN_TITLE, "Coordinator");
        values.put(StaffEntry.COLUMN_DEPARTMENT, "ITB");
        values.put(StaffEntry.COLUMN_PHONE, "0259128352");
        values.put(StaffEntry.COLUMN_PHOTO, R.drawable.staff);

        db.insert(StaffEntry.TABLE_NAME, null, values);
        values.clear();

        // appointment
        values.put(AppointmentEntry.COLUMN_DESCRIPTION, "General Business");
        values.put(AppointmentEntry.COLUMN_DATETIME, dateFormat.format(calendar.getTime()));
        values.put(AppointmentEntry.COLUMN_VISITOR, 1);
        values.put(AppointmentEntry.COLUMN_STAFF, 1);

        db.insert(AppointmentEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(AppointmentEntry.COLUMN_DESCRIPTION, "General Business");
        values.put(AppointmentEntry.COLUMN_DATETIME, dateFormat.format(calendar.getTime()));
        values.put(AppointmentEntry.COLUMN_VISITOR, 3);
        values.put(AppointmentEntry.COLUMN_STAFF, 3);

        db.insert(AppointmentEntry.TABLE_NAME, null, values);
        values.clear();

        // check_in
        values.put(CheckInEntry.COLUMN_PURPOSE, "Drop In");
        values.put(CheckInEntry.COLUMN_VISITOR, 2);
        values.put(CheckInEntry.COLUMN_STATUS, 1);

        db.insert(CheckInEntry.TABLE_NAME, null, values);
        values.clear();

        values.put(CheckInEntry.COLUMN_PURPOSE, "Scheduled Appointment");
        values.put(CheckInEntry.COLUMN_VISITOR, 1);
        values.put(CheckInEntry.COLUMN_STATUS, 1);

        db.insert(CheckInEntry.TABLE_NAME, null, values);
        values.clear();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CheckInEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AppointmentEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StaffEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VisitorEntry.TABLE_NAME);
        onCreate(db);
    }
}
