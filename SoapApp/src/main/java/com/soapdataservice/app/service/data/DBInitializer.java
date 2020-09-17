package com.soapdataservice.app.service.data;

import com.soapdataservice.app.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("dbInitializer")
public class DBInitializer {

    private final BrandDataServiceImp brandServiceImp;
    private final CategoryDataService categoryServiceImp;
    private final ItemDataService itemServiceImp;
    private final ManufacturerDataService manufacturerServiceImp;

    @Value("${database.initialize}")
    private boolean initialize;

    @Autowired
    public DBInitializer(BrandDataServiceImp brandServiceImp,
                         CategoryDataService categoryServiceImp, ItemDataService itemServiceImp,
                         ManufacturerDataService manufacturerServiceImp) {
        this.brandServiceImp = brandServiceImp;
        this.categoryServiceImp = categoryServiceImp;
        this.itemServiceImp = itemServiceImp;
        this.manufacturerServiceImp = manufacturerServiceImp;
    }

    @PostConstruct
    public void initDB() {
        if(initialize) {
            Address address = new Address();
            address.setCountry("Russia");
            address.setCity("Moscow");
            address.setStreet("Lenina");
            address.setHouse("51");

            Address address2 = new Address();
            address2.setCountry("Russia");
            address2.setCity("Kazan");
            address2.setStreet("Zukova");
            address2.setHouse("32");

            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setLastModifiedBy("InitDB");
            manufacturer.setName("Manufacturer 1");
            manufacturer.setAddress(address);

            Manufacturer manufacturer2 = new Manufacturer();
            manufacturer2.setLastModifiedBy("InitDB");
            manufacturer2.setName("Manufacturer 2");
            manufacturer2.setAddress(address2);

            manufacturer = manufacturerServiceImp.save(manufacturer);
            manufacturer2 = manufacturerServiceImp.save(manufacturer2);

            Brand brand = new Brand();
            brand.setLastModifiedBy("InitDB");
            brand.setName("Brand 1");
            brand.setOfficeAddress(address);
            brand.getManufacturers().add(manufacturer);
            brand.getManufacturers().add(manufacturer2);

            Brand brand2 = new Brand();
            brand2.setLastModifiedBy("InitDB");
            brand2.setName("Brand 2");
            brand2.setOfficeAddress(address);
            brand2.getManufacturers().add(manufacturer);

            brand = brandServiceImp.save(brand);
            brand2 = brandServiceImp.save(brand2);


            Category category = new Category();
            category.setLastModifiedBy("InitDB");
            category.setName("Winter stuff");

            Category category2 = new Category();
            category2.setLastModifiedBy("InitDB");
            category2.setName("China stuff");

            Category category3 = new Category();
            category3.setLastModifiedBy("InitDB");
            category3.setName("Pants");

            category = categoryServiceImp.save(category);
            category2 = categoryServiceImp.save(category2);
            category3 = categoryServiceImp.save(category3);

            Item item = new Item();
            item.setName("Item 1");
            item.setLastModifiedBy("InitDB");
            item.setDescription("First item of init");
            item.setPrice(5000);
            item.setBrandDetails(brand);
            item.setManufacturer(manufacturer);
            item.getCategories().add(category);

            Item item2 = new Item();
            item2.setName("Item 2");
            item2.setLastModifiedBy("InitDB");
            item2.setDescription("Second item of init");
            item2.setPrice(2000);
            item2.setBrandDetails(brand2);
            item2.setManufacturer(manufacturer);
            item2.getCategories().add(category2);
            item2.getCategories().add(category3);

            item = itemServiceImp.save(item);
            item2 = itemServiceImp.save(item2);
        }

    }

}
