package daniel.lyudov.beertag.models;


import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BeerID")
    private int id;

    @Column(name = "Beername")
    @NotNull(message = "Beer Name can't be null")
    private String beername;

    @Column(name = "Brand")
    @NotNull(message = "Brand can't be null")
    private String brand;

    @Column(name = "Country")
    @NotNull(message = "Origin Country can't be null")
    private String country;

    @Column(name = "Abv")
    @NotNull(message = "ABC % can't be null")
    private String abv;

    @Column(name = "Description")
    @NotNull(message = "Description can't be null")
    private String description;

    @Column(name = "Style")
    @NotNull(message = "Style can't be null")
    private String style;


    @Column(name = "Photo")
    @Nullable
    private String photo;


    public Beer() {

    }

    public Beer(Beer beer) {
        this.beername = getBeername();
        this.brand = getBrand();
        this.country = getCountry();
        this.abv = getAbv();
        this.description = getDescription();
        this.style = getStyle();
        this.photo = getPhoto();
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeername() {
        return this.beername;
    }

    public void setBeername(String beerName) {
        this.beername = beerName;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAbv() {
        return this.abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
