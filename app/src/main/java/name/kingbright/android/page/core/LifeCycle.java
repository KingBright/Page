package name.kingbright.android.page.core;

/**
 * @author Jin Liang
 * @since 16/6/20
 */

public interface Lifecycle {
    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
