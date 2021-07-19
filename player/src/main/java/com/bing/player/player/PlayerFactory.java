package com.bing.player.player;

import android.content.Context;

import com.bing.player.tool.MetaDataUtil;

public class PlayerFactory {
    public IPlayer createPlayer(Context context) {
        int playType = MetaDataUtil.getMetaDataValue(context);
        switch (playType) {
            case 1:
                SystemPlayer systemPlayer = new SystemPlayer();
                return systemPlayer;


            default:
                break;
        }
        return null;
    }
}
