package com.theoc.pixhell.manager;

import com.theoc.pixhell.R;

import android.media.AudioManager;
import android.media.MediaPlayer;

public final class SoundManager
{
	AudioManager audioManager = null;
	MediaPlayer  mediaPlayer  = null;
	
	public SoundManager(AudioManager audioManager, MediaPlayer mediaPlayer) {
		this.audioManager = audioManager;
		this.mediaPlayer  = mediaPlayer;
	}
	
	public void startTheme() {
		this.mediaPlayer.start();
	}
	
	public void stopTheme() {
		this.mediaPlayer.stop();
	}
	
	public void pauseTheme() {
		
	}
	
	public void resumeTheme() {
		
	}
	
	public void playSoundEffect() {
		this.audioManager.playSoundEffect(R.raw.player_hit04);
	}
}
