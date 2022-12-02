package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.io.File;
import java.nio.channels.SocketChannel;

public class DeleteDir implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        File file = new File(Config.nativeHexoPath + new String(data).replace("\\", File.separator));
        file.delete();
        System.out.println("删除一个文件夹"+file.getAbsolutePath());

    }
}
