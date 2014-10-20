package com.example.sqlitedemo;

import java.text.DecimalFormat;





import java.util.ArrayList;
import java.util.Date;
import java.util.List;







import android.R.string;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	 private ViewPager mViewPager;
	 private BMIDAO bmiDAO;
	 private boolean isAdded ;
	 
	private ListView item_list ;
	 
	// ListView使用的自定Adapter物件
//	 private ItemAdapter itemAdapter;
	 // 儲存所有記事本的List物件
	private ArrayAdapter<String> listAdapter;	
	
	private List<BMIObject> items;	 

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_background);
	
	    // 建立資料庫物件
		bmiDAO = new BMIDAO(getApplicationContext());
		items = bmiDAO.getAll();
		List strList = new ArrayList<String>();
        for(BMIObject obj:items){
        	String temp="項目:"+String.valueOf(obj.getId())+",  BMI:"+String.valueOf(obj.getBmi());
        	strList.add(temp);
        }                
        Object[] objs=  strList.toArray();        
	    String[] list = new String[objs.length];
	    for(int i=0 ; i<objs.length;i++){
	    	list[i] = objs[i].toString();
	    }
		
		
		
//		if(items!=null){
//			itemAdapter = new ItemAdapter(this,R.layout.activity_view_data,items);
//			item_list.setAdapter(itemAdapter);
//		}		
		
		
		 getLayoutInflater();
		 LayoutInflater mInflater = LayoutInflater.from(this);	
		View v1 = mInflater.inflate(R.layout.activity_main, null);
	    View v2 = mInflater.inflate(R.layout.activity_view_data, null);
	      
	      List<View> viewList = new ArrayList<View>(); 
	      viewList.add(v1);
	      viewList.add(v2);
	      mViewPager = (ViewPager) findViewById(R.id.viewpager);
	      mViewPager.setAdapter(new MyPagerAdapter(viewList));
	      mViewPager.setCurrentItem(0); //設定第幾頁為預設頁面 1為R.layout.activity_fouth	      
	      View view1 = viewList.get(0);//0為	R.layout.activity_main		
		
		
		Button button1 = (Button) view1.findViewById(R.id.submit);
		button1.setOnClickListener(calcBMI);
		
		Button button2 = (Button) view1.findViewById(R.id.save);
		button2.setOnClickListener(saveBMI);
		
	    View view2 = viewList.get(1);
		item_list = (ListView) view2.findViewById(R.id.listView);
		listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);		
		item_list.setAdapter(listAdapter);

		
		
		
	}
	
	private OnClickListener calcBMI = new OnClickListener() {
		public void onClick(View v) {
			
		
			DecimalFormat nf = new DecimalFormat("0.00");

			EditText Field_Height =(EditText) findViewById(R.id.height);
			EditText Field_Weight =(EditText) findViewById(R.id.weight);
			double height ;
			double weight ;
			try {
				height = Double.parseDouble(Field_Height.getText()==null?"0":Field_Height.getText().toString())/100;//可能會拋出exception)
			}catch(Exception e) {
				height=0;
			}			
			try {
				 weight = Double.parseDouble(Field_Weight.getText()==null?"0":Field_Weight.getText().toString());	//可能會拋出exception						
			}catch(Exception e) {
				 weight = 0;
			}
			if(height==0 && weight==0) {
				  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			        dialog.setTitle(R.string.alert_dialog);
			        dialog.setMessage(R.string.BothEmpty);
			        dialog.setPositiveButton(string.ok,
			                new DialogInterface.OnClickListener(){
			                    public void onClick(
			                            DialogInterface dialoginterface, int i){
			                            }
			                    });			        
			        dialog.show();				
			}else {
				if(height==0) {
				  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			        dialog.setTitle(R.string.alert_dialog);
			        dialog.setMessage(R.string.LackHeight);
			        dialog.setPositiveButton(string.ok,
			                new DialogInterface.OnClickListener(){
			                    public void onClick(
			                            DialogInterface dialoginterface, int i){
			                            }
			                    });			        
			        dialog.show();					
					
				}else if ( weight==0) {
				  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			        dialog.setTitle(R.string.alert_dialog);
			        dialog.setMessage(R.string.LackWeight);
			        dialog.setPositiveButton(string.ok,
			                new DialogInterface.OnClickListener(){
			                    public void onClick(
			                            DialogInterface dialoginterface, int i){
			                            }
			                    });			        
			        dialog.show();					
					
				}else {
					double BMI = weight/(height*height);
					TextView result = (TextView)findViewById(R.id.result);
					result.setText("Your BMI is"+nf.format(BMI));
					//Give Health advice
					TextView field_Suggest = (TextView) findViewById(R.id.suggest);
						if(BMI>25) {
							field_Suggest.setText(R.string.advice_heavy);
						}else if (BMI<20) {
							field_Suggest.setText(R.string.advice_light);
						}else {
							field_Suggest.setText(R.string.advice_average);
						}	
						
						isAdded = false;//
				}
			}
			
			
		}
	};
	
	
	private OnClickListener saveBMI = new OnClickListener(){
		public void onClick(View v){
			bmiDAO = new BMIDAO(getApplicationContext());
			
			DecimalFormat nf = new DecimalFormat("0.00");

			EditText Field_Height =(EditText) findViewById(R.id.height);
			EditText Field_Weight =(EditText) findViewById(R.id.weight);
			double height ;
			double weight ;
			try {
				height = Double.parseDouble(Field_Height.getText()==null?"0":Field_Height.getText().toString())/100;//可能會拋出exception)
			}catch(Exception e) {
				height=0;
			}			
			try {
				 weight = Double.parseDouble(Field_Weight.getText()==null?"0":Field_Weight.getText().toString());	//可能會拋出exception						
			}catch(Exception e) {
				 weight = 0;
			}
			if(height==0 && weight==0) {
				  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			        dialog.setTitle(R.string.alert_dialog);
			        dialog.setMessage(R.string.BothEmpty);
			        dialog.setPositiveButton(string.ok,
			                new DialogInterface.OnClickListener(){
			                    public void onClick(
			                            DialogInterface dialoginterface, int i){
			                            }
			                    });			        
			        dialog.show();				
			}else {
				if(height==0) {
				  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			        dialog.setTitle(R.string.alert_dialog);
			        dialog.setMessage(R.string.LackHeight);
			        dialog.setPositiveButton(string.ok,
			                new DialogInterface.OnClickListener(){
			                    public void onClick(
			                            DialogInterface dialoginterface, int i){
			                            }
			                    });			        
			        dialog.show();					
					
				}else if ( weight==0) {
				  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			        dialog.setTitle(R.string.alert_dialog);
			        dialog.setMessage(R.string.LackWeight);
			        dialog.setPositiveButton(string.ok,
			                new DialogInterface.OnClickListener(){
			                    public void onClick(
			                            DialogInterface dialoginterface, int i){
			                            }
			                    });			        
			        dialog.show();					
					
				}else {
					double BMI = weight/(height*height);

					if(!isAdded){
						
						BMIObject item = new BMIObject();
						item.setHeight(height);
						item.setWeight(weight);
						item.setBmi(BMI);										
						item.setDate(new Date().getDate());					
						bmiDAO.insert(item);
						isAdded = true;
					  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
				        dialog.setTitle(R.string.alert_dialog);
				        dialog.setMessage(R.string.SaveOK);
				        dialog.setPositiveButton(string.ok,
				                new DialogInterface.OnClickListener(){
				                    public void onClick(
				                            DialogInterface dialoginterface, int i){
				                            }
				                    });			        
				        dialog.show();
						listAdapter.notifyDataSetChanged(); 
						
					}else{
						
					  	AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
				        dialog.setTitle(R.string.alert_dialog);
				        dialog.setMessage(R.string.SaveFailure);
				        dialog.setPositiveButton(string.ok,
				                new DialogInterface.OnClickListener(){
				                    public void onClick(
				                            DialogInterface dialoginterface, int i){
				                            }
				                    });			        
				        dialog.show();								
					}

			        
									
					
					
				}
			}			
			
			
		}
		
		
	};
	
	
	
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
}
