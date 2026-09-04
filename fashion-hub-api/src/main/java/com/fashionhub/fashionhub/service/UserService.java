package com.fashionhub.fashionhub.service;

import com.fashionhub.fashionhub.dto.request.UsuarioCreateDTO;
import com.fashionhub.fashionhub.dto.request.UsuarioUpdateDTO;
import com.fashionhub.fashionhub.dto.response.UsuarioResponseDTO;
import com.fashionhub.fashionhub.enums.Role;
import com.fashionhub.fashionhub.exception.EmailExistenteException;
import com.fashionhub.fashionhub.exception.UserInexistenteException;
import com.fashionhub.fashionhub.exception.UsernameExistenteException;
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

        if (userRepository.existsByUsername(userDTO.getUsername())){
            throw new UsernameExistenteException("ya existe este nombre de usuario");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())){
            throw new EmailExistenteException("ya hay una cuenta asociada a este email");
        }

        User newUser = new User();

        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPasswordHash(
                passwordEncoder.encode(userDTO.getPassword())
        );
        newUser.setRole(Role.USER);

        User savedUser = userRepository.save(newUser);

        UsuarioResponseDTO response = new UsuarioResponseDTO();

        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;
    }

    public List<UsuarioResponseDTO> listAll(){
        return userRepository.findAll().stream().map(user -> {
            UsuarioResponseDTO response = new UsuarioResponseDTO();

            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            response.setCreatedAt(user.getCreatedAt());

            return response;
        }).toList();
    }

    public UsuarioResponseDTO listById(Long id){
        User usuario = userRepository.findById(id).orElseThrow(() -> new UserInexistenteException("Usuario inexistente"));

        UsuarioResponseDTO response = new UsuarioResponseDTO();

        response.setId(usuario.getId());
        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setRole(usuario.getRole());
        response.setCreatedAt(usuario.getCreatedAt());

        return response;
    }

    @Transactional
    public UsuarioResponseDTO updateUser(Long id, UsuarioUpdateDTO u){
        User usuario = userRepository.findById(id).orElseThrow(() -> new UserInexistenteException("Usuario inexistente"));

        if (userRepository.existsByUsernameAndIdNot(u.getUsername(), id)){
            throw new UsernameExistenteException("ya existe este nombre de usuario");
        }
        if(userRepository.existsByEmailAndIdNot(u.getEmail(), id)){
            throw new EmailExistenteException("ya hay una cuenta asociada a este email");
        }

        usuario.setUsername(u.getUsername());
        usuario.setEmail(u.getEmail());
        if (u.getPassword() != null && !u.getPassword().isBlank()) {
            usuario.setPasswordHash(passwordEncoder.encode(u.getPassword()));
        }
        userRepository.save(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO();

        response.setId(usuario.getId());
        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setRole(usuario.getRole());
        response.setCreatedAt(usuario.getCreatedAt());

        return response;
    }

    public UsuarioResponseDTO deleteUser(Long id){
        User u = userRepository.findById(id).orElseThrow(() -> new UserInexistenteException("Usuario inexistente"));
        userRepository.delete(u);
        UsuarioResponseDTO response = new UsuarioResponseDTO();

        response.setId(u.getId());
        response.setUsername(u.getUsername());
        response.setEmail(u.getEmail());
        response.setRole(u.getRole());
        response.setCreatedAt(u.getCreatedAt());

        return response;
    }
}
