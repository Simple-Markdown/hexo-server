package live.midreamsheep.hexo.server;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.message.queue.QueueApi;
import live.midreamsheep.hexo.server.message.queue.Task;
import live.midreamsheep.hexo.server.server.ServerController;
import live.midreamsheep.hexo.server.tool.io.SIO;

import java.io.IOException;

public class HexoServerStarter {
    public static void main(String[] args) throws Exception {
        //读取配置文件
        Config.setConfig(SIO.read(System.getProperty("user.dir") + "/config.properties"));
        //开启守护线程监听任务
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!QueueApi.isEmpty()) {
                    Task task = QueueApi.getTask();
                    task.getHandler().handle(task.getData(),task.getSocketChannel());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        //启动服务器
        new ServerController().start();
    }
}
