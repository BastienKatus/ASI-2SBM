package com.example.Controller;

import com.example.Model.CardReference;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CardRefRepository extends CrudRepository<CardReference, Integer> {
    @Transactional
    default void addPokemonStart() {
        save(new CardReference("Feu", "Lézard de feu", "Salamèche", "url_image_salamèche", "Salamèche", "small_url_image_salamèche"));
        save(new CardReference("Feu", "Flamme brillante", "Reptincel", "url_image_reptincel", "Reptincel", "small_url_image_reptincel"));
        save(new CardReference("Feu", "Flamme rageuse", "Dracaufeu", "url_image_dracaufeu", "Dracaufeu", "small_url_image_dracaufeu"));
        save(new CardReference("Eau", "Tortue mignonne", "Carapuce", "url_image_carapuce", "Carapuce", "small_url_image_carapuce"));
        save(new CardReference("Eau", "Canon à eau", "Carabaffe", "url_image_carabaffe", "Carabaffe", "small_url_image_carabaffe"));
        save(new CardReference("Eau", "Tortue enragée", "Tortank", "url_image_tortank", "Tortank", "small_url_image_tortank"));
        save(new CardReference("Plante", "Graine de vie", "Bulbizarre", "url_image_bulbizarre", "Bulbizarre", "small_url_image_bulbizarre"));
        save(new CardReference("Plante", "Fleur épanouie", "Herbizarre", "url_image_herbizarre", "Herbizarre", "small_url_image_herbizarre"));
        save(new CardReference("Plante", "Fleur géante", "Florizarre", "url_image_florizarre", "Florizarre", "small_url_image_florizarre"));
        save(new CardReference("Insecte", "Chenille vorace", "Chenipan", "url_image_chenipan", "Chenipan", "small_url_image_chenipan"));
        save(new CardReference("Insecte", "Cocon protecteur", "Chrysacier", "url_image_chrysacier", "Chrysacier", "small_url_image_chrysacier"));
        save(new CardReference("Insecte", "Papillon éclatant", "Papilusion", "url_image_papilusion", "Papilusion", "small_url_image_papilusion"));
        save(new CardReference("Normal", "Souris rapide", "Rattata", "url_image_rattata", "Rattata", "small_url_image_rattata"));
        save(new CardReference("Normal", "Rat enragé", "Rattatac", "url_image_rattatac", "Rattatac", "small_url_image_rattatac"));
        save(new CardReference("Sol", "Taupe féroce", "Taupiqueur", "url_image_taupiqueur", "Taupiqueur", "small_url_image_taupiqueur"));
        save(new CardReference("Sol", "Tunnelier massif", "Triopikeur", "url_image_triopikeur", "Triopikeur", "small_url_image_triopikeur"));
        save(new CardReference("Vol", "Oisillon timide", "Piafabec", "url_image_piafabec", "Piafabec", "small_url_image_piafabec"));
        save(new CardReference("Vol", "Oiseau féroce", "Rapasdepic", "url_image_rapasdepic", "Rapasdepic", "small_url_image_rapasdepic"));
        save(new CardReference("Poison", "Petit serpent", "Abo", "url_image_abo", "Abo", "small_url_image_abo"));
        save(new CardReference("Poison", "Cobra en colère", "Arbok", "url_image_arbok", "Arbok", "small_url_image_arbok"));
        save(new CardReference("Electrique", "Souris électrique", "Pikachu", "url_image_pikachu", "Pikachu", "small_url_image_pikachu"));
        save(new CardReference("Electrique", "Souris éclair", "Raichu", "url_image_raichu", "Raichu", "small_url_image_raichu"));
        save(new CardReference("Sol", "Scarabée furtif", "Sabelette", "url_image_sabelette", "Sabelette", "small_url_image_sabelette"));
        save(new CardReference("Sol", "Reptile rapide", "Saberoum", "url_image_saberoum", "Saberoum", "small_url_image_saberoum"));
        save(new CardReference("Combat", "Poing de feu", "Nidoran♀", "url_image_nidoran♀", "Nidoran♀", "small_url_image_nidoran♀"));
        save(new CardReference("Combat", "Double poing", "Nidorina", "url_image_nidorina", "Nidorina", "small_url_image_nidorina"));
        save(new CardReference("Combat", "Poing venimeux", "Nidoqueen", "url_image_nidoqueen", "Nidoqueen", "small_url_image_nidoqueen"));
        save(new CardReference("Combat", "Poing électrique", "Nidoran♂", "url_image_nidoran♂", "Nidoran♂", "small_url_image_nidoran♂"));
        save(new CardReference("Combat", "Double coup", "Nidorino", "url_image_nidorino", "Nidorino", "small_url_image_nidorino"));
        save(new CardReference("Combat", "Poing perçant", "Nidoking", "url_image_nidoking", "Nidoking", "small_url_image_nidoking"));
        save(new CardReference("Vol", "Chenille soyeuse", "Mélofée", "url_image_melofee", "Mélofée", "small_url_image_melofee"));
        save(new CardReference("Vol", "Mélodie apaisante", "Mélodelfe", "url_image_melodelfe", "Mélodelfe", "small_url_image_melodelfe"));
        save(new CardReference("Eau", "Crabe dur", "Krabby", "url_image_krabby", "Krabby", "small_url_image_krabby"));
        save(new CardReference("Eau", "Pince puissante", "Krabboss", "url_image_krabboss", "Krabboss", "small_url_image_krabboss"));
        save(new CardReference("Insecte", "Cocoon fragile", "Mimitoss", "url_image_mimitoss", "Mimitoss", "small_url_image_mimitoss"));
        save(new CardReference("Insecte", "Papillon poudré", "Aéromite", "url_image_aeromite", "Aéromite", "small_url_image_aeromite"));
        save(new CardReference("Normal", "Fureur féline", "Férosinge", "url_image_ferosinge", "Férosinge", "small_url_image_ferosinge"));
        save(new CardReference("Combat", "Poing de fer", "Colossinge", "url_image_colossinge", "Colossinge", "small_url_image_colossinge"));
        save(new CardReference("Normal", "Chauve-souris", "Nosferapti", "url_image_nosferapti", "Nosferapti", "small_url_image_nosferapti"));
        save(new CardReference("Vol", "Chauve-souris vénéneuse", "Nosferalto", "url_image_nosferalto", "Nosferalto", "small_url_image_nosferalto"));
        save(new CardReference("Normal", "Chiot courageux", "Mystherbe", "url_image_mystherbe", "Mystherbe", "small_url_image_mystherbe"));
        save(new CardReference("Plante", "Fleur odeur", "Ortide", "url_image_ortide", "Ortide", "small_url_image_ortide"));
        save(new CardReference("Plante", "Fleur géante", "Rafflesia", "url_image_rafflesia", "Rafflesia", "small_url_image_rafflesia"));
        save(new CardReference("Poison", "Lézard de boue", "Paras", "url_image_paras", "Paras", "small_url_image_paras"));
        save(new CardReference("Poison", "Champignon", "Parasect", "url_image_parasect", "Parasect", "small_url_image_parasect"));
        save(new CardReference("Insecte", "Abeille butineuse", "Mimitoss", "url_image_mimitoss", "Mimitoss", "small_url_image_mimitoss"));
        save(new CardReference("Insecte", "Papillon poudré", "Aéromite", "url_image_aeromite", "Aéromite", "small_url_image_aeromite"));
        save(new CardReference("Sol", "Crabe géant", "Krabby", "url_image_krabby", "Krabby", "small_url_image_krabby"));
        save(new CardReference("Sol", "Pince puissante", "Krabboss", "url_image_krabboss", "Krabboss", "small_url_image_krabboss"));
        save(new CardReference("Electrique", "Souris électrique", "Pikachu", "url_image_pikachu", "Pikachu", "small_url_image_pikachu"));
    };
}
