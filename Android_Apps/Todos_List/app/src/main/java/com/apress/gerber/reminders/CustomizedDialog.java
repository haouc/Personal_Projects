package com.apress.gerber.reminders;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hao on 4/27/2015.
 */
public class CustomizedDialog extends Dialog implements android.view.View.OnClickListener{

    private RemindersActivity mAction;
    private Button mSubmit, mCancel;
    private CheckBox mCheckBox;
    private EditText mText;
    private String mTitle;
    private int mColor;
    private TextView mTitleView;
    private LinearLayout mLinearLayout;
    private int mEditID;

    public CustomizedDialog(RemindersActivity action, String title, int color, int editID) {
        super(action);
        this.mAction = action;
        this.mTitle = title;
        mColor = color;
        this.mEditID = editID;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        mLinearLayout = (LinearLayout)findViewById(R.id.dialog);
        mLinearLayout.setBackgroundResource(mColor);
        mTitleView = (TextView)findViewById(R.id.title);
        mTitleView.setText(mTitle);
        mText = (EditText)findViewById(R.id.description);
        mCheckBox = (CheckBox)findViewById(R.id.checkBox);
        mSubmit = (Button) findViewById(R.id.submit);
        mCancel = (Button) findViewById(R.id.cancel);
        mSubmit.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                break;
            case R.id.submit:
                if(mEditID > 0){
                    mAction.update(mText.getText().toString(), mCheckBox.isChecked(), mEditID);
                }
                else
                    mAction.insert(mText.getText().toString(), mCheckBox.isChecked());
                mAction.updateList();
                break;
        }
        this.cancel();
    }

    public void setTextContent(String content, boolean checked) {
        mCheckBox.setChecked(checked);
        mText.setText(content);
    }

}