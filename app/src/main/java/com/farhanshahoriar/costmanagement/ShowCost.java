package com.farhanshahoriar.costmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowCost extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView costInfoTV;
    private TransListAdapter transListAdapter;
    private int totalCost=0;
    private ArrayList<TransInfo> transList = null;
    private ArrayList<UserInfo> userInfos = null;
    SharedPreferences sharedPref;
    Context mContext;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cost);
        costInfoTV = findViewById(R.id.tv_cost_info);
        mContext = this.getApplicationContext();
        sharedPref = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        final String username = sharedPref.getString("username","false");
        String groupID = sharedPref.getString("groupID","false");

        transList = new ArrayList<>();
        userInfos = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(groupID).child("alltrans");
        DatabaseReference userRef = database.getReference(groupID).child("user");
        //myRef.keepSynced(true);
        //final DatabaseReference dueRef = database.getReference("shuvoent").child("customers").child(alidx).child("duePayment");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //if(!dataSnapshot.exists())return;
                transList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    TransInfo info = postSnapshot.getValue(TransInfo.class);
                    transList.add(info);
                }

                transListAdapter.setData(transList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        userRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userInfos.clear();
                totalCost=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    int cost = postSnapshot.getValue(Integer.class);
                    String uname =postSnapshot.getKey();
                    totalCost+=cost;
                    userInfos.add(new UserInfo(uname,cost,0));

                }
                ///transListAdapter.setData(transList);
                costInfoTV.setText("Total Cost: "+totalCost+"\n");
                int numperson = userInfos.size();
                double avgcost=0;
                if(numperson>0) avgcost = (double)totalCost/numperson;
                for(UserInfo cuser : userInfos){
                    double balance = cuser.pCost-avgcost;
                    if(balance>=0){
                        costInfoTV.append("\n"+username+" spend: "+cuser.pCost+", will get: " +balance);
                    }
                    else {
                        balance=-balance;
                        costInfoTV.append("\n"+username+" spend: "+cuser.pCost +", will give: " +balance);
                    }


                }
                costInfoTV.append("\n");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        recyclerView = (RecyclerView) findViewById(R.id.rv_tran_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        transListAdapter = new TransListAdapter(this);
        recyclerView.setAdapter(transListAdapter);
        //transListAdapter.setData(transList);
    }

}
