package ru.maxima.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TestApi {
    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();

        String url = "https://reqres.in";
        String getEndPoint = "/api/users";
        String postEndPoint = "/api/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("name", "Aram");
        jsonData.put("job", "Java-developer");

        PersonToApi personToApi = new PersonToApi();
        personToApi.setName("Andrey");
        personToApi.setJob("Kotlin-developer");

        HttpEntity<PersonToApi> request = new HttpEntity<>(personToApi, headers);

        PersonToApi responsePersonToApi = template.postForObject(url + postEndPoint, request, PersonToApi.class);
        System.out.println(responsePersonToApi);
    }
}
