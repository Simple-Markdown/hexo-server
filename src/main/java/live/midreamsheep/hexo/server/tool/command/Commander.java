package live.midreamsheep.hexo.server.tool.command;

import live.midreamsheep.hexo.server.config.Config;

import java.io.*;

public class Commander {
    public static Process exec(String linuxName,String windowsName){
        //获取操作系统类型
        String os = System.getProperty("os.name");
        try {
            Process p;
            if (os.toLowerCase().startsWith("win")) {
                //调用bat文件
                p = Runtime.getRuntime().exec("cmd /c start " + System.getProperty("user.dir") + File.separator + "command" + File.separator + windowsName+" "+ Config.nativeHexoPath.substring(0,Config.nativeHexoPath.indexOf(":")+1)+" "+Config.nativeHexoPath);
                System.out.println("cmd /c start " + System.getProperty("user.dir") + File.separator + "command" + File.separator + windowsName+" "+ Config.nativeHexoPath.substring(0,Config.nativeHexoPath.indexOf(":")+1)+" "+Config.nativeHexoPath);
                System.out.println("调用完毕");
            } else {
                //调用sh文件
                String[] filePath = {"/bin/sh", "-c", "sh " + System.getProperty("user.dir") + File.separator + "command" + File.separator + linuxName+" "+Config.nativeHexoPath};
                p = Runtime.getRuntime().exec(filePath);
            }
            new Thread(()->{
                //将错误信息流输出
                try (BufferedReader br =  new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                    String line = "";
                    System.out.println("--------------error---------------");
                    while((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
