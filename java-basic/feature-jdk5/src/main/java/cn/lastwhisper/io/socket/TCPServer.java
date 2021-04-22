package cn.lastwhisper.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lastwhisper
 */
public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(65000);
            while (true) {
                // 监听请求
                Socket socket = ss.accept();
                new Thread() {
                    @Override
                    public void run() {
                        // 获取socket的输出流
                        // 获取socket的输入流
                        try (OutputStream os = socket.getOutputStream();
                             InputStream is = socket.getInputStream();) {
                            int ch;
                            byte[] buff = new byte[1024];
                            // buff主要用来读取输入的内容，存入byte数组，ch读取数组的长度
                            ch = is.read(buff);
                            // 将接收的byte数组转成字符串
                            String content = new String(buff, 0, ch);
                            System.out.println(content);

                            os.write(String.valueOf(content.length()).getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
