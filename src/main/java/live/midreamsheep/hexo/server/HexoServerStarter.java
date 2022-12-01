package live.midreamsheep.hexo.server;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.server.ServerController;
import live.midreamsheep.hexo.server.tool.io.SIO;

import java.io.IOException;

public class HexoServerStarter {
    public static void main(String[] args) throws Exception {
        //读取配置文件
        Config.setConfig(SIO.read(System.getProperty("user.dir") + "\\config.properties"));
        //启动服务器
        new ServerController().start();
    }
}
