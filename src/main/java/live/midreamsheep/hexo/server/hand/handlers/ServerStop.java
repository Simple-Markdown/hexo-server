package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.hand.HandlerInter;
import live.midreamsheep.hexo.server.tool.command.Commander;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerStop implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        //停止实时预览功能
        if(Server.p!=null){
            System.out.println("服务端实时预览功能");
            Process exec = Commander.exec("linServerStop.sh", "winServerStop.bat");
            try {
                exec.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("服务端已经停止了实时预览功能");
            Server.p.destroy();
            Server.p=null;
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
    }
}
