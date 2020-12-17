package com.hulunbuir.admin.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 * 说明：学习Socket，搭建服务端
 * </p >
 *
 * @author wangjunming
 * @since 2019-07-19 22:11
 */
public class SocketServers {

    //搭建服务器端
    public static void main(String[] args) throws IOException {
        //创建服务器
        ServerSocket server=new ServerSocket(12345);
        System.out.println("服务器启动成功");
        //等待客户端连接后，接收客户端socket
        Socket socket=server.accept();
        //获取客户端socket的输入流
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(true){
            //等待客户端socket的不为空输入流
            String str = in.readLine();
            if (str == null) {
                break;
            }
            System.out.println("客户端说：" + str);
        }
        in.close(); //关闭Socket输入流
        socket.close(); //关闭Socket
        server.close(); //关闭ServerSocket
    }

}
