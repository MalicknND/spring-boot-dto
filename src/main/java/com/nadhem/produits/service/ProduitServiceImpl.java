package com.nadhem.produits.service;

import java.util.List;
import java.util.stream.Collectors;

import com.nadhem.produits.dto.ProduitDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadhem.produits.entities.Categorie;
import com.nadhem.produits.entities.Produit;
import com.nadhem.produits.repos.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProduitDTO saveProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public ProduitDTO updateProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);

    }

    @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);

    }

    @Override
    public ProduitDTO getProduit(Long id) {
        return convertEntityToDto(produitRepository.findById(id).get());

    }

    @Override
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        //OU BIEN
                /*List<Produit> prods = produitRepository.findAll();
                  List<ProduitDTO> listprodDto = new ArrayList<>(prods.size());
                    for (Produit p : prods)
                        listprodDto.add(convertEntityToDto(p));
                    return listprodDto;*/
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return produitRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return produitRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return produitRepository.findByOrderByNomProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return produitRepository.trierProduitsNomsPrix();
    }

    @Override
    public ProduitDTO convertEntityToDto(Produit p) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProduitDTO produitDTO = modelMapper.map(p, ProduitDTO.class);
        return produitDTO;

//        ProduitDTO dto = new ProduitDTO();
//        dto.setIdProduit(p.getIdProduit());
//        dto.setNomProduit(p.getNomProduit());
//        dto.setPrixProduit(p.getPrixProduit());
//        dto.setCategorie(p.getCategorie());
//        return dto;

//        // avec le builder et lOMBOK
//        return ProduitDTO.builder().idProduit(p.getIdProduit())
//                .nomProduit(p.getNomProduit())
////                .prixProduit(p.getPrixProduit())
//                .dateCreation(p.getDateCreation())
////                .categorie(p.getCategorie())
//                .nomCat(p.getCategorie().getNomCat())
//                .build();

    }

    @Override
    public Produit convertDtoToEntity(ProduitDTO dto) {

        Produit product = new Produit();
        product = modelMapper.map(dto, Produit.class);
        return product;

//        Produit produit = new Produit();
//        produit.setIdProduit(dto.getIdProduit());
//        produit.setNomProduit(dto.getNomProduit());
//        produit.setDateCreation(dto.getDateCreation());
//        produit.setCategorie(dto.getCategorie());
//        return produit;
    }


}
