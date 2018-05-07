package com.owl.servlet.test2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
  public static void main(String[] args) {
    try {
      //监听8888端口
      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("正在监听端口" + serverSocket.getLocalPort());
      //等待客户TCP连接请求
      while (true) {
        Socket socket = null;
        try {
          socket = serverSocket.accept();
          System.out.println("与" + socket.getInetAddress() + "建立连接");
          //初始化请求数据
          MyHttpRequest request = new MyHttpRequest(socket.getInputStream());
          //初始化响应数据
          MyHttpResponse response = new MyHttpResponse(socket.getOutputStream());
          //调用UserServlet来处理具体业务
          UserServlet userServlet = new UserServlet();
          userServlet.doService(request, response);
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          assert socket != null;
          socket.close();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
