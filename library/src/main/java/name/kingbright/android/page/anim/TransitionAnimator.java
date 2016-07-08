package name.kingbright.android.page.anim;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;

/**
 * @author Jin Liang
 * @since 16/6/16
 */
public abstract class TransitionAnimator extends ValueAnimator implements ValueAnimator
        .AnimatorUpdateListener {
    private static final long DEFAULT_DURATION = 500L;

    private View oldView;
    private View newView;

    public TransitionAnimator() {
    }

    /**
     * Do some layout params settings here.
     *
     * @param oldView
     * @param newView
     */
    public void onViewAdded(View oldView, View newView) {
        this.oldView = oldView;
        this.newView = newView;
        setStartProperty(oldView, newView);
    }

    protected abstract void setStartProperty(View oldView, View newView);

    public void applyTransition(View oldView, View newView, AnimatorListener listener) {
        ArrayList<PropertyValuesHolder> holders = new ArrayList<>();
        addTransitionValues(oldView, newView, holders);
        PropertyValuesHolder[] array = new PropertyValuesHolder[holders.size()];
        setValues(holders.toArray(array));

        setDuration(getDuration());

        setInterpolator(getTimeInterpolator());
        addListener(listener);
        addUpdateListener(this);

        start();
    }

    public long getDuration() {
        return DEFAULT_DURATION;
    }

    /**
     * See {@link #setInterpolator(TimeInterpolator)}
     *
     * @return
     */
    public TimeInterpolator getTimeInterpolator() {
        return new AccelerateDecelerateInterpolator();
    }

    /**
     * @param oldView
     * @param newView
     * @param holders
     */
    public abstract void addTransitionValues(View oldView, View newView, ArrayList<PropertyValuesHolder> holders);

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        PropertyValuesHolder[] holders = animation.getValues();
        if (holders == null || holders.length == 0) {
            return;
        }
        for (PropertyValuesHolder holder : holders) {
            String propertyName = holder.getPropertyName();
            doTransitionValuesUpdate(oldView, newView, propertyName, animation.getAnimatedValue(propertyName));
        }
    }

    /**
     * Update view properties during view transition.
     *
     * @param oldView       The old view to animate out.
     * @param newView       The new view to animate in.
     * @param propertyName  The property name of which is changing.
     * @param animatedValue The property value to be set.
     */
    protected abstract void doTransitionValuesUpdate(View oldView, View newView, String propertyName, Object animatedValue);

    /**
     * EndListener only supports with animation end events.
     */
    public static class AnimatorListener implements Animator.AnimatorListener {
        @Override
        public final void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public final void onAnimationCancel(Animator animation) {

        }

        @Override
        public final void onAnimationRepeat(Animator animation) {

        }
    }
}
