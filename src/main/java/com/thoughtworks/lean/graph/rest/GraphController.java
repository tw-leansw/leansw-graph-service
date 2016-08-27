package com.thoughtworks.lean.graph.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
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

    @RequestMapping(value = "mget")
    public String mGet(@RequestBody String req) {
        return restTemplate.postForObject(kibanaUri + "elasticsearch/_mget?timeout=0&ignore_unavailable=true", getRequest(req), String.class);
    }

    @RequestMapping(value = "msearch")
    public String mSearch(@RequestBody String req) {
        return restTemplate.postForObject(kibanaUri + "elasticsearch/_msearch?timeout=0&ignore_unavailable=true", getRequest(req), String.class);
    }

    @RequestMapping(value = "index-pattern/search")
    public String indexPatternSearch(@RequestBody String req, @RequestParam("fields") String[] fields) {
        String url = UriComponentsBuilder.fromHttpUrl(kibanaUri + "elasticsearch/.kibana/index-pattern/_search").queryParam("fields", fields).build().toUriString();
        return restTemplate.postForObject(url, getRequest(req), String.class);
    }

    @RequestMapping(value = "mapping/field/source")
    public String mappingFieldSource(@RequestBody String req, @RequestParam("_") String _) {
        String url = UriComponentsBuilder.fromHttpUrl(kibanaUri + "elasticsearch/.kibana/_mapping/*/field/_source").queryParam("_", _).build().toUriString();
        return restTemplate.postForObject(url, getRequest(req), String.class);
    }


}
