package cool.dm.ehbrowser;

import cool.dm.ehbrowser.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

public class TabActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String NEW=(String) this.getResources().getText(R.string.New);
		String TOP=(String) this.getResources().getText(R.string.TOP);
		String fanle=(String) this.getResources().getText(R.string.fanle);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar
				.newTab()
				.setText(NEW)
				.setIcon(R.drawable.newcon2)
				.setTabListener(
						new MyTabListener<MessageFragment>(this,
								"MessageFragment", MessageFragment.class)));

		actionBar
				.addTab(actionBar
						.newTab()
						.setText(TOP)
						.setIcon(R.drawable.hotcon2)
						.setTabListener(
								new MyTabListener<News>(this, "ComicFragment",
										News.class)));
		actionBar.addTab(actionBar
				.newTab()
				.setText(fanle)
				.setIcon(R.drawable.seachicon2)
				.setTabListener(
						new MyTabListener<seach>(this, "ComicFragment",
								seach.class)));

	}

	public class MyTabListener<T extends Fragment> implements
			ActionBar.TabListener {
		private final Activity activity;
		private final String tag;
		private final Class<T> clz;
		private final Bundle bundle;
		private Fragment fragment;

		public MyTabListener(Activity activity, String tag, Class<T> clz) {
			this(activity, tag, clz, null);
		}

		public MyTabListener(Activity activity, String tag, Class<T> clz,
				Bundle bundle) {
			this.activity = activity;
			this.tag = tag;
			this.clz = clz;
			this.bundle = bundle;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (fragment == null) {
				fragment = Fragment
						.instantiate(activity, clz.getName(), bundle);
				ft.add(android.R.id.content, fragment, tag);
			} else {
				ft.attach(fragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (fragment != null) {
				ft.detach(fragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(activity, "Reselected!", Toast.LENGTH_SHORT).show();
		}
	}
}