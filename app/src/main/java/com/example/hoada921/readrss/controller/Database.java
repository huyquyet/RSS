package com.example.hoada921.readrss.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.hoada921.readrss.models.Category;
import com.example.hoada921.readrss.models.Data;
import com.example.hoada921.readrss.models.Informations;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 18/03/2016.
 */
public class Database {
    Activity context;
    Cursor cursor = null;

    public Database(Activity context) {
        this.context = context;
    }
//    private SQLiteDatabase database = null;

    private boolean isDatabaseExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("Select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public void insertDataBase(SQLiteDatabase database) {
        try {
            if (database != null) {
                if (!isDatabaseExists(database, "tblPost")) {
                    /**
                     * ko ton tai bang => tao moi CSDL trong
                     * bat dau tao moi cac bang
                     */
                    database.setLocale(Locale.getDefault());
                    database.setVersion(1);
                    /**
                     * Create table category
                     */
                    String sqlCategory = "create table tblCategory (" +
                            "id integer primary key autoincrement," +
                            "name)";
                    database.execSQL(sqlCategory);
                    Toast.makeText(context, "Insert tblCategory success !", Toast.LENGTH_SHORT).show();

                    /**
                     * Create table tblInformation
                     */
                    String sqlInformations = "create table tblInformation (" +
                            "id integer primary key autoincrement, " +
                            "title text, " +
                            "link text, " +
                            "description text, " +
                            "image text, " +
                            "language text, " +
                            "copyright text, " +
                            "ttl text, " +
                            "lastBuildDate text," +
                            "generator text, " +
                            "atom text)";
                    database.execSQL(sqlInformations);
                    Toast.makeText(context, "Insert tblInformation success !", Toast.LENGTH_SHORT).show();

                    /**
                     * Create table tblPost
                     */
                    String sqlPost = "create table tblPost (" +
                            "id integer primary key autoincrement, " +
                            "title text, " +
                            "description text, " +
                            "link text, " +
                            "guid text, " +
                            "pubDate text, " +
                            "category text, " +
                            "author text, " +
                            "enclosure text)";
                    database.execSQL(sqlPost);
                    Toast.makeText(context, "Insert tblPost success !", Toast.LENGTH_SHORT).show();

                    /**
                     * Create table tblTimeUpdate
                     */
                    String sqlTimeUpdate = " create table tblTimeUpdate (" +
                            "time text)";
                    database.execSQL(sqlTimeUpdate);
                    Toast.makeText(context, "Insert tblTimeUpdate success !", Toast.LENGTH_SHORT).show();

                    /**
                     * Create table tblCategoryPost
                     */
                    String sqlCategory_post = "create table tblCategoryPost(" +
                            "id_post integer not null constraint id_post references tblPost(id) on delete cascade," +
                            "id_category integer not null constraint id_category references tblCategory(id) on delete cascade," +
                            "category_primary integer)";

                    database.execSQL(sqlCategory_post);
                    Toast.makeText(context, "Insert tblCategoryPost success !", Toast.LENGTH_SHORT).show();
                    Category category = new Category();
                    ContentValues values = new ContentValues();
                    for (Category category1 : category.getCategoryArrayList()) {
                        values.put("name", category1.getName());
                        long check = database.insert("tblCategory", null, values);
                        if (check == -1) {
                            Toast.makeText(context, "Error insert data Category", Toast.LENGTH_SHORT).show();
                            delete_Database(database);
                            break;
                        } else
                            values.clear();

                    }
                    Toast.makeText(context, "Insert database CategoryPost success !", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Insert database success !", Toast.LENGTH_LONG).show();
                }   // End if (!isDatabaseExists(database, "tblPost"))
                database.close();
            }   // End if(database != null)
        } catch (Exception ex) {
            Log.d("error", ex.toString());
            delete_Database(database);
        }
    }

    public void delete_Database(SQLiteDatabase database) {
        String msg = "";
        if (context.deleteDatabase("readRss.db")) {
            msg = " Delete data success !";
        } else
            msg = " Delete data error !";
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public void insertOrUpdateDataPost(SQLiteDatabase sqLiteDatabase, ArrayList<Data> data) {
        if (checkDataPost(sqLiteDatabase)) {
            updateDataPost(sqLiteDatabase, data);
            sqLiteDatabase.close();
        } else {
            insertDataPost(sqLiteDatabase, data);
            sqLiteDatabase.close();
        }
    }

    public void insertOrUpdateDataInformation(SQLiteDatabase sqLiteDatabase, Informations data) {
        int id_information = checkDataInformation(sqLiteDatabase);
        if (id_information != -1) { //  Exists data in table
            updateDataInformation(sqLiteDatabase, data, id_information);
            sqLiteDatabase.close();
        } else {
            insertDataInformation(sqLiteDatabase, data);
            sqLiteDatabase.close();
        }
    }

    public void updateTime(String date) {

    }

    private int checkDataInformation(SQLiteDatabase sqLiteDatabase) {
        int id_information = -1;
        cursor = sqLiteDatabase.query("tblInformation", null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id_information = Integer.parseInt(cursor.getString(0));
                cursor.close();
                return id_information;
            }
            cursor.close();
        }
        return id_information;
    }

    private void updateDataInformation(SQLiteDatabase sqLiteDatabase, Informations data, int id_information) {
        ContentValues values = new ContentValues();
        values.put("title", data.getTitle());
        values.put("link", data.getLink());
        values.put("description", data.getDescription());
        values.put("image", data.getImage());
        values.put("language", data.getLanguage());
        values.put("copyright", data.getCopyright());
        values.put("ttl", data.getTtl());
        values.put("lastBuildDate", data.getLastBuildDate());
        values.put("generator", data.getGenerator());
        values.put("atom", data.getAtom());
        int check = sqLiteDatabase.update("tblInformation", values, "id=?", new String[]{String.valueOf(id_information)});
        if (check > 0) {
            values.clear();
            Toast.makeText(context, "Update data Information success !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error update data Information !", Toast.LENGTH_SHORT).show();
            values.clear();
        }
    }

    private void insertDataInformation(SQLiteDatabase sqLiteDatabase, Informations data) {
        ContentValues values = new ContentValues();
        values.put("title", data.getTitle());
        values.put("link", data.getLink());
        values.put("description", data.getDescription());
        values.put("image", data.getImage());
        values.put("language", data.getLanguage());
        values.put("copyright", data.getCopyright());
        values.put("ttl", data.getTtl());
        values.put("lastBuildDate", data.getLastBuildDate());
        values.put("generator", data.getGenerator());
        values.put("atom", data.getAtom());
        long check = sqLiteDatabase.insert("tblInformation", null, values);
        if (check == -1) {
            Toast.makeText(context, "Error insert data Information !", Toast.LENGTH_SHORT).show();
            values.clear();
        } else {
            Toast.makeText(context, "Insert data Information success !", Toast.LENGTH_SHORT).show();
            values.clear();
        }
    }

    private boolean checkDataPost(SQLiteDatabase sqLiteDatabase) {
        cursor = sqLiteDatabase.query("tblPost", null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    private void updateDataPost(SQLiteDatabase sqLiteDatabase, ArrayList<Data> data) {

    }

    private void insertDataPost(SQLiteDatabase sqLiteDatabase, ArrayList<Data> data) {
        ContentValues values = new ContentValues();
        int check = 0;
        for (Data item : data) {
            values.put("title", item.getTitle());
            values.put("description", item.getDescription());
            values.put("link", item.getLink());
            values.put("guid", item.getGuid());
            values.put("pubDate", item.getPubDate());
            values.put("category", item.getCategory());
            values.put("author", item.getAuthor());
            values.put("enclosure", item.getEnclosure());
            long id_post = sqLiteDatabase.insert("tblPost", null, values);
            if (id_post == -1) {
                Toast.makeText(context, "Error insert data Post !", Toast.LENGTH_SHORT).show();
                values.clear();
                check = 1;
//                break;
            } else {
                insertDataPostCategory(sqLiteDatabase, item.getCategory(), item.getarrayListcategory(), id_post);
                values.clear();
            }
        }
        if (check == 0)
            Toast.makeText(context, "Insert data Post  success !", Toast.LENGTH_LONG).show();

    }

    private void insertDataPostCategory(SQLiteDatabase sqLiteDatabase, String category, ArrayList<String> arrCategory, long id_post) {
        String id_category = null;
        ContentValues values = new ContentValues();
        for (String item_category : arrCategory) {
            id_category = selectIdCategoryByName(sqLiteDatabase, item_category);
            values.put("id_post", id_post);
            values.put("id_category", id_category);
            if (item_category.equalsIgnoreCase(category))
                values.put("category_primary", 1);
            else
                values.put("category_primary", 0);
            long id = sqLiteDatabase.insert("tblCategoryPost", null, values);
            if (id == -1) {
                Toast.makeText(context, "Error insert data Category !", Toast.LENGTH_SHORT).show();
                values.clear();
            } else
                values.clear();
        }
    }

    private String selectIdCategoryByName(SQLiteDatabase sqLiteDatabase, String name) {
        String id_category = null;
        try {
            cursor = sqLiteDatabase.query("tblCategoryPost", null, "name = ?", new String[]{name}, null, null, null, null);
            cursor.moveToFirst();
            id_category = cursor.getString(0);
        } catch (Exception ex) {
            id_category = "0";
        } finally {
            cursor.close();
        }
        return id_category;
    }
}
