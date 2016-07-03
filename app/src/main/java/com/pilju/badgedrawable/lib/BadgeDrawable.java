package com.pilju.badgedrawable.lib;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.TypedValue;

/**
 * Created by veronikapj on 15. 12. 23.
 */
public class BadgeDrawable extends Drawable {

    private static final int DEFAULT_CORNER_RADIUS_PX = 2;
    private static final int DEFAULT_BADGE_WIDTH = 25;
    private static final int DEFAULT_BADGE_HEIGHT= 14;
    private static final int DEFAULT_BADGE_COLOR = Color.parseColor("#ff3f47");

    private final TextPaint mTextPaint;
    private final Paint mBgPaint;

    private String mText;

    public BadgeDrawable(String text) {
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        //default setting

        setText(text);
        setBadgeSize(DEFAULT_BADGE_WIDTH, DEFAULT_BADGE_HEIGHT);
        setBadgeBackgroundColor(DEFAULT_BADGE_COLOR);
        setTextColor(Color.WHITE);
        setTextSize(11);
    }


    @Override
    public void draw(Canvas canvas) {
        Rect areaRect = getBounds();
        RectF bounds = new RectF(areaRect);
        Resources r = Resources.getSystem();
        float corner = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CORNER_RADIUS_PX, r.getDisplayMetrics());
        canvas.drawRoundRect(bounds, corner, corner, mBgPaint);

        bounds.right = mTextPaint.measureText(mText, 0, mText.length());
        bounds.bottom = mTextPaint.descent() - mTextPaint.ascent();

        bounds.left += (areaRect.width() - bounds.right) / 2.0f;
        bounds.top += (areaRect.height() - bounds.bottom) / 2.0f;
        canvas.drawText(mText, bounds.left, bounds.top - mTextPaint.ascent(), mTextPaint);
    }

    public void setBadgeSize(int width, int height) {
        Resources r = Resources.getSystem();
        float rWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, r.getDisplayMetrics());
        float rHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, r.getDisplayMetrics());
        setBounds(0, 0, (int) rWidth, (int) rHeight);
    }

    public void setBadgeBackgroundColor(int badgeColor) {
        if(mBgPaint.getColor() != badgeColor) {
            mBgPaint.setColor(badgeColor);
            invalidateSelf();
        }

    }

    public void setTypeface(Typeface tf) {
        if (mTextPaint.getTypeface() != tf) {
            mTextPaint.setTypeface(tf);
            invalidateSelf();
        }
    }

    public void setTextSize(float size) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setTextSize(int unit, float size) {
        Resources r = Resources.getSystem();

        setRawTextSize(TypedValue.applyDimension(
                unit, size, r.getDisplayMetrics()));
    }

    private void setRawTextSize(float size) {
        if (size != mTextPaint.getTextSize()) {
            mTextPaint.setTextSize(size);
            invalidateSelf();
        }
    }

    public void setTextColor(int color) {
        if(mTextPaint.getColor() != color) {
            mTextPaint.setColor(color);
            invalidateSelf();
        }
    }

    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        mText = text;
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}


