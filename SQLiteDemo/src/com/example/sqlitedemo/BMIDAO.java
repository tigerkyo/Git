package com.example.sqlitedemo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BMIDAO {
	
	 public static final String TABLE_NAME = "BMI_Data";
     public static final String KEY_ID="_id";
     
     // 其它表格欄位名稱
     
     public static final String Height_COLUMN = "Height";
     public static final String Weight_COLUMN = "weight";
     public static final String BMI_COLUMN = "bmi";     
     public static final String DATETIME_COLUMN = "datetime";
     
     // 使用上面宣告的變數建立表格的SQL指令
     public static final String CREATE_TABLE = 
             "CREATE TABLE " + TABLE_NAME + " (" + 
             KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +            
             Height_COLUMN + " REAL NOT NULL, " +
             Weight_COLUMN + " REAL NOT NULL, " +
             BMI_COLUMN + " REAL NOT NULL, " +
             DATETIME_COLUMN + " INTEGER NOT NULL)";     
     
     // 資料庫物件    
     private SQLiteDatabase db;
     
     // 建構子，一般的應用都不需要修改
     public BMIDAO(Context context) {
         db = MyDBHelper.getDataBase(context);
     }
     
     // 關閉資料庫，一般的應用都不需要修改
     public void close() {
         db.close();
     }   
     
     // 新增參數指定的物件
     public BMIObject insert(BMIObject item) {
         // 建立準備新增資料的ContentValues物件
         ContentValues cv = new ContentValues();     
  
         // 加入ContentValues物件包裝的新增資料
         // 第一個參數是欄位名稱， 第二個參數是欄位的資料
         cv.put(BMI_COLUMN, item.getBmi());
         cv.put(Height_COLUMN, item.getHeight());
         cv.put(Weight_COLUMN, item.getWeight());
         cv.put(BMI_COLUMN, item.getBmi());
         cv.put(DATETIME_COLUMN, item.getDate());
 
  
         // 新增一筆資料並取得編號
         // 第一個參數是表格名稱
         // 第二個參數是沒有指定欄位值的預設值
         // 第三個參數是包裝新增資料的ContentValues物件
         long id = db.insert(TABLE_NAME, null, cv);
  
         // 設定編號
         item.setId(id);
         // 回傳結果
         return item;
     }     
     
     // 讀取所有記事資料
     public List<BMIObject> getAll() {
         List<BMIObject> result = new ArrayList<>();
         Cursor cursor = db.query(
                 TABLE_NAME, null, null, null, null, null, null, null);
  
         while (cursor.moveToNext()) {
             result.add(getRecord(cursor));
         }
  
         cursor.close();
         return result;
     }     
     
     
     // 把Cursor目前的資料包裝為物件
     public BMIObject getRecord(Cursor cursor) {
         // 準備回傳結果用的物件
    	 BMIObject result = new BMIObject();
  
         result.setId(cursor.getLong(0));
         result.setHeight(cursor.getFloat(1));
         result.setWeight(cursor.getFloat(2));
         result.setBmi(cursor.getFloat(3));
         result.setDate(cursor.getInt(4));
  
         // 回傳結果
         return result;
     }     
     
     
     
     
}
