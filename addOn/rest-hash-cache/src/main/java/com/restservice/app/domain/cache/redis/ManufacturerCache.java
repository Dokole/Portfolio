package com.restservice.app.domain.cache.redis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class ManufacturerCache extends AbstractCacheObject {
    protected AddressCache address;
    protected List<BrandCache> brands = new ArrayList<>();

    public AddressCache getAddress() {
        return address;
    }

    public void setAddress(AddressCache address) {
        this.address = address;
    }

    public List<BrandCache> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandCache> brands) {
        this.brands = brands;
    }
}
