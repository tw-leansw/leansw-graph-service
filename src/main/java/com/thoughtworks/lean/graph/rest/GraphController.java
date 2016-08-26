package com.thoughtworks.lean.graph.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by yongliuli on 8/26/16.
 */
@RequestMapping(path = {"/api/graph"})
@RestController
public class GraphController {

    private HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.set("charset", "utf-8");
        headers.set("kbn-version", "4.4.2");
        headers.set("kbn-name", "kibana");
        return headers;
    }

    private HttpEntity<String> getRequest(String req) {
        return new HttpEntity<>(req, buildHttpHeaders());
    }

    RestTemplate restTemplate = new RestTemplate();
    @Value("${kibana.uri}")
    String kibanaUri = "http://deliflow-kibana-server:8088/";

    @RequestMapping(value = "get")
    public String mGet(@RequestBody String req) {
        return restTemplate.postForObject(kibanaUri + "elasticsearch/_mget?timeout=0&ignore_unavailable=true", getRequest(req), String.class);
    }

    @RequestMapping(value = "search")
    public String mSearch(@RequestBody String req) {
        return restTemplate.postForObject(kibanaUri + "elasticsearch/_msearch?timeout=0&ignore_unavailable=true", getRequest(req), String.class);
    }
}
