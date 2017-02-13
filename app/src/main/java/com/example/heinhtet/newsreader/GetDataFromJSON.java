package com.example.heinhtet.newsreader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heinhtet on 2/13/17.
 */

public class GetDataFromJSON  {
   public static String mLink;

    public GetDataFromJSON(){

    }

    public static final List<Google> list (String link){
        mLink = link;

        URL url = CreateURL(mLink);
        String jsonResponse = "";
        jsonResponse = makeHttpNetwork(url);

        List<Google> getJSONData = extractFromJson(jsonResponse);
        return getJSONData;

    }

    private static List<Google> extractFromJson(String jsonResponse) {

        List<Google>arrayList = new ArrayList<>();

        try {
            JSONObject baseOB = new JSONObject(jsonResponse);
            JSONArray baseArray = baseOB.getJSONArray("articles");
            for (int i = 0;i<baseArray.length();i++){
                JSONObject googleJson = baseArray.getJSONObject(i);
                arrayList.add(new Google(
                        googleJson.getString("title"),
                        googleJson.getString("description"),
                        googleJson.getString("url"),
                        googleJson.getString("urlToImage"),
                        googleJson.getString("publishedAt")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static String makeHttpNetwork(URL url) {
        String jsonResponse = "";
        HttpURLConnection httpURLConnection = null;
        InputStream stream = null;
        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1500000);
            httpURLConnection.setReadTimeout(1500000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode()==200){
                stream = httpURLConnection.getInputStream();
                jsonResponse =  ReadInputStream(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if (stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;

    }

    private static String ReadInputStream(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        while (line!=null){
            builder.append(line);
            line = buffer.readLine();
        }
        return builder.toString();
    }

    private static URL CreateURL(String mLink) {
        URL link = null;

        try {
            link = new URL(mLink);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return link;
    }
}
