package live.midreamsheep.hexo.server.message.queue;


import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.nio.channels.SocketChannel;

public class Task {
    private byte[] data;
    private HandlerInter handler;
    private SocketChannel socketChannel;

    public Task(byte[] datas, HandlerInter handler, SocketChannel socketChannel) {
        this.data = datas;
        this.handler = handler;
        this.socketChannel = socketChannel;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public HandlerInter getHandler() {
        return handler;
    }

    public void setHandler(HandlerInter handler) {
        this.handler = handler;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
}
