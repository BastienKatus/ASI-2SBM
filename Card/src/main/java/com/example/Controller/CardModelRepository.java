package com.example.Controller;

import com.example.CommonLib.UserDTO;
import com.example.Model.CardModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardModelRepository extends CrudRepository<CardModel, Integer> {
    // Méthode de requête personnalisée pour récupérer toutes les cartes avec userId = 0
    @Query("SELECT c FROM CardModel c WHERE c.isSell = False")
    List<CardModel> findAllToSell();

}
