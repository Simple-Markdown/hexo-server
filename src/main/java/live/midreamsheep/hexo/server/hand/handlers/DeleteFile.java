package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.hand.HandlerInter;

public class DeleteFile implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        System.out.println(new String(data));
    }
}