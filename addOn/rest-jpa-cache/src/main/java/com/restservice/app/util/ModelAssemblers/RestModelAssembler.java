package com.restservice.app.util.ModelAssemblers;

import com.restservice.app.domain.cache.redis.*;
import com.restservice.app.dto.CreationData;
import com.restservice.app.dto.rest.*;
import com.restservice.app.dto.MetaContainer;
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
