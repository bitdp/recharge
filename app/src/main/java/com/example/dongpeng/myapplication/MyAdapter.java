package com.example.dongpeng.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import static com.example.dongpeng.myapplication.R.id.et;

/**
 * Created by dongpeng on 2017/1/3.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private MoneyInputListener listener;
    private List<MoneyEntity> data;
    private EditText editText;
    private int oldPostion = -1;
    private Context context;

    public MyAdapter(List<MoneyEntity> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == data.size()) {//最后一个
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MyTextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, null));
        } else {
            return new MyEditViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position == data.size()) { //position 0-5,size=5
            editText = ((MyEditViewHolder) holder).editText;
            //当选择项目从TextView转移到EditText，editText重新获取焦点
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        notifyItem(oldPostion);
                        editText.setSelected(true);
                        listener.onGetMoneyInput(getMoneyString(editText.getText().toString()));
                    }
                }
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void afterTextChanged(Editable s) {
                    listener.onGetMoneyInput(getMoneyString(s.toString()));
                    String temp = s.toString();
                    int d = temp.indexOf(".");
                    if (d < 0) return;
                    if (temp.length() - d - 1 > 2) {
                        s.delete(d + 3, d + 4);
                    } else if (d == 0) {
                        s.delete(d, d + 1);
                    }

                }
            });

        } else {
            final TextView textView = ((MyTextViewHolder) holder).textView;
            textView.setText(data.get(position).getMoney());
            if (data.get(position).isSelected()) {
                textView.setSelected(true);
            } else {
                textView.setSelected(false);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setSelected(true);
                    if (position!=oldPostion){
                        notifyItem(position);
                    }
                    listener.onGetMoneyInput(data.get(position).getMoney());
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getWindowToken(), 0);
                    }
                    editText.clearFocus();
                    editText.setSelected(false);
                }
            });
        }
    }

    private void notifyItem(int posiont) {
        if (oldPostion>=0){
            data.get(oldPostion).setSelected(false);
            notifyItemChanged(oldPostion);
        }
        oldPostion = posiont;
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class MyTextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyTextViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    class MyEditViewHolder extends RecyclerView.ViewHolder {
        EditText editText;

        public MyEditViewHolder(View itemView) {
            super(itemView);
            editText = (EditText) itemView.findViewById(et);
        }
    }


    public void setMoneyInputListener(MoneyInputListener listener) {
        this.listener = listener;
    }


    //定义一个处理字符串的方法

    /**
     * 如果最后一个字符为"."，则自动在后面加上"00"，如果"."在倒数第二个位置，则自动加上"0"
     *
     * @param money
     * @return
     */
    private String getMoneyString(String money) {
        String overMoney = "";//结果
        if ("".equals(money)) {
            return "0.00元";
        }
        if (money.contains(".")) {
            if (money.lastIndexOf(".") == money.length() - 1) {
                overMoney = money + "00元";
            } else if (money.lastIndexOf(".") == money.length() - 2) {
                overMoney = money + "0元";
            } else {
                overMoney = money;
            }
        } else {
            overMoney = money + ".00元";
        }
        return overMoney;
    }
}
