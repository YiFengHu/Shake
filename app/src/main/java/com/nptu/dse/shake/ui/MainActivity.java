package com.nptu.dse.shake.ui;

import java.util.ArrayList;

import com.nptu.dse.shake.BlackBoardItem;
import com.nptu.dse.shake.R;
import com.nptu.dse.shake.R.anim;
import com.nptu.dse.shake.R.drawable;
import com.nptu.dse.shake.R.id;
import com.nptu.dse.shake.R.layout;
import com.nptu.dse.shake.R.menu;
import com.nptu.dse.shake.R.string;
import com.nptu.dse.shake.queue.VideoQueue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	private static final int ID_UPPER_SPORT_IMAGEVIEW = R.id.main_upperSportImageView;
	private static final int ID_UPPER_SPORT_2_IMAGEVIEW = R.id.main_upperSport2ImageView;
	private static final int ID_LOWER_SPORT_IMAGEVIEW = R.id.main_lowerSportImageView;
	private static final int ID_ALL_BODY_SPORT_IMAGEVIEW = R.id.main_allBodySportImageView;
	private static final int ID_STRETCH_SPORT_IMAGEVIEW = R.id.main_stretchSportImageView;
	private static final int ID_SWEAT_SPORT_IMAGEVIEW = R.id.main_sweatSportImageView;
	
	private static final int ID_FIRST_ROW_IMAGE_VIEW_LAYOUT = R.id.main_firstSportButtonLayout;
	private static final int ID_FIRST_ROW_BLACK_BOARD_LAYOUT = R.id.main_firstRowblackBoardLayout;
	private static final int ID_SECOND_ROW_BLACK_BOARD_LAYOUT = R.id.main_secondRowblackBoardLayout;

	
	private static final String[] sports = { "Upper Body Sports",
			"Upper Body Sports 2", "Lower Body Sports", "All Body Sports",
			"Stretch Sports", "Sweat Sports" };

	private static final int[] background = {
			R.drawable.selector_yellow_button, R.drawable.selector_red_button,
			R.drawable.selector_green_button,
			R.drawable.selector_light_blue_button,
			R.drawable.selector_purple_button, R.drawable.selector_blue_button };

	private static final int[] imageSource = { R.drawable.ic_upper_body_sport,
			R.drawable.ic_upper_boddy_sport2, R.drawable.ic_lower_body_sport,
			R.drawable.ic_all_body_sport, R.drawable.ic_stretch_sport,
			R.drawable.ic_sweat_sport };
	
	private static final String[] videoId = {"sSTEHb99ZOY", "hBPc6k7DpIA", "9kBQfjVAayM", "mtm_b681cBs", "6uW9PpQUFiU", "rl2oG1_rUI0"};
	private static final String restVideoId = "G3H9SGzGueo";
	
	private LinearLayout firstRowblackBoardLayout = null;
	private LinearLayout secondRowblackBoardLayout = null;

	private ImageView upperSportImageView = null;
	private ImageView upperSport2ImageView = null;
	private ImageView lowerSportImageView = null;
	private ImageView allBodySportImageView = null;
	private ImageView stretchSportImageView = null;
	private ImageView sweatSportImageView = null;
	private Button goButton = null;

	private ArrayList<BlackBoardItem> blackBoardList = null;
	
	private OnClickListener blackBoardImageClickListener = null;
	
	private Animation fadeInAnimation = null;
	private Animation fadeOutAnimation = null;
	
	private LinearLayout firstRowImageViewLayout = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		blackBoardList = new ArrayList<BlackBoardItem>();

		fadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_go_button_fade_in);   
		
		fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_go_button_fade_out);   
		
		initLayout();
		
		blackBoardImageClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				int positionInList = (Integer)view.getTag();
				
				blackBoardList.remove(positionInList);
				updateBlackBoardView();
			}
		};
	}

	private void initLayout() {
		firstRowImageViewLayout = (LinearLayout)findViewById(ID_FIRST_ROW_IMAGE_VIEW_LAYOUT);
		firstRowblackBoardLayout = (LinearLayout) findViewById(ID_FIRST_ROW_BLACK_BOARD_LAYOUT);
		secondRowblackBoardLayout = (LinearLayout) findViewById(ID_SECOND_ROW_BLACK_BOARD_LAYOUT);

		upperSportImageView = (ImageView) findViewById(ID_UPPER_SPORT_IMAGEVIEW);
		upperSport2ImageView = (ImageView) findViewById(ID_UPPER_SPORT_2_IMAGEVIEW);
		lowerSportImageView = (ImageView) findViewById(ID_LOWER_SPORT_IMAGEVIEW);
		allBodySportImageView = (ImageView) findViewById(ID_ALL_BODY_SPORT_IMAGEVIEW);
		stretchSportImageView = (ImageView) findViewById(ID_STRETCH_SPORT_IMAGEVIEW);
		sweatSportImageView = (ImageView) findViewById(ID_SWEAT_SPORT_IMAGEVIEW);

		goButton = (Button)findViewById(R.id.main_goButton);		
				
		upperSportImageView.setOnClickListener(this);
		upperSport2ImageView.setOnClickListener(this);
		lowerSportImageView.setOnClickListener(this);
		allBodySportImageView.setOnClickListener(this);
		stretchSportImageView.setOnClickListener(this);
		sweatSportImageView.setOnClickListener(this);
		
		goButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case ID_UPPER_SPORT_IMAGEVIEW:

			updateBlackBoardData(ClickType.Add, sports[0], background[0], imageSource[0], videoId[0]);
			break;

		case ID_UPPER_SPORT_2_IMAGEVIEW:

			updateBlackBoardData(ClickType.Add, sports[1], background[1], imageSource[1], videoId[1]);
			break;

		case ID_LOWER_SPORT_IMAGEVIEW:

			updateBlackBoardData(ClickType.Add, sports[2], background[2], imageSource[2], videoId[2]);
			break;

		case ID_ALL_BODY_SPORT_IMAGEVIEW:

			updateBlackBoardData(ClickType.Add, sports[3], background[3], imageSource[3], videoId[3]);
			break;

		case ID_STRETCH_SPORT_IMAGEVIEW:

			updateBlackBoardData(ClickType.Add, sports[4], background[4], imageSource[4], videoId[4]);
			break;

		case ID_SWEAT_SPORT_IMAGEVIEW:

			updateBlackBoardData(ClickType.Add, sports[5], background[5], imageSource[5], videoId[5]);
			break;
			
		case R.id.main_goButton:
			
			performClickGo();
			break;

		default:
			break;
		}

	}

	private void updateBlackBoardData(ClickType clickType, String sportsName,
			int backgroundID, int imageSource, String videoId) {
		if(blackBoardList.size()<6){
		
			if(clickType.equals(ClickType.Add)){
				BlackBoardItem item = new BlackBoardItem(sportsName, backgroundID, imageSource, videoId);
				blackBoardList.add(item);
			}
			
			updateBlackBoardView();
		}else{
			Toast.makeText(this, getString(R.string.you_can_only_choose_at_most_six_sports_a_time), Toast.LENGTH_SHORT).show();
		}
	}

	private void updateBlackBoardView() {
		
		firstRowblackBoardLayout.removeAllViews();
		secondRowblackBoardLayout.removeAllViews();

		if(blackBoardList!=null && !blackBoardList.isEmpty()){
			for(int i=0; i<blackBoardList.size(); i++){
				
				ImageView itemView = new ImageView(this);
				LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(120, 120);
				itemParams.gravity = Gravity.CENTER;
				itemView.setLayoutParams(itemParams);
				itemView.setBackgroundResource(blackBoardList.get(i).getBackgroundResource());
				itemView.setImageResource(blackBoardList.get(i).getImageSource());
				itemView.setTag(Integer.valueOf(i));
				itemView.setOnClickListener(blackBoardImageClickListener);

				
				if(i>0){
					ImageView plus = new ImageView(this);
					plus.setImageResource(R.drawable.ic_plus);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
					params.gravity = Gravity.CENTER;
					plus.setLayoutParams(params);
					if(i<=2){
						firstRowblackBoardLayout.addView(plus);
					}else{
						secondRowblackBoardLayout.addView(plus);
					}
				}
				
				if(i<=2){
					firstRowblackBoardLayout.addView(itemView);
				}else{
					secondRowblackBoardLayout.addView(itemView);
				}
			}
			
			if(goButton.getVisibility() == View.INVISIBLE){
				goButton.setVisibility(View.VISIBLE);
				goButton.startAnimation(fadeInAnimation);
			}
			
		}else{
			if(goButton.getVisibility() == View.VISIBLE){
				goButton.startAnimation(fadeOutAnimation);
				goButton.setVisibility(View.INVISIBLE);
			}
		}
		
	}
	
	public BlackBoardItem createRestItem(){
		BlackBoardItem restItem = new BlackBoardItem("rest", -1, -1, restVideoId);
		return restItem;
	}
	
	private void performClickGo(){
		
		VideoQueue.getInstance().clear();
		
		ArrayList<String> videoIds = new ArrayList<String>();
		Log.i(TAG, "item: "+blackBoardList.size());
		for(int i =0; i<blackBoardList.size(); i++){
			VideoQueue.getInstance().addToQueue(blackBoardList.get(i));
			Log.i(TAG, "add item: "+blackBoardList.get(i).getSportsName());
			videoIds.add(blackBoardList.get(i).videoId);

			if(i < blackBoardList.size()-1){
				BlackBoardItem restItem = createRestItem();
				VideoQueue.getInstance().addToQueue(restItem);
				Log.i(TAG, "add item: "+restItem.getSportsName());
				videoIds.add(restItem.videoId);

			}
			
		}
		
		VideoQueue.getInstance().setVideoIds(videoIds);
		startActivity(new Intent(this, VideoActivity.class));
	}

	enum ClickType {
		Add, Remove;
	}
}
