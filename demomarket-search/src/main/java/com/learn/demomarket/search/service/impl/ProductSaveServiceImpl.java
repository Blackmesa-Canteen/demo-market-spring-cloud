package com.learn.demomarket.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.learn.common.to.es.SkuEsModel;
import com.learn.demomarket.search.constant.EsConstant;
import com.learn.demomarket.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.learn.demomarket.search.config.ElasticSearchConfig.COMMON_OPTIONS;

/**
 * @author 996Worker
 * @program demo-Mail-demo
 * @description
 * @create 2021-12-10 17:37
 */

@Slf4j
@Service("productSaveService")
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
        // 在es中建立索引: 建立映射关系(手工)
//        PUT demomarket_product
//        {
//            "mappings": {
//            "properties": {
//                "skuId": {
//                    "type": "long"
//                },
//                "spuId": {
//                    "type": "long"
//                },
//                "skuTitle": {
//                    "type": "text",
//                            "analyzer": "ik_smart"
//                },
//                "skuPrice": {
//                    "type": "keyword"
//                },
//                "skuImg": {
//                    "type": "keyword",
//                            "index": false,
//                            "doc_values": false
//                },
//                "saleCount": {
//                    "type": "long"
//                },
//                "hosStock": {
//                    "type": "boolean"
//                },
//                "hotScore": {
//                    "type": "long"
//                },
//                "brandId": {
//                    "type": "long"
//                },
//                "catelogId": {
//                    "type": "long"
//                },
//                "brandName": {
//                    "type": "keyword",
//                            "index": false,
//                            "doc_values": false
//                },
//                "brandImg": {
//                    "type": "keyword",
//                            "index": false,
//                            "doc_values": false
//                },
//                "catelogName": {
//                    "type": "keyword",
//                            "index": false,
//                            "doc_values": false
//                },
//                "attrs": {
//                    "type": "nested",
//                            "properties": {
//                        "attrId": {
//                            "type": "long"
//                        },
//                        "attrName": {
//                            "type": "keyword",
//                                    "index": false,
//                                    "doc_values": false
//                        },
//                        "attrValue": {
//                            "type": "keyword"
//                        }
//                    }
//                }
//            }
//        }
//        }

        // 2. 保存数据(批量)
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            IndexRequest request = new IndexRequest(EsConstant.PRODUCT_INDEX);
            request.id(model.getSkuId().toString());
            String jsonString = JSON.toJSONString(model);

            request.source(jsonString, XContentType.JSON);

            bulkRequest.add(request);
        }

        BulkResponse bulk = restHighLevelClient.bulk(
                bulkRequest,
                COMMON_OPTIONS
        );

        //如果批量错误
        boolean hasFailures = bulk.hasFailures();

        List<String> collect = Arrays.asList(bulk.getItems()).stream().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());

        log.info("商品上架完成：{}",collect);

        return hasFailures;
    }
}