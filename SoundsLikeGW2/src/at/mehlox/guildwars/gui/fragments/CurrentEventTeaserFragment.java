package at.mehlox.guildwars.gui.fragments;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import at.mehlox.guildwars.R;
import at.mehlox.guildwars.gui.base.BaseFragment;

@EFragment(R.layout.fragment_current_event)
public class CurrentEventTeaserFragment extends BaseFragment {

	@ViewById(R.id.fragment_current_event_time)
	TextView mTextViewEventTime;

	private StopWatch mStopWatch;
	private boolean mContinueStopwatch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mStopWatch = new StopWatch();
	}

	@AfterViews
	protected void afterViews() {
		startStopwatch();
	}

	@Background
	protected void startStopwatch() {

		Log.e(TAG, "Try to start StopWatch.");

		try {

			if (mStopWatch.isStarted()) {
				mStopWatch.reset();
			}

			mStopWatch.start();
			mContinueStopwatch = true;

			while (mContinueStopwatch) {

				mStopWatch.split();
				updateEventTime(mStopWatch.getSplitTime());
				mStopWatch.unsplit();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IllegalStateException e) {
			Log.e(TAG, "Could not start StopWatch.", e);
		}

	}

	@UiThread
	protected void updateEventTime(long timeInMillis) {

		String time = DurationFormatUtils.formatDuration(timeInMillis, "mm:ss");
		mTextViewEventTime.setText(time);
	}

	protected void stopStopwatch() {
		mContinueStopwatch = false;
	}

}
