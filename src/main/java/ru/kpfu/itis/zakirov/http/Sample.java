package ru.kpfu.itis.zakirov.http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Sample {
    public static void main(String[] args) throws IOException {
        //get
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts?userid=2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection != null){
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder content = new StringBuilder();
                    String input;
                    while ((input = br.readLine()) != null) {
                        content.append(input);
                    }
                    System.out.println(content.toString());
                }
            }
            connection.disconnect();
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //post
        try {
            URL postUrl = new URL("https://gorest.co.in/public/v2/users");
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer d2a78d797dc15d660f7f6f48e2fd30d29e0d03a13bf8f03db03f704b776c2687");
            postConnection.setDoOutput(true);
            String jsonInput = "{\"name\": \"Sen. Anala Iyer\",\"email\": \"dsen_anala_iyer64@stroman-leannon.test\",\"gender\": \"female\",\"status\": \"active\"}";
            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(postConnection.getResponseCode());
            System.out.println(readResponse(postConnection));
            postConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //put
        try {
            URL putUrl = new URL("https://gorest.co.in/public/v2/users/6942292");
            HttpURLConnection putConnection = (HttpURLConnection) putUrl.openConnection();
            putConnection.setRequestMethod("PUT");
            putConnection.setRequestProperty("Content-Type", "application/json");
            putConnection.setRequestProperty("Accept", "application/json");
            putConnection.setRequestProperty("Authorization", "Bearer d2a78d797dc15d660f7f6f48e2fd30d29e0d03a13bf8f03db03f704b776c2687");
            putConnection.setDoOutput(true);
            String jsonInput = "{\"name\": \"Sen. Anala Iyer\",\"email\": \"anala.iyer@updatedemail.test\",\"gender\": \"female\",\"status\": \"active\"}";
            try (OutputStream outputStream = putConnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(putConnection.getResponseCode());
            System.out.println(readResponse(putConnection));
            putConnection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //delete
        try {
            URL deleteUrl = new URL("https://gorest.co.in/public/v2/users/6942292");
            HttpURLConnection deleteConnection = (HttpURLConnection) deleteUrl.openConnection();
            deleteConnection.setRequestMethod("DELETE");
            deleteConnection.setRequestProperty("Accept", "application/json");
            deleteConnection.setRequestProperty("Authorization", "Bearer d2a78d797dc15d660f7f6f48e2fd30d29e0d03a13bf8f03db03f704b776c2687");
            System.out.println(deleteConnection.getResponseCode());
            System.out.println(readResponse(deleteConnection));
            deleteConnection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }
}
