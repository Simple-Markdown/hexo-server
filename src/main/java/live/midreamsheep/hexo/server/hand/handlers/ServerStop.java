package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.nio.channels.SocketChannel;

public class ServerStop implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        //停止实时预览功能
    }
}
