package fa.training.controller;

import fa.training.entity.Product;
import fa.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
//@RestController

public class ProductController {


    @Autowired
    private ProductService productService;
    //Thymeleaf

    @GetMapping("/list")
    public String getAllProduct(Model model, @RequestParam(name="name", required = false) String name) {

        List<Product> listSearch = null;

        if(StringUtils.hasText(name)) {
            listSearch = productService.findByProductNameContaining(name);
        } else {
            listSearch = productService.getProduct();
        }

        model.addAttribute("product", listSearch);
        return "index";
    }




    @GetMapping("/list/add")
    public ModelAndView viewAddProduct( Product product) {
        return new ModelAndView("addproduct");
    }

    @PostMapping("/list")
    public String saveProduct(@ModelAttribute("save") Product product) {
        productService.addProduct(product);
        return "redirect:/list";
    }

    @GetMapping("/list/update/{id}")
    public String viewUpdateProduct( @PathVariable("id") Long id, Model model) {
        Optional<Product> product = Optional.ofNullable(productService.getOneProduct(id));
        Product product1 = product.get();
        model.addAttribute("product", product1);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
         productService.updateProduct(id, product);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public  String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/list";
    }











    // API
    //get All
    @GetMapping("/list1")
    public List<Product> getAllProduct() {
        return productService.getProduct();
    }

    //Add product
    @PostMapping("/add1")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    //Update Product
    @PutMapping("/update1")
    public Product updateProduct1(@RequestParam("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    //Delete Product
    @DeleteMapping("/delete1/{id}")
    public boolean deleteProduct1 (@PathVariable("id")Long id) {
        return productService.deleteProduct(id);
    }


}
