package com.owl.servlet.test3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
  private static final long serialVersionUID = 485923566163995521L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final HttpSession session = req.getSession();
    resp.setStatus(204);
//    Cookie cookie = new Cookie("JSESSIONID",session.getId());
//    cookie.setMaxAge(2000);
//    resp.addCookie(cookie);
//    final Optional<HttpSession> sessionOp = Optional.ofNullable(req.getSession(false));
//    if (sessionOp.isPresent()){
//      resp.setStatus(204);
//      resp.setHeader("session",sessionOp.get().getId());
//      Cookie cookie = new Cookie("JSESSIONID",sessionOp.get().getId());
//      cookie.setMaxAge(2000);
//      resp.addCookie(cookie);
//    }else {
//      resp.setStatus(403);
//    }
  }
}
