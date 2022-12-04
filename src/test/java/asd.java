import live.midreamsheep.hexo.server.config.Config;
import live.midreamsheep.hexo.server.tool.command.Commander;

import java.io.*;

public class asd {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("D:\\mine\\hexo\\hexo-server\\command\\winServer.bat E: E:\\hexo");
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
        System.out.println(p.isAlive());
        p.destroy();
    }
}
