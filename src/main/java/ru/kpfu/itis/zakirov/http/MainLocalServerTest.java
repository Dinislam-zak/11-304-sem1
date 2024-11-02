package ru.kpfu.itis.zakirov.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainLocalServerTest {
    static Random random = new Random();

    public static void main(String[] args) throws IOException {
        HttpClientImpl httpClient = new HttpClientImpl();

        String url;
        Map<String, String> headers;
        Map<String, String> params;
        Map<String, String> data;

        // get test
        url = "http://localhost:8080/11_304_sem1_war_exploded/hello";

        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        params = new HashMap<>();
        params.put("userId", "2");

        System.out.println("GET Response:");
        System.out.println(httpClient.get(url, headers, params));

        // post test
        url = "http://localhost:8080/11_304_sem1_war_exploded/hello";

        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer d2a78d797dc15d660f7f6f48e2fd30d29e0d03a13bf8f03db03f704b776c2687");

        data = new HashMap<>();
        data.put("name", "Atlas");
        data.put("email", generateEmail("Atlass"));
        data.put("gender", "male");
        data.put("status", "active");

        System.out.println("\nPOST Response:");
        System.out.println(httpClient.post(url, headers, data));

        // put test
        url = "http://localhost:8080/11_304_sem1_war_exploded/hello";

        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer d2a78d797dc15d660f7f6f48e2fd30d29e0d03a13bf8f03db03f704b776c2687");

        data = new HashMap<>();
        data.put("name", "Atlas");
        data.put("email", "AtlasKingsman@mail.ru");
        data.put("status", "active");

        System.out.println("\nPUT Response:");
        System.out.println(httpClient.put(url, headers, data));

        // delete-test
        url = "http://localhost:8080/11_304_sem1_war_exploded/hello";

        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer d2a78d797dc15d660f7f6f48e2fd30d29e0d03a13bf8f03db03f704b776c2687");

        data = new HashMap<>();

        System.out.println("\nDELETE Response:");
        System.out.println(httpClient.delete(url, headers, data));
    }

    private static String generateEmail(String name) {
        return name.replaceAll("\\s+", "") + random.nextInt(1000000) + "@mail.test";
    }
}
