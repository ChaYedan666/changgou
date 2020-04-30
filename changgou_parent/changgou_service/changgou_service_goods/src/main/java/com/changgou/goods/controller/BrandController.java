package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: BrandController
 * @description:
 * @date: 2020/4/29
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/all")
    public Result<Brand>findAll(){
        List<Brand> brandList = brandService.findAll();
        return new Result<Brand>(true, StatusCode.OK,"查询成功",brandList);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id){
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"查询成功",brand);
    }

    // 不加路径也可以，在访问brand时用post请求就会调用add方法
    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     *  复杂查询并且带分页
     * @return
     */
    @GetMapping("/search/{page}/{pageSize}")
    public PageResult<Brand> findPageBySearch(@RequestBody Map<String,String> searchMap, @PathVariable("page") Integer page,
                                              @PathVariable("pageSize") Integer pageSize){
        Page brandList = brandService.findPageBySearch(searchMap, page, pageSize);

        PageResult<Brand> result = new PageResult<>(brandList.getTotal(),brandList.getResult());

        return result;
    }
}
