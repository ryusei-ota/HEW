package local.hal.st31.android.lastwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DataAccess {
    /**
     * 全データ検索メソッド。
     *
     * @param db SQLiteDatabaseオブジェクト。
     * @return 検索結果のCursorオブジェクト。
     */
    public static Cursor findAll(SQLiteDatabase db) {
        String sql = "SELECT * FROM list";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public static Cursor findSearch(SQLiteDatabase db,String Genre) {
        String sql = "";
        if(Genre.equals("選択なし")){
            sql = "SELECT * FROM list";
        }else{
            sql = "SELECT * FROM list WHERE genre =\"" + Genre + "\"";
        }
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    /**
     * メールとパスワードが同じなのかを判定
     * @param db
     * @param mail
     * @param pass
     * @return
     */
    public static Boolean search(SQLiteDatabase db, String mail, String pass) {
        String sql = "select * from user where mail = \"" + mail + "\";";
        Cursor cursor = db.rawQuery(sql, null);
        ManageList result = null;
        Boolean flg = false;
        if(cursor.moveToFirst()) {
            int idxMail = cursor.getColumnIndex("mail");
            int idxPass = cursor.getColumnIndex("password");
            mail = cursor.getString(idxMail);
            String password = cursor.getString(idxPass);

            if(pass.equals(password)){
                result = new ManageList();
                result.setMail(mail);
                result.setPass(pass);
                flg = true;
            }else{
                flg = false;
            }
        }
        return flg;
    }

    /**
     * ログインしたユーザーIDを検索
     * @param db
     * @param mail
     * @return
     */
    public static ManageList searchId(SQLiteDatabase db, String mail) {
        String sql = "select * from user where mail = \"" + mail + "\";";
        Cursor cursor = db.rawQuery(sql, null);
        ManageList result = null;
        if(cursor.moveToFirst()) {
            int idxUser = cursor.getColumnIndex("_id");
            int user = cursor.getInt(idxUser);

            result = new ManageList();
            result.setUser(user);
        }
        return result;
    }

    /**
     * 新規ユーザーの登録
     * @param db
     * @param mail
     * @param password
     * @return
     */
    public static long insertUser(SQLiteDatabase db, String mail, String password) {
        String sql = "INSERT INTO user (mail, password, free) VALUES (?, ?,0)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, mail);
        stmt.bindString(2, password);
        long id = stmt.executeInsert();
        return id;
    }

    /**
     * リスト検索
     * @param db
     * @param id
     * @return
     */
    public static ManageList findByPK(SQLiteDatabase db, long id) {
        String sql = "SELECT * FROM list WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        ManageList result = null;
        if(cursor.moveToFirst()) {
            int idxUserId = cursor.getColumnIndex("userId");
            int idxName = cursor.getColumnIndex("name");
            int idxPass1 = cursor.getColumnIndex("pass1");
            int idxGenre = cursor.getColumnIndex("genre");
            int idxMemo = cursor.getColumnIndex("memo");

            int userId = cursor.getInt(idxUserId);
            String name = cursor.getString(idxName);
            String pass1 = cursor.getString(idxPass1);
            String genre = cursor.getString(idxGenre);
            String memo = cursor.getString(idxMemo);

            result = new ManageList();
            result.setUserId(userId);
            result.setListName(name);
            result.setListPass(pass1);
            result.setGenre(genre);
            result.setMemo(memo);
        }
        return result;
    }

    /**
     * 新規リスト登録
     * @param db
     * @param name
     * @param pass1
     * @param genre
     * @param memo
     * @return
     */
    public static long insert(SQLiteDatabase db, String name, String pass1, String genre, String memo) {
        String sql = "INSERT INTO list (userId, name, pass1, genre, memo, pass2) VALUES (?, ?, ?, ?, ?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, "");
        stmt.bindString(2, name);
        stmt.bindString(3, pass1);
        stmt.bindString(4, genre);
        stmt.bindString(5, memo);
        stmt.bindString(6, "");
        long id = stmt.executeInsert();
        return id;
    }

    /**
     * リストの更新
     * @param db
     * @param id
     * @param name
     * @param pass1
     * @param genre
     * @param memo
     * @return
     */
    public static int update(SQLiteDatabase db, long id, String name, String pass1, String genre, String memo) {
        String sql = "UPDATE list SET name = ?, pass1 = ?, genre = ?, memo = ? WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);

        stmt.bindString(1, name);
        stmt.bindString(2, pass1);
        stmt.bindString(3, genre);
        stmt.bindString(4, memo);
        stmt.bindLong(5, id);
        int result = stmt.executeUpdateDelete();
        return result;
    }

    /**
     * リストの削除
     * @param db
     * @param id
     * @return
     */
    public static int delete(SQLiteDatabase db, long id) {
        String sql = "DELETE FROM list WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindLong(1, id);
        int result = stmt.executeUpdateDelete();
        return result;
    }
}
