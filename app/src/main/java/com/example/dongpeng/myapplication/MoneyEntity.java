package com.example.dongpeng.myapplication;

/**
 * Created by dongpeng on 2017/1/3.
 */

public class MoneyEntity {
    private String money;
    private boolean isSelected;

    public MoneyEntity(String money, boolean isSelected) {
        this.money = money;
        this.isSelected = isSelected;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
