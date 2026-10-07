package com.eurobuddha.terminal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Base for one page in the ViewPager. The layout is inflated up-front with no
 * parent (the TerminalAdapter attaches it when the pager asks), and the whole
 * view lives for the life of the activity — pages here are cheap and stateful
 * (terminal scrollback), so nothing is torn down on swipe like fragments would
 * be. Subclasses override {@link #refreshView()} if they render on demand.
 */
public class BaseView {

    protected Activity mActivity;

    protected View mMainView;

    public BaseView(Activity zActivity, int zViewResID){
        mActivity = zActivity;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(mActivity.LAYOUT_INFLATER_SERVICE);
        mMainView = inflater.inflate(zViewResID, null);
    }

    public Activity getActivity(){
        return mActivity;
    }

    public View getMainView(){
        return mMainView;
    }

    public void refreshView(){}
}
