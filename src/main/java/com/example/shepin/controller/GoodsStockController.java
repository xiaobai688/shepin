package com.example.shepin.controller;

import com.alibaba.fastjson.JSON;
import com.example.shepin.common.RequestMethod;
import com.example.shepin.model.goods.GoodsCreateModel;
import com.example.shepin.model.goods.StockCreateModel;
import com.example.shepin.model.goods.StockQueryModel;
import com.example.shepin.request.goods.StockQueryRequest;
import com.example.shepin.response.JackyunResponse;
import com.example.shepin.response.goods.StockQueryResponse;
import com.example.shepin.service.GoodsService;
import com.example.shepin.service.GoodsStockService;
import com.example.shepin.service.JackyunOpenClientService;
import com.example.shepin.utils.JsonUtils;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.goods.GoodsVO;
import com.example.shepin.vo.goods.StockVO;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goodsStock")
public class GoodsStockController extends BaseController{
    @Autowired
    private GoodsStockService goodsStockService;
    @Autowired
    private GoodsService goodsService;

    private static JackyunOpenClientService clientService = new JackyunOpenClientService();

    @Scheduled(cron ="0 10 0/6 * * ?")
    @GetMapping("query")
    public void getGoodsStockList(){
        //封装查询参数
         StockQueryModel model = getStockQueryModel();

        StockQueryRequest request = new StockQueryRequest();
        request.setBizModel(model);
        request.setRequestMethod(RequestMethod.POST);

        // 发送请求
        StockQueryResponse response = client.execute(request);
        List<StockVO> newGoodsStockList=new ArrayList<>();
        int size=0;
        if (response.isSuccess()) {
            CommonPageVO<StockVO> content = response.getContent();
            List<StockVO> list = content.getList();
            newGoodsStockList.addAll(list);
            if (list != null) {
                size=list.size();
                int i=2;
                while (size==100){
                    model.setPageNo(i);
                    i++;
                    StockQueryRequest newRequest = new StockQueryRequest();
                    newRequest.setBizModel(model);
                    newRequest.setRequestMethod(RequestMethod.POST);
                    StockQueryResponse newResponse = client.execute(newRequest);
                    if (newResponse.isSuccess()) {
                        CommonPageVO<StockVO> newContent = newResponse.getContent();
                        List<StockVO> newList = newContent.getList();
                        size=newList.size();
                        newGoodsStockList.addAll(newList);
                    }
                }
            }
        } else {
            System.out.println("返回结果:" + JSON.toJSONString(response));
        }
        List<StockVO> inGoodsList=new ArrayList<>();
        List<StockVO> outGoodsList=new ArrayList<>();
        List<StockVO> all = goodsStockService.getAll();
        one : for(StockVO stockVO : newGoodsStockList){
            for(StockVO vo : all){
                if(stockVO.getSkuNo().equals(vo.getSkuNo())){
                    if(stockVO.getQuantity().equals(vo.getQuantity())){
                        continue one;
                    }else{
                        StockVO stockVO1=new StockVO();
                        stockVO1.setSkuNo(stockVO.getSkuNo());
                        if(stockVO.getQuantity()>vo.getQuantity()){
                            stockVO1.setQuantity(stockVO.getQuantity()-vo.getQuantity());
                            inGoodsList.add(stockVO1);
                        }else if(stockVO.getQuantity()<vo.getQuantity()){
                            stockVO1.setQuantity(vo.getQuantity()-stockVO.getQuantity());
                            outGoodsList.add(stockVO1);
                        }
                    }

                }
            }
        }
        if(inGoodsList.size()>0){
            createInGoodsStockList(inGoodsList);
        }

        if(outGoodsList.size()>0){
            createOutGoodsStockList(outGoodsList);
        }
        //写入数据库
        if(newGoodsStockList.size()>0) {
            goodsStockService.batchUpdate(newGoodsStockList);
        }
    }

    @GetMapping("queryAll")
    public void getAllgoodsList(){
        //查询所有物料
        List<GoodsVO> all = goodsService.getAll();
        List<GoodsVO> goodsVOS = all.subList(0, 99);
        //物料对应库存
        List<StockVO> newGoodsStockList = new ArrayList<>();
        if(goodsVOS.size()>0) {
            List<List<GoodsVO>> subs = ListUtils.partition(goodsVOS, 100);
            for (List<GoodsVO> list : subs) {
                List<String> skuNoList = list.stream().map(GoodsVO::getSkuNo).collect(Collectors.toList());
                //封装查询参数
                StockQueryModel model = getAllStockQueryModel(skuNoList);

                StockQueryRequest request = new StockQueryRequest();
                request.setBizModel(model);
                request.setRequestMethod(RequestMethod.POST);

                // 发送请求
                StockQueryResponse response = client.execute(request);
                if (response.isSuccess()) {
                    CommonPageVO<StockVO> content = response.getContent();
                    List<StockVO> stockVOList = content.getList();
                    newGoodsStockList.addAll(stockVOList);
                } else {
                    System.out.println("返回结果:" + JSON.toJSONString(response));
                }
            }
        }
        createInGoodsStockList(newGoodsStockList);
        //写入数据库
        if(newGoodsStockList.size()>0) {
            goodsStockService.batchUpdate(newGoodsStockList);
        }
    }

    public void createOutGoodsStockList(List<StockVO> stockVO){
        StockCreateModel outGoodsCreateModel = getOutGoodsCreateModel(stockVO);
        JackyunResponse jackresponse = clientService.call("erp.stock.createandstockout", "v1.0", JsonUtils.toJson(outGoodsCreateModel));
        System.out.println(jackresponse.getResult());
    }

    public void createInGoodsStockList(List<StockVO> stockVO){
        StockCreateModel outGoodsCreateModel = getInGoodsCreateModel(stockVO);
        JackyunResponse jackresponse = clientService.call("erp.stock.createandstockin", "v1.0", JsonUtils.toJson(outGoodsCreateModel));
        System.out.println(jackresponse.getResult());
    }

    /**
     * 封装查询参数
     * @return
     */
    private StockQueryModel getStockQueryModel() {
        String startTime = "";
        String endTime = "";
        String channelNo = "3046";
        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(1<=now.getHour() && now.getHour()<=6){
            LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
            LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 6, 0, 0);
            startTime=start.format(fmtData);
            endTime=end.format(fmtData);
        }else if(7<=now.getHour() && now.getHour()<=12){
            LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 6, 0, 0);
            LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 12, 0, 0);
            startTime=start.format(fmtData);
            endTime=end.format(fmtData);
        }else if(13<=now.getHour() && now.getHour()<=18){
            LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 12, 0, 0);
            LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 18, 0, 0);
            startTime=start.format(fmtData);
            endTime=end.format(fmtData);
        }else{
            LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 18, 0, 0);
            LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);
            startTime=start.format(fmtData);
            endTime=end.format(fmtData);
        }

        // 创建请求对象
        // 请求参数
        StockQueryModel model = new StockQueryModel();
        model.setPageSize(100);
        model.setStartTime(startTime);
        model.setEndTime(endTime);
        model.setChannelNo(channelNo);
        return model;
    }

    /**
     * 封装查询参数
     * @return
     */
    private StockQueryModel getAllStockQueryModel(List<String> skuNoList) {
        String channelNo = "3046";
        // 创建请求对象
        // 请求参数
        StockQueryModel model = new StockQueryModel();
        model.setPageSize(100);
        model.setSkuNos(skuNoList);
        model.setChannelNo(channelNo);
        return model;
    }


    /**
     * 封装吉客云商品创建参数
     */
    private StockCreateModel getOutGoodsCreateModel(List<StockVO> list) {
        // 创建请求对象
        StockCreateModel stockCreateModel =new StockCreateModel();
        stockCreateModel.setOutType("204");
        stockCreateModel.setOutWarehouseCode("SP001");
        stockCreateModel.setRelDataId("J10"+(int)((Math.random()*9+1)*100000));
        stockCreateModel.setApplyDepartCode("J10");
        stockCreateModel.setApplyDate(LocalDateTime.now());
        stockCreateModel.setOperator("彭俊");
        stockCreateModel.setSource("OPEN");
        List<GoodsCreateModel> goodsCreateModelList = new ArrayList<>();
        for(StockVO stockVO : list){
            GoodsCreateModel goodsCreateModel = new GoodsCreateModel();
            goodsCreateModel.setOutSkuCode(stockVO.getSkuNo());
            goodsCreateModel.setRelDetailId("0");
            goodsCreateModel.setSkuCount(new BigDecimal(stockVO.getQuantity()));
            goodsCreateModelList.add(goodsCreateModel);
        }
        stockCreateModel.setStockOutDetailViews(goodsCreateModelList);
        return stockCreateModel;
    }

    /**
     * 封装吉客云商品创建参数
     */
    private StockCreateModel getInGoodsCreateModel(List<StockVO> list) {
        // 创建请求对象
        StockCreateModel stockCreateModel =new StockCreateModel();
        stockCreateModel.setInType("104");
        stockCreateModel.setRelDataId("101-J10"+(int)((Math.random()*9+1)*100000));
        stockCreateModel.setApplyDepartCode("J10");
        stockCreateModel.setApplyCompanyCode("101");
        stockCreateModel.setInWarehouseCode("SP001");
        stockCreateModel.setApplyUserName("彭俊");
        stockCreateModel.setApplyDate(LocalDateTime.now());
        stockCreateModel.setOperator("彭俊");
        stockCreateModel.setSource("OPEN");
        List<GoodsCreateModel> goodsCreateModelList = new ArrayList<>();
        for(StockVO stockVO : list){
            GoodsCreateModel goodsCreateModel = new GoodsCreateModel();
            goodsCreateModel.setOutSkuCode(stockVO.getSkuNo());
            goodsCreateModel.setRelDetailId("0");
            goodsCreateModel.setSkuCount(new BigDecimal(stockVO.getQuantity()));
            goodsCreateModelList.add(goodsCreateModel);
        }
        stockCreateModel.setStockInDetailViews(goodsCreateModelList);
        return stockCreateModel;
    }

}
