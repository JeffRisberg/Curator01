package com.company.curator.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;

public class Main {

  // create a framework
  public static void main(String[] args) {
    try {
      System.out.println("framework");

      // This will have default connection parameters.  A client is a collection, much like MongoDB.
      CuratorFramework client = CreateClientExamples.createSimple("127.0.0.1:2181");

      client.getUnhandledErrorListenable().addListener((message, e) -> {
        System.err.println("error=" + message);
        e.printStackTrace();
      });
      client.getConnectionStateListenable().addListener((c, newState) -> {
        System.out.println("state=" + newState);
      });
      client.start();

      // This line just does what is in the next line.
      CrudExamples.create(client, "/a/path", "now is the time".getBytes());

      client.create().forPath("/b/path", "now is the time".getBytes());

      client.setData().forPath("/a/path", "this is the place".getBytes());

      // get the data back
      PathChildrenCache pcc = new PathChildrenCache(client, "/b/path", true);

      for (ChildData childData : pcc.getCurrentData()) {
        System.out.println(childData);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
