package com.fashionhub.fashionhub.repository;

import com.fashionhub.fashionhub.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    boolean existsByName(String name);

    boolean existsByWebsite(String website);
    
    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByWebsiteAndIdNot(String website, Long id);
}
