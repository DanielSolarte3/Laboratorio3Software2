package co.edu.unicauca.openmarket.domain.service;


import co.edu.unicauca.openmarket.access.IProductRepository;
import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.Category;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Libardo, Julio
 */
public class ProductService {

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private IProductRepository repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductRepository
     */
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }


    public boolean saveProduct(String name, String description, Long categoryId) {
        
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        Category categoria = new Category();
        categoria.setCategoryId(categoryId);
        newProduct.setCategory(categoria);
        
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }

        return repository.save(newProduct);

    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();;

        return products;
    }
    
    public Product findProductById(Long id){
        return repository.findById(id);
    }
    
    public Product findProductByName(String name){
        return repository.findByName(name);
    }
    
    public List<Product> findByCategory(Long id) {
        List<Product> products = new ArrayList<>();
        products = repository.findByCategory(id);;
        return products;
    }
    
    public boolean deleteProduct(Long id){
        return repository.delete(id);
    }

    public boolean editProduct(Long productId, Product prod) {
        
        //Validate product
        if (prod == null || prod.getName().isBlank() ) {
            return false;
        }
        return repository.edit(productId, prod);
    }
}
