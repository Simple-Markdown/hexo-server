package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddFile implements HandlerInter {
    @Override
    public void handle(byte[] data) {
        String content = new String(data);
        String path = content.substring(0,content.indexOf("\n")+1);
        String substring = content.substring(content.indexOf("\n") + 1);
        File file = new File((Config.nativeHexoPath+path).trim());
        try {
            System.out.println(file.getAbsolutePath());
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!substring.trim().equals("")){
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(substring.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
