package com.wwl.sse.utils;


import com.wwl.sse.model.sse.ChoicesWrapper;
import com.wwl.sse.model.sse.Data;
import com.wwl.sse.model.sse.DetailContent;
import com.wwl.sse.model.sse.SseEmitterUTF8;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author wwl
 */


public class SseHttpClientUtil {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static SseEmitter sendPostRequestWithSse(String url, String token, String requestBody) {
        SseEmitterUTF8 emitter = new SseEmitterUTF8(10 * 60 * 1000L);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Accept-Charset", "UTF-8")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    try {
                        String rJson = response.body().trim().replaceAll("data: ", "").replaceAll("[DONE]", ""); // remove "data: " and "[DONE]"

                        String[] jsons = rJson.split("\n");
                        if (jsons.length == 0) {
                            emitter.send(SseEmitter.event().name("message").data("data:无data返回", MediaType.APPLICATION_JSON));

                        }
                        for (String json : jsons) {
                            try {
                                if (StringUtils.isBlank(json) || json.equals("[]") || json.contains("DONE")) {
                                    continue;
                                }
                                Data data = JsonConvertUtil.jsonToObject(json, Data.class);
                                if (data == null) {
                                    continue;
                                }
                                for (ChoicesWrapper choice : data.getChoices()) {
                                    DetailContent delta = choice.getDetailContent();
                                    if (delta != null && !StringUtils.isBlank(delta.getContent())) {
                                        emitter.send(SseEmitter.event().name("message").data(delta.getContent(), MediaType.APPLICATION_JSON));
                                        //效果测试，生产去掉
                                        Thread.sleep(100);
                                    }
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        emitter.complete();
                    } catch (Exception ex) {
                        emitter.completeWithError(ex);
                    }
                })
                .exceptionally(ex -> {
                    emitter.completeWithError(ex);
                    return null;
                });

        return emitter;
    }
}

