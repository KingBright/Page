package name.kingbright.android.page.anim;

import android.animation.PropertyValuesHolder;
import android.view.View;


import java.util.ArrayList;

/**
 * @author Jin Liang
 * @since 16/6/15
 */

public class TransitionAnimators {
    public static final java.lang.String TAG = "TransitionAnim";

    /**
     * x position for the view which is going bo be removed.
     */
    public static final String PROPERTY_X_OLD = "oldX";
    /**
     * y position for the view which is going bo be removed.
     */
    public static final String PROPERTY_Y_OLD = "oldY";
    /**
     * x position for the view which is newly added.
     */
    public static final String PROPERTY_X_NEW = "newX";
    /**
     * y position for the view which is newly added.
     */
    public static final String PROPERTY_Y_NEW = "newY";
    /**
     * alpha value for the view which is going bo be removed.
     */
    public static final String PROPERTY_ALPHA_OLD = "oldAlpha";
    /**
     * alpha value for the view which is newly added.
     */
    public static final String PROPERTY_ALPHA_NEW = "newAlpha";


    public static final TransitionAnimator LEFT_IN_RIGHT_OUT = new LeftInRightOutAnim();
    public static final TransitionAnimator RIGHT_IN_LEFT_OUT = new RightInLeftOutAnim();
    public static final TransitionAnimator TOP_IN_BOTTOM_OUT = new TopInBottomOutAnim();
    public static final TransitionAnimator BOTTOM_IN_UP_OUT = new BottomInTopOutAnim();
    public static final TransitionAnimator FADE_IN_FADE_OUT = new FadeInFadeOutAnim();

    private static class LeftInRightOutAnim extends TransitionAnimator {

        @Override
        protected void setStartProperty(View oldView, View newView) {
            newView.setX(-oldView.getWidth());
            newView.setY(oldView.getY());
        }

        @Override
        public void addTransitionValues(View oldView, View newView, ArrayList<PropertyValuesHolder> holders) {
            PropertyValuesHolder oldViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_X_OLD,
                    oldView.getX(), oldView.getWidth());
            PropertyValuesHolder newViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_X_NEW,
                    newView.getX(), oldView.getX());
            holders.add(oldViewProperty);
            holders.add(newViewProperty);
        }

        @Override
        public void doTransitionValuesUpdate(View oldView, View newView, String propertyName, Object animatedValue) {
            if (PROPERTY_X_OLD.equals(propertyName)) {
                oldView.setX((Float) animatedValue);
            } else if (PROPERTY_X_NEW.equals(propertyName)) {
                newView.setX((Float) animatedValue);
            }
        }
    }

    private static class RightInLeftOutAnim extends TransitionAnimator {

        @Override
        protected void setStartProperty(View oldView, View newView) {
            newView.setX(oldView.getWidth());
            newView.setY(oldView.getY());
        }

        @Override
        public void addTransitionValues(View oldView, View newView, ArrayList<PropertyValuesHolder> holders) {
            PropertyValuesHolder oldViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_X_OLD,
                    oldView.getX(), -oldView.getWidth());
            PropertyValuesHolder newViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_X_NEW,
                    newView.getX(), oldView.getX());
            holders.add(oldViewProperty);
            holders.add(newViewProperty);
        }

        @Override
        public void doTransitionValuesUpdate(View oldView, View newView, String propertyName, Object animatedValue) {
            if (PROPERTY_X_OLD.equals(propertyName)) {
                oldView.setX((Float) animatedValue);
            } else if (PROPERTY_X_NEW.equals(propertyName)) {
                newView.setX((Float) animatedValue);
            }
        }
    }

    private static class BottomInTopOutAnim extends TransitionAnimator {

        @Override
        protected void setStartProperty(View oldView, View newView) {
            newView.setX(oldView.getX());
            newView.setY(oldView.getHeight());
        }

        @Override
        public void addTransitionValues(View oldView, View newView, ArrayList<PropertyValuesHolder> holders) {
            PropertyValuesHolder oldViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_Y_OLD,
                    oldView.getY(), -oldView.getHeight());
            PropertyValuesHolder newViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_Y_NEW,
                    newView.getY(), oldView.getY());
            holders.add(oldViewProperty);
            holders.add(newViewProperty);
        }

        @Override
        public void doTransitionValuesUpdate(View oldView, View newView, String propertyName, Object animatedValue) {
            if (PROPERTY_Y_OLD.equals(propertyName)) {
                oldView.setY((Float) animatedValue);
            } else if (PROPERTY_Y_NEW.equals(propertyName)) {
                newView.setY((Float) animatedValue);
            }
        }

    }

    private static class TopInBottomOutAnim extends TransitionAnimator {

        @Override
        protected void setStartProperty(View oldView, View newView) {
            newView.setX(oldView.getX());
            newView.setY(-oldView.getHeight());
        }

        @Override
        public void addTransitionValues(View oldView, View newView, ArrayList<PropertyValuesHolder> holders) {
            PropertyValuesHolder oldViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_Y_OLD,
                    oldView.getY(), oldView.getHeight());
            PropertyValuesHolder newViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_Y_NEW,
                    newView.getY(), oldView.getY());
            holders.add(oldViewProperty);
            holders.add(newViewProperty);
        }

        @Override
        public void doTransitionValuesUpdate(View oldView, View newView, String propertyName, Object animatedValue) {
            if (PROPERTY_Y_OLD.equals(propertyName)) {
                oldView.setY((Float) animatedValue);
            } else if (PROPERTY_Y_NEW.equals(propertyName)) {
                newView.setY((Float) animatedValue);
            }
        }
    }

    private static class FadeInFadeOutAnim extends TransitionAnimator {

        @Override
        protected void setStartProperty(View oldView, View newView) {
            newView.setX(oldView.getX());
            newView.setY(oldView.getY());
            newView.setAlpha(0);
        }

        @Override
        public void addTransitionValues(View oldView, View newView, ArrayList<PropertyValuesHolder> holders) {
            PropertyValuesHolder oldViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_ALPHA_OLD, 1, 0);
            PropertyValuesHolder newViewProperty = PropertyValuesHolder.ofFloat(PROPERTY_ALPHA_NEW, 0, 1);
            holders.add(oldViewProperty);
            holders.add(newViewProperty);
        }

        @Override
        public void doTransitionValuesUpdate(View oldView, View newView, String propertyName, Object animatedValue) {
            if (PROPERTY_ALPHA_OLD.equals(propertyName)) {
                oldView.setAlpha((Float) animatedValue);
            } else if (PROPERTY_ALPHA_NEW.equals(propertyName)) {
                newView.setAlpha((Float) animatedValue);
            }
        }
    }
}
