package local.hal.st31.android.lastwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * 新規登録モードを表す定数フィールド。
     */
    static final int MODE_INSERT = 1;
    /**
     * 更新モードを表す定数フィールド。
     */
    static final int MODE_EDIT = 2;
    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _helper = new DatabaseHelper(MainActivity.this);

        /**
         * 新規登録をする
         */
        Button topSignupButton = findViewById(R.id.btTopSignup);
        topSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), AddNewUserActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * dbのヘルパー定義
     */
    @Override
    protected void onResume() {
        super.onResume();
        SQLiteDatabase db = _helper.getWritableDatabase();
    }

    /**
     * 入力された値から本人確認をする
     * @param view
     */
    public void onSaveButtonClickLogin(View view) {
        EditText etInputMail = findViewById(R.id.etTopMail);
        String inputMail = etInputMail.getText().toString();
        EditText etInputPass = findViewById(R.id.etTopPass);
        String inputPass = etInputPass.getText().toString();
        if(inputPass.equals("") && inputMail.equals("")) {
            Toast.makeText(MainActivity.this, R.string.msg_err_title, Toast.LENGTH_SHORT).show();
        }else if(inputPass.equals("")) {
            Toast.makeText(MainActivity.this, R.string.msg_pass_title, Toast.LENGTH_SHORT).show();
        }else if(inputMail.equals("")){
            Toast.makeText(MainActivity.this, R.string.msg_mail_title, Toast.LENGTH_SHORT).show();
        }
        else{
            SQLiteDatabase db = _helper.getWritableDatabase();
            Boolean loginFlg = DataAccess.search(db, inputMail, inputPass);
            if(loginFlg.equals(true)){
                DataAccess.searchId(db, inputMail);
                Intent intent = new Intent(getApplication(), EditActivityList.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, R.string.msg_input_title, Toast.LENGTH_SHORT).show();
            }
        }
    }
}