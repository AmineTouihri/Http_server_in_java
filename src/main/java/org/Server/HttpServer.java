package org.Server;

import org.Server.config.ConFiguration;
import org.Server.config.ConfigManager;
import org.Server.core.ServerListnerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HttpServer {
    private final static Logger LOGGER =  LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {
        LOGGER.info("Server Starting...");
        ConfigManager.getInstance().loadConfigFile("src/main/resources/http.json");
        ConFiguration conf=ConfigManager.getInstance().getCurrentConfig();
        LOGGER.info("Using Port :"+conf.getPort());
        try {
            ServerListnerThread serverListnerThread =new ServerListnerThread(conf.getPort(),conf.getWebRoot());
            serverListnerThread.start();
        } catch (IOException e) {
//           TODO handel it later
        }
    }
}
