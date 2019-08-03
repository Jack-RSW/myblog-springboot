package com.jack.myblog.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        byte[] buff = "Hello World".getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buff, buff.length, address, 65001);
        socket.send(packet);

        byte[] data = new byte[100];
        DatagramPacket receivedPacket = new DatagramPacket(data, data.length);
        socket.receive(receivedPacket);
        String content = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
        System.out.println(content);
    }
}
