package name.kingbright.android.page.core;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import name.kingbright.android.page.anim.TransitionView;

/**
 * @author Jin Liang
 * @since 16/6/20
 */
class TransitionManager implements Lifecycle, TransitionView.RemoveListener, TransitionView.TransitionListener {
    private static final String TAG = "TransitionManager";

    private Page mShowingPage;
    private boolean mIsBack = false;

    private boolean mStarted = false;
    private boolean mResumed = false;

    private LayoutInflater mLayoutInflater;

    public enum Notify {
        CREATE, START, RESUME, PAUSE, STOP, DESTROY;
    }

    private TransitionView mContainer;

    private RecordStack mRecordStack;

    public TransitionManager() {
        mRecordStack = new RecordStack();
    }

    public void bind(TransitionView container) {
        if (container == null) {
            throw new NullPointerException("The container can not be empty.");
        }

        mContainer = container;
        mLayoutInflater = LayoutInflater.from(mContainer.getContext());
    }

    public boolean back() {
        if (mRecordStack.size() == 0) {
            Log.d(TAG, "No more page can be popped");
            return false;
        }
        if (mContainer.isInTransition()) {
            Log.d(TAG, "back() Is in transition");
            return true;
        }
        Log.d(TAG, "back()");
        if (mRecordStack.size() == 1) {
            PageRecord record = mRecordStack.peek();
            Page page = record.instance;
            mContainer.removeView(page.getView(), this);
        } else {
            PageRecord record = mRecordStack.getPrev();
            Page page = record.instance;
            show(page, true);
        }
        return true;
    }

    public void show(Page page) {
        show(page, false);
    }

    public void show(Page page, boolean back) {
        if (mContainer.isInTransition()) {
            Log.d(TAG, "Show() Is in transition");
            return;
        }
        Log.d(TAG, "show() do transition now");
        mShowingPage = page;
        mIsBack = back;
        mContainer.doTransition(mShowingPage.getView(), back ? page.getBackTransitionAnimator() : page
                .getTransitionAnimator(), this);
    }

    @Override
    public void onTransitionFinish() {
        Log.d(TAG, "onTransitionFinish()");
        PageRecord record;
        if (mIsBack) {
            record = mRecordStack.pop();
        } else {
            record = mRecordStack.peek();
        }
        if (record != null) {
            Page last = record.instance;
            last.onPause();
            last.onStop();
            last.onDestroy();
        }
        if (mStarted) {
            mShowingPage.onStart();
        }
        if (mResumed) {
            mShowingPage.onResume();
        }
        if (!mIsBack) {
            mRecordStack.push(mShowingPage);
        }
    }

    @Override
    public void onRemoveFinish() {
        PageRecord record = mRecordStack.pop();
        if (record != null) {
            Page page = record.instance;
            page.onPause();
            page.onStop();
            page.onDestroy();
        }
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public ViewGroup getContainer() {
        return mContainer;
    }

    public void onCreate(Bundle savedInstanceState) {

    }

    private void notifyLifecycle(Notify notify) {
        if (notify == Notify.CREATE) {

        } else if (notify == Notify.DESTROY) {

        } else {
            PageRecord last = mRecordStack.peek();
            if (last == null || mContainer.isInTransition()) {
                Log.d(TAG, "Current page is null or is in transition");
                return;
            }
            Log.d(TAG, "Notify current page for " + notify);
            Page page = last.instance;
            switch (notify) {
                case RESUME:
                    page.onResume();
                    break;
                case START:
                    page.onStart();
                    break;
                case STOP:
                    page.onStop();
                    break;
                case PAUSE:
                    page.onPause();
                    break;
            }
        }
    }

    @Override
    public void onCreate() {
        notifyLifecycle(Notify.CREATE);
    }

    @Override
    public void onStart() {
        mStarted = true;
        notifyLifecycle(Notify.START);
    }

    @Override
    public void onResume() {
        mResumed = true;
        notifyLifecycle(Notify.RESUME);
    }

    @Override
    public void onPause() {
        mResumed = false;
        notifyLifecycle(Notify.PAUSE);
    }

    @Override
    public void onStop() {
        mStarted = false;
        notifyLifecycle(Notify.STOP);
    }

    @Override
    public void onDestroy() {
        notifyLifecycle(Notify.DESTROY);
    }

    public void onSaveInstanceState(Bundle outState) {

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

}
