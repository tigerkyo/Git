package com.example.sqlitedemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<BMIObject>{

	// 畫面資源編號
    private int resource;

    // 包裝的記事資料
    private List<BMIObject> items;
    
    
	public ItemAdapter(Context context, int resource, List<BMIObject> objects) {
		super(context, resource, objects);
		this.resource=resource;
		this.items=objects;
		
	}
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        // 讀取目前位置的記事物件
        final BMIObject item = getItem(position);
 
        if (convertView == null) {
            // 建立項目畫面元件
            itemView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater)
                    getContext().getSystemService(inflater);
            li.inflate(resource, itemView, true);
        }
        else {
            itemView = (LinearLayout) convertView;
        }
 
        // 讀取資料
//        TextView numberView = (TextView) itemView.findViewById(R.id.No_text);
//        TextView heightView = (TextView) itemView.findViewById(R.id.Height_text);
//        TextView weightView = (TextView) itemView.findViewById(R.id.Weight_text);
//        TextView BMIView = (TextView) itemView.findViewById(R.id.BMI_text);
//        TextView DateView = (TextView) itemView.findViewById(R.id.Date_text);
  
 
        // 設定資料
//        numberView.setText(String.valueOf(item.getId()));
//        heightView.setText(String.valueOf(item.getHeight()));
//        weightView.setText(String.valueOf(item.getWeight()));
//        BMIView.setText(String.valueOf(item.getBmi()));
//        DateView.setText(String.valueOf(item.getDate()));

 
        return itemView;
    }	

	
}
