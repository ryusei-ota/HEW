package local.hal.st31.android.lastwork;

public class ManageList {
    /**
     * 承認ID
     */
    private int _user;

    /**
     * 主キーのID値。
     */
    private int _id;
    /**
     * 本人確認メールアドレス
     */
    private String _mail;
    /**
     * 本人確認パスワード
     */
    private String _password;
    /**
     * 次回自動ログイン
     */
    private String _free;
    /**
     * リストのID値
     */
    private int _listId;
    /**
     * 保存用の名前
     */
    private int _userId;
    /**
     * 保存用の名前
     */
    private String _name;
    /**
     * 保存したいPASS
     */
    private String _pass1;
    /**
     * ジャンル
     */
    private String _genre;
    /**
     * メモ
     */
    private String _memo;
    /**
     * 二重認証用PASS
     */
    private String _pass2;

    //以下アクセサメソッド。
    public int getUser() {
        return _user;
    }
    public void setUser(int user) {
        _user = user;
    }

    public int getId() {
        return _id;
    }
    public void setId(int id) {
        _id = id;
    }

    public String getMail() {
        return _mail;
    }
    public void setMail(String mail) {
        _mail = mail;
    }

    public String getPass() {
        return _password;
    }
    public void setPass(String pass) {
        _password = pass;
    }

    public String getFreeCheck() {
        return _free;
    }
    public void setFreeCheck(String freeCheck) {
        _free = freeCheck;
    }

    public int getListId() {
        return _listId;
    }
    public void setListId(int listId) {
        _listId = listId;
    }

    public int getUserId() { return _userId; }
    public void setUserId(int userId) {
        _userId = userId;
    }

    public String getListName() {
        return _name;
    }
    public void setListName(String listName) {
        _name = listName;
    }

    public String getListPass() {
        return _pass1;
    }
    public void setListPass(String listPass) {
        _pass1 = listPass;
    }

    public String getGenre() { return _genre;}
    public void setGenre(String genre) {
        _genre = genre;
    }

    public String getMemo() {
        return _memo;
    }
    public void setMemo(String memo) {
        _memo = memo;
    }

    public String getDoublePass() {
        return _pass2;
    }
    public void setDoublePass(String doublePass) {
        _pass2 = doublePass;
    }
}

