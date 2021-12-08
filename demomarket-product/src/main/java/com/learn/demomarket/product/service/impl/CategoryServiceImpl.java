package com.learn.demomarket.product.service.impl;

import com.learn.demomarket.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;

import com.learn.demomarket.product.dao.CategoryDao;
import com.learn.demomarket.product.entity.CategoryEntity;
import com.learn.demomarket.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1.查出所有分类(没条件查出所有)
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 2.组装成父子树形结构
        // 找到所有一级fenlei
        List<CategoryEntity> levelOneMenus = entities.stream().filter((categoryEntity) -> {
            return categoryEntity.getParentCid() == 0;
        }).map((menu) -> {
            menu.setChildren(getChildren(menu, entities));
            return menu;
        }).sorted((menu1, menu2) -> {
            if (menu1.getSort() == null || menu2.getSort() == null) {
                return 0;
            }

            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());

        return levelOneMenus;
    }

    /**
     * 递归查找所有菜单的子菜单, 从entities里找到parent菜单的子菜单
     * @param parent
     * @param entities
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity parent, List<CategoryEntity> entities) {
        List<CategoryEntity> children = entities.stream().filter(categoryEntity -> {

            // 找到子菜单(这里应该也算递归结束条件了,当没有匹配的,filter为空)
            return Objects.equals(categoryEntity.getParentCid(), parent.getCatId());
        }).map(categoryEntity -> {

            //递归设置子菜单的子菜单
            categoryEntity.setChildren(getChildren(categoryEntity, entities));
            return categoryEntity;

        }).sorted((menu1, menu2) -> {
            if (menu1.getSort() == null || menu2.getSort() == null) {
                return 0;
            }

            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());

        return children;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {

        //TODO 检查其他的表中有没有引用这个categories

        // 物理删除:真删除这条记录
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> parentPath = findParentPath(catelogId, new LinkedList<>());

        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[0]);
    }


    /**
     * 级联更新所有关联的数据
     *
     * @CacheEvict:失效模式
     * @CachePut:双写模式，需要有返回值
     * 1、同时进行多种缓存操作：@Caching
     * 2、指定删除某个分区下的所有数据 @CacheEvict(value = "category",allEntries = true)
     * 3、存储同一类型的数据，都可以指定为同一分区
     * @param category
     */
    // @Caching(evict = {
    //         @CacheEvict(value = "category",key = "'getLevel1Categorys'"),
    //         @CacheEvict(value = "category",key = "'getCatalogJson'")
    // })
//    @CacheEvict(value = "category",allEntries = true)       //删除某个分区下的所有数据
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCascade(CategoryEntity category) {
//        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("catalogJson-lock");
//        //创建写锁
//        RLock rLock = readWriteLock.writeLock();
//
//        try {
//            rLock.lock();
//            this.baseMapper.updateById(category);
//            categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            rLock.unlock();
//        }

        //同时修改缓存中的数据
        //删除缓存,等待下一次主动查询进行更新

        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {

        paths.add(catelogId);

        CategoryEntity byId
                = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }

        return paths;
    }
}