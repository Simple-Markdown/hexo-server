package live.midreamsheep.hexo.server.hand;

import live.midreamsheep.hexo.server.hand.handlers.AddDir;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapper {
    public static final Map<Integer,HandlerInter> HANDLER_MAP = new HashMap<>();
    static {
/*        HANDLER_MAP.put(0x01,new Push());
        HANDLER_MAP.put(0x02,new Pull());
        HANDLER_MAP.put(0x03,new DeleteFile());
        HANDLER_MAP.put(0x04,new AddFile());
        HANDLER_MAP.put(0x05,new UpdateFile());*/
        HANDLER_MAP.put(0x06,new AddDir());
/*        HANDLER_MAP.put(0x07,new DeleteDir());*/
    }
}
