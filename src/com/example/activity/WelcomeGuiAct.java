package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.dianping.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeGuiAct extends Activity {

	@ViewInject(R.id.welcome_guide_btn)
	Button welcome_guide_btn;
	@ViewInject(R.id.welcome_pager)
	ViewPager welcome_pager;
	
	private boolean isFirstRun = false;

	private List<View> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_gui);
		ViewUtils.inject(this);

		initPager();
	}

	@OnClick(R.id.welcome_guide_btn)
	public void click(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
		
	}

	@SuppressWarnings("deprecation")
	private void initPager() {
		list = new ArrayList<View>();

		list = new ArrayList<View>();
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.guide_01);
		list.add(iv);
		ImageView iv1 = new ImageView(this);
		iv1.setImageResource(R.drawable.guide_02);
		list.add(iv1);
		ImageView iv2 = new ImageView(this);
		iv2.setImageResource(R.drawable.guide_03);
		list.add(iv2);
		welcome_pager.setAdapter(new guideViewAdapter());
		welcome_pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 2) {
					welcome_guide_btn.setVisibility(View.VISIBLE);
				} else {
					welcome_guide_btn.setVisibility(View.GONE);
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private class guideViewAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(list.get(position));

		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}

	}
}
