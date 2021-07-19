package com.bing.player.player;

import android.content.Context;

public interface IPlayer {

    //播放器释放
    void release();

    void prepare(Context context, String url);

    void setPlayerListener(PlayerListener listener);


    void paused();

    void reStart();

}
