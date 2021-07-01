package com.pt;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nate-pt
 * @date 2021/7/1 14:04
 * @Since 1.8
 * @Description 删除对应的index
 */
public class Demo3 {

    public static void main(String[] args) throws Exception {
        TransportClient client = ClientUtil.getClient();

        // 通过选中要删除的index
        DeleteResponse deleteResponse = client.prepareDelete("a", "tweet", "1").get();

        String index = deleteResponse.getIndex();
        String type = deleteResponse.getType();
        String id = deleteResponse.getId();
        Long version = deleteResponse.getVersion();

        Map<String, Object> result = new HashMap<>(16);
        result.put("index", index);
        result.put("type", type);
        result.put("id", id);
        result.put("version", version);

        System.out.println(JSON.toJSONString(result));

        client.close();

    }
}
