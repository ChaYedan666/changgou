package com.changgou.goods.service;

import com.changgou.entity.PageResult;
import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: BrandService
 * @description:
 * @date: 2020/4/29
 */
public interface BrandService {
    List<Brand> findAll();

    Brand findById(Integer id);

    void add(Brand brand);

    void update(Brand brand);

    void delete(Integer id);

    Page findPageBySearch(Map<String,String> searchMap, Integer page, Integer pageSize);
}
