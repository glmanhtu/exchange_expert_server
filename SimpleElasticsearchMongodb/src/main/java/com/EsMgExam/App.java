package com.EsMgExam;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import com.google.common.io.Files;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	MogoIf myMgIf = new MogoIf();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		File tempDir = Files.createTempDir();
		Settings settings = Settings.builder().put("path.home", tempDir.getAbsolutePath()).build();
		Node server = NodeBuilder.nodeBuilder().settings(settings).build();
		final String clusterName = server.settings().get("cluster.name");
		System.out.printf("starting server with cluster-name: %s\n", clusterName);
		server.start();
        Thread.sleep(2000);
        Client client = server.client();
        ElasticEle myelsticele = new ElasticEle(client);
        
        myMgIf.addDocument("Quoc", "3534 4535");
        myelsticele.add(new ElsPerson("Quoc","3534 4535"));
        
        myMgIf.addDocument("abc", "abdsf asdf");
        myelsticele.add(new ElsPerson("abc","abdsf asdf"));
        
        myMgIf.addDocument("def", "3534 4535");
        myelsticele.add(new ElsPerson("def","3534 4535"));
        
        myMgIf.addDocument("hkj", "uuuussss");
        myelsticele.add(new ElsPerson("hkj","uuuussss"));
        List<ElsPerson> persons = myelsticele.findByAddr("uuuussss");
        persons.forEach(System.out::println);
        System.out.printf("closing server with cluster-name: %s\n", clusterName);
        server.close();
//        MogoIf myMgIf = new MogoIf();
//        System.out.print("Input Name");
//        String inName = br.readLine();
//        System.out.print("Input Addrress");
//        String inAddr = br.readLine();
//        myMgIf.addDocument(inName, inAddr);
    }
}
