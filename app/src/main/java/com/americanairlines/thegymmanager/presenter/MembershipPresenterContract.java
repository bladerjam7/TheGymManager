package com.americanairlines.thegymmanager.presenter;

import android.content.Context;

import com.americanairlines.thegymmanager.model.db.GymMember;

import java.util.List;

public interface MembershipPresenterContract {

    interface MembershipPresenter{
        void getAllMembers();
        void deleteMember(GymMember deleteMember);
    }

    interface MembershipView{
        void displayMembers(List<GymMember> allMembers);
        Context getContext();
    }

    interface AddMemberPresenter {
        void insertMember(GymMember newMember);
    }

    interface AddMemberView{
        void displayError(String errorMessage);
        void displaySuccess(String successMessage);
        Context getContext();
    }
}
