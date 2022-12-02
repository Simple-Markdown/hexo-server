package live.midreamsheep.hexo.server.hand;

import java.nio.channels.SocketChannel;

public interface HandlerInter {
    void handle(byte[] data, SocketChannel socketChannel);
}
