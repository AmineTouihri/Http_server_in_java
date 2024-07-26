package org.Server.config;

import java.lang.module.Configuration;

public class ConFiguration {

  public ConFiguration(){

  }

  private int port;
  private String webRoot;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getWebRoot() {
    return webRoot;
  }

  public void setWebRoot(String webRoot) {
    this.webRoot = webRoot;
  }
}
