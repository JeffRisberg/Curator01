package com.company.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class GetZnodeData {
  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    String hostname = "localhost:2181";

    ZookeeperConnection zk = new ZookeeperConnection();

    zk.createConnection(hostname);

    String path = "/tutorialdrive";
    boolean watch = false;

    byte[] databytes = zk.zoo.getData(path, watch, null);

    String str = new String(databytes, "UTF-8");

    System.out.println(str);
  }
}
