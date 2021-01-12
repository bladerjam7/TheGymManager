package com.americanairlines.thegymmanager.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.americanairlines.thegymmanager.R;
import com.americanairlines.thegymmanager.model.db.GymMember;
import com.americanairlines.thegymmanager.presenter.MemberDisplayPresenter;
import com.americanairlines.thegymmanager.presenter.MembershipPresenterContract;
import com.americanairlines.thegymmanager.view.adapters.MembersAdapter;

import java.lang.reflect.Member;
import java.util.List;

import static com.americanairlines.thegymmanager.presenter.MembershipPresenterContract.*;

public class GymMemberActivity extends AppCompatActivity implements MembershipView, MembersAdapter.Selected {

    private MembershipPresenter presenter;

    private ListView lvGymMembers;
    private MembersAdapter adapter;

    private List<GymMember> gymMemberList;

    private Button btnAdd;
    private Button btnDelete;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvGymMembers = findViewById(R.id.lv_member_list);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);

        presenter = new MemberDisplayPresenter(this);
        //presenter.getAllMembers();

        btnAdd.setOnClickListener(v->{
            startActivity(new Intent(GymMemberActivity.this, AddMemberActivity.class));
        });

        btnDelete.setOnClickListener(v->{
            for(GymMember member: gymMemberList){
                presenter.deleteMember(member);
            }
            presenter.getAllMembers();
        });

    }

    @Override
    public void displayMembers(List<GymMember> allMembers) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG_Z", String.valueOf(allMembers.size()));
                adapter = new MembersAdapter(allMembers, GymMemberActivity.this);
                lvGymMembers.setAdapter(adapter);
            }
        });


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        Log.d("TAG_Y", "onResume()");
        presenter.getAllMembers();
    }

    @Override
    public void getSelected(List<GymMember> memberList) {
        gymMemberList = memberList;
    }
}