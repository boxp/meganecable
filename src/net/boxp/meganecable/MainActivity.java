package net.boxp.meganecable;

import twitter4j.TwitterFactory;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //テキスト入力を受け付けるビューを作成します。
        final EditText editView = new EditText(MainActivity.this);
        new AlertDialog.Builder(MainActivity.this)
            .setIcon(android.R.drawable.ic_dialog_info)
            .setTitle("テキスト入力ダイアログ")
            //setViewにてビューを設定します。
            .setView(editView)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //入力した文字をトースト出力する
                    Toast.makeText(MainActivity.this, 
                            editView.getText().toString(), 
                            Toast.LENGTH_LONG).show();
                }
            })
            .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            })
            .show();
    }
}
