package com.company.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class GetChildInfo {

  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    String hostname = "localhost:2181";

    ZookeeperConnection zk = new ZookeeperConnection();

    ZookeeperConnection.createConnection(hostname);

    String path = "/";
    System.out.println(zk.zoo.getChildren(path, false));
  }
}
