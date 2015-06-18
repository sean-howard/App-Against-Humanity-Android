package com.appsagainst.humanity.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Chris on 18/06/2015.
 */
public abstract class CardView extends LinearLayout {

    TextView text;

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CardView(Context context) {
        super(context);
        init();
    }

    public void init(){}
}
