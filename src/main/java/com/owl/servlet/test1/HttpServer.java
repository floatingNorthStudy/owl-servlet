package com.owl.servlet.test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * java socket实现一次简单的http请求
 * @author liuao
 * @date 2018/5/7 9:07
 */
public class HttpServer {
  public static void main(String[] args) {
    try {
      //监听8888端口
      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("正在监听端口" + serverSocket.getLocalPort());
      //等待客户TCP连接请求
      while (true) {
        final Socket socket = serverSocket.accept();
        System.out.println("与" + socket.getInetAddress() + "建立连接");
        service(socket);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //解析请求信息
  private static void service(Socket socket) throws IOException, InterruptedException {
    final InputStream is = socket.getInputStream();
    Thread.sleep(500);
    final int size = is.available();
    byte[] buffer = new byte[size];
    //获取请求数据
    is.read(buffer);
    String request = new String(buffer, "UTF-8");
    System.out.println("收到请求数据："+request);
    //解析请求第一行
    String lineOne = request.substring(0, request.indexOf("\r\n"));
    final String[] str = lineOne.split(" ");
    //读取到uri /user.html
    String uri = str[1];
    //处理业务并响应结果
    response(socket, uri);
  }

  /**
   * 响应请求
   */
  private static void response(Socket socket, String uri) throws IOException, InterruptedException {
    String contentType;//简化，只模拟两种情况
    if (uri.contains(".html")) {
      contentType = "text/html";
    } else {
      contentType = "application/octed-stream";//字节流
    }
    //准备响应头信息
    String responseHeader = "HTTP/1.1 200 OK\r\nContent-Type:" + contentType + "\r\n\r\n";
    //读取静态页面user.html
    final InputStream responseBody = HttpServer.class.getClassLoader().getResourceAsStream("template"+uri);
    final OutputStream socketOut = socket.getOutputStream();
    //写入响应头数据
    socketOut.write(responseHeader.getBytes("UTF-8"));
    int len;
    byte[] buffer = new byte[1024];
    //将html页面写入响应体中
    while ((len = responseBody.read(buffer)) != -1) {
      socketOut.write(buffer, 0, len);
    }
    Thread.sleep(1000);
    System.out.println("响应成功");
    socket.close();
  }
}
