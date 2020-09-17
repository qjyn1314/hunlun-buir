package com.hulunbuir.clam.admin.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable{
	public int port;
	public SocketServer(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
	}
	@SuppressWarnings("resource")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket socket = null;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while(true) {
				//等待客户端连接
				System.out.println("等待客户端请求。。。");
				socket = serverSocket.accept();
				//接收到客户端消息
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				System.out.println("进入输入流阻塞状态");
				String readUTF = dataInputStream.readUTF();
				System.out.println("客户端发送信息：" + readUTF);
				//发送给客户端消息
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataOutputStream.writeUTF("服务端已接收客户端发送报文");
				socket.close();
			}
		} catch (EOFException e) {
			String hostAddress = socket.getInetAddress().getHostAddress();
			System.out.println("客户端IP："+hostAddress+"断开了链接");
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SocketServer server = new SocketServer(12345);
		server.run();
	}

}
