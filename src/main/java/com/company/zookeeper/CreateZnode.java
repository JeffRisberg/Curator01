package com.company.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.List;

public class CreateZnode {

  static ZooKeeper zoo;

  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    String hostname = "localhost:2181";

    createConnection(hostname);

    String path = "/tutorialdrive";
    byte[] data = "now is the time".getBytes();
    List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
    CreateMode createMode = CreateMode.PERSISTENT;

    zoo.create(path, data, acl, createMode);
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
