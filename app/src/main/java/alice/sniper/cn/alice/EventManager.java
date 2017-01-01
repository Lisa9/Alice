package alice.sniper.cn.alice;

import android.os.Message;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * 项目名称：EventBusDemo
 * 类描述：
 * 创建人：Yan_Gzc
 * 创建时间：2016/12/14 13:41
 * 修改人：Yan_Gzc
 * 修改时间：2016/12/14 13:41
 * 修改备注：
 */
public class EventManager {
    ReferenceQueue referenceQueue = new ReferenceQueue();
    private WeakReference<? extends Object> weakRef = new WeakReference<Object>(referenceQueue);
    WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();

    private static final String TAG = "***";
    private static EventBus eventBus = new EventBus();

    private static EventManager eventManager = new EventManager();

    private EventManager() {
    }

    private static EventManager getEventManager() {
        return eventManager;
    }

    public static void register() {
        register(getEventManager());
    }

    public static void unregister() {
        unregister(getEventManager());
    }

    public static void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    public static void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    public static void post(Message mag) {
        eventBus.post(mag);
    }

    public static void postSticky(Message mag) {
        eventBus.postSticky(mag);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 100)
    public void onEventMain(Message message) {
        int what = message.what;
        Log.d(TAG, "Receive events, what: " + what);
        switch (what) {
            case EventType.EVENT_01:
                Log.i(TAG, "onEventMain: 666666666666666");

                break;
            case EventType.EVENT_02:

                break;
            case EventType.EVENT_03:

                break;
            case EventType.EVENT_04:

                break;
            case EventType.EVENT_05:

                break;
            case EventType.EVENT_06:

                break;
            default:
                Log.w(TAG, "Events not match, what: " + what);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = false, priority = 0)
    public void onEventAsync(Message message) {
        Log.d(TAG, "onEventAsync: " + message.toString());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = false, priority = 0)
    public void onEventBackground(Message message) {
        Log.d(TAG, "onEventBackground: " + message.toString());
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = false, priority = 0)
    public void onEvent(Message message) {
        Log.d(TAG, "onEvent: " + message.toString());
    }


    /**
     * 事件监听
     */
    public interface EventListener {
        /**
         * 有新事件需要消费时通知
         *
         * @param msg 消息
         */
        void newEvent(Message msg);
    }
}
