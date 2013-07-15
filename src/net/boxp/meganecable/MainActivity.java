package net.boxp.meganecable;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.SharedPreferences;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //SharedPreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        //Read SharedPreferences
        String token = sp.getString("token",null);
        String tokenSecret = sp.getString("tokenSecret",null);

        //OAuth
        if (token == null || tokenSecret == null) {
          //Move to OAuth activity
          Intent intent = new Intent(MainActivity.this, Oauth.class);
          startActivity(intent);
        } else {

          //twitter object
          final Twitter tw = new TwitterFactory().getInstance();
          //AccessToken object
          AccessToken at = new AccessToken(token, tokenSecret);
          //Consumer key, Consumer key secret
          tw.setOAuthConsumer("DZPpj7XHoSqWEiL736cpjw", "H4KAPjY1N4qhIFJCpVftgIKVNVcz2bwOLX8HT8NcJAc");
          //Set AccessToken object
          tw.setOAuthAccessToken(at);

        //IS01
        try{

            Class<?> sgManager = Class.forName("jp.co.sharp.android.softguide.SoftGuideManager");
            Class<?> paramstype[] = {boolean.class};
            setFullScreenMode = sgManager.getMethod("setFullScreenMode", paramstype);

        }catch(Exception o){

            Log.d("is01fullscreen", "failed" + o.getMessage() + ":" + o.getClass().toString());

        }

          // Text edit view
          final EditText editView = new EditText(MainActivity.this);
          new AlertDialog.Builder(MainActivity.this)
              .setIcon(android.R.drawable.ic_dialog_info)
              .setTitle("meganecable")
              // View
              .setView(editView)
              .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int whichButton) {
                      // Post input strings
                      try {
                        tw.updateStatus(editView.getText().toString());
                        Toast.makeText(MainActivity.this, 
                                    "Success! -> " + editView.getText().toString(), 
                                    Toast.LENGTH_LONG).show();
                        MainActivity.this.finish();
                      } catch (TwitterException e) {
                          e.printStackTrace();
                          if (e.isCausedByNetworkIssue()) {
                            Toast.makeText(MainActivity.this, 
                                    "Something has wrong.", 
                                    Toast.LENGTH_LONG).show();
                            
                          }
                      }
                      

                  }
              })
              .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int whichButton) {
                        MainActivity.this.finish();
                  }
              })
              .show();
      }
    }

    //IS01
    @Override
    public void onResume(){
        super.onResume();
        try{
            setFullScreenMode.invoke(null, true);
        }catch(Exception o){
            Log.d("is01fullscreen", "failed");
        }
    }

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event) {
      Log.v("KeyDown", "KeyCode=" + keyCode);
      return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
      Log.v("keyUp", "KeyCode=" + keyCode);
      return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
      String action="";

      switch(event.getAction()) {
      case keyEvent.ACTION_COWN:
        action = "Key Down";
        break;
      case keyEvent.ACTION_UP:
        action = "Key Up";
        break;
      case keyEvent.ACTION_MULTIPLE:
        action = "Key Multiple";
        break;
      }
      
      Log.v("KeyEvent", action + ":keyCode=" + event.getKeyCode());
      return super.dispatchKeyEvent(event);
    }
}
