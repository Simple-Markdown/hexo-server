package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.io.File;

public class DeleteFile implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        File file = new File(Config.nativeHexoPath + new String(data));
        file.delete();
    }
}
