package com.example.quotereader;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = ( ListView ) findViewById( R.id.quotes_list );
		mListView.setAdapter( new QuoteAdapter( this ) );
		
		//An activity in android is instantiated with an intent, an intent is used to start an activity.
		mListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick( AdapterView arg0, View arg1, int position, long arg3 ) {
				Intent i = new Intent( MainActivity.this, QuoteDetail.class );
				i.putExtra( "position", position );
				startActivity( i );
			}
		});
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
	
	public class QuoteAdapter extends BaseAdapter {
		
		private Context mContext;
		private LayoutInflater mInflater;
		private DataSource mDataSource;
	
		public QuoteAdapter( Context c ) {
			mContext = c;
			mInflater = ( LayoutInflater ) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			mDataSource = new DataSource();
		}
		
		@Override
		public int getCount() {
			return mDataSource.getDataSourceLength();
		}
		
		@Override
		public Object getItem( int position ) {
			return position;
		}
		
		@Override
		public long getItemId( int position ) {
			return position;
		}
		
		@Override
		public View getView( int position, View convertView, ViewGroup parent ) {
			ImageView thumbnail;
			TextView quote;
			
			if( convertView == null ) {
				convertView = mInflater.inflate( R.layout.list_item_layout, parent, false );
			}
			
			thumbnail = ( ImageView ) convertView.findViewById( R.id.thumb );
			thumbnail.setImageResource( mDataSource.getmPhotoPool().get( position ) );
			quote = ( TextView ) convertView.findViewById( R.id.text );
			quote.setText( mDataSource.getmQuotePool().get( position ) );
			
			return convertView;
		}
	}

}
