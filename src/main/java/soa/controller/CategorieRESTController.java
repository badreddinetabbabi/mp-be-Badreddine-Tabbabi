package soa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import soa.entities.Categorie;
import soa.repository.CategorieRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategorieRESTController {

    @Autowired
    private CategorieRepository categorieRepository;

    // Accueil du service
    @GetMapping(value = "/index")
    public String accueil() {
        return "Bienvenue au service Web REST 'categories'.....";
    }

    // Récupérer toutes les catégories
    @GetMapping(value = "/")
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    // Récupérer une catégorie par son ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Categorie getCategorie(@PathVariable Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    // Ajouter une nouvelle catégorie
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Categorie saveCategorie(@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Mettre à jour une catégorie existante
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Categorie updateCategorie(@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie); // Sauvegarde la catégorie mise à jour
    }

    // Supprimer une catégorie par son ID
    @DeleteMapping(value = "/{id}")
    public void deleteCategorie(@PathVariable Long id) {
        categorieRepository.deleteById(id);
    }
}
