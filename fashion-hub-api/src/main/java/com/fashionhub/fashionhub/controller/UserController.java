package com.fashionhub.fashionhub.controller;

import com.fashionhub.fashionhub.dto.request.UsuarioCreateDTO;
import com.fashionhub.fashionhub.dto.response.UsuarioResponseDTO;
import com.fashionhub.fashionhub.model.User;
import com.fashionhub.fashionhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UsuarioResponseDTO save(@RequestBody UsuarioCreateDTO request){
        return userService.save(request);
    }

    @GetMapping
    public List<User> listAll(){
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public User listById(@PathVariable  Long id){
        return userService.listById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User u){
        return userService.updateUser(id, u);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable  Long id){
        return userService.deleteUser(id);
    }
}
