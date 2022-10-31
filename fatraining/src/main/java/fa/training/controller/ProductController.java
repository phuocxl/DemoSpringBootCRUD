package fa.training.controller;

import fa.training.entity.Product;
import fa.training.repository.ProductRepository;
import fa.training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
        return "index--";
    }

    @GetMapping("/paginated")
    //paginated
    public String getAllProduct(Model model, @RequestParam(name="name", required = false) String name,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        //Sort.by("fullName")
        Page<Product> resultPage = null;


        if(StringUtils.hasText(name)) {
            resultPage = productService.findByProductNameContaining(name,pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = productService.getPageProduct(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0) {
            int start = Math.max(1, currentPage-2);
            int end = Math.min(currentPage +2, totalPages);

            if(totalPages > 5) {
                if(end == totalPages){
                    start = end - 5;
                } else if (start ==1) {
                    end = start +5;
                }
            }

            List<Integer> pageNumber = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumber);

        }

        System.out.println(resultPage.toString());
        model.addAttribute("product", resultPage);
        return "index";
    }




    @GetMapping("/list/add")
    public ModelAndView viewAddProduct( Product product) {
        return new ModelAndView("addproduct");
    }

    @PostMapping("/list")
    public String saveProduct(@ModelAttribute("save") Product product) {
        productService.addProduct(product);
        return "redirect:/paginated";
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
        return "redirect:/paginated";
    }

    @GetMapping("/delete/{id}")
    public  String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/paginated";
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
