package com.example.fragment;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dianping.R;
import com.example.utils.ShareUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class FragmentHome extends Fragment implements LocationListener {

	@ViewInject(R.id.index_top_city)
	TextView index_top_city;
	@ViewInject(R.id.iv_top_scan)
	ImageView top_scan;

	private String cityName;
	private LocationManager locationManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		ViewUtils.inject(this, view);
		// get the city name
		index_top_city.setText(ShareUtils.getCityName(getActivity()));
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		checkGPSisOpen();
	}

	private void checkGPSisOpen() {
		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		boolean isOpen = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!isOpen) {
			Intent intent = new Intent();
			intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivityForResult(intent, 0);
		}
		startLocation();
	}

	private void startLocation() {
		// TODO Auto-generated method stub
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				2000, 10, this);

	}

	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == 1) {
				Toast.makeText(getActivity(), "123", Toast.LENGTH_LONG).show();
				Toast.makeText(getActivity(), cityName, Toast.LENGTH_LONG)
						.show();
				index_top_city.setText(cityName);
			}
			return false;
		}
	});

	@Override
	public void onLocationChanged(Location location) {
		updateWithNewLocation(location);
	}

	private void updateWithNewLocation(Location location) {
		double lat = 0.0, lng = 0.0;
		if (location != null) {
			lat = location.getLatitude();
			lng = location.getLongitude();

			Log.i("TAG", "Lat is" + lat + ",LNG is" + lng);
		} else {
			cityName = "Can't get the CityName";
		}
		List<Address> list = null;
		Geocoder ge = new Geocoder(getActivity());


		try {
			Toast.makeText(getActivity(), lat + "---" + lng, Toast.LENGTH_LONG)
					.show();
			list = ge.getFromLocation(lat, lng, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Address ad = list.get(i);
				cityName = ad.getLocality();
			}
		}
		handler.sendEmptyMessage(1);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		stopLocation();
		ShareUtils.setCityName(getActivity(), cityName);

	}

	private void stopLocation() {
		locationManager.removeUpdates(this);

	}
}
