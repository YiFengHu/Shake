package com.nptu.dse.shake.ui;

import java.util.ArrayList;

import com.nptu.dse.shake.BlackBoardItem;
import com.nptu.dse.shake.R;
import com.nptu.dse.shake.queue.VideoQueue;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();


	private static final int ID_UPPER_SPORT_CARDVIEW = R.id.main_upperSportCardView;
	private static final int ID_UPPER_SPORT_2_CARDVIEW = R.id.main_upperSport2CardView;
	private static final int ID_LOWER_SPORT_CARDVIEW = R.id.main_lowerSportCardView;
	private static final int ID_ALL_BODY_SPORT_CARDVIEW = R.id.main_allBodySportCardView;
	private static final int ID_STRETCH_SPORT_CARDVIEW = R.id.main_stretchSportCardView;
	private static final int ID_SWEAT_SPORT_CARDVIEW = R.id.main_sweatSportCardView;
	
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
	private CardView[] cardViews = null;
    private static final String restVideoId = "G3H9SGzGueo";
	
	private LinearLayout firstRowblackBoardLayout = null;
	private LinearLayout secondRowblackBoardLayout = null;
	private FloatingActionButton goButton = null;

	private ArrayList<BlackBoardItem> blackBoardList = null;
	
	private OnClickListener blackBoardImageClickListener = null;

    private Animation buttonFadeInAnimation = null;
    private Animation buttonFadeOutAnimation = null;
    private Animation itemFadeInAnimation = null;
    private Animation itemFadeOutAnimation = null;

    private CardView upperSportsCardview = null;
    private CardView upperSports2Cardview = null;
    private CardView lowerSportsCardview = null;
    private CardView allBodySportsCardview = null;
    private CardView stretchSportsCardview = null;
    private CardView sweatSportsCardview = null;

    private View cardViewContainer = null;

    ViewTreeObserver.OnPreDrawListener mPreDrawListener =
            new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    cardViewContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                    cardViewContainer.animate().setDuration(500).withEndAction(new Runnable() {

                        @Override
                        public void run() {
                            revealSportCards();
                        }
                    });
                    return false;
                }
            };

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        cardViews = new CardView[6];
		blackBoardList = new ArrayList<BlackBoardItem>();

        final Toolbar toolBar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolBar);

        initAnimation();
		initLayout();
		
		blackBoardImageClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				final int positionInList = (Integer)view.getTag();

                itemFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        removeBlackBoardData(positionInList);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                view.startAnimation(itemFadeOutAnimation);
                view.setVisibility(View.INVISIBLE);

			}
		};

        cardViewContainer = (View)findViewById(R.id.main_cardContainer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cardViewContainer.getViewTreeObserver().addOnPreDrawListener(mPreDrawListener);

    }

    private void initAnimation() {
        buttonFadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_grow_fade_in);

        buttonFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_shrink_fade_out);

        itemFadeInAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in_top);

        itemFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_out_bottom);
    }

    private void initLayout() {

		firstRowblackBoardLayout = (LinearLayout) findViewById(ID_FIRST_ROW_BLACK_BOARD_LAYOUT);
		secondRowblackBoardLayout = (LinearLayout) findViewById(ID_SECOND_ROW_BLACK_BOARD_LAYOUT);

        upperSportsCardview = (CardView)findViewById(ID_UPPER_SPORT_CARDVIEW);
        upperSports2Cardview = (CardView)findViewById(ID_UPPER_SPORT_2_CARDVIEW);
        lowerSportsCardview = (CardView)findViewById(ID_LOWER_SPORT_CARDVIEW);
        allBodySportsCardview = (CardView)findViewById(ID_ALL_BODY_SPORT_CARDVIEW);
        stretchSportsCardview = (CardView)findViewById(ID_STRETCH_SPORT_CARDVIEW);
        sweatSportsCardview = (CardView)findViewById(ID_SWEAT_SPORT_CARDVIEW);
        upperSportsCardview.setOnClickListener(this);
        upperSports2Cardview.setOnClickListener(this);
        lowerSportsCardview.setOnClickListener(this);
        allBodySportsCardview.setOnClickListener(this);
        stretchSportsCardview.setOnClickListener(this);
        sweatSportsCardview.setOnClickListener(this);

        cardViews[0] = upperSportsCardview;
        cardViews[1] = upperSports2Cardview;
        cardViews[2] = allBodySportsCardview;
        cardViews[3] = stretchSportsCardview;
        cardViews[4] = lowerSportsCardview;
        cardViews[5] = sweatSportsCardview;


        goButton = (FloatingActionButton)findViewById(R.id.main_goButton);
		goButton.setOnClickListener(this);

        revealSportCards();
	}

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
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

                updateBlackBoardView(clickType);
            }
			
		}else{
			Toast.makeText(this, getString(R.string.you_can_only_choose_at_most_six_sports_a_time), Toast.LENGTH_SHORT).show();
		}
	}

    private void removeBlackBoardData(int deletedInex){
        blackBoardList.remove(deletedInex);
        updateBlackBoardView(ClickType.Remove);
    }

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void updateBlackBoardView(ClickType type) {
		
		firstRowblackBoardLayout.removeAllViews();
		secondRowblackBoardLayout.removeAllViews();

		if(blackBoardList!=null && !blackBoardList.isEmpty()){
			for(int i=0; i<blackBoardList.size(); i++){

                CardView cardView = blackBoardList.get(i).genCardView(i);
                cardView.setOnClickListener(blackBoardImageClickListener);

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

                if(ClickType.Add.equals(type)){
                    if(i==blackBoardList.size()-1){
                        cardView.startAnimation(itemFadeInAnimation);
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
                goButton.startAnimation(buttonFadeInAnimation);
			}
			
		}else{
			if(goButton.getVisibility() == View.VISIBLE){
				goButton.startAnimation(buttonFadeOutAnimation);
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

        Bundle translateBundle = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
        startActivity(new Intent(this, VideoActivity.class), translateBundle);

    }

    private void revealSportCards(){
        TimeInterpolator decelerateInterpolator = new DecelerateInterpolator();

        ObjectAnimator[] childAnims = new ObjectAnimator[6];
        for (int i = 0; i < 6; ++i) {
            View child = cardViews[i];

            child.setScaleX(0.8F);
            child.setScaleY(0.8F);
            child.setAlpha(0F);
            PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1);
            PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1);
            PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1);

            ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(cardViews[i], pvhSX, pvhSY, pvhAlpha);
            anim.setDuration(100);
            anim.setInterpolator(decelerateInterpolator);
            childAnims[i] = anim;
        }
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(childAnims);
        set.start();
    }

	enum ClickType {
		Add, Remove;
	}
}
