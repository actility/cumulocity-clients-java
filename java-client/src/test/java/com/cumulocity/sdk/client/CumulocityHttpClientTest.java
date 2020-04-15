package com.cumulocity.sdk.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.apache.ApacheHttpClientHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CumulocityHttpClientTest {
    
    private static final String HOST = "http://management.cumulocity.com:8080";
    
    private CumulocityHttpClient client;

    private Client jerseyClient;

    private ClientConfig clientConfig;

    private int chunkedEncodingSize = 1024;

    private  ResponseParser responseParser;

    private RestConnector restConnector;
    
    @Before
    public void setUp() {
        PlatformParameters platformParameters = new PlatformParameters();
        platformParameters.setForceInitialHost(true);
        platformParameters.setHost(HOST);
        platformParameters.setChunkedEncodingSize(chunkedEncodingSize);
        restConnector = new RestConnector(platformParameters,responseParser);
        jerseyClient = restConnector.getClient();
        client = createClient(platformParameters);
    }

    @Test
    public void shouldChangeHostToPlatformHostIfThisIsForcedInParameters() throws Exception {
        String queryParams = "?test=1&a=1";
        String pathParams = "/test/1/q=1&a=1";

        verifyResolvedPath(HOST, HOST);
        verifyResolvedPath(HOST, "http://127.0.0.1");
        verifyResolvedPath(HOST, "http://127.0.0.1:8181");
        verifyResolvedPath(HOST + queryParams, "http://127.0.0.1:8181" + queryParams);
        verifyResolvedPath(HOST + queryParams, "http://127.0.0.1" + queryParams);
        verifyResolvedPath(HOST + pathParams, "http://127.0.0.1" + pathParams);
    }

    @Test
    public void shouldEnableChunckedEncoding() throws Exception {
        Integer chunkedProperty = (Integer) jerseyClient.getProperties().get(clientConfig.PROPERTY_CHUNKED_ENCODING_SIZE);
        assertEquals(chunkedProperty,Integer.valueOf(chunkedEncodingSize));
    }
    
    private void verifyResolvedPath(String expected, String initial) {
        String resolved = client.resolvePath(initial);
        assertThat(expected, is(resolved));
    }
    
    public static CumulocityHttpClient createClient(PlatformParameters platformParameters) {
        CumulocityHttpClient client = new CumulocityHttpClient(createDefaultClientHander(), null);
        client.setPlatformParameters(platformParameters);
        return client;
    }
    
    private static ApacheHttpClientHandler createDefaultClientHander() {
        final HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        return new ApacheHttpClientHandler(client, mock(ClientConfig.class));
    }

}
