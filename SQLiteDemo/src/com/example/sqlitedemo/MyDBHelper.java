package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{
	
	 // ��Ʈw�W��
	public static final String DataBase_Name="MyDB";
    // ��Ʈw�����A��Ƶ��c���ܪ��ɭԭn���o�ӼƦr�A�q�`�O�[�@
	public static final int version=1;
	 // ��Ʈw����A�T�w������ܼ�
	private static SQLiteDatabase dataBase;
	
	
	   // �غc�l�A�b�@�몺���γ����ݭn�ק�
	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	 // �ݭn��Ʈw������I�s�o�Ӥ�k�A�o�Ӥ�k�b�@�몺���γ����ݭn�ק�
	public static SQLiteDatabase getDataBase(Context context){
		if(dataBase==null||!dataBase.isOpen()){
			dataBase =  new MyDBHelper(context,DataBase_Name,null,version).getWritableDatabase();				
		}
		return dataBase;
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	     // �إ����ε{���ݭn�����
	
		db.execSQL(BMIDAO.CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // �R���즳�����
	    
		db.execSQL("DROP TABLE IF EXISTS " + BMIDAO.TABLE_NAME);
		
		
		// �I�sonCreate�إ߷s�������
        onCreate(db);
	}

}
