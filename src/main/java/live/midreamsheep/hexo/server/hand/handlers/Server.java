package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.nio.channels.SocketChannel;

public class Server implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        //启动实时预览功能
    }
}
