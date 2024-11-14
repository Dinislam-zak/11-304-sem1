package ru.kpfu.itis.zakirov.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpClientImpl implements HttpClient {
    //get
    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        StringBuilder response = new StringBuilder();

        try {
            StringBuilder urlWithParams = new StringBuilder(url);
            if (params != null && !params.isEmpty()) {
                urlWithParams.append("?");
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlWithParams.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                            .append("&");
                }
                urlWithParams.setLength(urlWithParams.length() - 1);
            }

            URL getUrl = new URL(urlWithParams.toString());
            HttpURLConnection con = (HttpURLConnection) getUrl.openConnection();

            con.setRequestMethod("GET");

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    con.setRequestProperty(header.getKey(), header.getValue());
                }
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return response.toString();
    }
    //post
    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            URL postUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    connection.setRequestProperty(header.getKey(), header.getValue());
                }
            }
            return getResponse(connection, data).toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
    //put
    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data){
        try {
            URL putUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) putUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    connection.setRequestProperty(header.getKey(), header.getValue());
                }
            }

            return getResponse(connection, data).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    //delete
    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data){
        try {
            URL deleteUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) deleteUrl.openConnection();
            connection.setRequestMethod("DELETE");
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = connection.getResponseCode();
            return Integer.toString(responseCode);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    //достаем респонс отдельно в методе
    private StringBuilder getResponse (HttpURLConnection connection, Map<String,String> data){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(data);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonData);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
