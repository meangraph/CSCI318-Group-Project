package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "Part")
public class Part {
    @NotBlank
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partID;
    @NotBlank
    private String description;
    @ManyToOne
    //@JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Supplier supplier;

    public Part(@JsonProperty String name, @JsonProperty long partID, @JsonProperty String description, @JsonProperty Supplier supplier){
        this.name = name;
        this.partID = partID;
        this.description = description;
        this.supplier = supplier;
    }

    public Part(){}

    //Accessors
    public String getName(){ return name;}

    public long getPartID(){return partID;}

    public String getDescription(){return description;}

    public Product getProduct() { return product; }

    public Supplier getSupplier() {return supplier;}

    public void setProduct(Product product) {
        this.product = product;
    }
}
