package com.nhcz500.base.base.widget;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nhcz500.base.R;


public class TextDialog  extends BaseDialog{
    public static TextDialog newInstance(String text) {

        Bundle args = new Bundle();
        args.putString("title",text);
        TextDialog fragment = new TextDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private String text;
    private TextView title;
    private ViewClick ok;
    @Override
    public int getLayoutId() {
        return R.layout.dialog_text;
    }

    @Override
    protected void createView(Bundle savedInstanceState) {
        text=getArguments().getString("title","");
        title=getView(R.id.dialog_title);
        if(!TextUtils.isEmpty(text)){
            title.setText(text);
        }
        addViewListener(R.id.dialog_tv_cancel,R.id.dialog_tv_ok);
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        dismiss();
        if (v.getId() == R.id.dialog_tv_ok) {
            if (ok != null) {
                ok.onViewClick(v);
            }
        }
    }

    public TextDialog setOk(ViewClick ok) {
        this.ok = ok;
        return this;
    }
}
