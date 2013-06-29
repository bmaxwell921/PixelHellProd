package com.theoc.pixhell.db;

import java.sql.SQLDataException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.theoc.pixhell.model.PersistentConsumable;

public class PixhellDBHelper extends SQLiteOpenHelper {

	// Keeping version constant to persist data across app updates
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "pixhellDB";

	private static interface TableConsumables {
		String TABLE_NAME = "PurchasedConsumable";
		String ITEMSKU = "item_sku";
		String COUNT = "count";
	}

	public PixhellDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {

		String CREATE_CONSUMABLES_TABLE = "CREATE TABLE "
				+ TableConsumables.TABLE_NAME + "(" + TableConsumables.ITEMSKU
				+ " TEXT," + TableConsumables.COUNT + " TEXT)";
		db.execSQL(CREATE_CONSUMABLES_TABLE);
	}

	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		/*
		 * arg0.execSQL("DROP TABLE IF EXISTS " + TableConsumables.TABLE_NAME);
		 * onCreate(arg0)
		 */;
	}

	public int updatePersistantConsumable(
			PersistentConsumable persistentConsumable) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TableConsumables.TABLE_NAME,
				new String[] { TableConsumables.COUNT },
				TableConsumables.ITEMSKU + "=?",
				new String[] { persistentConsumable.getSkuString() }, null,
				null, null, null);
		int result = 0;
		if (cursor != null && cursor.getCount() > 0) {
			SQLiteDatabase db2 = this.getWritableDatabase();
			Integer currentCount = cursor.getInt(cursor
					.getColumnIndex(TableConsumables.COUNT));
			if (persistentConsumable.isIncrement())
				currentCount += persistentConsumable.getCount();
			else {
				currentCount -= persistentConsumable.getCount();
			}
			result = currentCount;
			ContentValues values = new ContentValues();
			values.put(TableConsumables.COUNT, currentCount);
			db2.update(TableConsumables.TABLE_NAME, values,
					TableConsumables.ITEMSKU + "=?",
					new String[] { persistentConsumable.getSkuString() });
			db2.close();
		} else {
			SQLiteDatabase db2 = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(TableConsumables.ITEMSKU,
					persistentConsumable.getSkuString());
			values.put(TableConsumables.COUNT, result);

			db2.insert(TableConsumables.TABLE_NAME, null, values);
			db2.close();
		}
		db.close();
		return result;
	}

	public int getPersistantConsumableCount(String iapSku)
			throws SQLDataException {
		SQLiteDatabase db = getReadableDatabase();
		int result = -1;
		Cursor cursor = db.query(TableConsumables.TABLE_NAME,
				new String[] { TableConsumables.COUNT },
				TableConsumables.ITEMSKU + "=?", new String[] { iapSku }, null,
				null, null, null);
		if (cursor.getCount() != 1) {
			// IAP Sku doesn't exist
			throw new SQLDataException();
		}
		result = cursor.getInt(cursor.getColumnIndex(TableConsumables.COUNT));
		db.close();
		return result;
	}

	private void printAllTables() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query("sqlite_master", new String[] { "name" }, "type" + "=?",
						new String[] { "table" }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				System.out.println(cursor.getString(0));
				cursor.moveToNext();
			}
		}
	}

	public ArrayList<StoreItemDTO> getStoreData() {
		ArrayList<StoreItemDTO> result = null;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM" + DATABASE_NAME, null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {

				result = new ArrayList<StoreItemDTO>();
				cursor.moveToLast();
				while (!cursor.isLast()) {
					StoreItemDTO temp = new StoreItemDTO();
					//temp.setSku(cursor.getString(0));
					//temp.setCount(cursor.getString(1));
					result.add(temp);
				}
			}
		}
		
		return result;
	}
}
