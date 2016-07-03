package com.pilju.badgedrawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.pilju.badgedrawable.lib.BadgeDrawable;

public class MainActivity extends AppCompatActivity {

    private TextView mBadge1;
    private TextView mBadge2;
    private TextView mBadge3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBadge1 = (TextView)findViewById(R.id.badge1);
        mBadge2 = (TextView)findViewById(R.id.badge2);
        mBadge3 = (TextView)findViewById(R.id.badge3);

        setLeftBadge(mBadge1, "34");
        setRightBadge(mBadge2, "new");
        attachBadge(mBadge3, "New New New \n Messages", "67");
    }

    private void setLeftBadge(TextView badgeView, String badgeCount) {
        BadgeDrawable badge = new BadgeDrawable(badgeCount);
        badgeView.setCompoundDrawables(badge, null, null, null);
    }

    private void setRightBadge(TextView badgeView, String text) {
        BadgeDrawable badge = new BadgeDrawable(text);
        badge.setBadgeBackgroundColor(Color.parseColor("#20d5da"));
        badgeView.setCompoundDrawables(null, null, badge, null);
    }

    public void attachBadge(TextView badgeView, String text, String count) {
        BadgeDrawable badge = new BadgeDrawable(count);
        SpannableStringBuilder badgeString = new SpannableStringBuilder();
        badgeString.append(text);
        SpannableString imageSp = new SpannableString("  ");
        ImageSpan badgeSp = new ImageSpan(badge, ImageSpan.ALIGN_BOTTOM) {
            @Override
            public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
                Drawable d = getDrawable();
                canvas.save();

                int transY = bottom - d.getBounds().bottom;
                transY -= paint.getFontMetricsInt().descent / 2;
                canvas.translate(x, transY);
                d.draw(canvas);
                canvas.restore();
            }
        };
        imageSp.setSpan(badgeSp, 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        badgeString.append(imageSp);
        badgeView.setText(badgeString);
    }
}
