package com.americanairlines.thegymmanager.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "member_db";
    public static int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "member_table";
    public static final String COLUMN_MEMBERSHIP_ID = "membership_id";
    public static final String COLUMN_MEMBER_NAME = "member_name";
    public static final String COLUMN_MEMBER_AGE = "member_age";
    public static final String COLUMN_MEMBERSHIP_STATUS = "membership_status";

    public Resources resources;


    public MemberDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        resources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_MEMBERSHIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MEMBER_NAME + " TEXT, "
                + COLUMN_MEMBER_AGE + " TEXT, "
                + COLUMN_MEMBERSHIP_STATUS + " TEXT)";

        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String update = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(update);
        onCreate(db);
        DATABASE_VERSION = newVersion;
    }

    public List<GymMember> getAllMembers(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);

        List<GymMember> members = new ArrayList<>();
        cursor.move(-1);
        while(cursor.moveToNext()){
            int memberID = cursor.getInt(cursor.getColumnIndex(COLUMN_MEMBERSHIP_ID));
            String memberName = cursor.getString(cursor.getColumnIndex(COLUMN_MEMBER_NAME));
            int memberAge = cursor.getInt(cursor.getColumnIndex(COLUMN_MEMBER_AGE));
            String membershipStatus = cursor.getString(cursor.getColumnIndex(COLUMN_MEMBERSHIP_STATUS));

            members.add(new GymMember(memberID, memberName, memberAge, membershipStatus));
        }

        return members;

    }

    public void insertMemberIntoDatabase(GymMember member){

        ContentValues shoeValues = new ContentValues();
        shoeValues.put(COLUMN_MEMBER_NAME, member.getName());
        shoeValues.put(COLUMN_MEMBER_AGE, member.getAge());
        shoeValues.put(COLUMN_MEMBERSHIP_STATUS, member.getMembershipStatus());

        getWritableDatabase().insert(TABLE_NAME, null, shoeValues);

    }

    public void deleteMemberFromDatabase(GymMember member){
        String deleteSql = "DELETE FROM " + TABLE_NAME + " " + COLUMN_MEMBERSHIP_ID + " = " + member.getMembershipID();

        getWritableDatabase().execSQL(deleteSql);
    }
}
