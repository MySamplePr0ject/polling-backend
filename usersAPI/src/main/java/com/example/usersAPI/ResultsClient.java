package com.example.usersAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "resultsAPI", url = "${resultsAPI.url}")
public interface ResultsClient {
    @DeleteMapping("/deleteUser/{id}")
    String deleteUserResponses (@PathVariable ("id") int id, @RequestHeader ("auth") String phrase);
}
