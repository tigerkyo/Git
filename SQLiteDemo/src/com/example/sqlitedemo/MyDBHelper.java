package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{
	
	 // 資料庫名稱
	public static final String DataBase_Name="MyDB";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
	public static final int version=1;
	 // 資料庫物件，固定的欄位變數
	private static SQLiteDatabase dataBase;
	
	
	   // 建構子，在一般的應用都不需要修改
	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	 // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
	public static SQLiteDatabase getDataBase(Context context){
		if(dataBase==null||!dataBase.isOpen()){
			dataBase =  new MyDBHelper(context,DataBase_Name,null,version).getWritableDatabase();				
		}
		return dataBase;
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	     // 建立應用程式需要的表格
	
		db.execSQL(BMIDAO.CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // 刪除原有的表格
	    
		db.execSQL("DROP TABLE IF EXISTS " + BMIDAO.TABLE_NAME);
		
		
		// 呼叫onCreate建立新版的表格
        onCreate(db);
	}

}
