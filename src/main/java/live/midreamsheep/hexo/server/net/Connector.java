package live.midreamsheep.hexo.server.net;


import live.midreamsheep.hexo.server.config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class Connector {
    public static final ServerSocketChannel serverSocketChannel;

    static {
        try {
            serverSocketChannel = ServerSocketChannel.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() throws IOException {
        serverSocketChannel.bind(new InetSocketAddress(Config.Port));
    }
}
