package com.jack.myblog.test;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        //创建socket
        ServerSocket serverSocket = new ServerSocket(65000);
        //死循环，使得socket一直等待并处理客户端发过来的请求
        while (true){
            //监听65000端口，直到客户端返回连接信息后才返回
            Socket socket = serverSocket.accept();
            //获取客户端的信息并执行相关逻辑
            new LengthCalculator(socket).start();
        }
    }
}
