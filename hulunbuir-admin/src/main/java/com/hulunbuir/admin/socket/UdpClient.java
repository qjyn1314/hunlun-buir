package com.hulunbuir.admin.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/6 0:12
 */
public class UdpClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket(10009);
        System.out.println("client_start_success_....");
        String data = "你好";
        byte[] datas = data.getBytes();
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1",10008);
        //数据包
        DatagramPacket packet = new DatagramPacket(datas,0,datas.length,socketAddress);
        client.send(packet);
        client.close();
    }





}
