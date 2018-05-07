package com.owl.servlet.test2;

import java.io.OutputStream;

/**
 * 自定义userServlet类来根据请求参数动态响应页面
 */
public class UserServlet {
  //具体的业务方法
  public void doService(MyHttpRequest request, MyHttpResponse response) {
    //准备响应头数据
    String responseHeader = "HTTP/1.1 200 OK\r\nContent-Type:" + response.getContentType() + ";charset=UTF-8\r\n\r\n";
    //准备响应体数据
    String body = "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "    <meta content=\"text/html;charset=UTF-8\">\n" +
        "    <title>User</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "    <h1>姓名：#name</h1>\n" +
        "    <h1>年龄：#age</h1>\n" +
        "</body>\n" +
        "</html>";
    String responseBody = body.replace("#name", request.getRequestParam("name")).replace("#age", request.getRequestParam("age"));
    //响应
    try (OutputStream outputStream = response.getOutputStream()) {
      outputStream.write((responseHeader+responseBody).getBytes("utf-8"));
      Thread.sleep(1000);//睡眠一秒等待客户端处理响应结果
      System.out.println("响应成功");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}