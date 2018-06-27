//package com.kiisoo.athena.goods.service;
//
//import com.kiisoo.athena.common.utils.CollectionUtil;
//import com.kiisoo.athena.common.utils.CommonUtil;
//import com.kiisoo.athena.goods.constant.Constants;
//import com.kiisoo.athena.goods.mapper.PosDOMapper;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * 畅滞销不动销优化
// */
//@Service
//public class SellWellOutAnalyzeNew {
//
//    @Autowired
//    GoodsSaleAnalysisService goodsSaleAnalysisService;
//
//    @Autowired
//    PosDOMapper posDOMapper;
//
//    private static final Logger logger = LoggerFactory.getLogger(SellWellOutAnalyzeNew.class);// 日志
//
//    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(6);
//
//
//    /**
//     * 不动销优化：spu
//     *
//     * @param paramary
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> getStationarySpu(final Map<String, Object> paramary) throws Exception {
//        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//
//        List<Long> shopIdLsits = (List<Long>) paramary.get("shopIds");
//        if (null == shopIdLsits || shopIdLsits.size() == 0) {
//            return resultList;
//        }
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<String, Map<String, Object>> spuMap = (Map<String, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//        }
//
//
//        final List<Map<String, Object>> skcSalesList = new ArrayList<Map<String, Object>>();//库存
//        final List<Long> skcStockList = new ArrayList<Long>();//销售
//        final CountDownLatch countDownLatch = new CountDownLatch(2);
//        newFixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    List<Map<String, Object>> listTemple = posDOMapper.selectSpuSaleOfStationaryNew(paramary);//库存
//                    if (null != listTemple && listTemple.size() > 0) {
//                        skcSalesList.addAll(listTemple);
//                    }
//                } catch (Exception e) {
//                    logger.error("获取库存时发生了错误");
//                } finally {
//                    countDownLatch.countDown();
//                }
//
//            }
//        });
//        newFixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    List<Long> listTemple = posDOMapper.selectSpuStockOfStationaryNew(paramary);//销售
//                    if (null != listTemple && listTemple.size() > 0) {
//                        skcStockList.addAll(listTemple);
//                    }
//                } catch (Exception e) {
//                    logger.error("获取库存发生了错误");
//                } finally {
//                    countDownLatch.countDown();
//                }
//
//            }
//        });
//
//        countDownLatch.await();
//
//
//        Map<Long, Object> stockMap = getSkcMap(skcStockList);
//
//        int size = skcSalesList.size();
//        for (int i = 0; i < size; i++) {
//            Map<String, Object> map = skcSalesList.get(i);
//            if (null != map) {
//                Object skcObj = map.get("spuId");
//                if (null != skcObj) {
//                    long skc = Long.parseLong(skcObj.toString());
//                    if (!stockMap.containsKey(skc)) {//有库存没有销售
//                        resultList.add(map);
//                        Object spuObj = map.get("spuId");
//                        if (null != spuObj) {
//                            Map<String, Object> map2 = spuMap.get(String.valueOf(spuObj));
//                            if (null != map2) {
//                                map.putAll(map2);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        resultList = CommonUtil.orderListByTwoColumn(resultList, "desc", "stock", "skcId");
//        if (resultList.size() > 0) {
//            resultList = (List<Map<String, Object>>) CollectionUtil
//                    .portListByQuantity(resultList, Integer.parseInt(paramary.get("size").toString())).get(0);
//        }
//        return resultList;
//    }
//
//
//    /**
//     * 不动销优化：skc
//     *
//     * @param paramary
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> getStationarySkc(final Map<String, Object> paramary) throws Exception {
//        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//
//        List<Long> shopIdLsits = (List<Long>) paramary.get("shopIds");
//        if (null == shopIdLsits || shopIdLsits.size() == 0) {
//            return resultList;
//        }
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<String, Map<String, Object>> spuMap = (Map<String, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//        }
//
//
//        final List<Map<String, Object>> skcSalesList = new ArrayList<Map<String, Object>>();//库存
//        final List<Long> skcStockList = new ArrayList<Long>();//销售
//        final CountDownLatch countDownLatch = new CountDownLatch(2);
//        newFixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    List<Map<String, Object>> listTemple = posDOMapper.selectSKcSaleOfStationaryNew(paramary);//库存
//                    if (null != listTemple && listTemple.size() > 0) {
//                        skcSalesList.addAll(listTemple);
//                    }
//                } catch (Exception e) {
//                    logger.error("获取库存时发生了错误");
//                } finally {
//                    countDownLatch.countDown();
//                }
//
//            }
//        });
//        newFixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    List<Long> listTemple = posDOMapper.selectSKcStockOfStationaryNew(paramary);//销售
//                    if (null != listTemple && listTemple.size() > 0) {
//                        skcStockList.addAll(listTemple);
//                    }
//                } catch (Exception e) {
//                    logger.error("获取库存发生了错误");
//                } finally {
//                    countDownLatch.countDown();
//                }
//
//            }
//        });
//
//        countDownLatch.await();
//
//
//        Map<Long, Object> stockMap = getSkcMap(skcStockList);
//
//        int size = skcSalesList.size();
//        for (int i = 0; i < size; i++) {
//            Map<String, Object> map = skcSalesList.get(i);
//            if (null != map) {
//                Object skcObj = map.get("skcId");
//                if (null != skcObj) {
//                    long skc = Long.parseLong(skcObj.toString());
//                    if (!stockMap.containsKey(skc)) {//有库存没有销售
//                        resultList.add(map);
//                        Object spuObj = map.get("spuId");
//                        if (null != spuObj) {
//                            long spuId = Long.parseLong(spuObj.toString());
//                            Map<String, Object> map2 = spuMap.get(String.valueOf(spuObj));
//                            if (null != map2) {
//                                map2.remove("code");
//                                map.putAll(map2);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        Set<String> codes = new HashSet<String>();
//
//        for (Map<String, Object> map : resultList) {
//            codes.add(String.valueOf(map.hashCode()));
//        }
//        if (codes.size() < resultList.size()) {
//            System.out.println("我少了");
//        }
//
//
//        resultList = CommonUtil.orderListByTwoColumn(resultList, "desc", "stock", "skcId");
//        if (resultList.size() > 0) {
//            resultList = (List<Map<String, Object>>) CollectionUtil
//                    .portListByQuantity(resultList, Integer.parseInt(paramary.get("size").toString())).get(0);
//        }
//        return resultList;
//    }
//
//    public Map<Long, Object> getSkcMap(List<Long> skcList) {
//        Map<Long, Object> resultMap = new HashMap<Long, Object>();
//
//        if (null != skcList) {
//            int size = skcList.size();
//            for (int i = 0; i < size; i++) {
//                Long skcObj = skcList.get(i);
//                if (null != skcObj) {
//                    resultMap.put(Long.parseLong(skcObj.toString()), "");
//                }
//            }
//        }
//        return resultMap;
//    }
//
//    /**
//     * 畅滞销优化：售罄-----》spu
//     *
//     * @param paramary
//     * @param pageNum
//     * @param size
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> getSellOutUnsalableSpu(final Map<String, Object> paramary, int pageNum, int size) throws Exception {
//        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<Long, Map<String, Object>> spuMap = (Map<Long, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//        }
//
//        // 库存
//        List<Map<String, Object>> stockList = posDOMapper.selectStockSpuNew(paramary);
//
//        // 销售(累销)
//        List<Map<String, Object>> saleList = posDOMapper.selectSellSpuNew(paramary);
//
//        Map<Long, Map<String, Object>> stockMap = getStockOfSellOutUnsalableSkc(stockList, "spuId");
//
//        if (null != saleList && saleList.size() > 0 && null != stockMap) {
//            for (Map<String, Object> salesMap : saleList) {
//                if (null != salesMap) {
//                    // 目标：在salesMap中加入库存和售罄率
//                    Object skcIdObj = salesMap.get("spuId");
//                    if (null != skcIdObj) {
//                        long skcId = Long.parseLong(skcIdObj.toString());
//                        Map<String, Object> mm = stockMap.get(skcId);
//                        if (null != mm && null != mm.get("stock")){
//                            Integer stock = Integer.parseInt(mm.get("stock").toString());
//                            if (null == stock || stock <= 0) {
//                                continue;
//                            }
//                            int sale = 0;
//                            Object saleObj = salesMap.get("sumQuantity");
//                            sale = saleObj == null ? 0 : Integer.parseInt(saleObj.toString());
//                            computeSellOutRate(salesMap, sale, stock);
//                            salesMap.putAll(mm);
//                            resultList.add(salesMap);
//                        }
//                    }
//                }
//            }
//        }
//
//        addDiscount(resultList, "priceActual", "priceListSum");
//
//        Object sorObj = paramary.get("sort");
//        String sort = null;
//        if (null != sorObj) {
//            sort = String.valueOf(sorObj);
//        }
//
//        List<Map<String, Object>> templeList = CollectionUtil
//                .portListByQuantity(CommonUtil.orderListByTwoColumn(resultList, sort, "sellOut", "skcId"), size);
//        if (null != templeList && templeList.size() > 0) {
//            resultList = (List<Map<String, Object>>) templeList.get(0);
//        }
//        addSpuInfo(resultList, spuMap, null);
//        return resultList;
//    }
//
//
//    /**
//     * 畅滞销优化：售罄-----》skc
//     *
//     * @param paramary
//     * @param pageNum
//     * @param size
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> getSellOutUnsalableSkc(final Map<String, Object> paramary, int pageNum, int size) throws Exception {
//        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<Long, Map<String, Object>> spuMap = (Map<Long, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//        }
//
//        // 库存
//        List<Map<String, Object>> stockList = posDOMapper.selectStockSkcNew(paramary);
//
//        // 销售
//        List<Map<String, Object>> saleList = posDOMapper.selectSellSkcNew(paramary);
//
//        Map<Long, Map<String, Object>> stockMap = getStockOfSellOutUnsalableSkc(stockList, "skcId");
//
//        if (null != saleList && saleList.size() > 0 && null != stockMap) {
//            for (Map<String, Object> salesMap : saleList) {
//                if (null != salesMap) {
//                    // 目标：在salesMap中加入库存和售罄率
//                    Object skcIdObj = salesMap.get("skcId");
//                    if (null != skcIdObj) {
//                        long skcId = Long.parseLong(skcIdObj.toString());
//                        Map<String, Object> mm = stockMap.get(skcId);
//                        if (null != mm && null != mm.get("stock")){
//                            Integer stock = Integer.parseInt(mm.get("stock").toString());
//                            if (null == stock || stock <= 0) {
//                                continue;
//                            }
//                            int sale = 0;
//                            Object saleObj = salesMap.get("sumQuantity");
//                            sale = saleObj == null ? 0 : Integer.parseInt(saleObj.toString());
//                            computeSellOutRate(salesMap, sale, stock);
//                            salesMap.putAll(mm);
//                            resultList.add(salesMap);
//                        }
//
//                    }
//                }
//            }
//        }
//
//        addDiscount(resultList, "priceActualSum", "priceListSum");
//
//        Object sorObj = paramary.get("sort");
//        String sort = null;
//        if (null != sorObj) {
//            sort = String.valueOf(sorObj);
//        }
//        List<Map<String, Object>> templeList = CollectionUtil
//                .portListByQuantity(CommonUtil.orderListByTwoColumn(resultList, sort, "sellOut", "skcId"), size);
//        if (null != templeList && templeList.size() > 0) {
//            resultList = (List<Map<String, Object>>) templeList.get(0);
//        }
//        String[] removeAttrs = {"code"};
//        addSpuInfo(resultList, spuMap, removeAttrs);
//        return resultList;
//    }
//
//    /**
//     * 计算到店和售罄率
//     *
//     * @param map
//     * @param saleQuantity
//     * @param stock
//     */
//    private void computeSellOutRate(Map<String, Object> map, int saleQuantity, int stock) {
//        if (null == map) {
//            return;
//        }
//
//        // 进店
//        int inStock = saleQuantity + stock;
//        map.put("inStock", inStock);
//        if (inStock != 0) {
//            // 计算售罄率
//            double sellOut = saleQuantity / (double) inStock;
//            sellOut = sellOut > 1 ? 1 : sellOut < 0 ? 0 : sellOut;
//            map.put("sellOut", new BigDecimal(sellOut * 100).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
//        }
//
//    }
//
//
//    /**
//     * 畅滞销构造店铺Map
//     *
//     * @param stockList
//     * @return
//     */
//    public Map<Long, Map<String, Object>> getStockOfSellOutUnsalableSkc(List<Map<String, Object>> stockList, String diminsion) {
//        Map<Long, Map<String, Object>> resultMap = new HashMap<Long, Map<String, Object>>();
//        if (null != stockList && stockList.size() > 0) {
//            for (Map<String, Object> map : stockList) {
//                if (null != map) {
//                    Object skcIdObj = map.get(diminsion);
//                    if (null != skcIdObj) {
//                        long skcId = Long.parseLong(skcIdObj.toString());
//                        resultMap.put(skcId, map);
//                    }
//                }
//            }
//        }
//        return resultMap;
//    }
//
//
//    /**
//     * 优化畅滞销分析模块的：按销量----》spu优化
//     *
//     * @param paramary
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> getSaleSpuNew(Map<String, Object> paramary) throws Exception {
//        // 拿到所有店铺
//        List<Long> shopIds = (List<Long>) paramary.get("shopIds");
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<Long, Map<String, Object>> spuMap = (Map<Long, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//
//        // 判断时候要在条件中加入spuIdList
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//
//        }
//
//
//        // 畅销skc的销售 priceList saleQuantity priceActualSum priceListSum shopCount discount
//        List<Map<String, Object>> resultList = posDOMapper.selectSpuNew(paramary);
//
//
//        //增加折扣率
//        addDiscount(resultList, "priceActualSum", "priceListSum");
//
//        //为skc的销售添加spu信息
//        String[] removeAttrs = {"category"};
//        addSpuInfo(resultList, spuMap, removeAttrs);
//        return resultList;
//    }
//
//    public void addDiscount(List<Map<String, Object>> list, String actualStr, String listStr) {
//        if (null != list && list.size() > 0) {
//            for (Map<String, Object> e : list) {
//                if (null != e) {
//                    Object act = e.get(actualStr);
//                    Float priceActualSum = (act == null ? null : Float.valueOf(act.toString()));
//
//                    Object pri = e.get(listStr);
//                    Float priceListSum = (pri == null ? null : Float.valueOf(pri.toString()));
//                    BigDecimal discount = getDiscount(priceActualSum, priceListSum);
//                    e.put("discount", discount);
//                }
//            }
//        }
//    }
//
//    private BigDecimal getDiscount(Float one, Float another){
//        BigDecimal rate = null;
//
//        if (null != one && null != another && another != 0f){
//            rate = new BigDecimal(one).divide(new BigDecimal(another), 3, BigDecimal.ROUND_HALF_UP);
//        }
//        return rate;
//    }
//
//    /**
//     * 优化畅滞销分析模块的：按销量-----》按色
//     *
//     * @param paramary
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> getSkcSaleBySpus(Map<String, Object> paramary) throws Exception {
//        // 拿到所有店铺
//        List<Long> shopIds = (List<Long>) paramary.get("shopIds");
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<Long, Map<String, Object>> spuMap = (Map<Long, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//
//        // 判断时候要在条件中加入spuIdList
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//
//        }
//
//        // 畅销skc的销售
//        List<Map<String, Object>> resultList = posDOMapper.selectSkcSaleBySpuNew(paramary);
//
//        addDiscount(resultList, "priceActualSum", "priceListSum");
//
//        //为skc的销售添加spu信息
//        String[] removeAttrs = {"category", "code"};
//        addSpuInfo(resultList, spuMap, removeAttrs);
//        return resultList;
//    }
//
//    /**
//     * 为指定list增加spu的信息，并删除一些不必要的属性
//     *
//     * @param resultList
//     * @param spuMap
//     * @param removeAttrs
//     */
//    public void addSpuInfo(List<Map<String, Object>> resultList, Map<Long, Map<String, Object>> spuMap,
//                           String[] removeAttrs) {
//        if (null != resultList) {
//            int size1 = resultList.size();
//            for (int i = 0; i < size1; i++) {
//                Map<String, Object> map1 = resultList.get(i);
//                if (null != map1) {
//                    Object spuIdObj = map1.get("spuId");
//                    if (null != spuIdObj) {
//                        long spuId = Long.parseLong(spuIdObj.toString());
//                        Map<String, Object> map2 = spuMap.get(String.valueOf(spuIdObj.toString()));
//                        if (null != map2) {
//
//                            // 删除一些不必要的属性
//                            if (null != removeAttrs && removeAttrs.length > 0) {
//                                for (String removeAttr : removeAttrs) {
//                                    if (StringUtils.isNotBlank(removeAttr)) {
//                                        map2.remove(removeAttr);
//                                    }
//                                }
//                            }
//                            map1.putAll(map2);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 畅滞销模块------------》按金额、spu
//     *
//     * @param paramary
//     * @param type
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> saleActualPriceOfSpu(Map<String, Object> paramary, int type) throws Exception {
//        //获得拥有的店铺
//        List<Long> shopIds = (List) paramary.get("shopIds");
//        //查询销量spu，按照spu分类、此处是主要的性能瓶颈
//        //List<Map<String, Object>> list = posDOMapper.selectSkcSaleBySortOfSpu(paramary);
//        List<Map<String, Object>> list = getSaleSpuNewOrderByPriceActual(paramary);
//
//        if (null == list || (list.size() == 0)) {
//            return new ArrayList<Map<String, Object>>();
//        }
//
//        //键spuId 值当前spuId对象，处理seriesType、version,并且将list中的数据以spuId的维度抽取出来
//        Map<Long, Map<String, Object>> spuMap = new HashMap<Long, Map<String, Object>>();
//        for (Map<String, Object> map : list) {
//            //处理推广类型  款式类型
//            map.put("seriesType", Constants.seriesMap.get(map.get("seriesType")));
//            map.put("version", Constants.versionMap.get(map.get("version")));
//            spuMap.put((Long) map.get("spuId"), map);
//        }
//
//        //所有的spuId
//        List<Long> spuIds = new ArrayList<Long>(spuMap.keySet());
//
//        paramary.put("spuIdList", spuIds);
//       /* List<Map<String, Object>> skcInfos = new ArrayList<Map<String, Object>>();
//        if (type == 1){
//            skcInfos = posDOMapper.sellWell(paramary);
//        }else {
//            skcInfos = posDOMapper.sellOut(paramary);
//        }
//        Map<Long, List<Map<String, Object>>> skcInfoMap = getSkcListOfSpu(skcInfos);
//        addSkcInfo4Spu(list, skcInfoMap);*/
//        goodsSaleAnalysisService.addSkcInfo(spuIds, list);
//
//
//        //总销售 当前库存 总进货   售罄率，查询销售以及库存
//        List<Map<String, Object>> storeList = posDOMapper.selectSaleInfoByShopAndSpu(spuIds, shopIds);
//        for (Map<String, Object> store : storeList) {
//            Map<String, Object> map = spuMap.get(store.get("spuId"));
//            if (null != map) {
//                map.putAll(store);
//                goodsSaleAnalysisService.computeSellOutRate(map);
//            }
//        }
//
//        goodsSaleAnalysisService.dealOthers(list, (Date) paramary.get("startDate"), (Date) paramary.get("endDate"),
//                Long.parseLong(paramary.get("brandId").toString()), true);
//        return list;
//
//    }
//
//    /**
//     * 畅滞销分析模块-----》按金额、按色
//     *
//     * @param paramary
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, Object>> saleActualPriceSort(Map<String, Object> paramary) throws Exception {
//        //获得拥有的店铺
//        List<Long> shopIds = (List) paramary.get("shopIds");
//        //查询销量spu，按照spu分类、此处是主要的性能瓶颈
//        //List<Map<String, Object>> list = posDOMapper.selectSkcSaleBySortOfSpu(paramary);
//        List<Map<String, Object>> list = getSkcSaleBySpusOrderByActualPrice(paramary);
//
//
//        //键skc 值当前skc对象，处理seriesType、version,并且将list中的数据以skcId的维度抽取出来
//        Map<Long, Map<String, Object>> skcMap = new HashMap<Long, Map<String, Object>>();
//        for (Map<String, Object> map : list) {
//            //处理推广类型  款式类型
//            map.put("seriesType", Constants.seriesMap.get(map.get("seriesType")));
//            map.put("version", Constants.versionMap.get(map.get("version")));
//            skcMap.put((Long) map.get("skcId"), map);
//        }
//
//        //所有的skcId
//        List<Long> skcIds = new ArrayList<Long>(skcMap.keySet());
//
//
//        //总销售 当前库存 总进货   售罄率，查询销售以及库存
//        List<Map<String, Object>> storeList = posDOMapper.selectSaleInfoByShopAndSkc(skcIds, shopIds);
//        for (Map<String, Object> store : storeList) {
//            Map<String, Object> map = skcMap.get(store.get("skcId"));
//            if (null != map) {
//                map.putAll(store);
//                goodsSaleAnalysisService.computeSellOutRate(map);
//            }
//        }
//
//        goodsSaleAnalysisService.        dealOthers(list, (Date) paramary.get("startDate"), (Date) paramary.get("endDate"),
//                Long.parseLong(paramary.get("brandId").toString()), false);
//        return list;
//    }
//
//    public List<Map<String, Object>> getSkcSaleBySpusOrderByActualPrice(Map<String, Object> paramary) throws Exception {
//        // 拿到所有店铺
//        List<Long> shopIds = (List<Long>) paramary.get("shopIds");
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<Long, Map<String, Object>> spuMap = (Map<Long, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//
//        // 判断时候要在条件中加入spuIdList
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//
//        }
//
//        // 畅销skc的销售
//        List<Map<String, Object>> resultList = posDOMapper.selectSkcSaleOrderByPriceActualNew(paramary);
//        addDiscount(resultList, "priceActualSum", "priceListSum");
//
//        //为skc的销售添加spu信息
//        String[] removeAttrs = {"category", "code"};
//        addSpuInfo(resultList, spuMap, removeAttrs);
//        return resultList;
//    }
//
//    public List<Map<String, Object>> getSaleSpuNewOrderByPriceActual(Map<String, Object> paramary) throws Exception {
//        // 拿到所有店铺
//        List<Long> shopIds = (List<Long>) paramary.get("shopIds");
//
//        // spu
//        List<Map<String, Object>> spuMapList = goodsSaleAnalysisService.getSpuList(paramary);
//        Object[] spuArray = goodsSaleAnalysisService.getSpuArray(spuMapList);
//        Map<Long, Map<String, Object>> spuMap = (Map<Long, Map<String, Object>>) spuArray[1];// spuId和spu信息的映射
//
//        // 判断时候要在条件中加入spuIdList
//        if (goodsSaleAnalysisService.selectOrNot(paramary)) {// 如果用户有筛选操作，那么下面查询销售和库存就关联spuIdList
//            List<Long> spuIdList = (List<Long>) spuArray[0];// spu列表
//            if (null == spuIdList || spuIdList.size() == 0) {
//                spuIdList.add(-1L);
//            }
//            paramary.put("spuIdList", spuIdList);
//
//        }
//
//        // 畅销skc的销售
//        List<Map<String, Object>> resultList = posDOMapper.selectSpuNewOrderByActualPrice(paramary);
//        addDiscount(resultList, "priceActualSum", "priceListSum");
//
//        //为skc的销售添加spu信息
//        String[] removeAttrs = {"category"};
//        addSpuInfo(resultList, spuMap, removeAttrs);
//        return resultList;
//    }
//
//}
