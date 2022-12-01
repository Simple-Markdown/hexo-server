package live.midreamsheep.hexo.server.server;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.data.Constant;
import live.midreamsheep.hexo.server.monitor.FileListener;
import live.midreamsheep.hexo.server.monitor.MonitorStarter;

public class ServerController {
    boolean isStart = true;
    public void start() throws Exception {
        //从云端获取文件覆盖本地文件
        MonitorStarter monitorStarter = new MonitorStarter(1000);
        monitorStarter.monitor(Config.nativeHexoPath+ Constant.blogPath, new FileListener());
        monitorStarter.start();
    }
}
