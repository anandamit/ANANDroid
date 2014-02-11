package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";
	private static final String PAUSE_KEY = "pause";
	private static final String DESTROY_KEY = "destroy";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// Lifecycle counters
	// Create counter variables for onCreate(), onRestart(), onStart() and
	// onResume(), called mCreate, etc.
	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called
	private int mCreate = 0;
	private int mStart = 0;
	private int mResume = 0;
	private int mRestart = 0;

	// Create variables for each of the TextViews, called
	private TextView mTvCreate;
	private TextView mTvStart;
	private TextView mTvResume;
	private TextView mTvRestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		// Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		mTvCreate = (TextView) findViewById(R.id.create);
		mTvStart = (TextView) findViewById(R.id.start);
		mTvResume = (TextView) findViewById(R.id.resume);
		mTvRestart = (TextView) findViewById(R.id.restart);

		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Launch Activity Two
				// Hint: use Context's startActivity() method
				// Create an intent stating which Activity you would like to
				// start
				// Launch the Activity using the intent
				try {
					Intent newIntent = new Intent(v.getContext(),ActivityTwo.class);
					startActivity(newIntent);
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
			}
		});

		// Check for previously saved state
		if (savedInstanceState != null) {
			// Restore value of counters from saved state
			// Only need 4 lines of code, one for every count variable
			mCreate = savedInstanceState.getInt(CREATE_KEY);
			mRestart = savedInstanceState.getInt(RESTART_KEY);
			mResume = savedInstanceState.getInt(RESUME_KEY);
			mStart = savedInstanceState.getInt(START_KEY);
		}

		Log.i(TAG, "Entered the onCreate() method");
		// Update the appropriate count variable
		// Update the user interface via the displayCounts() method
		mCreate++;
		displayCounts();
	}

	// Life cycle callback overrides

	@Override
	public void onStart() {
		super.onStart();
		Log.i(TAG, "Entered the onStart() method");
		// Update the appropriate count variable
		// Update the user interface
		mStart++;
		displayCounts();
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "Entered the onResume() method");
		// Update the appropriate count variable
		// Update the user interface
		mResume++;
		displayCounts();
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.i(TAG, "Entered the onRestart() method");
		// Update the appropriate count variable
		// Update the user interface
		mRestart++;
		displayCounts();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save state information with a collection of key-value pairs
		// 4 lines of code, one for every count variable
		//savedInstanceState.getString(key, defaultValue);
		savedInstanceState.putInt(CREATE_KEY, mCreate);
		savedInstanceState.putInt(RESTART_KEY, mRestart);
		savedInstanceState.putInt(RESUME_KEY, mResume);
		savedInstanceState.putInt(START_KEY, mStart);
		super.onSaveInstanceState(savedInstanceState);
	}

	// Updates the displayed counters
	public void displayCounts() {
		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	}
}
