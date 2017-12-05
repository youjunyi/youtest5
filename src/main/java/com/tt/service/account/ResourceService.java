package com.tt.service.account;

import com.tt.entity.Resource;
import com.tt.repository.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.Map;

@Component
@Transactional(readOnly = true)
public class ResourceService {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private ResourceDao resourceDao;
    
    public Page<Resource> getResource(Map<String, Object> searchParams, int pageNumber, String sortType) {
        PageRequest pageRequest = buildPageRequest(pageNumber,PAGE_SIZE , sortType);
        Specification<Resource> spec = buildSpecification( searchParams);
       return resourceDao.findAll(spec,pageRequest);
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "id");
        } else if ("title".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, "title");
        }

        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<Resource> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Resource> spec = DynamicSpecifications.bySearchFilter(filters.values(), Resource.class);
        return spec;
    }
}
