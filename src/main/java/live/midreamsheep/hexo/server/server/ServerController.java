package live.midreamsheep.hexo.server.server;

import live.midreamsheep.hexo.server.net.Connector;

public class ServerController {
    boolean isStart = true;
    public void start() throws Exception {
        Connector.init();
        while (isStart){
            new Thread(new ServerHandler(Connector.serverSocketChannel.accept())).start();
        }
    }
}
