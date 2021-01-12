package com.americanairlines.thegymmanager.presenter;

import android.widget.Toast;

import com.americanairlines.thegymmanager.model.db.GymMember;
import com.americanairlines.thegymmanager.model.db.MemberDatabaseHelper;

public class MemberAddPresenter implements MembershipPresenterContract.AddMemberPresenter {

    private MembershipPresenterContract.AddMemberView addMemberView;
    private MemberDatabaseHelper memberDatabaseHelper;

    public MemberAddPresenter(MembershipPresenterContract.AddMemberView addMemberView) {
        this.addMemberView = addMemberView;
        memberDatabaseHelper = new MemberDatabaseHelper(addMemberView.getContext());
    }

    @Override
    public void insertMember(GymMember newMember) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    memberDatabaseHelper.insertMemberIntoDatabase(newMember);
                    Toast.makeText(addMemberView.getContext(), "Member Added", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
