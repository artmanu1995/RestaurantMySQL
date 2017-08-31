package test.jakkit.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KHAMMA on 4/2/2017.
 */

public class FoodTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String TABLE_FOOD = "foodTABLE";
    public static final String COLUMN_ID_FOOD = "_id";
    public static final String COLUMN_FOOD = "Food";
    public static final String COLUMN_PRICE = "Price";

    public FoodTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    //Read All Price
    public String[] readAllPrice() {

        String strListPrice[] = null;
        Cursor objCursor = readDatabase.query(TABLE_FOOD, new String[]{COLUMN_ID_FOOD, COLUMN_PRICE}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListPrice = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListPrice[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_PRICE));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListPrice;
    }

    //Read All Food
    public String[] readAllFood() {

        String strListFood[] = null;
        Cursor objCursor = readDatabase.query(TABLE_FOOD, new String[]{COLUMN_ID_FOOD, COLUMN_FOOD}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListFood = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_FOOD));
            objCursor.moveToNext();
        }   // for

        objCursor.close();

        return strListFood;
    }
    //Read All NumFood
    public String[] readAllNumFood() {

        String strListNumFood[] = null;
        Cursor objCursor = readDatabase.query(TABLE_FOOD, new String[]{COLUMN_ID_FOOD, COLUMN_ID_FOOD}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListNumFood = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListNumFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_FOOD));
            objCursor.moveToNext();
        }   // for

        objCursor.close();

        return strListNumFood;
    }

    public long addValueToFood(String strNumFood, String strFood, String strPrice) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_FOOD, strNumFood);
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_PRICE, strPrice);
        return writeDatabase.insert(TABLE_FOOD, null, objContentValues);
    }

}   // Main Class
