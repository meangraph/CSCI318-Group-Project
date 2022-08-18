package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class Part {
    @NotBlank
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int partID;
    @NotBlank
    private String description;
    @NotBlank
    private Product product;

    @NotBlank
    private Supplier supplier;

    public Part(@JsonProperty String name, @JsonProperty int partID, @JsonProperty String description, @JsonProperty Supplier supplier){
        this.name = name;
        this.partID = partID;
        this.description = description;
        this.supplier = supplier;
    }

    //Accessors
    public String getName(){ return name;}

    public int getPartID(){return partID;}

    public String getDescription(){return description;}

    public Product getProduct() { return product; }

    public Supplier getSupplier() {return supplier;}
}
