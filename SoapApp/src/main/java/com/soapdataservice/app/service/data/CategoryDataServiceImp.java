package com.soapdataservice.app.service.data;

import com.soapdataservice.app.domain.Category;
import com.soapdataservice.app.domain.MetadataContainer;
import com.soapdataservice.app.repository.CategoryRepository;
import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("categoryDataService")
@Transactional
public class CategoryDataServiceImp implements CategoryDataService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDataServiceImp.class);
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryDataServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Category> findAll() {
        return Sets.newHashSet(categoryRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetadataContainer> findAllIdsAndNames() {
        return categoryRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Category> findAllByItemsName(String name) {
        return Sets.newHashSet(categoryRepository.findAllByItems_Name(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Category> findAllByItemsId(Long id) {
        return Sets.newHashSet(categoryRepository.findAllByItems_Id(id));
    }
}
