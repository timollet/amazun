package fr.amu.services;

import fr.amu.Application;
import fr.amu.models.Product;
import fr.amu.models.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// à remplir selon l'interface montrée dan le PDF
// Pas nécessaire de créer réellement l'interface, c'est un indicatif pour les méthodes à mettre
@Service
public class ProductService{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ProductDAO productDAO;
    public void addProduct(Product product) {
        productDAO.add(product);
    }

    Product getProduct(int id) {
        return productDAO.findById(id);
    }

    public void removeProduct(int id) {
        Product product = productDAO.findById(id);
        productDAO.delete(product);
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    public List<Product> getCategoryProducts(String category) {
        return productDAO.findByCategory(category);
    }
}