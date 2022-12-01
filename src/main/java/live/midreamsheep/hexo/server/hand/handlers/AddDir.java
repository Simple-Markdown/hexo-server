package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.io.File;

public class AddDir implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        String path = new String(data);
        File dir = new File(Config.nativeHexoPath+path);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }
}
