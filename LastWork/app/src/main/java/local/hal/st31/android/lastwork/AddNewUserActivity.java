package local.hal.st31.android.lastwork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddNewUserActivity extends AppCompatActivity {
    /**
     * 新規登録モードか更新モードかを表すフィールド。
     */
    private int _mode = MainActivity.MODE_INSERT;
    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        _helper = new DatabaseHelper(AddNewUserActivity.this);

        Intent intent = getIntent();
        _mode = intent.getIntExtra("mode", MainActivity.MODE_INSERT);

    }

    /**
     * アクションバーの遷移先
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 新規登録をする
     * @param view
     */
    public void onSaveButtonClickUser(View view) {
        EditText etInputMail = findViewById(R.id.etUserMail);
        String inputMail = etInputMail.getText().toString();
        EditText etInputPass = findViewById(R.id.etUserPass);
        String inputPass = etInputPass.getText().toString();
        if(inputPass.equals("")||inputMail.equals("")) {
        }
        else {
            SQLiteDatabase db = _helper.getWritableDatabase();
            if(_mode == MainActivity.MODE_INSERT) {
                DataAccess.insertUser(db, inputMail, inputPass);
            }
            finish();
        }
    }

}
