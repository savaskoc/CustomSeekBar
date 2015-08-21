package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.savaskoc.customseekbar.R;

/**
 * Created by savaskoc on 21/08/2015.
 */
public class RangeSeekBar extends SeekBar {
    private int minValue = 20;
    private int maxValue = 40;

    public RangeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeSeekBar);
        init(
                a.getInt(R.styleable.RangeSeekBar_rsb_min, 0),
                a.getInt(R.styleable.RangeSeekBar_rsb_max, 100)
        );
        a.recycle();
    }

    private void init(int min, int max) {
        setMinValue(min);
        setMaxValue(max);
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getProgressValue() {
        return Math.round((((maxValue - minValue) * getProgress()) / 100f) + minValue);
    }

    public void setProgressValue(int progress) {
        if (progress < minValue) progress = minValue;
        else if (progress > maxValue) progress = maxValue;

        setProgress(Math.round((100f * (progress - minValue)) / (maxValue - minValue)));
    }

    @Override
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        super.setOnSeekBarChangeListener(l instanceof RangeListener ? l : new RangeListener(l));
    }

    protected class RangeListener implements OnSeekBarChangeListener {
        private OnSeekBarChangeListener listener;

        public RangeListener(OnSeekBarChangeListener listener) {
            this.listener = listener;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (listener != null)
                listener.onProgressChanged(seekBar, getProgressValue(), fromUser);
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
