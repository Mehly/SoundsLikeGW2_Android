package at.mehlox.guildwars;

import java.sql.SQLException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.time.StopWatch;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import at.mehlox.guildwars.data.ormlite.DatabaseHelper;
import at.mehlox.guildwars.gui.base.SlidingBaseActivity;
import at.mehlox.guildwars.gui.fragments.*;
import at.mehlox.guildwars.rest.RestClient;
import at.mehlox.guildwars.rest.entities.base.ItemBase;

import com.nineoldandroids.view.animation.AnimatorProxy;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

@EActivity(R.layout.activity_main)
public class MainActivity extends SlidingBaseActivity {

	private final String TAG = getClass().getSimpleName();
	private static final String SAVED_STATE_ACTION_BAR_HIDDEN = "saved_state_action_bar_hidden";

	@ViewById(R.id.sliding_layout)
	SlidingUpPanelLayout mSlidingPanel;

	// @ViewById(R.id.sliding_panel_alarms)
	// Button mButtonAlarms;

	@Bean
	RestClient mRestClient;

	@Bean
	DatabaseHelper mDatabase;

	StopWatch mStopWatch;
	private boolean mContinueStopwatch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

		mStopWatch = new StopWatch();

	}

	@AfterViews
	protected void afterViews() {

		try {
			initSlidingUpPanel();
		} catch (Exception e) {
			Log.e(TAG, "Could not initialize SlidingUpPanel.", e);
		}

		updateCurrentEvent();
		loadEvents();
		initButtons();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, !getActionBar()
				.isShowing());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		boolean actionBarHidden = savedInstanceState != null ? savedInstanceState
				.getBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, false) : false;
		if (actionBarHidden) {
			getActionBar().hide();
		}
	}

	private void initButtons() {

		// mButtonAlarms.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// putFragment(AboutFragment_.builder().build());
		// }
		// });

	}

	@Click(R.id.sliding_panel_alarms)
	protected void onClickAlarms() {
		putFragment(AboutFragment_.builder().build());
	}

	@Click(R.id.sliding_panel_about)
	protected void onClickAbout() {

		setTitle(getString(R.string.about));
		putFragment(AboutFragment_.builder().build());
	}

	@Background
	protected void loadEvents() {

		int[] itemIds = mRestClient.getItemIds();
		try {
			ItemBase itemBase = mDatabase.getItem(itemIds[0]);
			if (itemBase == null) {

				itemBase = mRestClient.getItemDetails(itemIds[0]);

				mDatabase.createOrUpdateItem(itemBase);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Log.i(getClass().getSimpleName(), "Loaded items: " + itemIds.length);

	}

	private void initSlidingUpPanel() throws Exception {

		final SlidingUpPanelLayout layout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

		if (layout != null) {

			layout.setPanelSlideListener(new PanelSlideListener() {
				@Override
				public void onPanelSlide(View panel, float slideOffset) {
					setActionBarTranslation(layout.getCurrentParalaxOffset());
				}

				@Override
				public void onPanelExpanded(View panel) {
					Log.i(TAG, "onPanelExpanded");

				}

				@Override
				public void onPanelCollapsed(View panel) {
					Log.i(TAG, "onPanelCollapsed");

				}

				@Override
				public void onPanelAnchored(View panel) {
					Log.i(TAG, "onPanelAnchored");

				}
			});
		}

	}

	public void setActionBarTranslation(float y) {
		// Figure out the actionbar height
		int actionBarHeight = 0;
		TypedValue tv = new TypedValue();
		if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					getResources().getDisplayMetrics());
		}
		// A hack to add the translation to the action bar
		ViewGroup content = ((ViewGroup) findViewById(android.R.id.content)
				.getParent());
		int children = content.getChildCount();
		for (int i = 0; i < children; i++) {
			View child = content.getChildAt(i);
			if (child.getId() != android.R.id.content) {
				if (y <= -actionBarHeight) {
					child.setVisibility(View.GONE);
				} else {
					child.setVisibility(View.VISIBLE);
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
						child.setTranslationY(y);
					} else {
						AnimatorProxy.wrap(child).setTranslationY(y);
					}
				}
			}
		}
	}

	private void updateCurrentEvent() {
		FragmentManager fragmentManager = getSupportFragmentManager();

		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right, R.anim.slide_in_right,
				R.anim.slide_out_left);

		ft.replace(R.id.activity_main_current_event_fragment,
				CurrentEventTeaserFragment_.builder().build()).commit();

	}

	protected void putFragment(Fragment f) {

		FragmentManager fragmentManager = getSupportFragmentManager();

		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right, R.anim.slide_in_right,
				R.anim.slide_out_left);

		ft.replace(R.id.fragment_frame, f)
				.addToBackStack(f.getClass().getSimpleName()).commit();

		mSlidingPanel.collapsePane();

	}

}
