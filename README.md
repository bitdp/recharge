# recharge
话费充值页
![效果图](https://github.com/bitdp/recharge/raw/master/imgfiles/recharge.gif)

1.要求是只能输入数字，最多小数点后两位，下方的充值金额小计一栏始终显示两位小数，除非金额为0，才可以显示为0。
2.GridView 的最后一项是EditText ,点击的时候不能在OnItemClickListener 中监听到，通过OnFocusChangeListener 进行了监听。
3.从输入框切换到具体金额时，清除输入框的焦点clearFocus()，以便下次点击输入框时能回调OnFocusChangeListener 中的方法。

4.从输入框切换到具体金额时，隐藏键盘，同时避免隐藏时影响画面布局。

5.从具体金额切换到输入框时，提取输入框中已经输入的金额到小计一栏。

6.控制输入框中的输入，利用正则表达式结合TextWatcher() 监听器，当输入不符合规定时，动态改变显示的数字(要避免进入死循环)，再实时更新到小计一栏。
