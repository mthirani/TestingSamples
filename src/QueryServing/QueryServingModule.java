import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentType.JSON;

/**
 * Query Serving Class using Elastic Search
 */
public class QueryServingModule {
    static Client client;
    static BufferedReader reader;
    static final String YES = "Y";
    static final String NAME = "name";
    static final String SCORE = "score";
    static final String INDEX = "people";
    static final String TYPE = "_doc";
    static final int RETURN_SIZE = 10;

    public static void main(String []args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            client = new PreBuiltTransportClient(
                    Settings.builder()
                            .put("cluster.name", args[2]).build())
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(args[0]),
                            Integer.parseInt(args[1])));
            System.out.println("Do you have any file to load? Press Y for Yes or any other " +
                    "character for No");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            if (name.equalsIgnoreCase(YES)) {
                System.out.println("Press enter after you input the path for the file to load :: ");
                name = reader.readLine();   ///Users/mayankthirani/Desktop/document.json
                List<Map<String, Object>> scoreMapList = mapper.readValue(new File(name),
                        new TypeReference<List<Object>>() {});
                scoreMapList.forEach( scoreMap -> {
                    try {
                        String eachContent = mapper.writeValueAsString(scoreMap);
                        IndexResponse response = client.prepareIndex(INDEX, TYPE)
                                .setSource(eachContent, JSON).get();
                    } catch (JsonProcessingException e) {
                        System.out.println("JsonProcessingException captured:: " + e);
                    }
                });
                queryServingMethod();
            } else {
                queryServingMethod();
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknown host exception captured :: " + e);
        } catch (IOException e) {
            System.out.println("IOException captured :: " + e);
        } finally {
            client.close();
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("IOException captured :: " + e);
            }
        }
    }

    private static void queryServingMethod() throws IOException {
        String name;
        do {
            System.out.println("Press enter after you input the string to query for :: ");
            name = reader.readLine();   //String to query for
            String query1 = name.toLowerCase() + "*";
            String query2 = "(.+)_" + name.toLowerCase() + "(.*)";
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.should(QueryBuilders.wildcardQuery(NAME, query1));
            boolQueryBuilder.should(QueryBuilders.regexpQuery(NAME, query2));
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.sort(new FieldSortBuilder(SCORE).order(SortOrder.DESC));
            sourceBuilder.query(boolQueryBuilder);
            sourceBuilder.size(RETURN_SIZE);
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.source(sourceBuilder);
            ActionFuture<SearchResponse> searchResponse = client.search(searchRequest);
            List<SearchHit> searchHits = Arrays.asList(searchResponse.actionGet().getHits().getHits());
            if (searchHits.size() == 0) {
                System.out.println("No suggestions found for the string: " + name);
            } else {
                System.out.println("Found " + searchHits.size() + " below suggestions for the " +
                        "query string");
            }
            searchHits.forEach( hit -> {
                System.out.println(hit.getSourceAsMap().get(NAME)); //getSourceAsString
            });
            System.out.println("Do you have more string to query for? Press Y for Yes or any " +
                    "other character for No");
            name = reader.readLine();
        } while (name.equalsIgnoreCase(YES));

    }
}
