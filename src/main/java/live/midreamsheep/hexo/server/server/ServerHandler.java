package live.midreamsheep.hexo.server.server;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;
import live.midreamsheep.hexo.server.hand.HandlerMapper;
import live.midreamsheep.hexo.server.hand.handlers.Pull;
import live.midreamsheep.hexo.server.message.queue.QueueApi;
import live.midreamsheep.hexo.server.message.queue.Task;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerHandler implements Runnable {
    SocketChannel socketChannel;
    //用于使用传输的数据
    ByteBuffer byteBuffer = ByteBuffer.allocate(5);
    public ServerHandler(SocketChannel accept) {
        this.socketChannel = accept;
    }

    @Override
    public void run() {
        //密码验证
        if(!checkPassword()){
            return;
        }
        while (socketChannel.isOpen()){
            try {
                //获取数据信息
                int read = socketChannel.read(byteBuffer);
                if (read == -1){
                    break;
                }
                byte[] bytes = byteBuffer.array();
                byteBuffer.clear();
                //第一位是id
                int id = bytes[0];
                //后四位是数据
                int data = bytes[1] << 24 | (bytes[2] & 0xff) << 16 | (bytes[3] & 0xff) << 8 | (bytes[4] & 0xff);
                //获取data长度的数据
                byte[] dataBytes = new byte[data];
                System.out.println(dataBytes.length);
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                int read1 = 0;
                while (read1 < data){
                    int read2 = socketChannel.read(allocate);
                    if (read2 == -1){
                        break;
                    }
                    byte[] array = allocate.array();
                    System.arraycopy(array,0,dataBytes,read1,read2);
                    read1 += read2;
                    allocate.clear();
                }
                //处理数据
                handle(id, dataBytes,socketChannel);
            } catch (Exception e) {
                try {
                    socketChannel.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
                return;
            }
        }
    }

    private void handle(int id, byte[] array, SocketChannel socketChannel) {
        HandlerInter handlerInter = HandlerMapper.HANDLER_MAP.get(id);
        if(handlerInter instanceof Pull){
            handlerInter.handle(array,socketChannel);
            return;
        }
        QueueApi.addTask(new Task(array, handlerInter,socketChannel));
    }

    private boolean checkPassword() {
        ByteBuffer pass = ByteBuffer.allocate(2);
        try {
            socketChannel.read(pass);
            byte[] array = pass.array();
            int length = array[0]<<8 | array[1];
            ByteBuffer password = ByteBuffer.allocate(length);
            socketChannel.read(password);
            byte[] array1 = password.array();
            String s = new String(array1);
            if (!s.equals(Config.password)){
                socketChannel.close();
                return false;
            }
            System.out.println("连接成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
