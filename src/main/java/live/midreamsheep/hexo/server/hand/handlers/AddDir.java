package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.io.File;
import java.nio.channels.SocketChannel;

public class AddDir implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        String path = new String(data).replace("\\", File.separator);
        File dir = new File(Config.nativeHexoPath+path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        System.out.println("创建一个文件夹"+dir.getAbsolutePath());
    }
}
