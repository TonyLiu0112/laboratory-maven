package com.liuboyu.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

    public static void main(String[] args) throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "swhyes").build();

        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(
                new TransportAddress(InetAddress.getByName("www.tony666.com"), 9300));

        SearchResponse searchResponse = client.prepareSearch("adviser_theme_index")
                .setTypes("adviser_theme_index_type")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("thumbnail", "bbb"))
                .get();
        searchResponse.getHits().forEach(searchHitFields -> System.out.println(searchHitFields.getSourceAsString()));
    }

}
