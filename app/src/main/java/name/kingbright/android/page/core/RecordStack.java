package name.kingbright.android.page.core;

import android.util.Log;

import java.util.ArrayList;

/**
 * @author Jin Liang
 * @since 16/7/7
 */

class RecordStack {
    private static final String TAG = "RecordStack";
    private ArrayList<PageRecord> mStack = new ArrayList<>();

    public void push(Page page) {
        String className = page.getClass().getSimpleName();
        String tag = generateTag(className);
        PageRecord record = new PageRecord(className, tag, page);
        Log.d(TAG, "push to stack : " + record.toString());
        mStack.add(record);
    }

    private String generateTag(String className) {
        int hit = 0;
        for (PageRecord record : mStack) {
            if (record.className.equals(className)) {
                hit++;
            }
        }
        String tag = new StringBuilder(className).append("_").append(hit).toString();
        return tag;
    }

    public PageRecord pop() {
        if (mStack.size() == 0) {
            Log.d(TAG, "pop : " + null);
            return null;
        }
        PageRecord record = mStack.remove(mStack.size() - 1);
        Log.d(TAG, "pop : " + record.toString());
        return record;
    }

    public PageRecord peek() {
        if (mStack.size() == 0) {
            Log.d(TAG, "peek : " + null);
            return null;
        }
        PageRecord record = mStack.get(mStack.size() - 1);
        Log.d(TAG, "peek : " + record.toString());
        return record;
    }

    public PageRecord getPrev() {
        if (mStack.size() <= 1) {
            Log.d(TAG, "getPrev : " + null);
            return null;
        }
        PageRecord record = mStack.get(mStack.size() - 2);
        Log.d(TAG, "getPrev : " + record.toString());
        return record;
    }

    public int size() {
        return mStack.size();
    }

}
