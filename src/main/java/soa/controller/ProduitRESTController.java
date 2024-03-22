package soa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.entities.Produit;
import soa.repository.ProduitRepository;

@RestController // pour déclarer un service web de type REST
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/produits")  //    http://localhost:8080/produits
public class ProduitRESTController {
    @Autowired // pour l'injection de dépendances
    private ProduitRepository produitRepos;

    //  Message d'accueil
    //  http://localhost:8080/produits/index  (GET)
    @GetMapping(value ="/index" )
    public String accueil() {
        return "BienVenue au service Web REST 'produits'.....";
    }

    //  Afficher la liste des produits
    //  http://localhost:8080/produits/ (GET)

    @GetMapping(
            // spécifier le path de la méthode
            value= "/",
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public  List<Produit> getAllProduits() {
        return produitRepos.findAll();

    }

    //  Afficher un produit en spécifiant son 'id'
    //  http://localhost:8080/produits/{id} (GET)
    @GetMapping(
            // spécifier le path de la méthode qui englobe un paramètre
            value= "/{id}" ,
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public Produit getProduit(@PathVariable Long id) {
        Produit p =produitRepos.findById(id).get();
        return p;
    }

    // Supprimer un produit par 'id' avec la méthode 'GET'
    //  http://localhost:8080/produits/delete/{id}  (GET)
    @GetMapping(
            // spécifier le path de la méthode
            value = "/delete/{id}")
    public void deleteProduit(@PathVariable Long id)
    {
        produitRepos.deleteById(id);
    }

    //  ajouter un produit avec la méthode "POST"
    //  http://localhost:8080/produits/   (POST)
    @PostMapping(
            // spécifier le path de la méthode
            value = "/"  ,
            //spécifier le format de retour
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public Produit saveProduit(@RequestBody Produit p)
    {
        return produitRepos.save(p);
    }

//  modifier un produit avec la méthode "PUT"
//  http://localhost:8080/produits/{id}   (PUT)
@PutMapping("/{id}")
public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit nouveauProduit) {
    Produit existingProduit = produitRepos.findById(id)
            .orElseThrow();

    // Mettre à jour les propriétés du produit existant avec les nouvelles valeurs
    existingProduit.setDesignation(nouveauProduit.getDesignation());
    existingProduit.setPrix(nouveauProduit.getPrix());
    existingProduit.setCategorie(nouveauProduit.getCategorie());
    // Ajoutez d'autres propriétés à mettre à jour selon vos besoins

    // Enregistrer les modifications dans la base de données
    Produit updatedProduit = produitRepos.save(existingProduit);

    // Retourner une réponse avec le produit mis à jour et un statut 200 (OK)
    return ResponseEntity.ok(updatedProduit);
}

    // Supprimer un produit  avec la méthode 'DELETE'
    //  http://localhost:8080/produits/   (DELETE)
    // Supprimer un produit par ID avec DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit1(@PathVariable Long id) {
        Produit produit = produitRepos.findById(id).orElse(null);
        if (produit != null) {
            produitRepos.delete(produit);
            return ResponseEntity.noContent().build(); // Retourne un code 204 (No Content) en cas de succès
        } else {
            return ResponseEntity.notFound().build(); // Retourne un code 404 (Not Found) si le produit n'est pas trouvé
        }
    }
}
