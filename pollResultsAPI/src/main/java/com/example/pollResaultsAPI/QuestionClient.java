package com.example.pollResaultsAPI;

import com.example.pollResaultsAPI.model.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "questionAPI", url = "${questionAPI.url}")
public interface QuestionClient {
    @GetMapping("/{id}")
    QuestionDTO getQuestionDetails (@PathVariable ("id") int questionId);
}
