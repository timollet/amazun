package fr.amu.controllers;// à remplir
// aura l'annotation @Controller

import fr.amu.models.Product;
import fr.amu.models.ProductDAO;
import fr.amu.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ProductController
{
    @Autowired
    ServletContext context;
    @Autowired
    ProductService ps;


    @PostMapping("/add") // raccourci pour @RequestMapping( value = "/", method = RequestMethod.GET)
    public String add(Map<String, Object> model, @RequestParam(value = "title") String title ,
                      @RequestParam(value = "description") String description,
                      @RequestParam(value = "categorie") String categorie) {

        Product product = new Product(categorie,title,description);
        ps.addProduct(product);
        model.put("products", ps.getProducts() );
        return "homepage";
    }

    @PostMapping("/remove") // raccourci pour @RequestMapping( value = "/", method = RequestMethod.GET)
    public String remove(Map<String, Object> model, @RequestParam(value = "id") int id) {


        ps.removeProduct(id);
        model.put("products", ps.getProducts() );
        return "homepage";
    }

    @PostMapping("/category") // raccourci pour @RequestMapping( value = "/", method = RequestMethod.GET)
    public String remove(Map<String, Object> model, @RequestParam(value = "category") String categorie) {

        if(categorie.equals("all"))
        {
            model.put("products", ps.getProducts() );
        }
        else{

            model.put("products", ps.getCategoryProducts(categorie) );

        }
        return "homepage";
    }
}