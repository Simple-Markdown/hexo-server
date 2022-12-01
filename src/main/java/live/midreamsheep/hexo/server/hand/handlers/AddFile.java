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
        String path = content.substring(0,content.indexOf("\n"));
        File file = new File((Config.nativeHexoPath+path).trim());
        try {
            System.out.println(file.getAbsolutePath());
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int pointor = -1;
        for(int i = 0;i<data.length;i++){
            if(data[i]=='\n'){
                pointor = i;
                break;
            }
        }
        byte[] datas = new byte[data.length-pointor-1];
        for(int i = pointor+1;i<data.length;i++){
            datas[i-pointor-1] = data[i];
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(datas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
