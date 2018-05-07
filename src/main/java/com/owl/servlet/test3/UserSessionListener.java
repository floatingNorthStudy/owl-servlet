package com.owl.servlet.test3;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener {
  @Override
  public void sessionCreated(HttpSessionEvent se) {
    final HttpSession session = se.getSession();
    System.out.println(LocalDateTime.now()+"创建sessionId="+session.getId());
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    System.out.println(LocalDateTime.now()+"销毁sessionId="+se.getSession().getId());
  }
}
