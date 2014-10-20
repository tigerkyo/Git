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
     
     // �䥦������W��
     
     public static final String Height_COLUMN = "Height";
     public static final String Weight_COLUMN = "weight";
     public static final String BMI_COLUMN = "bmi";     
     public static final String DATETIME_COLUMN = "datetime";
     
     // �ϥΤW���ŧi���ܼƫإߪ�檺SQL���O
     public static final String CREATE_TABLE = 
             "CREATE TABLE " + TABLE_NAME + " (" + 
             KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +            
             Height_COLUMN + " REAL NOT NULL, " +
             Weight_COLUMN + " REAL NOT NULL, " +
             BMI_COLUMN + " REAL NOT NULL, " +
             DATETIME_COLUMN + " INTEGER NOT NULL)";     
     
     // ��Ʈw����    
     private SQLiteDatabase db;
     
     // �غc�l�A�@�몺���γ����ݭn�ק�
     public BMIDAO(Context context) {
         db = MyDBHelper.getDataBase(context);
     }
     
     // ������Ʈw�A�@�몺���γ����ݭn�ק�
     public void close() {
         db.close();
     }   
     
     // �s�W�Ѽƫ��w������
     public BMIObject insert(BMIObject item) {
         // �إ߷ǳƷs�W��ƪ�ContentValues����
         ContentValues cv = new ContentValues();     
  
         // �[�JContentValues����]�˪��s�W���
         // �Ĥ@�ӰѼƬO���W�١A �ĤG�ӰѼƬO��쪺���
         cv.put(BMI_COLUMN, item.getBmi());
         cv.put(Height_COLUMN, item.getHeight());
         cv.put(Weight_COLUMN, item.getWeight());
         cv.put(BMI_COLUMN, item.getBmi());
         cv.put(DATETIME_COLUMN, item.getDate());
 
  
         // �s�W�@����ƨè��o�s��
         // �Ĥ@�ӰѼƬO���W��
         // �ĤG�ӰѼƬO�S�����w���Ȫ��w�]��
         // �ĤT�ӰѼƬO�]�˷s�W��ƪ�ContentValues����
         long id = db.insert(TABLE_NAME, null, cv);
  
         // �]�w�s��
         item.setId(id);
         // �^�ǵ��G
         return item;
     }     
     
     // Ū���Ҧ��O�Ƹ��
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
     
     
     // ��Cursor�ثe����ƥ]�ˬ�����
     public BMIObject getRecord(Cursor cursor) {
         // �ǳƦ^�ǵ��G�Ϊ�����
    	 BMIObject result = new BMIObject();
  
         result.setId(cursor.getLong(0));
         result.setHeight(cursor.getFloat(1));
         result.setWeight(cursor.getFloat(2));
         result.setBmi(cursor.getFloat(3));
         result.setDate(cursor.getInt(4));
  
         // �^�ǵ��G
         return result;
     }     
     
     
     
     
}
