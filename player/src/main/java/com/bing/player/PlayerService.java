package com.bing.player;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bing.player.player.IPlayer;
import com.bing.player.player.PlayerFactory;
import com.bing.player.player.PlayerListener;
import com.bing.player.source.IPlaySource;
import com.bing.player.status.PlayStatus;

public class PlayerService extends Service implements PlayerListener {
    private PlayStatus status = PlayStatus.IDLE;
    private IPlayer mPlayer;
    private PlayerFactory mFactory;

    @Override
    public void playStatusChange(PlayStatus status) {
        this.status=status;
    }


    public class PlayerBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }


    //onStartCommand在startService有作用,start多次就会调用多次,
    // 一般用作activity给service传递数据,不能反着传
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //onCreate 不管start还是bind,也不管调用几次,只会启动一次,常用来做全局初始化操作,
    //activity和service可以互相传递数据
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void playOrPause(IPlaySource iPlaySource, Context context) {
        switch (status) {
            case IDLE:
                //初始化播放源
                String url = iPlaySource.getUrl();
                if (mPlayer != null) {
                    mPlayer.release();
                }
                if (mFactory == null) {
                    mFactory = new PlayerFactory();
                }
                if (mPlayer == null) {
                    mPlayer = mFactory.createPlayer(context);
                }
                if (mPlayer == null) {
                    //播放器创建失败
                    return;
                }

                mPlayer.setPlayerListener(this);
                //拿到播放器去播放
                mPlayer.prepare(context, url);

                break;
            case STARTED:
                if(mPlayer!=null){
                    mPlayer.paused();
                }
                break;
            case PAUSED:
                if(mPlayer!=null){
                    mPlayer.reStart();
                }
                break;

            case PREPARING:
                break;
            default:
                break;
        }
    }

}
