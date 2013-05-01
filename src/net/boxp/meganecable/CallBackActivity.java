package net.boxp.meganecable;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.net.Uri;
import android.content.Intent;
import android.widget.TextView;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class CallBackActivity extends Activity {
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.callback);

    AccessToken token = null;

    //Take Uri from Twitter
    Uri uri = getIntent().getData();

    if (uri != null && uri.toString().startsWith("Callback://CallBackActivity")) {
      //Take oauth_verifier
      String verifier = uri.getQueryParameter("oauth_verifier");
      try {
        //Take AccessToken
        token = Oauth._oauth.getOAuthAccessToken(Oauth._req, verifier);
      } catch (TwitterException e) {
        e.printStackTrace();
      }
    }

    //Print tokens
    TextView tv = (TextView)findViewById(R.id.textView1);
    CharSequence cs = "token:" + token.getToken() + "\r\n" + "token secret:" + token.getTokenSecret();
    tv.setText(cs);

    //Save tokens
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
    sp.edit().putString("token", token.getToken()).commit();
    sp.edit().putString("tokenSecret", token.getTokenSecret()).commit();

    Intent intent = new Intent(CallBackActivity.this, MainActivity.class);
    startActivity(intent);
  }
}
