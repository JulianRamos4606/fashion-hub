package com.fashionhub.fashionhub.controller;

import com.fashionhub.fashionhub.dto.request.UsuarioCreateDTO;
import com.fashionhub.fashionhub.dto.request.UsuarioUpdateDTO;
import com.fashionhub.fashionhub.dto.response.UsuarioResponseDTO;
import com.fashionhub.fashionhub.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public List<UsuarioResponseDTO> listAll(){
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO listById(@PathVariable  Long id){
        return userService.listById(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUser(@PathVariable Long id, @RequestBody UsuarioUpdateDTO u){
        return userService.updateUser(id, u);
    }

    @DeleteMapping("/{id}")
    public UsuarioResponseDTO deleteUser(@PathVariable  Long id){
        return userService.deleteUser(id);
    }
}
