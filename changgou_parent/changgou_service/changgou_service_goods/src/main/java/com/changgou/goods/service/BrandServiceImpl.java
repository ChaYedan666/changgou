package com.changgou.goods.service;

import com.changgou.entity.PageResult;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: BrandServiceImpl
 * @description:
 * @date: 2020/4/29
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        List<Brand> brands = brandMapper.selectAll();
        return brands;
    }

    @Override
    public Brand findById(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page findPageBySearch(Map<String, String> searchMap, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        // 创建查询条件封装对象
        Example example = new Example(Brand.class);
        // 创建where条件对象，即查询条件放在criteria当中，criteria放在example当中，example再放入selectByExample当中
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null){
            // 名称不为空，使用模糊查询
            if (!StringUtils.isEmpty(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            if (!StringUtils.isEmpty(searchMap.get("letter"))){
                criteria.andLike("letter",searchMap.get("letter"));
            }
        }
        Page<Brand> brands = (Page<Brand>)brandMapper.selectByExample(example);
        return brands;
    }
}
