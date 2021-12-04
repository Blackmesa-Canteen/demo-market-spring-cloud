package com.learn.demomarket.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;

import com.learn.demomarket.product.dao.CategoryDao;
import com.learn.demomarket.product.entity.CategoryEntity;
import com.learn.demomarket.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


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
}