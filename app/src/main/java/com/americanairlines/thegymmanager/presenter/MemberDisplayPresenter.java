package com.americanairlines.thegymmanager.presenter;

import com.americanairlines.thegymmanager.model.db.GymMember;
import com.americanairlines.thegymmanager.model.db.MemberDatabaseHelper;

import static com.americanairlines.thegymmanager.presenter.MembershipPresenterContract.*;

public class MemberDisplayPresenter implements MembershipPresenter{

    private MembershipView membershipView;
    private MemberDatabaseHelper memberDatabaseHelper;

    public MemberDisplayPresenter(MembershipView membershipView) {
        this.membershipView = membershipView;
        memberDatabaseHelper = new MemberDatabaseHelper(membershipView.getContext());
    }

    @Override
    public void getAllMembers() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    membershipView.displayMembers(memberDatabaseHelper.getAllMembers());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void deleteMember(GymMember deleteMember) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                
            }
        }
    }
}
