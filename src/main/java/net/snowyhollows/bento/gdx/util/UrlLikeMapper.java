package net.snowyhollows.bento.gdx.util;

public class UrlLikeMapper extends StringMapper {

    public UrlLikeMapper(String url) {
        super(("name=" + url).split("[=:?/&]"));
    }
    
    
}
