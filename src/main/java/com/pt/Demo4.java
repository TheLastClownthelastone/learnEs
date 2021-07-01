package com.pt;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

/**
 * @author nate-pt
 * @date 2021/7/1 14:38
 * @Since 1.8
 * @Description 更新文档
 */
public class Demo4 {

    @Test
    public  void test1() throws Exception{
        TransportClient client = ClientUtil.getClient();

        // 创建更新的内容
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("index3").type("user").id("47")
                .doc(
                        XContentFactory.jsonBuilder().startObject().field("name","pt").endObject()
                );

        // 执行更新操作
        UpdateResponse updateResponse = client.update(updateRequest).get();

        System.out.println(updateResponse);

        client.close();


    }
}
