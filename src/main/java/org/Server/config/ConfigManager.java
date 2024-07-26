package org.Server.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.Server.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigManager {

  private static ConfigManager configManager;
  private static ConFiguration mycurrentConfig;


  private ConfigManager (){
  }

  public static ConfigManager getInstance(){

    if (configManager==null)
      configManager=new ConfigManager();
    return configManager;
  }


  /**
    * Used to load a conifg file by the path
    */
  public void loadConfigFile(String path)  {
      FileReader fr= null;
      try {
          fr = new FileReader(path);
      } catch (FileNotFoundException e) {
        throw new HttpConfigurationException("exception while file reading");
      }
      StringBuffer sb=new StringBuffer();
    int i;
    while (true){
        try {
            if (!((i=fr.read())!=-1)) break;
        } catch (IOException e) {
           throw  new HttpConfigurationException(e);
        }
        sb.append((char) i);
    }
      JsonNode conf= null;
      try {
          conf = Json.parse(sb.toString());
      } catch (JsonProcessingException e) {
        throw  new HttpConfigurationException("error while parssing configuration file");
      }
      try {
          mycurrentConfig=Json.fromJson(conf,ConFiguration.class);
      } catch (JsonProcessingException e) {
         throw new HttpConfigurationException("error while parssing the configuration file !",e) ;
      }
  }

  public ConFiguration getCurrentConfig(){
    if(mycurrentConfig==null)
    {
      throw new HttpConfigurationException("no current config set !");
    }
    return mycurrentConfig;
  }
  
}
