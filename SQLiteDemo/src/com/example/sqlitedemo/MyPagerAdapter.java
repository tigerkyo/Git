package com.example.sqlitedemo;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

	/**
	 * Pager”Ï∞tæπ
	 */
	public class MyPagerAdapter extends PagerAdapter {
		
		List<View> listview =  new ArrayList<View>();
		public MyPagerAdapter(List<View> list) {
			this.listview = list;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object obj) {
			container.removeView(listview.get(position));
		}


		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(listview.get(position),0);
			return listview.get(position);
		}

		@Override
		public int getCount() {
			return listview.size();
		}

		@Override
		public boolean isViewFromObject(View v, Object obj) {
			return v == obj;
		}


	}