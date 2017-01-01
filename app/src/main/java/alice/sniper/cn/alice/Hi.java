package alice.sniper.cn.alice;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import alice.sniper.cn.alice.Brain.Brain;
import alice.sniper.cn.alice.Brain.BrainInterFace.BrainSay;
import alice.sniper.cn.alice.Hear.Hear;
import alice.sniper.cn.alice.Thought.Thought;

/**
 * Created by pei_song on 2016/12/26.
 */

public class Hi extends Brain{

    /**
     * 开始说话按钮
     */
    private Button start_say;

    /**
     * 主页显示Text
     */
    private TextView start_say_tex;

    /**
     * 语音解析结果
     */
    private String str;

    /**
     * 说话按钮控制
     */
    public Boolean or = true;

    /**
     * 听写类
     */
    private Hear hear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alice);
        initData();
        initView();
        setEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setEvent(){

        Thought.BrainSay(new BrainSay.ToastSay() {
            @Override
            public void Say(String str) {
                Bundle b = new Bundle();
                b.putString("msg",str);
                Message m = new Message();
                m.what = 1;
                m.setData(b);
                hanler.sendMessage(m);
            }
        });

        /**
         * 开启 “思想” 类刷新线程
         */
        new Thought().start();

        /**
         * 设置开始说话按钮的点击事件
         */
        start_say.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (or) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        start_say.setBackgroundResource(R.color.button_Start);
                        start_say_tex.setText(R.string.EndSay);
                        hear.start();
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        start_say.setBackgroundResource(R.color.button_End);
                        start_say_tex.setText(R.string.StartSay);
                        hear.stop();
                        or = false;
                    }
                }
                return true;
            }
        });
    }


    /**
     * 初始化布局
     */
    public void initView() {
        /**
         * 初始化控件
         */
        start_say_tex = (TextView) findViewById(R.id.start_say_tex);
        start_say = (Button) findViewById(R.id.start_say);
        start_say.setText(R.string.StartSay);

        /**
         * 初始化各项类对象
         */
        hear = new Hear(alice);

    }

    public void initData(){

    }
}
