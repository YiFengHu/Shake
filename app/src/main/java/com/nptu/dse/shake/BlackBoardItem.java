package com.nptu.dse.shake;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubePlayer;
import com.nptu.dse.shake.queue.VideoQueue.QueueExecutor;

public class BlackBoardItem implements QueueExecutor {

	private static final String TAG = BlackBoardItem.class.getSimpleName();

    private Context context = null;
	private String sportsName = null;
	private int backgroundColor = 0;
	private int imageSource = 0;
    private CardView cardView = null;
    private ImageView imageView = null;
	public String videoId = null;

	public BlackBoardItem(Context context, String sportsName, int backgroundColor, int imageSource,
			String videoId) {
        this.context = context;
		this.sportsName = sportsName;
		this.backgroundColor = backgroundColor;
		this.imageSource = imageSource;
		this.videoId = videoId;
	}

	public int getImageSource() {
		return imageSource;
	}

	public String getSportsName() {
		return sportsName;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}
	
	public String getVideoId(){
		return videoId;
	}

    public CardView genCardView(int blackBoardIndex){

        if(cardView == null) {
            cardView = new CardView(context);
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(120, 120);
            cardViewParams.gravity = Gravity.CENTER;
            cardView.setCardBackgroundColor(context.getResources().getColor(getBackgroundColor()));
            cardView.setCardElevation(context.getResources().getDimension(R.dimen.board_elevation));
            cardView.setRadius(context.getResources().getDimension(R.dimen.board_corner_sport_button));
            cardView.setClickable(true);
            cardView.setTag(blackBoardIndex);
            cardView.setLayoutParams(cardViewParams);


            imageView = new ImageView(context);
            FrameLayout.LayoutParams itemParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(itemParams);
            imageView.setImageResource(getImageSource());

            cardView.addView(imageView, itemParams);
        }

        return cardView;
    }

    public CardView getCardView(){
        return cardView;
    }

    public void setCardViewClickLitener(View.OnClickListener listener){
        cardView.setOnClickListener(listener);
    }

	@Override
	public void execute(Object tag) {

		if (tag instanceof YouTubePlayer) {
			YouTubePlayer player = (YouTubePlayer) tag;
			player.cueVideo(videoId);
			Log.d(TAG, "cueVideo: "+videoId);
		}

	}

}
