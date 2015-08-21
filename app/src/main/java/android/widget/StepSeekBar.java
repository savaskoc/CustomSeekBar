package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.savaskoc.customseekbar.R;
import com.savaskoc.customseekbar.util.IntegerUtils;


/**
 * Created by savaskoc on 21/08/2015.
 */
public class StepSeekBar extends RangeSeekBar {
    private int interval;

    private Paint paint;
    private Paint selectedPaint;

    private boolean showSteps;

    public StepSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StepSeekBar);
        init(
                a.getInt(R.styleable.StepSeekBar_ssb_interval, 4),
                a.getColor(R.styleable.StepSeekBar_ssb_textColor, Color.BLACK),
                a.getColor(R.styleable.StepSeekBar_ssb_selectedTextColor, Color.WHITE),
                a.getDimensionPixelSize(R.styleable.StepSeekBar_ssb_textSize, IntegerUtils.dpToPx(15)),
                a.getBoolean(R.styleable.StepSeekBar_ssb_showSteps, true)
        );
        a.recycle();
    }

    private void init(int stepSize, int textColor, int selectedTextColor, int textSize, boolean showSteps) {
        setInterval(stepSize);
        setShowSteps(showSteps);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Align.LEFT);
        setTextColor(textColor);

        selectedPaint = new Paint(paint);
        setSelectedTextColor(selectedTextColor);

        setTextSize(textSize);

        setOnSeekBarChangeListener(null);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setTextColor(int color) {
        paint.setColor(color);
    }

    public void setSelectedTextColor(int color) {
        selectedPaint.setColor(color);
    }

    public void setTextSize(int px) {
        paint.setTextSize(px);
        selectedPaint.setTextSize(px);
    }

    public boolean isShowSteps() {
        return showSteps;
    }

    public void setShowSteps(boolean showSteps) {
        this.showSteps = showSteps;
    }

    private void drawStep(Canvas canvas, int step) {
        float ratio = (step - getMinValue()) / (float) (getMaxValue() - getMinValue());
        String text = String.valueOf(step);

        Rect r = new Rect();
        paint.getTextBounds(text, 0, text.length(), r);

        int width = canvas.getWidth() - getPaddingLeft() - getPaddingRight();
        int height = canvas.getHeight();
        float x = (width * ratio) - r.width() / 2f - r.left;

        float y = height / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x + getPaddingLeft(), y, step == getProgressValue() ? selectedPaint : paint);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        if (!isShowSteps()) {
            super.onDraw(canvas);
            return;
        }
        for (int i = getMinValue(); i <= getMaxValue(); i += interval)
            if (i != getProgressValue())
                drawStep(canvas, i);
        super.onDraw(canvas);
        drawStep(canvas, getProgressValue());
    }

    @Override
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        super.setOnSeekBarChangeListener(l instanceof StepListener ? l : new StepListener(l));
    }

    private class StepListener implements OnSeekBarChangeListener {
        private OnSeekBarChangeListener listener;

        public StepListener(OnSeekBarChangeListener listener) {
            this.listener = listener;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            ((RangeSeekBar) seekBar).setProgressValue(IntegerUtils.round(progress, interval));
            if (listener != null)
                listener.onProgressChanged(seekBar, progress, fromUser);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (listener != null)
                listener.onStartTrackingTouch(seekBar);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (listener != null)
                listener.onStopTrackingTouch(seekBar);
        }
    }
}
