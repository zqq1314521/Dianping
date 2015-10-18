package com.example.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.dianping.R;
import com.example.fragment.FragmentHome;
import com.example.fragment.FragmentMy;
import com.example.fragment.FragmentSearch;
import com.example.fragment.FragmentTuan;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {
	@ViewInject(R.id.main_button_tabs)
	RadioGroup radioGroup;
	@ViewInject(R.id.rb_main_home)
	private RadioButton radioButton;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		fragmentManager = getSupportFragmentManager();
		radioButton.setChecked(true);
		radioGroup.setOnCheckedChangeListener(this);
		changeFragment(new FragmentHome(), false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_main_home:
			changeFragment(new FragmentHome(), true);
			break;
		case R.id.rb_main_tuan:
			changeFragment(new FragmentTuan(), true);
			break;
		case R.id.rb_main_search:
			changeFragment(new FragmentSearch(), true);
			break;
		case R.id.rb_main_my:
			changeFragment(new FragmentMy(), true);
			break;
		default:
			break;
		}
	}
	
	public void changeFragment(Fragment fragment, boolean isFirst){
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(R.id.main_content, fragment);
		if(!isFirst){
			ft.addToBackStack(null);
		}
		ft.commit();
		
	}

	private void initFragment() {
		
		
	}
}
