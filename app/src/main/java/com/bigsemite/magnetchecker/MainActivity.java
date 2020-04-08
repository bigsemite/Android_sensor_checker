package com.bigsemite.magnetchecker;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements SensorEventListener{

	SensorManager sm;
	Sensor mSensor;
	EditText sList;
	TextView res;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maglist);
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		res = (TextView) findViewById(R.id.textView2);

		
	}
	
	public void getSensor(View v){
		
		sList = (EditText) findViewById(R.id.editText1);
		//Toast.makeText(getApplicationContext(),"Good", Toast.LENGTH_SHORT).show();
		
		if(sm.getDefaultSensor(Sensor.TYPE_ALL) != null){
			//get the list of magnetic sensors
			
			
			List<Sensor> magSensors = sm.getSensorList(Sensor.TYPE_ALL);
			for (int i=0; i <magSensors.size(); i++){
				sList.append("\n" + magSensors.get(i).getName() +": " + magSensors.get(i).getVendor());
				//Toast.makeText(getApplicationContext(),"Good", Toast.LENGTH_SHORT).show();
			}//end for
			
		}
		else{
			sList.setText("No Magnetic Sensor found on this device");
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		int v = (int) arg0.values[0];
		//if (v)
		res.setText((int)arg0.values[0] +"lux");
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		sm.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		sm.unregisterListener(this);
	}

}
