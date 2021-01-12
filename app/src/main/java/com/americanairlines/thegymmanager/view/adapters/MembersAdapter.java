package com.americanairlines.thegymmanager.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.americanairlines.thegymmanager.R;
import com.americanairlines.thegymmanager.model.db.GymMember;
import com.americanairlines.thegymmanager.model.db.SelectedMember;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MembersAdapter extends BaseAdapter {

    private List<GymMember> member;
    private Selected selected;
    private int previousPosition;
    private List<GymMember> selectedMemberList;

    private boolean isSelected;

    public interface Selected{
        void getSelected(List<GymMember> selectedMemberList);
    }

    public MembersAdapter(List<GymMember> member, Selected selected) {
        this.member = member;
        this.selected = selected;
        previousPosition = -1;

        selectedMemberList = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return member.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return member.get(position).getMembershipID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext() ).inflate(R.layout.item_members, parent, false);

        TextView memberName = view.findViewById(R.id.tv_member_name);
        TextView memberAge = view.findViewById(R.id.tv_member_age);
        View cardViewItem = view.findViewById(R.id.v_card);

        memberName.setText(member.get(position).getName());
        memberAge.setText(String.valueOf(member.get(position).getAge()));

        decideColor(position, parent, cardViewItem);

        cardViewItem.getBackground();


        cardViewItem.setOnClickListener(v->{
            if(member.get(position).isSelected()){
                decideColor(position, parent,cardViewItem);
                member.get(position).setSelected(false);
                selectedMemberList.remove(member.get(position));
                selected.getSelected(selectedMemberList);
            } else {
                cardViewItem.setBackgroundColor(parent.getContext().getResources().getColor(R.color.selected));
                member.get(position).setSelected(true);
                selectedMemberList.add(member.get(position));
                selected.getSelected(selectedMemberList);
            }
        });

        return view;
    }

    private void decideColor(int position, ViewGroup parent, View cardViewItem) {
        switch (member.get(position).getMembershipStatus()){
            case "Regular":
                cardViewItem.setBackgroundColor(parent.getContext().getResources().getColor(R.color.regular));
                break;
            case "Gold":
                cardViewItem.setBackgroundColor(parent.getContext().getResources().getColor(R.color.gold));
                break;
            case "Platinum":
                cardViewItem.setBackgroundColor(parent.getContext().getResources().getColor(R.color.platinum));
                break;
            default:
                break;
        }
    }
}
