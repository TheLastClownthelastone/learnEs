package com.pt;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author nate-pt
 * @date 2021/7/1 13:38
 * @Since 1.8
 * @Description
 */
public class ClientUtil {

    public static TransportClient getClient() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name","elasticsearch").build();

        // es的端口号，ip
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"),9300);

        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);

        return client;
    }
}
