package com.farhanshahoriar.costmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText userNameET;
    private EditText groupIDET;
    private Intent intentCA;
    SharedPreferences sharedPref;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = (EditText)findViewById(R.id.et_username);
        groupIDET = (EditText) findViewById(R.id.et_group_id);
        mContext = getApplicationContext();
        intentCA = new Intent(this,CostInput.class);

        sharedPref = mContext.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String loginStr = sharedPref.getString("username","false");
        if(!loginStr.equals("false")){
            startActivity(intentCA);
            finish();
        }

    }

    public void onClickLogin(View view){
        String username = userNameET.getText().toString().trim();
        String groupID = groupIDET.getText().toString().trim();
        if(username.equals("")||groupID.equals("")){
            //Toast.makeText(mContext,"Invalid ID or Password",Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",username);
        editor.putString("groupID",groupID);
        editor.apply();
        startActivity(intentCA);
        finish();
    }
}
