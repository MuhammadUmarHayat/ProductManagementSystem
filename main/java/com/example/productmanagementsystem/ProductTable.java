package com.example.productmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ProductTable extends SQLiteOpenHelper
{
    Context context;
    private static String DATABASE_NAME="ProductDB.db";
    private static int DATABASE_VERSION=2;

    private static String createTableQuery="create table ProductTable(id INTEGER PRIMARY KEY,name Text,qty integer,price integer)";



    public ProductTable(Context context)//constructor
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//create database
        this.context=  context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        try
        {
            sqLiteDatabase.execSQL(createTableQuery);//create table
        }
        catch(Exception exp)
        {

            Toast.makeText(context,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS ProductTable" );
        // Create tables again
        onCreate(db);
    }

    //add Product

    public void addProduct(Product p)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values=new ContentValues();

        values.put("name",p.getName());
        values.put("qty",p.getQty());
        values.put("price",p.getPrice());
        db.insert("ProductTable", null, values);
        db.close();

    }
    public Cursor getAllProducts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from ProductTable", null);
        return res;
    }

    public Cursor getProduct(String name)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from ProductTable where name='"+name+"'" , null);
        return res;
    }

    public boolean deleteProduct(int id)
    {
        String rowId="1"; //Set your row id here
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("ProductTable",  id + " = ?",
                new String[]{rowId});  // What to delete
        sqLiteDatabase.close();
        return true;
    }

    public boolean updateProduct(int id,String name, int qty,int price)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE  ProductTable SET name ='"+name+"', qty ='"+qty+"', price='"+price+"' where id='"+id+"'");
        return true;
    }
    public Integer deleteProduct (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ProductTable", "ID = ?",new String[] {id});
    }





}
