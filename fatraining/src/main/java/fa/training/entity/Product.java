package fa.training.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_name")
    @NotEmpty(message = "Please enter productName")
    private String productName;
    @Column(name = "catalogy")
    @NotEmpty(message = "Please enter category")
    private String catalogy;
    @Column(name = "desc")
    @NotEmpty(message = "Please enter desc")
    private String desc;
    @Column(name = "price")
    @NotNull(message = "Please enter price")
    private Long price;
    @Column(name = "color")
    @NotEmpty(message = "Please enter color")
    private String color;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Product() {
    }

    public Product(String productName, String catalogy, String desc, Long price, String color) {
        this.productName = productName;
        this.catalogy = catalogy;
        this.desc = desc;
        this.price = price;
        this.color = color;
    }

    public Product(Long id, String productName, String catalogy, String desc, Long price, String color) {
        this.id = id;
        this.productName = productName;
        this.catalogy = catalogy;
        this.desc = desc;
        this.price = price;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCatalogy() {
        return catalogy;
    }

    public void setCatalogy(String catalogy) {
        this.catalogy = catalogy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", catalogy='" + catalogy + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", category=" + category +
                '}';
    }
}
