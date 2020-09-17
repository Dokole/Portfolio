package com.restservice.app.service.soapService;

import com.restservice.app.dto.CreationData;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ObjectCreationDataSoapService {
    CreationData getItemCreationData();
    CreationData getBrandCreationData();
    CreationData getManufacturerCreationData();
    CreationData getCategoryCreationData();

}
