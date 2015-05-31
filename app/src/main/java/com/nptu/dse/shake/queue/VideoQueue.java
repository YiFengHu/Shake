package com.nptu.dse.shake.queue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import com.google.android.youtube.player.YouTubePlayer;

public class VideoQueue {
	
	private static VideoQueue mInstance = null;
	private ArrayBlockingQueue<QueueExecutor> mQueue = null;
	private ArrayList<String> videoIds = null;
	
	private VideoQueue(){
		mQueue = new ArrayBlockingQueue<QueueExecutor>(11);
		videoIds = new ArrayList<String>(11);
	}
	
	public ArrayList<String> getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(ArrayList<String> videoIds) {
		this.videoIds = videoIds;
	}

	public static VideoQueue getInstance(){
		if(mInstance == null){
			mInstance = new VideoQueue();
		}
		
		return mInstance;
	}
	
	public synchronized void addToQueue(QueueExecutor executor){
		mQueue.add(executor);
	}

	
	public synchronized QueueExecutor pop(){
		return mQueue.poll();
	}
	
	public void clear(){
		mQueue.clear();
	}
	
	public interface QueueExecutor{
		public void execute(Object tag);
	}
	
	public enum ItemType{
		Video, Music;
	}

}
