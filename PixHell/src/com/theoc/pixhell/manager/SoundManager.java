package com.theoc.pixhell.manager;

import com.theoc.pixhell.logic.AssetMap;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

public final class SoundManager {
	AudioManager audioManager = null;
	MediaPlayer mediaPlayer = null;
	SoundPool soundPool = null;
	float musicVol = 1.0f;
	float sfxVol = 1.0f;

	public SoundManager(AudioManager audioManager, MediaPlayer mediaPlayer,
			SoundPool soundPool) {
		this.soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		this.audioManager = audioManager;
		this.mediaPlayer = mediaPlayer;
		this.soundPool = soundPool;

		updateMediaPlayerVolume();
		this.mediaPlayer.setLooping(true);
	}

	public void setVolumes(float music, float sfx) {
		this.musicVol = music;
		this.sfxVol = sfx;
		updateMediaPlayerVolume();
		updateSoundEffectVolume(sfx);
	}

	private void updateSoundEffectVolume(float sfx) {
		
	}

	private void updateMediaPlayerVolume(){
		this.mediaPlayer.setVolume(musicVol, musicVol);
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

	public void resumeTheme() {
	}

	public void playSoundEffect(int soundEffect) {
		this.soundPool.play(AssetMap.getSoundID(soundEffect), sfxVol, sfxVol,
				1, 0, 1f);
	}
}
