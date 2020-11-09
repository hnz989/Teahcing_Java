import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.sampled.SourceDataLine;

public class Server{
    public static void main(String[] args) {
        try{
            //1创建监听套接字
            ServerSocket listener = new ServerSocket(5666);

            //2接收客户端请求
            System.out.println("Start client...");

            Socket socket = listener.accept();

            //3获取字节输出流
            OutputStream os = socket.getOutputStream();

            //4创建FileInputStream字节输入流
            FileInputStream fis = new FileInputStream("/home/hnz678/Desktop/Test_server/index.html");

            //设置缓冲区域
            byte[] buf = new byte[1024*4];
            int len = 0;

            //循环读取，写入
            while((len=fis.read(buf))!=-1)
            {
                os.write(buf, 0 , len);

                os.flush();
            }

            //5关闭
            fis.close();
            os.close();
            socket.close();
            listener.close();

            System.out.println("Service execution finished");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
