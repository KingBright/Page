package name.kingbright.android.page.example;

import android.app.Activity;
import android.os.Bundle;

import name.kingbright.android.page.R;
import name.kingbright.android.page.anim.TransitionView;
import name.kingbright.android.page.core.PageManager;

public class MainActivity extends Activity {

    private PageManager mPageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPageManager = new PageManager();
        mPageManager.bind((TransitionView) findViewById(R.id.transition_container));
        mPageManager.onCreate(savedInstanceState);

        mPageManager.startPage(TestPage1.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPageManager.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPageManager.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPageManager.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPageManager.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (!mPageManager.back()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPageManager.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPageManager.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPageManager.onDestroy();
    }
}
