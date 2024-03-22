package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import soa.entities.Categorie;
import soa.repository.CategorieRepository;

public class CategorieMetierImpl implements CategorieMetierInterface {

    // Injection de dépendance du repository
    @Autowired
    private CategorieRepository categorieRepository;

    // Implémentation de la méthode ajouterCategorie de l'interface
    @Override
    public void ajouterCategorie(Categorie c) {
        // Appel à la méthode save du repository pour ajouter la catégorie
        categorieRepository.save(c);
    }
}
