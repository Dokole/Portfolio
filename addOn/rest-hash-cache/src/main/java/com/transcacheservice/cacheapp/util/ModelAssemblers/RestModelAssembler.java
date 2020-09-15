package com.transcacheservice.cacheapp.util.ModelAssemblers;

import com.transcacheservice.cacheapp.dto.CreationData;
import com.transcacheservice.cacheapp.dto.rest.*;
import com.transcacheservice.cacheapp.dto.MetaContainer;
import com.transcacheservice.cacheapp.domain.cache.redis.*;
import org.springframework.hateoas.EntityModel;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface RestModelAssembler {

    EntityModel<BrandRest> brandToBrandRestModel(BrandCache brand);

    EntityModel<CategoryRest> categoryToCategoryRestModel(CategoryCache category);

    EntityModel<ManufacturerRest> manufacturerToManufacturerRestModel(ManufacturerCache manufacturer);

    EntityModel<ItemRest> itemToItemRestModel(ItemCache item);

    AddressCache addressRestToAddress(AddressRest addressRest);

    AddressRest addressToAddressRest(AddressCache address);

    CreationDataRest creationDataToCreationDataRest(CreationData creationData);

    MetaContainerRest metaContainerToMetaContainerRest(MetaContainer metaContainer);

}
