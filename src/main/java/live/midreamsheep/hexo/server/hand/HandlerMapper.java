package live.midreamsheep.hexo.server.hand;

import live.midreamsheep.hexo.server.hand.handlers.*;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapper {
    public static final Map<Integer,HandlerInter> HANDLER_MAP = new HashMap<>();
    static {
        HANDLER_MAP.put(HandlerEnum.ADD_DIR.getId(),new AddDir());
        HANDLER_MAP.put(HandlerEnum.ADD_FILE.getId(), new AddFile());
        HANDLER_MAP.put(HandlerEnum.DELETE_DIR.getId(),new DeleteDir());
        HANDLER_MAP.put(HandlerEnum.DELETE_FILE.getId(),new DeleteFile());
        HANDLER_MAP.put(HandlerEnum.UPDATE_FILE.getId(),new UpdateAFile());
        HANDLER_MAP.put(HandlerEnum.PUSH.getId(),new Push());
        HANDLER_MAP.put(HandlerEnum.PULL.getId(),new Pull());
        HANDLER_MAP.put(HandlerEnum.SERVER.getId(),new Server());
        HANDLER_MAP.put(HandlerEnum.SERVER_STOP.getId(),new ServerStop());
    }
}
