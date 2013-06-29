package com.theoc.pixhell.manager;

import com.theoc.pixhell.logic.AssetMap;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public final class SoundManager
{
	AudioManager audioManager = null;
	MediaPlayer  mediaPlayer  = null;
	SoundPool    soundPool    = null;
	
	public SoundManager(AudioManager audioManager, MediaPlayer mediaPlayer, SoundPool soundPool) {
		this.soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		this.audioManager = audioManager;
		this.mediaPlayer  = mediaPlayer;
		this.soundPool    = soundPool;
		
		this.mediaPlayer.setVolume(0.10f, 0.10f);
		this.mediaPlayer.setLooping(true);
	}
	
	public void startTheme() {
		this.mediaPlayer.start();
	}
	
	public void stopTheme() {
		this.mediaPlayer.stop();
	}
	
	public void pauseTheme() {
		this.mediaPlayer.pause();
	}
	
	public void resumeTheme() { }
	
	public void playSoundEffect(int soundEffect) {
		this.soundPool.play(AssetMap.getSoundID(soundEffect), 0.75f, 0.75f, 1, 0, 1f);
	}
}
