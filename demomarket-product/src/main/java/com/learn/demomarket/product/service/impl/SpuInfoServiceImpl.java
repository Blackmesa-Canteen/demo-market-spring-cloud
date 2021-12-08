package com.learn.demomarket.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.learn.common.constant.ProductConstant;
import com.learn.demomarket.product.entity.BrandEntity;
import com.learn.demomarket.product.entity.ProductAttrValueEntity;
import com.learn.demomarket.product.entity.SkuInfoEntity;
import com.learn.demomarket.product.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;

import com.learn.demomarket.product.dao.SpuInfoDao;
import com.learn.demomarket.product.entity.SpuInfoEntity;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public SpuInfoEntity getSpuInfoBySkuId(Long skuId) {
        //先查询sku表里的数据
        SkuInfoEntity skuInfoEntity = skuInfoService.getById(skuId);

        //获得spuId
        Long spuId = skuInfoEntity.getSpuId();

        //再通过spuId查询spuInfo信息表里的数据
        SpuInfoEntity spuInfoEntity = this.baseMapper.selectById(spuId);

        //查询品牌表的数据获取品牌名
        BrandEntity brandEntity = brandService.getById(spuInfoEntity.getBrandId());
        spuInfoEntity.setBrandName(brandEntity.getName());

        return spuInfoEntity;

    }

    @Override
    public void up(Long spuId) {
        //1、查出当前spuId对应的所有sku信息,品牌的名字
        List<SkuInfoEntity> skuInfoEntities = skuInfoService.getSkusBySpuId(spuId);

        //TODO 4、查出当前sku的所有可以被用来检索的规格属性
        List<ProductAttrValueEntity> baseAttrs = productAttrValueService.baseAttrListforspu(spuId);

        List<Long> attrIds = baseAttrs.stream().map(attr -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        List<Long> searchAttrIds = attrService.selectSearchAttrs(attrIds);
        //转换为Set集合
        Set<Long> idSet = searchAttrIds.stream().collect(Collectors.toSet());

//        List<SkuEsModel.Attrs> attrsList = baseAttrs.stream().filter(item -> {
//            return idSet.contains(item.getAttrId());
//        }).map(item -> {
//            SkuEsModel.Attrs attrs = new SkuEsModel.Attrs();
//            BeanUtils.copyProperties(item, attrs);
//            return attrs;
//        }).collect(Collectors.toList());

        List<Long> skuIdList = skuInfoEntities.stream()
                .map(SkuInfoEntity::getSkuId)
                .collect(Collectors.toList());
        //TODO 1、发送远程调用，库存系统查询是否有库存
//        Map<Long, Boolean> stockMap = null;
//        try {
//            R skuHasStock = wareFeignService.getSkuHasStock(skuIdList);
//            //
//            TypeReference<List<SkuHasStockVo>> typeReference = new TypeReference<List<SkuHasStockVo>>() {};
//            stockMap = skuHasStock.getData(typeReference).stream()
//                    .collect(Collectors.toMap(SkuHasStockVo::getSkuId, item -> item.getHasStock()));
//        } catch (Exception e) {
//            log.error("库存服务查询异常：原因{}",e);
//        }

        //2、封装每个sku的信息
//        Map<Long, Boolean> finalStockMap = stockMap;
//        List<SkuEsModel> collect = skuInfoEntities.stream().map(sku -> {
//            //组装需要的数据
//            SkuEsModel esModel = new SkuEsModel();
//            esModel.setSkuPrice(sku.getPrice());
//            esModel.setSkuImg(sku.getSkuDefaultImg());
//
//            //设置库存信息
//            if (finalStockMap == null) {
//                esModel.setHasStock(true);
//            } else {
//                esModel.setHasStock(finalStockMap.get(sku.getSkuId()));
//            }
//
//            //TODO 2、热度评分。0
//            esModel.setHotScore(0L);
//
//            //TODO 3、查询品牌和分类的名字信息
//            BrandEntity brandEntity = brandService.getById(sku.getBrandId());
//            esModel.setBrandName(brandEntity.getName());
//            esModel.setBrandId(brandEntity.getBrandId());
//            esModel.setBrandImg(brandEntity.getLogo());
//
//            CategoryEntity categoryEntity = categoryService.getById(sku.getCatalogId());
//            esModel.setCatalogId(categoryEntity.getCatId());
//            esModel.setCatalogName(categoryEntity.getName());
//
//            //设置检索属性
//            esModel.setAttrs(attrsList);
//
//            BeanUtils.copyProperties(sku,esModel);
//
//            return esModel;
//        }).collect(Collectors.toList());

//        //TODO 5、将数据发给es进行保存：gulimall-search
//        R r = searchFeignService.productStatusUp(collect);
//
//        if (r.getCode() == 0) {
//            //远程调用成功
//            //TODO 6、修改当前spu的状态
            this.baseMapper.updaSpuStatus(spuId, ProductConstant.ProductStatusEnum.SPU_UP.getCode());
//        } else {
//            //远程调用失败
//            //TODO 7、重复调用？接口幂等性:重试机制
//        }
    }

}