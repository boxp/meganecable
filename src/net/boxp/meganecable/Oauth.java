package net.boxp.meganecable;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.view.View;
import android.util.Log;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationContext;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

// from http://techbooster.org/android/5040/ 
public class Oauth extends Activity {
 
    public static RequestToken _req = null;
    public static OAuthAuthorization _oauth = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("Oauth", "Success to open Oauth.");
        executeOauth();
    }

    private void executeOauth(){
        //Twitetr4Jの設定を読み込む
        Configuration conf = ConfigurationContext.getInstance();
 
        //Oauth認証オブジェクト作成
        _oauth = new OAuthAuthorization(conf);
        //Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
        _oauth.setOAuthConsumer("wEBi7xZIVIjhV6phyYLmQg", "j8nhSFbvApITevmQrOb1VYOK37xPCo5ScO2pbV6SA");
        //アプリの認証オブジェクト作成
        try {
            _req = _oauth.getOAuthRequestToken("Callback://CallBackActivity");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        String _uri;
        _uri = _req.getAuthorizationURL();
        //Open Browser
        startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(_uri)), 0);
    }
}
