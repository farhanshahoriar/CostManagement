package com.farhanshahoriar.costmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CostInput extends AppCompatActivity {
    SharedPreferences sharedPref;
    Context mContext;
    FirebaseDatabase database;
    DatabaseReference groupRef;

    private String username;
    private String groupID;
    private String userInfo;
    private TextView userInfoTV;
    private EditText costInfoET;
    private EditText costamountET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_input);
        userInfoTV = findViewById(R.id.user_info);
        costInfoET = findViewById(R.id.et_cost_info);
        costamountET = findViewById(R.id.et_cost_amount);

        mContext = getApplicationContext();
        sharedPref = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        username = sharedPref.getString("username", "false");
        groupID = sharedPref.getString("groupID", "false");

        userInfo = "Group: " + groupID + " " + username + "\n";
        userInfoTV.setText(userInfo);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        groupRef = database.getReference(groupID);
        //groupRef.setValue(username);


    }

    public void onClickAddCost(View view) {
        String costInfo = costInfoET.getText().toString().trim();
        String costAmount = costamountET.getText().toString().trim();
        //costamountET.clearAnimation();
        //costInfoET.clearComposingText();
        if (costAmount.equals("")) {
            //Toast.makeText(mContext,"Invalid ID or Password",Toast.LENGTH_LONG).show();
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        String currentTime = sdf.format(new Date());
        TransInfo newtrans = new TransInfo(currentTime, costInfo, username, Integer.valueOf(costAmount));
        groupRef.child("alltrans").push().setValue(newtrans);
        groupRef.child("user").child(username).push().setValue(Integer.valueOf(costAmount));

        costInfoET.setText("");
        costamountET.setText("");

    }

    public void onClickShowCost(View view) {
        Intent intent = new Intent(this, ShowCost.class);
        startActivity(intent);
    }
}
