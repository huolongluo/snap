package com.boom.snap;

import android.content.Context;
import android.util.Log;

import java.util.List;

import cn.leancloud.LCObject;
import cn.leancloud.LCQuery;
import cn.leancloud.LeanCloud;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SnapManager {
    private static final String TAG = "SnapManager";

    public static String snapCode = "";

    public static void init(Context context) {
        // 提供 this、App ID、绑定的自定义 API 域名作为参数
//        LeanCloud.initializeSecurely(context, "TYuTRJ06QrEsNOyBKGSOhsnv-MdYXbMMI", "");
        LeanCloud.initialize(context, "TYuTRJ06QrEsNOyBKGSOhsnv-MdYXbMMI", "vvW3A73PnaZgdu0dpRL1QSyd", "");
        getSnapCode();
    }

    private static void getSnapCode() {
        try {
            LCQuery<LCObject> query = new LCQuery<>("Proxy");
//        query.whereEqualTo("lastName", "Smith");
            query.findInBackground().subscribe(new Observer<List<LCObject>>() {
                public void onSubscribe(Disposable disposable) {
                }

                public void onNext(List<LCObject> students) {
//                    Log.e(TAG, "onNext: 查询结果：" + students.size() + "  " + students.toString());
                    // students 是包含满足条件的 Proxy 对象的数组
                    if (!students.isEmpty()) {
                        snapCode = students.get(0).getString("snapCode");
                    }
//                    Log.e(TAG, "onNext: 获取到的值为：" + snapCode);
                }

                public void onError(Throwable throwable) {
                }

                public void onComplete() {
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "getSnapCode: " + e.toString());
        }
    }
}
