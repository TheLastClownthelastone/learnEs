package com.pt;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nate-pt
 * @date 2021/7/1 13:37
 * @Since 1.8
 * @Description 创建index
 */
public class Demo2 {

    public static void main(String[] args) throws IOException {
        TransportClient client = ClientUtil.getClient();

        // 设置index中的内容
        // 设置content内容之前调用startObject方法，设置完属性之后调用endObject方法
        XContentBuilder source = XContentFactory.jsonBuilder().startObject()
                .field("id","5")
                .field("name","pt")
                .field("age",10).endObject();

        // 创建index
        IndexResponse response = client.prepareIndex("a","tweet","4").setSource(source).get();

        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();

        long version = response.getVersion();
        boolean fragment = response.isFragment();
        Map<String,Object> map = new HashMap<>(16);
        map.put("index",index);
        map.put("type",type);
        map.put("id",id);
        map.put("version",version);
        map.put("fragment",fragment);

        System.out.println(JSON.toJSONString(map));

        client.close();
    }

    @Test
    public void test1() throws Exception {

        TransportClient client = ClientUtil.getClient();

        // 创建文档对象
        XContentBuilder document = XContentFactory.jsonBuilder().startObject()
                .field("name", "zz")
                .field("gender", true)
                .field("age", 3).endObject();

        // 添加文档
        IndexResponse indexResponse = client.prepareIndex("index3", "user", "2").setSource(document).get();
        System.out.println(indexResponse);

        client.close();



    }

}
