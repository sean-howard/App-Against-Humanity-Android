package com.appsagainst.humanity.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.appsagainst.humanity.R;

/**
 * Created by Chris on 18/06/2015.
 */
public class WhiteCardView extends CardView {

    public WhiteCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WhiteCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public WhiteCardView(Context context) {
        super(context);
    }

    public void init(){
        inflate(getContext(), R.layout.ui_black_card, this);
        text = (TextView) findViewById(R.id.textView);
    }
}
