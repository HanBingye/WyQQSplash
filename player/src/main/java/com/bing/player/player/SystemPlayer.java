package com.bing.player.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.bing.player.status.PlayStatus;

import java.io.IOException;

public class SystemPlayer implements IPlayer, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer;
    private PlayerListener listener;

    public SystemPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    @Override
    public void release() {


    }

    public void prepare(Context context, String url) {
        try {
            mediaPlayer.setDataSource(context, Uri.parse(url));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPlayerListener(PlayerListener listener) {
        this.listener = listener;
    }


    public void playStatusChange(PlayStatus status) {
        if (listener != null) {
            listener.playStatusChange(status);
        }
    }

    @Override
    public void paused() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            playStatusChange(PlayStatus.PAUSED);


        }
    }

    @Override
    public void reStart() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            playStatusChange(PlayStatus.STARTED);


        }
    }

    //初始化成功,且播放器准备后回调
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        playStatusChange(PlayStatus.STARTED);

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d("SystemPlayer", "what: " + what + "extra: " + extra);
        return false;
    }
}
