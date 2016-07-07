package name.kingbright.android.page.anim;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


/**
 * @author Jin Liang
 * @since 16/6/15
 */

public class TransitionView extends FrameLayout {
    private boolean mIsInTransition;

    public TransitionView(Context context) {
        this(context, null);
    }

    public TransitionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransitionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (getChildCount() >= 2) {
            throw new RuntimeException("Can not support more than 1 views when init");
        }
    }

    public void doTransition(View view, TransitionAnimator anim, TransitionListener listener) {
        mIsInTransition = true;
        if (anim == null) {
            // Just add new view && remove the old one if exists
            simplyReplace(view, listener);
            return;
        }

        addViewAndTransition(view, anim, listener);
    }

    private void addViewAndTransition(View newView, TransitionAnimator anim, final TransitionListener listener) {
        final View oldView = getChildAt(0);

        // If has previous view, set new view to gone.
        if (oldView != null) {
            newView.setVisibility(View.GONE);
        }
        addView(newView);

        // If has previous view, prepare for transition.
        if (oldView != null) {
            anim.onViewAdded(oldView, newView);
            newView.setVisibility(View.VISIBLE);
            anim.applyTransition(oldView, newView, new TransitionAnimator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    removeView(oldView);
                    notifyFinish(listener);
                }
            });
        } else {
            notifyFinish(listener);
        }
    }

    private void notifyFinish(TransitionListener listener) {
        if (listener != null) {
            listener.onTransitionFinish();
        }
        mIsInTransition = false;
    }

    private void simplyReplace(View view, TransitionListener listener) {
        if (getChildCount() == 1) {
            removeViewAt(0);
        }
        addView(view);
        notifyFinish(listener);
    }

    private void simpleRemove(View view, RemoveListener listener) {
        removeView(view);
        notifyRemove(listener);
    }

    private void notifyRemove(RemoveListener listener) {
        if (listener != null) {
            listener.onRemoveFinish();
        }
        mIsInTransition = false;
    }

    /**
     * If the transition is still ongoing.
     *
     * @return
     */
    public boolean isInTransition() {
        return mIsInTransition;
    }

    public void removeView(View view, RemoveListener
            listener) {
        mIsInTransition = true;
        // Just remove view
        simpleRemove(view, listener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIsInTransition) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsInTransition) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface TransitionListener {
        void onTransitionFinish();
    }

    public interface RemoveListener {
        void onRemoveFinish();
    }
}
