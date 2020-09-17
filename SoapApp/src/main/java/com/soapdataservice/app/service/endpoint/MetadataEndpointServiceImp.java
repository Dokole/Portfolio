package com.soapdataservice.app.service.endpoint;

import com.soapdataservice.app.domain.MetadataContainer;
import com.soapdataservice.app.service.data.BrandDataService;
import com.soapdataservice.app.service.data.CategoryDataService;
import com.soapdataservice.app.service.data.ItemDataService;
import com.soapdataservice.app.service.data.ManufacturerDataService;
import com.soapdataservice.app.util.CastToDtoUtil;
import com.soapdataservice.app.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service(value = "metadataService")
public class MetadataEndpointServiceImp implements MetadataEndpointService {

    private final Logger logger = LoggerFactory.getLogger(MetadataEndpointServiceImp.class);

    private final ItemDataService itemDataService;
    private final BrandDataService brandDataService;
    private final CategoryDataService categoryDataService;
    private final ManufacturerDataService manufacturerDataService;
    private final CastToDtoUtil castToDtoUtil;

    @Autowired
    public MetadataEndpointServiceImp(ItemDataService itemDataService, BrandDataService brandDataService, CategoryDataService categoryDataService, ManufacturerDataService manufacturerDataService, CastToDtoUtil castToDtoUtil) {
        this.itemDataService = itemDataService;
        this.brandDataService = brandDataService;
        this.categoryDataService = categoryDataService;
        this.manufacturerDataService = manufacturerDataService;
        this.castToDtoUtil = castToDtoUtil;
    }

    @Override
    public CreateDataDto getForItemCreation() {
        CreateDataDto createDataDto = new CreateDataDto();
        List<MetadataContainer> meta = brandDataService.findAllIdsAndNames();
        meta.forEach(m -> createDataDto.getBrandsMeta().add(castToDtoUtil.metaContainerToDto(m)));
        meta = categoryDataService.findAllIdsAndNames();
        meta.forEach(m -> createDataDto.getCategoriesMeta().add(castToDtoUtil.metaContainerToDto(m)));
        meta = manufacturerDataService.findAllIdsAndNames();
        meta.forEach(m -> createDataDto.getManufacturersMeta().add(castToDtoUtil.metaContainerToDto(m)));
        return createDataDto;

    }

    @Override
    public CreateDataDto getForBrandCreation() {
        CreateDataDto createDataDto = new CreateDataDto();
        List<MetadataContainer> meta = manufacturerDataService.findAllIdsAndNames();
        meta.forEach(m -> createDataDto.getManufacturersMeta().add(castToDtoUtil.metaContainerToDto(m)));
        return createDataDto;
    }

    @Override
    public CreateDataDto getForManufacturerCreation() {
        CreateDataDto createDataDto = new CreateDataDto();
        List<MetadataContainer> meta = brandDataService.findAllIdsAndNames();
        meta.forEach(m -> createDataDto.getBrandsMeta().add(castToDtoUtil.metaContainerToDto(m)));
        return createDataDto;
    }

    @Override
    public CreateDataDto getForCategoryCreation() {
        CreateDataDto createDataDto = new CreateDataDto();
        List<MetadataContainer> meta = itemDataService.findAllIdsAndNames();
        meta.forEach(m -> createDataDto.getItemsMeta().add(castToDtoUtil.metaContainerToDto(m)));
        return createDataDto;
    }


}
