package br.com.mobiplus.tictactoe.mvp.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luis.fernandez on 3/16/16.
 */
public abstract class BaseView {

    protected Activity mActivity;
    protected ViewGroup mRootView;

    public BaseView(Activity activity) {
        this.mActivity = activity;
        this.initDefaultViews(this.getLayoutResId());
        this.initViews();
    }

    private void initDefaultViews(int layoutResId) {
        mActivity.setContentView(layoutResId);
        mRootView = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    protected View findViewById(int resId) {
        return mActivity.findViewById(resId);
    }

    protected abstract int getLayoutResId();

    protected abstract void initViews();
}
