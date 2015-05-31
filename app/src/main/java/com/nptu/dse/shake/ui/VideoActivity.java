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
import com.nptu.dse.shake.R.id;
import com.nptu.dse.shake.R.layout;
import com.nptu.dse.shake.queue.VideoQueue;
import com.nptu.dse.shake.queue.VideoQueue.QueueExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class VideoActivity extends YouTubeFailureRecoveryActivity{

	private static final String TAG = VideoActivity.class.getSimpleName();

	private YouTubePlayerView youtubePlayerView = null;
	private YouTubePlayer youtubePlayer= null;

	private String videoId = null;
	private PlayerStateChangeListener playerListener = null;
	
	private ArrayList<String> videoIds = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		initPlayList();
		
		youtubePlayerView = (YouTubePlayerView) findViewById(R.id.video_youtubeView);
		youtubePlayerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
		
		initPlayerListener();
	}

	private void initPlayList() {
		videoIds = new ArrayList<String>();
		
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
//				QueueExecutor executor = VideoQueue.getInstance().pop();
//				if(executor!=null){
//					executor.execute(youtubePlayer);
//				}else{
//					finish();
//				}
				
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
			youtubePlayer.setFullscreen(true);
			youtubePlayer.setPlayerStateChangeListener(playerListener);

			youtubePlayer.cueVideos(VideoQueue.getInstance().getVideoIds());
//			QueueExecutor executor = VideoQueue.getInstance().pop();
//			if(executor!=null){
//				executor.execute(youtubePlayer);
//			}else{
//				finish();
//			}
		}
	}

	@Override
	protected Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.video_youtubeView);
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
			case RequestCode.PLAY_MUSIC:
				
//			QueueExecutor executor = VideoQueue.getInstance().pop();
//			if(executor!=null){
//				executor.execute(youtubePlayer);
//			}else{
//				finish();
//			}
			
			break;
			
			default:
				break;
		}
	}
	
	

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
