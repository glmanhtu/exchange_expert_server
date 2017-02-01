package com.EsMgExam;
import java.io.File;
import java.util.List;
 
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class ElasticIf {
	public void indexDoc(String name, String addr) throws InterruptedException
	{
		File tempDir = Files.createTempDir();
		Settings settings = Settings.builder().put("path.home", tempDir.getAbsolutePath()).build();
		Node server = NodeBuilder.nodeBuilder().settings(settings).build();
		final String clusterName = server.settings().get("cluster.name");
		System.out.printf("starting server with cluster-name: %s\n", clusterName);
		server.start();
        Thread.sleep(2000);
        Client client = server.client();
        ElasticEle myelsticele = new ElasticEle(client);
        myelsticele.add(new ElsPerson(name,addr));
	}

}
