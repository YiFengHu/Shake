package com.nptu.dse.shake.ui;

import java.util.ArrayList;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.nptu.dse.shake.DeveloperKey;
import com.nptu.dse.shake.R;
import com.nptu.dse.shake.RequestCode;
import com.nptu.dse.shake.queue.VideoQueue;
import com.nptu.dse.shake.queue.VideoQueue.QueueExecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.View;

public class VideoActivity extends YouTubeFailureRecoveryActivity{

	private static final String TAG = VideoActivity.class.getSimpleName();

	private YouTubePlayerView youtubePlayerView = null;
	private YouTubePlayer youtubePlayer= null;
	private PlayerStateChangeListener playerListener = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
        initPlayerListener();

        youtubePlayerView = (YouTubePlayerView) findViewById(R.id.video_youtubeView);
		youtubePlayerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
	}

	private void nextPlayList() {

		QueueExecutor executor = VideoQueue.getInstance().pop();
		if(executor!=null){
			executor.execute(youtubePlayer);
		}else{
			finish();
		}
	}

	private void initPlayerListener() {
		playerListener = new PlayerStateChangeListener(){

			@Override
			public void onAdStarted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(ErrorReason arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLoaded(String arg0) {
				youtubePlayer.play();
				Log.d(TAG, "onLoaded an play");

			}

			@Override
			public void onLoading() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoEnded() {
				Log.d(TAG, "onVideoEnded");
                nextPlayList();
				
			}

			@Override
			public void onVideoStarted() {
				// TODO Auto-generated method stub
				
			}
			
		};	
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		if (!wasRestored) {
			youtubePlayer = player;
//			youtubePlayer.setFullscreen(true);
			youtubePlayer.setPlayerStateChangeListener(playerListener);

            nextPlayList();
		}
	}

	@Override
	protected Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.video_youtubeView);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
