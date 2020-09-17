package com.restservice.app.service.databaseService;

import com.restservice.app.dto.CreationData;
import com.restservice.app.service.soapService.ObjectCreationDataSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Getting metadata through SOAP service, no caching, cause if somebody wants it,
 * it's going to invalidate itself after object creation, update or delete.
 *
 * @author Zahar Zaitsev
 * @version 1.0
 *
 */
@Service
public class ObjectCreationDataDatabaseServiceImp implements ObjectCreationDataDatabaseService {

    private final ObjectCreationDataSoapService objectCreationDataSoapService;
    @Autowired
    public ObjectCreationDataDatabaseServiceImp(ObjectCreationDataSoapService objectCreationDataSoapService) {
        this.objectCreationDataSoapService = objectCreationDataSoapService;
    }

    @Override
    public CreationData getBrandsMetadata() {
        return objectCreationDataSoapService.getBrandCreationData();
    }

    @Override
    public CreationData getCategoriesMetadata() {
        return objectCreationDataSoapService.getCategoryCreationData();
    }

    @Override
    public CreationData getManufacturersMetadata() {
        return objectCreationDataSoapService.getManufacturerCreationData();
    }

    @Override
    public CreationData getItemsMetadata() {
        return objectCreationDataSoapService.getItemCreationData();
    }

}
