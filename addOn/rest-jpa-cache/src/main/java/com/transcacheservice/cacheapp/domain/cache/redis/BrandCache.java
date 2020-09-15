package com.transcacheservice.cacheapp.domain.cache.redis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public class BrandCache extends AbstractCacheObject {
    protected AddressCache address;
    protected List<ManufacturerCache> manufacturers = new ArrayList<>();

    public AddressCache getAddress() {
        return address;
    }

    public void setAddress(AddressCache address) {
        this.address = address;
    }

    public List<ManufacturerCache> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<ManufacturerCache> manufacturers) {
        this.manufacturers = manufacturers;
    }
}

