package com.example.egorb.contactsviewsample

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

public class SquareRelativeLayout(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec) //(int)(widthMeasureSpec * 0.75));
    }
}
