package com.bing.player.source;

public class RawPlaySource implements IPlaySource{
    private String url;

    @Override
    public void setUrl(String url) {
        this.url=url;
    }

    @Override
    public String getUrl() {
        return url;
    }
    public IPlaySource setPath(String packageName,int raw){
        setUrl("android.resource://" + packageName + "/" + raw);
        return this;
    }
}
