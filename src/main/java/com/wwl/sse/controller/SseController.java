package com.wwl.sse.controller;


import com.wwl.sse.model.gptParameter;
import com.wwl.sse.model.gptRole;
import com.wwl.sse.utils.JsonConvertUtil;
import com.wwl.sse.utils.SseHttpClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/sse")
public class SseController {

    /**
     * test
     *  todo: 未实现上下文和用户token等，仅作为sse test
     *
     * @param response
     * @param prompt
     * @return
     */
    @GetMapping("/test")
    public SseEmitter streamSSE(HttpServletResponse response, String prompt) {
        gptRole chatInfo = new gptRole() {{
            setContent(prompt);
            setRole("user");
        }};

        gptParameter chatParameter = new gptParameter() {{
            setMessages(List.of(chatInfo));
            setStream(true);
            setTemperature(0.7F);
            setMax_tokens(3000);
            setTop_p(0.95F);
            setFrequency_penalty(0);
            setPresence_penalty(0);
        }};
        response.setHeader("Content-Type", "text/event-stream; charset=UTF-8");
        return SseHttpClientUtil.sendPostRequestWithSse("https://api.openai.com/v1/engines/davinci-codex/completions", "SK-**********",
                JsonConvertUtil.objectToJson(chatParameter));
    }
}
