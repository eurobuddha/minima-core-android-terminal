package org.minimarex.terminal;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * PagerAdapter over two pre-built, long-lived views (Terminal, Logs) — plain
 * views rather than fragments, so the terminal's scrollback and input state
 * survive tab swipes without any saved-state plumbing.
 */
public class TerminalAdapter extends PagerAdapter {

    MainActivity mMainActivity;

    BaseView[] mAllViews;

    public TerminalAdapter(MainActivity zContext){
        mMainActivity = zContext;

        //Store of all current valid views..
        mAllViews = new BaseView[2];

        //mAllViews[0] = new HomeView(mActivity);
        mAllViews[0] = new TerminalView(mMainActivity);
        mAllViews[1] = new LogsView(mMainActivity);
    }

    public void refreshPagerView(int zPosition){
        mAllViews[zPosition].refreshView();
        mAllViews[zPosition].getMainView().invalidate();
    }

    public TerminalView getTerminalView(){
        return (TerminalView)mAllViews[0];
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        // Remove-then-add: the pager can re-instantiate a position whose view it
        // still holds; addView on an already-parented view throws
        // IllegalStateException, so detach defensively first.
        container.removeView(mAllViews[position].getMainView());

        //Add to our view..
        container.addView(mAllViews[position].getMainView());

        return mAllViews[position].getMainView();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object==view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //Remove from container
        container.removeView((View)object);
    }

    public void refreshAllViews(){
        for(int i=0;i<mAllViews.length;i++){
            if(mAllViews[i] != null){
                mAllViews[i].refreshView();
                mAllViews[i].getMainView().invalidate();
            }
        }
    }
}
