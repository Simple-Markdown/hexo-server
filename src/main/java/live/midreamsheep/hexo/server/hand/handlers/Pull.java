package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;

public class Pull implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        //遍历文件夹
        File dir = new File(Config.nativeHexoPath);
        try {
            handleFile(dir,socketChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(300);
            socketChannel.write(ByteBuffer.wrap(new byte[]{0,0,0,0,0}));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleFile(File file, SocketChannel socketChannel) throws IOException {
        //判断文件类型
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {
                handleFile(listFile,socketChannel);
            }
            return;
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        //文件
        String path = file.getAbsolutePath().replace(new File(Config.nativeHexoPath).getPath(),"");
        System.out.println(path+"开始传输");
        //发送文件数据
        byte[] meta = new byte[3];
        meta[0] = 0x01;
        int length = path.getBytes().length+4;
        meta[1] = (byte) (length >> 8);
        meta[2] = (byte) length;
        //元数据写出 0x01+长度
        socketChannel.write(ByteBuffer.wrap(meta));

        //System.out.println("源数据发送 长度"+length);
        int FileLength = (int) file.length();
        byte[] fileMeta = new byte[4+path.getBytes().length];
        fileMeta[0] = (byte) (FileLength >> 24);
        fileMeta[1] = (byte) (FileLength >> 16);
        fileMeta[2] = (byte) (FileLength >> 8);
        fileMeta[3] = (byte) FileLength;
        System.arraycopy(path.getBytes(),0,fileMeta,4,path.getBytes().length);

        //文件元数据写出 文件长度(4)+文件路径(n)
        socketChannel.write(ByteBuffer.wrap(fileMeta));

        //读取是否发送文件
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        socketChannel.read(byteBuffer);

        byte[] array = byteBuffer.array();
        if(array[0]==1){
            return;
        }
        byte[] buffer = new byte[1024];
        int len;
        int total = 0;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            while ((len = fileInputStream.read(buffer)) != -1) {
                socketChannel.write(ByteBuffer.wrap(buffer,0,len));
                total += len;
            }
        }
        System.out.println(total+"字节传输完成");
        System.out.println( "文件传输完成 length"+(total+3+ fileMeta.length));
    }

}
