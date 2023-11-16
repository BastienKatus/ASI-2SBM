package com.example.Controller;

import com.example.Model.CardReference;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CardRefRepository extends CrudRepository<CardReference, Integer> {
    @Transactional
    default void addPokemonStart() {
        save(new CardReference("Bulbizarre", "Graine", "Plante", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png", "small_url_image_bulbizarre"));
        save(new CardReference("Herbizarre", "Graine", "Plante", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png", "small_url_image_herbizarre"));
        save(new CardReference("Florizarre", "Fleur", "Plante", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png", "small_url_image_florizarre"));
        save(new CardReference("Salamèche", "Lézard de feu", "Feu", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png", "small_url_image_salamèche"));
        save(new CardReference("Reptincel", "Flamme", "Feu", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png", "small_url_image_reptincel"));
        save(new CardReference("Dracaufeu", "Flamme", "Feu", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png", "small_url_image_dracaufeu"));
        save(new CardReference("Carapuce", "Tortue", "Eau", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png", "small_url_image_carapuce"));
        save(new CardReference("Carabaffe", "Tortue", "Eau", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/008.png", "small_url_image_carabaffe"));
        save(new CardReference("Tortank", "Tortue", "Eau", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/009.png", "small_url_image_tortank"));
        save(new CardReference("Chenipan", "Chenille", "Insecte", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/010.png", "small_url_image_chenipan"));
        save(new CardReference("Chrysacier", "Cocon", "Insecte", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/011.png", "small_url_image_chrysacier"));
        save(new CardReference("Papilusion", "Papillon", "Insecte", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/012.png", "small_url_image_papilusion"));
        save(new CardReference("Aspicot", "Ver", "Insecte", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/013.png", "small_url_image_aspicot"));
        save(new CardReference("Coconfort", "Cocon", "Insecte", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/014.png", "small_url_image_coconfort"));
        save(new CardReference("Dardargnan", "Abeille", "Insecte", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/015.png", "small_url_image_dardargnan"));
        save(new CardReference("Roucool", "Oiseau", "Normal", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/016.png", "small_url_image_roucool"));
        save(new CardReference("Roucoups", "Oiseau", "Normal", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/017.png", "small_url_image_roucoups"));
        save(new CardReference("Roucarnage", "Oiseau", "Normal", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/018.png", "small_url_image_roucarnage"));
        save(new CardReference("Rattata", "Souris", "Normal", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/019.png", "small_url_image_rattata"));
        save(new CardReference("Rattatac", "Rat", "Normal", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/020.png", "small_url_image_rattatac"));
        save(new CardReference("Piafabec", "Oiseau", "Normal", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/021.png", "small_url_image_piafabec"));
        save(new CardReference("Rapasdepic", "Oiseau", "Normal", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/022.png", "small_url_image_rapasdepic"));
        save(new CardReference("Abo", "Serpent", "Poison", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/023.png", "small_url_image_abo"));
        save(new CardReference("Arbok", "Cobra", "Poison", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/024.png", "small_url_image_arbok"));
        save(new CardReference("Pikachu", "Souris", "Électrique", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png", "small_url_image_pikachu"));
        save(new CardReference("Raichu", "Souris", "Électrique", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/026.png", "small_url_image_raichu"));
        save(new CardReference("Sabelette", "Souris", "Sol", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/027.png", "small_url_image_sabelette"));
        save(new CardReference("Sablaireau", "Souris", "Sol", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/028.png", "small_url_image_sablaireau"));
        save(new CardReference("Nidoran♀", "Venin", "Poison", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/029.png♀", "small_url_image_nidoran♀"));
        save(new CardReference("Nidorina", "Venin", "Poison", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/030.png", "small_url_image_nidorina"));
        save(new CardReference("Nidoqueen", "Perce-roi", "Poison", "Sol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/031.png", "small_url_image_nidoqueen"));
        save(new CardReference("Nidoran♂", "Venin", "Poison", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/032.png♂", "small_url_image_nidoran♂"));
        save(new CardReference("Nidorino", "Venin", "Poison", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/033.png", "small_url_image_nidorino"));
        save(new CardReference("Nidoking", "Perce-roi", "Poison", "Sol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/034.png", "small_url_image_nidoking"));
        save(new CardReference("Mélofée", "Fée", "Fée", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/035.png", "small_url_image_mélofée"));
        save(new CardReference("Mélodelfe", "Fée", "Fée", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/036.png", "small_url_image_mélodelfe"));
        save(new CardReference("Goupix", "Renard", "Feu", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/037.png", "small_url_image_goupix"));
        save(new CardReference("Feunard", "Renard", "Feu", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/038.png", "small_url_image_feunard"));
        save(new CardReference("Rondoudou", "Ballon", "Normal", "Fée", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/039.png", "small_url_image_rondoudou"));
        save(new CardReference("Grodoudou", "Ballon", "Normal", "Fée", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/040.png", "small_url_image_grodoudou"));
        save(new CardReference("Nosferapti", "Chauve-souris", "Poison", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/041.png", "small_url_image_nosferapti"));
        save(new CardReference("Nosferalto", "Chauve-souris", "Poison", "Vol", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/042.png", "small_url_image_nosferalto"));
        save(new CardReference("Mystherbe", "Mauvaises herbes", "Plante", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/043.png", "small_url_image_mystherbe"));
        save(new CardReference("Ortide", "Mauvaises herbes", "Plante", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/044.png", "small_url_image_ortide"));
        save(new CardReference("Rafflesia", "Fleur", "Plante", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/045.png", "small_url_image_rafflesia"));
        save(new CardReference("Paras", "Champignon", "Insecte", "Plante", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/046.png", "small_url_image_paras"));
        save(new CardReference("Parasect", "Champignon", "Insecte", "Plante", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/047.png", "small_url_image_parasect"));
        save(new CardReference("Mimitoss", "Chenille", "Insecte", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/048.png", "small_url_image_mimitoss"));
        save(new CardReference("Aéromite", "Papillon", "Insecte", "Poison", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/049.png", "small_url_image_aéromite"));
        save(new CardReference("Taupiqueur", "Taupe", "Sol", "null", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/050.png", "small_url_image_taupiqueur"));
    }
}
