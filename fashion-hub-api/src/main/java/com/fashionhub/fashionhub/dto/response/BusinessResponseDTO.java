package com.fashionhub.fashionhub.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessResponseDTO {

    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = true, unique = false)
    private String descripcion;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = true)
    private String website;

}

