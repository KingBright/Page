package name.kingbright.android.page.core;

import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import name.kingbright.android.page.anim.Animatable;
import name.kingbright.android.page.anim.TransitionAnimator;
import name.kingbright.android.page.anim.TransitionAnimators;

/**
 * Lifecycle of a page is as following:
 * <p>
 * {@link #onCreate} -> {@link #onViewCreated} ->{@link #onStart} -> {@link #onResume} ->
 * {@link #onPause} -> {@link #onStop} -> {@link #onDestroy}
 * </p>
 *
 * @author Jin Liang
 * @since 16/6/20
 */
public abstract class Page implements Lifecycle, Animatable {

    private PageManager mPageManager;

    private View mView;

    public View getView() {
        return mView;
    }

    public abstract int getLayoutId();

    public void inflate(LayoutInflater inflater, ViewGroup container) {
        int res = getLayoutId();
        if (res <= 0) {
            throw new AndroidRuntimeException("Layout res id is invalid");
        }
        mView = inflater.inflate(res, container, false);
        onViewCreated(mView);
    }

    protected void onViewCreated(View view) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public TransitionAnimator getTransitionAnimator() {
        return TransitionAnimators.LEFT_IN_RIGHT_OUT;
    }

    @Override
    public TransitionAnimator getBackTransitionAnimator() {
        return TransitionAnimators.RIGHT_IN_LEFT_OUT;
    }

    public PageManager getPageManager() {
        return mPageManager;
    }

    public void setPageManager(PageManager pageManager) {
        mPageManager = pageManager;
    }
}
