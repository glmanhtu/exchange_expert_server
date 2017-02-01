package com.EsMgExam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
 
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

public class ElasticEle {
	private static final String TYPE_PERSON = "person";
	private static final String INDEX = "address";
	private final Client client;
    public ElasticEle(Client client) {
        this.client = client;
    }
    public void add(ElsPerson person) {
        System.out.printf("adding person to search index: %s\n", person);
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE_PERSON);
        indexRequest.source(person.toJson());
        IndexResponse response = client.index(indexRequest).actionGet();
        System.out.printf("entry added to index '%s', type '%s', doc-version: '%s', doc-id: '%s', created: %s\n",
                response.getIndex(), response.getType(), response.getVersion(), response.getId(), response.isCreated());
    }
    public List<ElsPerson> findByAddr(String iaddr) {
        System.out.printf("searching persons for given tag: %s\n", iaddr);
        SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE_PERSON).addFields("name", "addr")
                .setQuery(QueryBuilders.termQuery("addr", iaddr)).execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.printf("%s hits for addr '%s' found\n", hits.totalHits(), iaddr);
 
        return StreamSupport.stream(hits.spliterator(), true).map(hit -> {
            String name = hit.field("name").getValue();
            String addr = hit.field("addr").getValue();
            return new ElsPerson(name,addr);
        }).collect(Collectors.toList());
    }
}
