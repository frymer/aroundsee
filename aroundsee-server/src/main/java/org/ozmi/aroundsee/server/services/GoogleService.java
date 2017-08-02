package org.ozmi.aroundsee.server.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.sprockets.net.HttpClient;

public class GoogleService {
//    private static final String GOOGLE_API_KEY  = "***";
//
//    private final HttpClient    client          = new DefaultHttpClient();
//
//    public static void main(final String[] args) throws ParseException, IOException, URISyntaxException
//    {
//        new GooglePlacesClient().performSearch("establishment", 8.6668310, 50.1093060);
//    }
//
//    public void performSearch(final String types, final double lon, final double lat) throws ParseException, IOException, URISyntaxException
//    {
//        final URIBuilder builder = new URIBuilder().setScheme("https").setHost("maps.googleapis.com").setPath("/maps/api/place/search/json");
//
//        builder.addParameter("location", lat + "," + lon);
//        builder.addParameter("radius", "5");
//        builder.addParameter("types", types);
//        builder.addParameter("sensor", "true");
//        builder.addParameter("key", GooglePlacesClient.GOOGLE_API_KEY);
//
//        final HttpUriRequest request = new HttpGet(builder.build());
//
//        final HttpResponse execute = this.client.execute(request);
//
//        final String response = EntityUtils.toString(execute.getEntity());
//
//        System.out.println(response);
//    }
}
