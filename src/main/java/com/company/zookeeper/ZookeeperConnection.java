package com.company.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZookeeperConnection {

  static ZooKeeper zoo;

  public static void main(String[] args) throws IOException, InterruptedException {
    String hostname = "localhost:2181";

    createConnection(hostname);

    while (true) {
      Thread.sleep(1000);

      System.out.println("");
      zoo.getState();
    }
  }

  public static void createConnection(String hostname) throws IOException {
    zoo = new ZooKeeper(hostname, 3000, new Watcher() {
      @Override
      public void process(WatchedEvent event) {
        System.out.println("WatchedEvent " + event.toString());

      }
    });
  }
}
