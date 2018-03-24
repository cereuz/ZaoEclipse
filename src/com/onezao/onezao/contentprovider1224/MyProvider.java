package com.onezao.onezao.contentprovider1224;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyProvider extends ContentProvider {

	private MyOpenHelper helper;
	private String table =  "info";
	private String error =  "�������";
	private static int QUERY_MATCHED = 0;
	private static int INSERT_MATCHED = 1;
	private static int UPDATE_MATCHED = 2;
	private static int DELETE_MATCHED = 3;
	private static final UriMatcher   sURIMatcher = new  UriMatcher(UriMatcher.NO_MATCH);
	
	static 
	{
		//����ǰ��URI���һ��ƥ�����
		sURIMatcher.addURI("com.onezao.provider1224", "query", QUERY_MATCHED);
		sURIMatcher.addURI("com.onezao.provider1224", "insert", INSERT_MATCHED);
		sURIMatcher.addURI("com.onezao.provider1224", "insert", UPDATE_MATCHED);
		sURIMatcher.addURI("com.onezao.provider1224", "insert", DELETE_MATCHED);
	}

	@Override
	public boolean onCreate() {
		helper = new  MyOpenHelper(getContext());
		return false;
	}
   
	//��ѯ��ʱ���ǲ��ܹر����ݿ��
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		//ʹ��URIƥ���� ����URIƥ����жϣ����ƥ�����ˣ�������ȷ�Ľ�������û��ƥ���ϣ����׳��쳣
		int match = sURIMatcher.match(uri);
		if(match == QUERY_MATCHED){
		SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query( table, projection, selection, selectionArgs, null, null, sortOrder);
		return cursor;
		}   else  {
			throw   new  IllegalStateException(error);  
		}
	}

	@Override
	public String getType(Uri uri) {

		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int match = sURIMatcher.match(uri);
		if(match == INSERT_MATCHED){
		SQLiteDatabase db = helper.getReadableDatabase();
		long insert = db.insert(table, null, values);
		return Uri.parse(String.valueOf(insert));
		}  else  {
			return  null;
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int match = sURIMatcher.match(uri);
		if(match == DELETE_MATCHED){
		SQLiteDatabase db = helper.getReadableDatabase();
		int delete = db.delete(table, selection, selectionArgs);
		return delete;
		}  else {
			return  -1;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int match = sURIMatcher.match(uri);
		if(match == UPDATE_MATCHED){
		SQLiteDatabase db = helper.getReadableDatabase();
		int update = db.update(table, values, selection, selectionArgs);
		db.close();
		return  update;
		}   else {
			return -1;
		}
	}

}
