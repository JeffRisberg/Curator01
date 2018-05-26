package com.company.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class DeleteZnode {
  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    String hostname = "localhost:2181";

    ZookeeperConnection zk = new ZookeeperConnection();

    zk.createConnection(hostname);

    String path = "/tutorialdrive";
    int version = -1;

    zk.zoo.delete(path, version);
  }
}
