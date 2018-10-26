package fr.amu.models;
// à remplir
// implémente l'interface ProductDAO
// aura les annotations @Transactional et @Repository
// Jdbctemplate sera injecté en @Autowired

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO
{
    @Autowired
    // injection de dépendance pour un jdbctemplate global récupérant ses paramètres dans src/main/resources/application.properties
    JdbcTemplate jt;
    @Override
    public void add(Product product) {
        String sql = "INSERT INTO products (category,TITLE,description) values(?,?,?)";
        jt.update(sql, product.getCategory(), product.getTitle(), product.getDescription() );
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET (category =  ? ,Producttitle= ? ,description= ? )";
        jt.update(sql, product.getCategory(), product.getTitle(), product.getDescription());

    }

    @Override
    public void delete(Product product) {
        String sql = "delete from PRODUCTS WHERE ID=?";
        jt.update(sql, product.getId());
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * from Products ";
        // mapper de lignes des éléments récupérés, automatique liés au bean Rendezvous grâce à
        //BeanPropertyRowMapper<T>(T.class)
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
        // jdbcTemplate (jt) possède une méthode pour récupérer un seul objet : queryForObject.
        return jt.query(sql, rowMapper);
    }

    @Override
    public Product findById(Integer id) {
        String sql = "SELECT * from Products WHERE ID=?";
        // mapper de lignes des éléments récupérés, automatique liés au bean Rendezvous grâce à
        //BeanPropertyRowMapper<T>(T.class)
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
        // jdbcTemplate (jt) possède une méthode pour récupérer un seul objet : queryForObject.
        return jt.queryForObject(sql, new Object[] {id}, rowMapper);

    }

    @Override
    public List<Product> findByCategory(String category) {
        String sql = "SELECT *  from Products where category=?";
        // mapper de lignes des éléments récupérés, automatique liés au bean Rendezvous grâce à
        //BeanPropertyRowMapper<T>(T.class)
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
        // jdbcTemplate (jt) possède une méthode pour récupérer un seul objet : queryForObject.
        return jt.query(sql, rowMapper, category);
    }
}

