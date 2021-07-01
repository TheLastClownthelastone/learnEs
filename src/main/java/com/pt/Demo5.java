package com.pt;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.util.Map;

/**
 * @author nate-pt
 * @date 2021/7/1 14:45
 * @Since 1.8
 * @Description 查询es中内容
 */
public class Demo5 {

    /**
     * 基本查询
     * @throws Exception
     */
    @Test
    public void test1() throws Exception{
        TransportClient client = ClientUtil.getClient();

        GetResponse documentFields = client.prepareGet("index3", "user", "2").execute().actionGet();

        Map<String, DocumentField> fields = documentFields.getFields();

        DocumentField name = documentFields.getField("name");

        System.out.println(fields);

        System.out.println(name);

        Map<String, Object> source = documentFields.getSource();

        System.out.println(source);

        client.close();
    }

    /**
     * term 查询
     */
    @Test
    public  void test2() throws Exception{

        TransportClient client = ClientUtil.getClient();

        String index = "index3";

        String type = "user";

        // 设置request 对象
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);

        // 指定条件进行查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.from(0);

        searchSourceBuilder.size(3);

        searchSourceBuilder.query(QueryBuilders.termQuery("name","pt"));

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest).get();

        SearchHit[] hits = searchResponse.getHits().getHits();

        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);

        }

        client.close();

    }


}
