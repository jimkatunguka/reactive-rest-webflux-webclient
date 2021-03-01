package com.jim.katunguka.reactiverestwebflux.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.net.URL;

@Service
public class WidgetService {
    private final WebClient webClient;
    private Logger logger = LogManager.getLogger();
    private URL url;

    public WidgetService(WebClient webClient, @Value("http://localhost:8087/widgets/") URL url){
        this.webClient = webClient;
        this.url = url;
    }

    public String getDescription(int num){
        String desc = "";
        try{
            JsonNode retrievedNode = callWidgetService(num);
            desc = retrievedNode.get("description").asText();
        }catch (Exception e){
            logger.info("failed to get description " + e);
        }
        return desc;
    }

    private JsonNode callWidgetService(int num){
        JsonNode node = null;
        try {
             node = webClient
                    .get()
                    .uri(url + "" + num)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
             logger.info("service called succcessfully");
        }catch (WebClientException wce){
            logger.error("WebClientException received: " + wce.getMessage());
        }
        catch(Exception e){
            logger.error("failed to call service");
        }
        return node;
    }
}
