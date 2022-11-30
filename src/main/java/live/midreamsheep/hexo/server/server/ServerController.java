package live.midreamsheep.hexo.server.server;

import live.midreamsheep.hexo.server.config.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    boolean isStart = true;
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(Config.Port);
        //监听端口
        while (true){
            Socket accept = serverSocket.accept();
            //验证密码

        }
    }
}
