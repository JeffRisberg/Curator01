package com.company.curator.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Main {

  // create a framework
  public static void main(String[] args) {
    try {
      System.out.println("framework");

      ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, Integer.MAX_VALUE);
      CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

      client.getUnhandledErrorListenable().addListener((message, e) -> {
        System.err.println("error=" + message);
        e.printStackTrace();
      });
      client.getConnectionStateListenable().addListener((c, newState) -> {
        System.out.println("state=" + newState);
      });
      client.start();
      client.getZookeeperClient().blockUntilConnectedOrTimedOut();

      // This line just does what is in the next line.
      if (client.checkExists().forPath("/a/qwerty") != null) {
        CrudExamples.delete(client, "/a/qwerty");
      }
      CrudExamples.create(client, "/a/qwerty", "now is the time".getBytes());

      if (client.checkExists().forPath("/yui") != null) {
        CrudExamples.delete(client, "/yui");
      }
      client.create().forPath("/yui", "now is the time".getBytes());

      client.setData().forPath("/a/qwerty", "this is really the place".getBytes());

      // get the data back
      byte[] x = client.getData().forPath("/a/qwerty");

      System.out.println(new String(x));

      NodeCache nc = new NodeCache(client, "/a/qwerty");
      nc.getListenable().addListener(new NodeCacheListener() {
        @Override
        public void nodeChanged() throws Exception {
          ChildData currentData = nc.getCurrentData();
          System.out.println("data change watched, and current data = " + new String(currentData.getData()));
        }
      });
      nc.start(true);

      client.setData().forPath("/a/qwerty", "now this is really the place".getBytes());

      byte[] y = nc.getCurrentData().getData();  // this required the buildInitial=true

      System.out.println(new String(y));

      Thread.sleep(Integer.MAX_VALUE);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
