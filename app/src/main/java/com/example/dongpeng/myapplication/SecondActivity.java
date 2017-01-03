package com.example.dongpeng.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongpeng on 2016/12/27.
 */

public class SecondActivity extends Activity {
    private TextView tv_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_lay);
        tv_money= (TextView) findViewById(R.id.text);
        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        rv.setItemAnimator(new MyItemAnimator());
        GridLayoutManager manager=new GridLayoutManager(this,3);
        List<String> data=new ArrayList<>();
        data.add("5元");
        data.add("10元");
        data.add("20元");
        data.add("50元");
        data.add("100元");
        MyAdapter adapter=new MyAdapter(data,this);
        adapter.setMoneyInputListener(new MoneyInputListener() {
            @Override
            public void onGetMoneyInput(String money) {
                tv_money.setText(money);
            }
        });
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }
}
