package com.pt;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nate-pt
 * @date 2021/7/1 11:09
 * @Since 1.8
 * @Description 链接es服务器
 */
public class Demo1 {

    public static void main(String[] args) throws UnknownHostException {
        // 制定es的集群名称
        Settings settings = Settings.builder().put("cluster.name","elasticsearch").build();

        // es的端口号，ip
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"),9300);

        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);

        List<DiscoveryNode> nodes = client.connectedNodes();

        for (DiscoveryNode node : nodes) {
            System.out.println(node.getHostAddress());
            System.out.println(node.getName());
        }

    }
}
