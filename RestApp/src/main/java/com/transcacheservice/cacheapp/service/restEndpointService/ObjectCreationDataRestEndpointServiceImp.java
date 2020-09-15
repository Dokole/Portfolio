package com.transcacheservice.cacheapp.service.restEndpointService;

import com.transcacheservice.cacheapp.dto.rest.CreationDataRest;
import com.transcacheservice.cacheapp.repository.soapRepository.ObjectCreationDataRepository;
import com.transcacheservice.cacheapp.util.CastDto;
import com.transcacheservice.cacheapp.util.ModelAssemblers.RestModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ObjectCreationDataRestEndpointServiceImp implements ObjectCreationDataRestEndpointService {

    private final Logger logger = LoggerFactory.getLogger(ObjectCreationDataRestEndpointServiceImp.class);

    private final ObjectCreationDataRepository objectCreationDataRepository;
    private final RestModelAssembler restModelAssembler;
    private final CastDto castDto;
    @Autowired
    public ObjectCreationDataRestEndpointServiceImp(ObjectCreationDataRepository objectCreationDataRepository, RestModelAssembler restModelAssembler, CastDto castDto) {
        this.objectCreationDataRepository = objectCreationDataRepository;
        this.restModelAssembler = restModelAssembler;
        this.castDto = castDto;
    }

    @Override
    public CreationDataRest getBrandsMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(
                castDto.creationDataDtoToCreationData(objectCreationDataRepository.getBrandCreationData()));
    }

    @Override
    public CreationDataRest getCategoriesMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(
                castDto.creationDataDtoToCreationData(objectCreationDataRepository.getCategoryCreationData()));
    }

    @Override
    public CreationDataRest getManufacturersMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(
                castDto.creationDataDtoToCreationData(objectCreationDataRepository.getManufacturerCreationData()));
    }

    @Override
    public CreationDataRest getItemsMetadata() {
        return restModelAssembler.creationDataToCreationDataRest(
                castDto.creationDataDtoToCreationData(objectCreationDataRepository.getItemCreationData()));
    }
}
