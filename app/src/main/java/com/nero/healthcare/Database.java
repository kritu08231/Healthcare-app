package com.nero.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    private SQLiteDatabase sqliteDatabase;

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE users(Username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "CREATE TABLE cart (Username TEXT, product TEXT, price FLOAT, otype TEXT)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 ="CREATE TABLE orderplace(Username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
   public void register(String Username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("Username",Username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    public int login(String Username,String password){
        int result=0;
        String str[] = new String[2];
        str[0] = Username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where Username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }
    public void addCart(String Username,String product,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("Username",Username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkCart(String Username,String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = Username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where Username= ? and product = ?", str);
        if (c.moveToFirst()) {
            result=1;
        }
        c.close();
        db.close();
        return result;
    }

    public void removeCart(String Username,String oytpe){
        String str[] = new String[2];
        str[0] = Username;
        str[1] = oytpe;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","Username=? and otype=?",str);
        db.close();
    }
    public ArrayList getCartData(String Username,String otype){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0]= Username;
        str[1]= otype;
        Cursor c =db.rawQuery("select * from cart where Username = ? and otype = ?",str);
        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String Username,String fullname,String address,String contact,int pincode,String date,String time,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("Username",Username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db= getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }

    public ArrayList getOrderData(String Username){
        ArrayList<String> arr= new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = Username;
        Cursor c = db.rawQuery("select * from orderplace where Username=?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String Username,String fullname,String address,String contact,String date,String time){
        int result=0;
        String str[]= new String[6];
        str[0] = Username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =db.rawQuery("select * from orderplace where Username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
    public void deleteOrder(String username, String fullname, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {username, fullname, date, time};
        db.delete("orderplace", "Username = ? AND fullname = ? AND date = ? AND time = ?", whereArgs);
        db.close();
    }


}
