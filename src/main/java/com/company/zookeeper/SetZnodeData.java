package com.company.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class SetZnodeData {
  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    String hostname = "localhost:2181";

    ZookeeperConnection zk = new ZookeeperConnection();

    zk.createConnection(hostname);

    String path = "/tutorialdrive";
    byte[] data = "updated data".getBytes();
    int version = -1;

    zk.zoo.setData(path, data, version);
  }
}
