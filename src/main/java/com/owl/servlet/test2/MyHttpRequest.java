package com.owl.servlet.test2;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
/**
 * 封装请求对象
 * @author liuao
 * @date 2018/5/7 9:12
 */
public class MyHttpRequest {
  //请求参数
  private Map<String, String> requestParams;
  //构造器需要传入请求的输入流以解析请求参数等数据
  public MyHttpRequest(InputStream inputStream) {
    try {
      Thread.sleep(500);
      initParams(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 解析请求参数，这里只考虑get请求的情况，即请求参数在URI中
   */
  private void initParams(InputStream inputStream) throws IOException {
    this.requestParams = new HashMap<>();
    final int size = inputStream.available();
    byte[] buffer = new byte[size];
    //读取请求数据
    inputStream.read(buffer);
    String request = new String(buffer, "UTF-8");
    System.out.println("收到请求数据：\n" + request);
    //解析请求
    String lineOne = request.substring(0, request.indexOf("\r\n"));
    //获取第一行数据
    final String[] str = lineOne.split(" ");
    String uri = str[1];
    //user?name=owl&age=18
    String[] paramsStr = uri.split("\\?")[1].split("&");
    for (String param : paramsStr) {
      String[] kv = param.split("=");
      this.requestParams.put(kv[0], kv[1]);
    }
  }
  //根据请求参数名获取请求参数值
  public String getRequestParam(String key) {
    return requestParams.get(key);
  }
}
