package com.owl.servlet.test2;

import java.io.OutputStream;

/**
 * 封装响应对象
 * @author liuao
 * @date 2018/5/7 9:12
 */
public class MyHttpResponse {
  private OutputStream outputStream;
  //响应媒体类型
  private String contentType;

  public MyHttpResponse(OutputStream outputStream) {
    //只模拟响应类型为text/html的情况
    this.contentType = "text/html";
    this.outputStream = outputStream;
  }

  public OutputStream getOutputStream() {
    return this.outputStream;
  }

  public String getContentType() {
    return contentType;
  }
}
