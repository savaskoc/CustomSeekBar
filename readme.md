#CustomSeekBar
Ranged and/or stepped SeekBar for android.

![Tinypic](http://i59.tinypic.com/xghaxk.png)

###Installation
Add [jitpack.io](https://jitpack.io/) to your repositories.

    repositories {
        maven { url "https://jitpack.io" }
    }

###Usage Sample
    <RangeSeekBar
                    android:id="@+id/rsb"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    app:rsb_max="30"
                    app:rsb_min="5" />

or

    <StepSeekBar
                    android:id="@+id/ssb"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    app:rsb_max="30"
                    app:rsb_min="5"
                    app:ssb_interval="5" />

###Properties for RangeSeekBar
* app:rsb_min (Min value for SeekBar)
* app:rsb_max (Max value for SeekBar)

###Properties for StepSeekBar (also RangeSeekBar, so you can use its properties too)
* app:ssb_interval (Interval for SeekBar) (eg. for interval 5, you have steps 0.5.10.15.20...100)
* app:ssb_showSteps (Show steps on SeekBar)
* app:ssb_textColor (Text color for step text)
* app:ssb_selectedTextColor (Text color for selected step text)
* app:ssb_textSize (Text size for step text)

###Known bugs
* When using too big thumb drawable, steps will misplaced. (in StepSeekBar)
