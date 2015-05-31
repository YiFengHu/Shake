package com.nptu.dse.shake.ui;

import java.util.ArrayList;

import com.nptu.dse.shake.BlackBoardItem;
import com.nptu.dse.shake.R;
import com.nptu.dse.shake.queue.VideoQueue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();


	private static final int ID_UPPER_SPORT_CARDVIEW = R.id.main_upperSportCardView;
	private static final int ID_UPPER_SPORT_2_CARDVIEW = R.id.main_upperSport2CardView;
	private static final int ID_LOWER_SPORT_CARDVIEW = R.id.main_lowerSportCardView;
	private static final int ID_ALL_BODY_SPORT_CARDVIEW = R.id.main_allBodySportCardView;
	private static final int ID_STRETCH_SPORT_CARDVIEW = R.id.main_stretchSportCardView;
	private static final int ID_SWEAT_SPORT_CARDVIEW = R.id.main_sweatSportCardView;
	
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

    private static final int[] backgroundColor = {
            R.color.yellow, R.color.red,
            R.color.green,
            R.color.light_blue,
            R.color.purple, R.color.dark_blue };

	private static final int[] imageSource = { R.drawable.ic_upper_body_sport,
			R.drawable.ic_upper_boddy_sport2, R.drawable.ic_lower_body_sport,
			R.drawable.ic_all_body_sport, R.drawable.ic_stretch_sport,
			R.drawable.ic_sweat_sport };
	
	private static final String[] videoId = {"sSTEHb99ZOY", "hBPc6k7DpIA", "9kBQfjVAayM", "mtm_b681cBs", "6uW9PpQUFiU", "rl2oG1_rUI0"};
	private static final String restVideoId = "G3H9SGzGueo";
	
	private LinearLayout firstRowblackBoardLayout = null;
	private LinearLayout secondRowblackBoardLayout = null;

	private CardView upperSportCardView = null;
	private CardView upperSport2CardView = null;
	private CardView lowerSportCardView = null;
	private CardView allBodySportCardView = null;
	private CardView stretchSportCardView = null;
	private CardView sweatSportCardView = null;
	private FloatingActionButton goButton = null;

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
                R.anim.abc_slide_in_bottom);
		
		fadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.abc_slide_out_bottom);
		
		initLayout();
		
		blackBoardImageClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				int positionInList = (Integer)view.getTag();
				
				blackBoardList.remove(positionInList);
				updateBlackBoardView(ClickType.Remove ,positionInList);
			}
		};

	}

    private void initLayout() {
		firstRowImageViewLayout = (LinearLayout)findViewById(ID_FIRST_ROW_IMAGE_VIEW_LAYOUT);
		firstRowblackBoardLayout = (LinearLayout) findViewById(ID_FIRST_ROW_BLACK_BOARD_LAYOUT);
		secondRowblackBoardLayout = (LinearLayout) findViewById(ID_SECOND_ROW_BLACK_BOARD_LAYOUT);

		upperSportCardView = (CardView) findViewById(ID_UPPER_SPORT_CARDVIEW);
		upperSport2CardView = (CardView) findViewById(ID_UPPER_SPORT_2_CARDVIEW);
		lowerSportCardView = (CardView) findViewById(ID_LOWER_SPORT_CARDVIEW);
		allBodySportCardView = (CardView) findViewById(ID_ALL_BODY_SPORT_CARDVIEW);
		stretchSportCardView = (CardView) findViewById(ID_STRETCH_SPORT_CARDVIEW);
		sweatSportCardView = (CardView) findViewById(ID_SWEAT_SPORT_CARDVIEW);

		goButton = (FloatingActionButton)findViewById(R.id.main_goButton);

		upperSportCardView.setOnClickListener(this);
		upperSport2CardView.setOnClickListener(this);
		lowerSportCardView.setOnClickListener(this);
		allBodySportCardView.setOnClickListener(this);
		stretchSportCardView.setOnClickListener(this);
		sweatSportCardView.setOnClickListener(this);
		
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
		case ID_UPPER_SPORT_CARDVIEW:

			updateBlackBoardData(ClickType.Add, sports[0], backgroundColor[0], imageSource[0], videoId[0]);
			break;

		case ID_UPPER_SPORT_2_CARDVIEW:

			updateBlackBoardData(ClickType.Add, sports[1], backgroundColor[1], imageSource[1], videoId[1]);
			break;

		case ID_LOWER_SPORT_CARDVIEW:

			updateBlackBoardData(ClickType.Add, sports[2], backgroundColor[2], imageSource[2], videoId[2]);
			break;

		case ID_ALL_BODY_SPORT_CARDVIEW:

			updateBlackBoardData(ClickType.Add, sports[3], backgroundColor[3], imageSource[3], videoId[3]);
			break;

		case ID_STRETCH_SPORT_CARDVIEW:

			updateBlackBoardData(ClickType.Add, sports[4], backgroundColor[4], imageSource[4], videoId[4]);
			break;

		case ID_SWEAT_SPORT_CARDVIEW:

			updateBlackBoardData(ClickType.Add, sports[5], backgroundColor[5], imageSource[5], videoId[5]);
			break;
			
		case R.id.main_goButton:
			
			performClickGo();
			break;

		default:
			break;
		}

	}

	private void updateBlackBoardData(ClickType clickType, String sportsName,
			int backgroundColor, int imageSource, String videoId) {
        Log.i(TAG, "updateBlackBoardData: "+sportsName);
		if(blackBoardList.size()<6){
		
			if(clickType.equals(ClickType.Add)){
				BlackBoardItem item = new BlackBoardItem(this, sportsName, backgroundColor, imageSource, videoId);
				blackBoardList.add(item);

                updateBlackBoardView(clickType, blackBoardList.size()-1);
            }
			
		}else{
			Toast.makeText(this, getString(R.string.you_can_only_choose_at_most_six_sports_a_time), Toast.LENGTH_SHORT).show();
		}
	}

	private void updateBlackBoardView(ClickType type, int indexInBliackBoard) {
		
		firstRowblackBoardLayout.removeAllViews();
		secondRowblackBoardLayout.removeAllViews();

        if(ClickType.Remove.equals(type)){
            CardView deletedCardView = blackBoardList.get(indexInBliackBoard).getCardView();

            if(deletedCardView.getVisibility() == View.VISIBLE){
                deletedCardView.startAnimation(fadeOutAnimation);
                deletedCardView.setVisibility(View.INVISIBLE);
            }
        }

		if(blackBoardList!=null && !blackBoardList.isEmpty()){
			for(int i=0; i<blackBoardList.size(); i++){

//                CardView cardView = new CardView(this);
//                LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(120, 120);
//                cardViewParams.gravity = Gravity.CENTER;
//                cardView.setCardBackgroundColor(getResources().getColor(blackBoardList.get(i).getBackgroundColor()));
//                cardView.setCardElevation(getResources().getDimension(R.dimen.board_elevation));
//                cardView.setRadius(getResources().getDimension(R.dimen.board_corner_sport_button));
//                cardView.setLayoutParams(cardViewParams);
//
//
//                ImageView itemView = new ImageView(this);
//				FrameLayout.LayoutParams itemParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//				itemView.setLayoutParams(itemParams);
//				itemView.setImageResource(blackBoardList.get(i).getImageSource());
//				itemView.setTag(Integer.valueOf(i));
//                itemView.setClickable(true);
//				itemView.setOnClickListener(blackBoardImageClickListener);
//
//                cardView.addView(itemView, itemParams);

                CardView cardView = blackBoardList.get(i).genCardView(i);
                cardView.setOnClickListener(blackBoardImageClickListener);
                if(ClickType.Add.equals(type) && i==blackBoardList.size()-1) {
                    cardView.setVisibility(View.INVISIBLE);
                    cardView.startAnimation(fadeInAnimation);
                    cardView.setVisibility(View.VISIBLE);
                }


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
					firstRowblackBoardLayout.addView(cardView);
				}else{
					secondRowblackBoardLayout.addView(cardView);
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
		BlackBoardItem restItem = new BlackBoardItem(this, "rest", -1, -1, restVideoId);
		return restItem;
	}
	
	private void performClickGo(){

            VideoQueue.getInstance().clear();

            ArrayList<String> videoIds = new ArrayList<String>();
            Log.i(TAG, "item: " + blackBoardList.size());
            for (int i = 0; i < blackBoardList.size(); i++) {
                VideoQueue.getInstance().addToQueue(blackBoardList.get(i));
                Log.i(TAG, "add item: " + blackBoardList.get(i).getSportsName());
                videoIds.add(blackBoardList.get(i).videoId);

                if (i < blackBoardList.size() - 1) {
                    BlackBoardItem restItem = createRestItem();
                    VideoQueue.getInstance().addToQueue(restItem);
                    Log.i(TAG, "add item: " + restItem.getSportsName());
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
