package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.hand.HandlerInter;
import live.midreamsheep.hexo.server.tool.command.Commander;

import java.nio.channels.SocketChannel;

public class Push implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        //获取操作系统类型
        try {
            Process p= Commander.exec("linux.sh","windows.bat");
            //等待进程完成。
            int exitVal = p.waitFor();
            System.out.println("Process Exitvalue: " + exitVal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
