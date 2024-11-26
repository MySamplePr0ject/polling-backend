package com.example.pollResaultsAPI;

import com.example.pollResaultsAPI.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userAPI", url = "${userAPI.url}")
public interface UserClient {
    @GetMapping("/{id}")
    UserDTO getUserDetails (@PathVariable int id);
}
