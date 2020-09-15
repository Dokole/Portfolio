package com.transcacheservice.cacheapp.service.soapService;

import com.transcacheservice.cacheapp.dto.CreationData;
import com.transcacheservice.cacheapp.repository.soapRepository.ObjectCreationDataRepository;
import com.transcacheservice.cacheapp.util.CastDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ObjectCreationDataSoapServiceImp implements ObjectCreationDataSoapService {

    private final ObjectCreationDataRepository objectCreationDataRepository;
    private final CastDto castDto;

    @Autowired
    public ObjectCreationDataSoapServiceImp(ObjectCreationDataRepository objectCreationDataRepository, CastDto castDto) {
        this.objectCreationDataRepository = objectCreationDataRepository;
        this.castDto = castDto;
    }

    @Override
    public CreationData getItemCreationData() {
        return castDto.creationDataDtoToCreationData(objectCreationDataRepository.getItemCreationData());
    }

    @Override
    public CreationData getBrandCreationData() {
        return castDto.creationDataDtoToCreationData(objectCreationDataRepository.getBrandCreationData());
    }

    @Override
    public CreationData getManufacturerCreationData() {
        return castDto.creationDataDtoToCreationData(objectCreationDataRepository.getManufacturerCreationData());
    }

    @Override
    public CreationData getCategoryCreationData() {
        return castDto.creationDataDtoToCreationData(objectCreationDataRepository.getCategoryCreationData());
    }
}
