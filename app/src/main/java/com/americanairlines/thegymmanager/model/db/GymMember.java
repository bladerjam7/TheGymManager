package com.americanairlines.thegymmanager.model.db;

public class GymMember {
    private int membershipID;
    private String name;
    private int age;
    private String membershipStatus;
    private boolean isSelected;

    public GymMember(int membershipID, String name, int age, String membershipStatus) {
        this.membershipID = membershipID;
        this.name = name;
        this.age = age;
        this.membershipStatus = membershipStatus;
        isSelected = false;
    }

    public GymMember(String name, int age, String membershipStatus) {
        this.name = name;
        this.age = age;
        this.membershipStatus = membershipStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMembershipStatus(String membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
