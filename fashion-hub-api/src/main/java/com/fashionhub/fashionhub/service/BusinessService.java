package com.fashionhub.fashionhub.service;

import com.fashionhub.fashionhub.dto.request.BusinessCreateDTO;
import com.fashionhub.fashionhub.dto.response.BusinessResponseDTO;
import com.fashionhub.fashionhub.exception.BusinessNameExists;
import com.fashionhub.fashionhub.exception.BusinessNotFound;
import com.fashionhub.fashionhub.exception.BusinessWebsiteAlreadyExists;
import com.fashionhub.fashionhub.model.Business;
import com.fashionhub.fashionhub.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;
    public BusinessResponseDTO create(BusinessCreateDTO businesC){

        if(businessRepository.existsByName(businesC.getName())){
            throw new BusinessNameExists("Ya existe un local con este nombre");
        }

        if(businessRepository.existsByWebsite(businesC.getWebsite())){
            throw new BusinessWebsiteAlreadyExists("ya existe un local con este sitio web");
        }

        Business newBusiness = new Business();

        newBusiness.setName(businesC.getName());
        newBusiness.setDescription(businesC.getDescripcion());
        newBusiness.setLogo(businesC.getLogo());
        newBusiness.setWebsite(businesC.getWebsite());

        Business savedBusiness = businessRepository.save(newBusiness);

        BusinessResponseDTO response = new BusinessResponseDTO();

        response.setName(savedBusiness.getName());
        response.setLogo(savedBusiness.getLogo());
        response.setWebsite(savedBusiness.getWebsite());
        response.setDescripcion(savedBusiness.getDescription());
        response.setId(savedBusiness.getId());

        return response;
    }

    public BusinessResponseDTO listById(Long id){
        Business business = businessRepository.findById(id).orElseThrow(() -> new BusinessNotFound("No se ha encontrado un local"));

        BusinessResponseDTO response = new BusinessResponseDTO();

        response.setName(business.getName());
        response.setLogo(business.getLogo());
        response.setWebsite(business.getWebsite());
        response.setDescripcion(business.getDescription());
        response.setId(business.getId());

        return response;
    }

    public List<BusinessResponseDTO> listAll(){
        return businessRepository.findAll().stream().map( business -> {
            BusinessResponseDTO response = new BusinessResponseDTO();

            response.setId(business.getId());
            response.setName(business.getName());
            response.setDescripcion(business.getDescription());
            response.setWebsite(business.getWebsite());
            response.setLogo(business.getLogo());

            return response;
        }).toList();
    }

    public BusinessResponseDTO delete(Long id){
        Business b = businessRepository.findById(id).orElseThrow(() -> new BusinessNotFound("No se ha encontrado ningun local"));

        businessRepository.delete(b);

        BusinessResponseDTO response = new BusinessResponseDTO();

        response.setId(b.getId());
        response.setName(b.getName());
        response.setDescripcion(b.getDescription());
        response.setLogo(b.getLogo());
        response.setWebsite(b.getWebsite());

        return response;
    }


}
