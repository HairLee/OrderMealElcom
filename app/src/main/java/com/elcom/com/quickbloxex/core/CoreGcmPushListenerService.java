package com.elcom.com.quickbloxex.core;

public abstract class CoreGcmPushListenerService {
    private static final String TAG = CoreGcmPushListenerService.class.getSimpleName();
//
//    @Override
//    public void sendPushMessage(Bundle data, String from, String message) {
//        super.sendPushMessage(data, from, message);
//        Log.v(TAG, "From: " + from);
//        Log.v(TAG, "Message: " + message);
//
//        if (ActivityLifecycle.getInstance().isBackground()) {
//            showNotification(message);
//        }
//    }

    protected abstract void showNotification(String message);
}