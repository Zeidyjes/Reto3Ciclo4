/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zorrillo.colonias.servicio;

import com.zorrillo.colonias.modelo.Fragance;
import com.zorrillo.colonias.repositorio.FraganceRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zeidy Johana Estupiñan S.
 */
@Service
public class UserFragance {
     @Autowired
    private FraganceRepositorio fraganceRepository;

    public List<Fragance> getAll() {
        return fraganceRepository.getAll();
    }

   public Optional<Fragance> getFragance(String reference) {
        return fraganceRepository.getFragance(reference);
    }

    public Fragance create(Fragance accesory) {
        if (accesory.getReference() == null) {
            return accesory;
        } else {
            return fraganceRepository.create(accesory);
        }
    }

    public Fragance update(Fragance accesory) {

        if (accesory.getReference() != null) {
            Optional<Fragance> accesoryDb = fraganceRepository.getFragance(accesory.getReference());
            if (!accesoryDb.isEmpty()) {
                
                if (accesory.getBrand()!= null) {
                    accesoryDb.get().setBrand(accesory.getBrand());
                }
                
                if (accesory.getCategory() != null) {
                    accesoryDb.get().setCategory(accesory.getCategory());
                }
                 if (accesory.getPresentation() != null) {
                    accesoryDb.get().setPresentation(accesory.getPresentation());
                }
                                
                if (accesory.getDescription() != null) {
                    accesoryDb.get().setDescription(accesory.getDescription());
                }
                if (accesory.getPrice() != 0.0) {
                    accesoryDb.get().setPrice(accesory.getPrice());
                }
                if (accesory.getQuantity() != 0) {
                    accesoryDb.get().setQuantity(accesory.getQuantity());
                }
                if (accesory.getPhotography() != null) {
                    accesoryDb.get().setPhotography(accesory.getPhotography());
                }
                accesoryDb.get().setAvailability(accesory.isAvailability());
                fraganceRepository.update(accesoryDb.get());
                return accesoryDb.get();
            } else {
                return accesory;
            }
        } else {
            return accesory;
        }
    }

    public boolean delete(String reference) {
        Boolean aBoolean = getFragance(reference).map(accesory -> {
            fraganceRepository.delete(accesory);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    

}
