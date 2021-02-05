package com.hulunbuir.admin.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * <p>
 * explain:
 * DatagramSocket  表示用于发送和接收数据报数据包的套接字。
 * DatagramPacket  数据包
 *
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 0:12
 */
public class UdpServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(10008);
        System.out.println("server_start_success_....");
        byte[] pack = new byte[1024];
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1",10008);
        DatagramPacket packet = new DatagramPacket(pack,0,pack.length,socketAddress);
        server.receive(packet);
        byte[] data = packet.getData();
        System.out.println(data.length);
        System.out.println(new String(data,0,data.length));
        server.close();

    }

}
