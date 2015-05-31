package com.nptu.dse.shake;


import android.util.Log;

import com.google.android.youtube.player.YouTubePlayer;
import com.nptu.dse.shake.queue.VideoQueue.QueueExecutor;

public class BlackBoardItem implements QueueExecutor {

	private static final String TAG = BlackBoardItem.class.getSimpleName();
	
	private String sportsName = null;
	private int backgroundResource = 0;
	private int imageSource = 0;
	public String videoId = null;

	public BlackBoardItem(String sportsName, int backgroundColor, int imageSource,
			String videoId) {
		this.sportsName = sportsName;
		this.backgroundResource = backgroundColor;
		this.imageSource = imageSource;
		this.videoId = videoId;
	}

	public int getImageSource() {
		return imageSource;
	}

	public String getSportsName() {
		return sportsName;
	}

	public int getBackgroundResource() {
		return backgroundResource;
	}
	
	public String getVideoId(){
		return videoId;
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
