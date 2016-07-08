package name.kingbright.android.page.example;

import android.view.View;

import name.kingbright.android.page.core.Page;

/**
 * @author Jin Liang
 * @since 16/7/7
 */
public class TestPage2 extends Page {
    @Override
    public int getLayoutId() {
        return R.layout.test_page_2;
    }

    @Override
    protected void onViewCreated(View view) {
        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPageManager().startPage(TestPage2.class);
            }
        });
        view.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPageManager().back();
            }
        });
    }
}
