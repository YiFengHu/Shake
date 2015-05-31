package com.nptu.dse.shake.ui;


import com.nptu.dse.shake.R;
import com.nptu.dse.shake.R.id;
import com.nptu.dse.shake.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

@Deprecated
public class MusicActivity extends Activity{

	private final String MUSIC_RESOURCE = null;
	
	private Intent intent = null;
	private ImageView imageView = null;
	private MediaPlayer mediaPlayer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		
		imageView = (ImageView)findViewById(R.id.music_imageView);
				
	}
	
	

}
