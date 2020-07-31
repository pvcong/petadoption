package com.ck.services;

import com.ck.data.RescueOrderEntity;
import com.ck.dto.PetDTO;
import com.ck.dto.RescueOrderDTO;

import java.util.List;

public interface RescueOrderService {
    public void save(RescueOrderDTO rescueOrderDTO);
    public void update(RescueOrderDTO rescueOrderDTO);
    public void addPet(PetDTO petDTO);
    public void removePet(PetDTO petDTO);

    public void delete(List<RescueOrderDTO> rescueOrderDTOS);

    public RescueOrderDTO findById(Integer id);
    Object[] findPetDontOrder(PetDTO petDTO,Integer limit, Integer offset);
    public Object[] findPetOfEmployye(Integer employeeId, Integer limit, Integer offset);
    public Object[] findRescueOrderByPropertiesAdmin(RescueOrderDTO rescueOrderDTO,Integer limit,Integer offset);
}
