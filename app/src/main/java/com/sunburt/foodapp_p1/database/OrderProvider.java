package com.sunburt.foodapp_p1.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderProvider extends ContentProvider {

    public static final String CONTENT_AUTHORITY = "com.sunburt.foodapp_p1";
    public static  final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH = "orderig";
    public static final int ORDER = 1000;
    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);
    static {
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH, ORDER);
    }
    public DBHelper db;

    @Override
    public boolean onCreate() {
        db = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor;
        int match = uriMatcher.match(uri);
        switch (match){
            case ORDER:
                cursor = database.query("tbl_order",projection,selection,selectionArgs, null,null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("CANT QUERY");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    // will work on this method because have to insert
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = uriMatcher.match(uri);
        switch (match){
            case ORDER:
                return insertCart(uri, values);
            default:
                throw new IllegalArgumentException("Can't insert data");
        }

    }

    private Uri insertCart(Uri uri, ContentValues values) {
        String name = values.getAsString("order_name");
        if (name == null) {
            throw new IllegalArgumentException("Name is required");
        }

        String number = values.getAsString("order_number");
        if (number == null) {
            throw new IllegalArgumentException("Number is required");
        }

        float price = values.getAsFloat("order_price");
        if (price == 0 ) {
            throw new IllegalArgumentException("Price is required");
        }

        SQLiteDatabase database = db.getWritableDatabase();
        int id = (int) database.insert("tbl_order", null, values);

        if (id == -1){
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase database = db.getWritableDatabase();
        int match = uriMatcher.match(uri);
        switch (match) {
            case ORDER:
                rowsDeleted = database.delete("tbl_order", selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Cannot delete");
        }

        if (rowsDeleted!=0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
