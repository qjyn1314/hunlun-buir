package com.hulunbuir.admin.socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/5 21:01
 */
public class TcpUdpStudy {

    /**
     * windows中查看本机端口号：
     * netstat -ano
     *
     *
     *
     * @author wangjunming
     * @since 2021/2/5 21:02
     */
    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        System.out.println(hostAddress);
        String hostName = localHost.getHostName();
        System.out.println(hostName);
        InetSocketAddress socketAddress = InetSocketAddress.createUnresolved("127.0.0.1",9999);

    }

}
