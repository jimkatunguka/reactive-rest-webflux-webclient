package com.jim.katunguka.reactiverestwebflux.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
public class WidgetService {
    private final WebClient webClient;
    private Logger logger = LogManager.getLogger();

    public WidgetService(WebClient webClient){
        this.webClient = webClient;
    }
    String url = "http://localhost:8087/widgets/5";

    public String getDescription(){
        String desc = "";
        try{
            JsonNode retrievedNode = callWidgetService();
            desc = retrievedNode.get("description").asText();
        }catch (Exception e){
            logger.info("failed to get description");
        }
        return desc;
    }

    private JsonNode callWidgetService(){
        JsonNode node = null;
        try {
             node = webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
             logger.info("service called succcessfully");
        }catch (WebClientException wce){
            logger.error("webclientexception received" + wce.getMessage());
        }
        catch(Exception e){
            logger.error("failed to call service");
        }
        return node;
    }
}
