package com.nhcz500.base.weiget;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.nhcz500.base.R;


public class CSEditText extends LinearLayoutCompat implements View.OnClickListener,TextWatcher {
    EditText editText;
    ImageView clean,show,hint;
    private  boolean isShow;//是否显示密码

    private boolean isShowClose,isShowPassword;
    private String hintString;
    private int inputType;
    private int maxLength;
    public CSEditText(Context context) {
        this(context,null);
    }
    public CSEditText( Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public CSEditText(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CSEditText);
        isShowClose=typedArray.getBoolean(R.styleable.CSEditText_isClose,false);
        isShowPassword=typedArray.getBoolean(R.styleable.CSEditText_isPass,true);
        hintString=typedArray.getString(R.styleable.CSEditText_hintStr);
        inputType=typedArray.getInt(R.styleable.CSEditText_android_inputType,0);
        maxLength=typedArray.getInt(R.styleable.CSEditText_maxLength,0);
        LayoutInflater.from(context).inflate(R.layout.edit_close_pass,this,true);

        editText = findViewById(R.id.edit);
        editText.addTextChangedListener(this);

        editText.setHint(hintString);
        editText.setInputType(inputType);
        if(maxLength!=0){
            InputFilter[] filters = {new InputFilter.LengthFilter(maxLength)};
            editText.setFilters(filters);
        }


        clean = findViewById(R.id.edit_clean);
        clean.setVisibility(GONE);
        clean.setOnClickListener(this);
        show = findViewById(R.id.edit_show);
        show.setOnClickListener(this);

        hint=findViewById(R.id.edit_hint);
        hint.setOnClickListener(this);

        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String input = editText.getText().toString();
        if(input.isEmpty()){
            clean.setVisibility(GONE);
            hint.setVisibility(GONE);
            show.setVisibility(GONE);
        }else {
            clean.setVisibility(isShowClose?VISIBLE:GONE);
            if(!isShow){//默认显示
                show.setVisibility(isShowPassword?VISIBLE:GONE);
                hint.setVisibility(GONE);
            }else{
                show.setVisibility(GONE);
                hint.setVisibility(isShowPassword?VISIBLE:GONE);
            }
            editText.setSelection(editText.getText().length());
        }

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        //当点击了清空按钮，则清空editText的内容
        if (id== R.id.edit_clean) {
            editText.setText("");
        }else if(id==R.id.edit_hint){//隐藏密码
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            hint.setVisibility(GONE);
            show.setVisibility(VISIBLE);
            isShow=false;
        }else if(id==R.id.edit_show){
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            show.setVisibility(GONE);
            hint.setVisibility(VISIBLE);
            isShow=true;
        }
    }

    public EditText getEditText() {
        return editText;
    }
}
