package com.fashionhub.fashionhub.service;

import com.fashionhub.fashionhub.dto.request.UsuarioCreateDTO;
import com.fashionhub.fashionhub.dto.response.UsuarioResponseDTO;
import com.fashionhub.fashionhub.enums.Role;
import com.fashionhub.fashionhub.exception.UserInexistenteException;
import com.fashionhub.fashionhub.model.User;
import com.fashionhub.fashionhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioResponseDTO save(UsuarioCreateDTO userDTO){
        User newUser = new User();

        newUser.setUsername(userDTO.getUser());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPasswordHash(
                passwordEncoder.encode(userDTO.getPassword())
        );
        newUser.setRole(Role.USER);

        userRepository.save(newUser);

        UsuarioResponseDTO response = new UsuarioResponseDTO();

        response.setId(newUser.getId());
        response.setUsername(newUser.getUsername());
        response.setEmail(newUser.getEmail());
        response.setRole(newUser.getRole());
        response.setCreatedAt(newUser.getCreatedAt());

        return response;
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User listById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserInexistenteException("Usuario inexistente"));
    }

    @Transactional
    public User updateUser(Long id, User u){
        User usuario = userRepository.findById(id).orElseThrow(() -> new UserInexistenteException("Usuario inexistente"));

        usuario.setEmail(u.getEmail());
        usuario.setUsername(u.getUsername());
        usuario.setPasswordHash(u.getPasswordHash());

        return userRepository.save(usuario);
    }

    public User deleteUser(Long id){
        User u = userRepository.findById(id).orElseThrow(() -> new UserInexistenteException("Usuario inexistente"));
        userRepository.delete(u);
        return u;
    }
}
