package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;
import live.midreamsheep.hexo.server.tool.patch.FilePatchUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class UpdateAFile implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        List<String> patch = new LinkedList<>();
        String[] lines = new String(data).split("\n");
        for (int i = 1; i < lines.length; i++) {
            patch.add(lines[i]);
        }
        String filePath = Config.nativeHexoPath+lines[0];
        File file = new File(filePath);
        if(file.exists()){
            if(file.length()==0){
                try {
                    Files.write(file.toPath(),patch.get(patch.size()-1).substring(1).getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
            FilePatchUtil.patch(filePath, patch);
        }
    }
}
