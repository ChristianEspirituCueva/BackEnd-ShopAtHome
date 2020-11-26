package com.pe.shopathome.converters;

import com.pe.shopathome.dto.StoreDTO;
import com.pe.shopathome.entity.Store;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StoreConverter extends AbstractConverter<Store,StoreDTO> {
    @Override
    public StoreDTO fromEntity(Store entity){
        if(entity==null)return null;
        return  StoreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .RUC(entity.getRUC())
                .phone(entity.getPhone())
                .country(entity.getCountry())
                .city(entity.getCity())
                .address(entity.getAddress())
                .build();
    }
    @Override
    public Store fromDTO(StoreDTO dto){
        if(dto==null)return null;
        return Store.builder()
                .id(dto.getId())
                .name(dto.getName())
                .RUC(dto.getRUC())
                .phone(dto.getPhone())
                .country(dto.getCountry())
                .city(dto.getCity())
                .address(dto.getAddress())
                .build();
    }
}
