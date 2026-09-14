package com.fashionhub.fashionhub.controller;

import com.fashionhub.fashionhub.dto.request.BusinessCreateDTO;
import com.fashionhub.fashionhub.dto.request.BusinessUpdateDTO;
import com.fashionhub.fashionhub.dto.response.BusinessResponseDTO;
import com.fashionhub.fashionhub.service.BusinessService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    @PostMapping
    public BusinessResponseDTO create(@Valid @RequestBody BusinessCreateDTO request){
        return businessService.create(request);
    }

    @GetMapping("/{id}")
    public BusinessResponseDTO listById(@PathVariable Long id){
        return businessService.listById(id);
    }

    @GetMapping
    public List<BusinessResponseDTO> listAll(){
        return businessService.listAll();
    }

    @DeleteMapping("/{id}")
    public BusinessResponseDTO delete(@PathVariable Long id){
        return businessService.delete(id);
    }

    @PutMapping("/{id}")
    public BusinessResponseDTO update(@PathVariable Long id, @Valid @RequestBody BusinessUpdateDTO requestUpdate){
        return businessService.update(id, requestUpdate);
    }
}
