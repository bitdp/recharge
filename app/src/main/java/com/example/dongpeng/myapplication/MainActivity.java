package com.example.dongpeng.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongpeng on 2016/12/27.
 */

public class MainActivity extends Activity {
    private TextView tv_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_lay);
        tv_money= (TextView) findViewById(R.id.text);
        RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        //防止recycleview闪烁
        ((SimpleItemAnimator)rv.getItemAnimator()).setSupportsChangeAnimations(false);
        GridLayoutManager manager=new GridLayoutManager(this,3);
        List<MoneyEntity> data=new ArrayList<MoneyEntity>();
        data.add(new MoneyEntity("5元",false));
        data.add(new MoneyEntity("10元",false));
        data.add(new MoneyEntity("30元",false));
        data.add(new MoneyEntity("50元",false));
        data.add(new MoneyEntity("100元",false));
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
