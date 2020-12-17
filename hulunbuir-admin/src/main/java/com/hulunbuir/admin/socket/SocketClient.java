package com.hulunbuir.admin.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

	public static void main(String[] args) {
		Socket socket = null;
		while(true) {
			try {
				socket = new Socket("localhost",12345);
				System.out.println("请输入报文。。。");
				Scanner scanner = new Scanner(System.in);
				String string = scanner.nextLine();
				if(string.equals("bye")) {
					break;
				}
				//发送给服务器数据
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataOutputStream.writeUTF(string);
				//服务器返回数据
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				System.out.println("服务器发送了：" + dataInputStream.readUTF());
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
