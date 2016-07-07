package name.kingbright.android.page.core;

import android.os.Bundle;
import android.os.Looper;
import android.util.AndroidRuntimeException;

import name.kingbright.android.page.anim.TransitionView;

/**
 * @author Jin Liang
 * @since 16/6/20
 */
public class PageManager implements Lifecycle {
    private TransitionManager mTransitionManager;

    public PageManager() {
        mTransitionManager = new TransitionManager();
    }

    /**
     * Provide a container view.
     *
     * @param view
     */
    public void bind(TransitionView view) {
        mTransitionManager.bind(view);
    }

    public void startPage(Class<? extends Page> cls) {
        startPageForResult(cls, -1);
    }

    public void startPageForResult(Class<? extends Page> cls, int requestCode) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("You should do this only on main thread.");
        }
        if (cls == null) {
            throw new NullPointerException("The target page can not be empty.");
        }
        Page page = createPage(cls);
        mTransitionManager.show(page);
    }

    private Page createPage(Class<? extends Page> cls) {
        if (!Page.class.isAssignableFrom(cls)) {
            String className = cls.getName();
            throw new ClassCastException(className + " can not be cast to Page");
        }

        Page page;
        try {
            page = cls.newInstance();
            page.setPageManager(this);
            page.onCreate();
            page.inflate(mTransitionManager.getLayoutInflater(), mTransitionManager.getContainer());
        } catch (Exception e) {
            throw new AndroidRuntimeException(e);
        }
        return page;
    }

    public void onCreate(Bundle savedInstanceState) {
        mTransitionManager.onCreate(savedInstanceState);
    }

    @Override
    public void onCreate() {
        /**
         *  Do nothing here. We deal with {@link #onCreate(Bundle)}
         */
    }

    public void onStart() {
        mTransitionManager.onStart();
    }

    public void onResume() {
        mTransitionManager.onResume();
    }

    public void onPause() {
        mTransitionManager.onPause();
    }

    public void onStop() {
        mTransitionManager.onStop();
    }

    public void onDestroy() {
        mTransitionManager.onDestroy();
    }

    public void onSaveInstanceState(Bundle outState) {
        mTransitionManager.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mTransitionManager.onRestoreInstanceState(savedInstanceState);
    }

    public boolean back() {
        return mTransitionManager.back();
    }
}
