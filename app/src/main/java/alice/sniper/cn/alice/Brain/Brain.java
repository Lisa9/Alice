package alice.sniper.cn.alice.Brain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Brain 大脑类, 主要的存在.
 *
 * Created by pei_song on 2016/12/21.
 */
public abstract class Brain extends Activity{

    /**
     * Brain The Context, 提供给继承者对自己的控制权.
     */
    public Context alice = this;


    public Handler hanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    String str = msg.getData().getString("msg");
                    if (str.equals("打开QQ。") || str.equals("打开QQ")) {
                        Intent intent = alice.getPackageManager().getLaunchIntentForPackage("com.tencent.mq");
                        startActivity(intent);
                    }

                    if (str.equals("打开微信。") || str.equals("打开微信")) {
                        Intent intent = alice.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                        startActivity(intent);
                    }

                    Toast.makeText(alice, msg.getData().getString("msg"), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}
