package com.bing.player.player;

import com.bing.player.status.PlayStatus;

public interface PlayerListener {
    void playStatusChange(PlayStatus status);
}
