package name.kingbright.android.page.core;

/**
 * @author Jin Liang
 * @since 16/7/7
 */

class PageRecord {
    public String tag;
    public String className;
    public Page instance;

    public PageRecord(String className, String tag, Page page) {
        this.tag = tag;
        this.className = className;
        this.instance = page;
    }

    @Override
    public String toString() {
        return tag;
    }
}
