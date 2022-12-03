package live.midreamsheep.hexo.server.server;

import live.midreamsheep.hexo.server.net.Connector;

public class ServerController {
    boolean isStart = true;
    public void start() throws Exception {
        Connector.init();
        System.out.println("服务器已启动");
        while (isStart){
            new Thread(new ServerHandler(Connector.serverSocketChannel.accept())).start();
            System.out.println("一个客户端已连接");
        }
    }
}
