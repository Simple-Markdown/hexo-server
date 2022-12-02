package live.midreamsheep.hexo.server.hand.handlers;

import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.hand.HandlerInter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.SocketChannel;

public class Push implements HandlerInter {
    @Override
    public void handle(byte[] data, SocketChannel socketChannel) {
        //获取操作系统类型
        String os = System.getProperty("os.name");
        try {
            Process p;
            if (os.toLowerCase().startsWith("win")) {
                //调用bat文件
                p = Runtime.getRuntime().exec("cmd /c start " + System.getProperty("user.dir") + File.separator + "command" + File.separator + "windows.bat "+Config.nativeHexoPath.substring(0,Config.nativeHexoPath.indexOf(":")+1)+" "+Config.nativeHexoPath);
             } else {
                //调用sh文件
                String[] filePath = {"/bin/sh", "-c", "sh " + System.getProperty("user.dir") + File.separator + "command" + File.separator + "linux.sh "+Config.nativeHexoPath};
                p = Runtime.getRuntime().exec(filePath);
            }
            //获取错误信息流。
            InputStream stderr = p.getErrorStream();
            //将错误信息流输出
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            System.out.println("--------------error---------------");
            while((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("");
            //等待进程完成。
            int exitVal = p.waitFor();
            System.out.println("Process Exitvalue: " + exitVal);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
