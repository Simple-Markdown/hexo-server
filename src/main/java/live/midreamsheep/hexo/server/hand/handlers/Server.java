package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;
import live.midreamsheep.hexo.server.tool.command.Commander;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Server implements HandlerInter {
    public volatile static Process p = null;
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        //启动实时预览功能
        if(p!=null){
            try {
                socketChannel.write(ByteBuffer.wrap(new byte[]{(byte) 0x01}));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        try {
            socketChannel.write(ByteBuffer.wrap(new byte[]{(byte) 0x00}));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //获取操作系统类型
        p = Commander.exec("linServer.sh", "winServer.bat");
    }
}
