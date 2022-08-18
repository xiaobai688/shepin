package com.example.shepin.controller;

import com.alibaba.fastjson.JSON;
import com.example.shepin.common.RequestMethod;
import com.example.shepin.model.goods.*;
import com.example.shepin.request.goods.GoodsAllQueryRequest;
import com.example.shepin.request.goods.GoodsQueryRequest;
import com.example.shepin.request.goods.PriceQueryRequest;
import com.example.shepin.response.JackyunResponse;
import com.example.shepin.response.goods.GoodsAllQueryResponse;
import com.example.shepin.response.goods.GoodsQueryResponse;
import com.example.shepin.response.goods.PriceQueryResponse;
import com.example.shepin.service.GoodsPriceService;
import com.example.shepin.service.GoodsService;
import com.example.shepin.service.JackyunOpenClientService;
import com.example.shepin.utils.JsonUtils;
import com.example.shepin.vo.CommonAllPageVO;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.goods.GoodsCreateVO;
import com.example.shepin.vo.goods.GoodsVO;
import com.example.shepin.vo.goods.PriceVO;
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
@RequestMapping("/goodsPrice")
public class GoodsPriceController extends BaseController{
    @Autowired
    private GoodsPriceService goodsPriceService;
    @Autowired
    private GoodsService goodsService;

    private static JackyunOpenClientService clientService = new JackyunOpenClientService();

    @Scheduled(cron ="0 5 0 * * ?")
    @GetMapping("query")
    public void getGoodsPriceList(){
        //封装查询参数
        PriceQueryModel model = getPriceQueryModel();

        PriceQueryRequest request = new PriceQueryRequest();
        request.setBizModel(model);
        request.setRequestMethod(RequestMethod.POST);

        // 发送请求
        PriceQueryResponse response = client.execute(request);
        List<PriceVO> newGoodsPriceList=new ArrayList<>();
        int size=0;
        if (response.isSuccess()) {
            CommonPageVO<PriceVO> content = response.getContent();
            List<PriceVO> list = content.getList();
            newGoodsPriceList.addAll(list);
            if (list != null) {
                size=list.size();
                int i=2;
                while (size==100){
                    model.setPageNo(i);
                    i++;
                    PriceQueryRequest newRequest = new PriceQueryRequest();
                    newRequest.setBizModel(model);
                    newRequest.setRequestMethod(RequestMethod.POST);
                    PriceQueryResponse newResponse = client.execute(newRequest);
                    if (newResponse.isSuccess()) {
                        CommonPageVO<PriceVO> newContent = newResponse.getContent();
                        List<PriceVO> newList = newContent.getList();
                        size=newList.size();
                        newGoodsPriceList.addAll(newList);
                    }
                }
            }
        } else {
            System.out.println("返回结果:" + JSON.toJSONString(response));
        }
        //写入数据库
        if(newGoodsPriceList.size()>0) {
            goodsPriceService.batchUpdate(newGoodsPriceList);
        }
        createGoodsPriceList(newGoodsPriceList);

    }

//    @Scheduled(cron ="0 0 0 * * ?")
    @GetMapping("queryAll")
    public void getAllGoodsPriceList(){
        //查询所有物料
        List<GoodsVO> all = goodsService.getAll();
//        List<GoodsVO> goodsVOS = all.subList(0, 99);
        List<PriceVO> newGoodsPriceList = new ArrayList<>();
        if(all.size()>0) {
            List<List<GoodsVO>> subs = ListUtils.partition(all, 100);
            for (List<GoodsVO> list : subs) {
                List<String> skuNoList = list.stream().map(GoodsVO::getSkuNo).collect(Collectors.toList());
                //封装查询参数
                PriceQueryModel model = getAllPriceQueryModel(skuNoList);

                PriceQueryRequest request = new PriceQueryRequest();
                request.setBizModel(model);
                request.setRequestMethod(RequestMethod.POST);

                // 发送请求
                PriceQueryResponse response = client.execute(request);
                if (response.isSuccess()) {
                    CommonPageVO<PriceVO> content = response.getContent();
                    List<PriceVO> priceVOS = content.getList();
                    newGoodsPriceList.addAll(priceVOS);
                } else {
                    System.out.println("返回结果:" + JSON.toJSONString(response));
                }
            }
        }
        //写入数据库
        if(newGoodsPriceList.size()>0) {
            goodsPriceService.batchUpdate(newGoodsPriceList);
        }
    }

    @GetMapping("create")
    public void createGoodsPriceList(List<PriceVO> priceVOList){
        if(priceVOList==null) {
            List<PriceVO> all = goodsPriceService.getAll();
            List<PriceVO> priceVOS = all.subList(0, 99);
//        for(PriceVO priceVO : priceVOS) {
//            GoodsVO bySkuNo = goodsService.getBySkuNo(priceVO.getSkuNo());
//            if(bySkuNo!=null){
//
//            }
//            //创建货品价目前,需要判断改条码对应的外部编码是否在外部系统中存在,存在即可申请调价
//            JackyunResponse jackresponse = clientService.call("erp.storage.goodslist", "v1.0", JsonUtils.toJson(priceCreateModel));
//        }
            PriceCreateModel priceCreateModel = getPriceCreateModel(priceVOS);
            JackyunResponse jackresponse = clientService.call("erp.salesgoodsskuprice.create", "v1.0", JsonUtils.toJson(priceCreateModel));
            System.out.println(jackresponse.getResult());
        }else{
            PriceCreateModel priceCreateModel = getPriceCreateModel(priceVOList);
            JackyunResponse jackresponse = clientService.call("erp.salesgoodsskuprice.create", "v1.0", JsonUtils.toJson(priceCreateModel));
            System.out.println(jackresponse.getResult());
        }
    }
    /**
     * 封装查询参数
     * @return
     */
    private PriceQueryModel getPriceQueryModel() {
        String startTime = "";
        String endTime = "";
        String channelNo = "3046";
        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth()-1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        startTime=start.format(fmtData);
        endTime=end.format(fmtData);
        // 创建请求对象
        // 请求参数
        PriceQueryModel model = new PriceQueryModel();
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
    private PriceQueryModel getAllPriceQueryModel(List<String> skuNoList) {
//        String startTime = "";
//        String endTime = "";
        String channelNo = "3046";
//        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime start = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth()-1, 0, 0, 0);
//        LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
//        startTime=start.format(fmtData);
//        endTime=end.format(fmtData);
        // 创建请求对象
        // 请求参数
        PriceQueryModel model = new PriceQueryModel();
        model.setPageSize(100);
        model.setSkuNos(skuNoList);
//        model.setStartTime(startTime);
//        model.setEndTime(endTime);
        model.setChannelNo(channelNo);
        return model;
    }


    /**
     * 封装吉客云商品创建参数
     */
    private PriceCreateModel getPriceCreateModel(List<PriceVO> list) {
        // 创建请求对象
        PriceCreateModel priceCreateModel =new PriceCreateModel();
        priceCreateModel.setCompanyCode("101");
        priceCreateModel.setDepartCode("J10");
        priceCreateModel.setApplyUserName("彭俊");
        List<GoodsCreateModel> goodsCreateModelList = new ArrayList<>();
        for(PriceVO priceVO : list){
          GoodsCreateModel goodsCreateModel = new GoodsCreateModel();
            goodsCreateModel.setPrice1(new BigDecimal(priceVO.getMarketPrice()));
            goodsCreateModel.setPrice2(new BigDecimal(priceVO.getCostPrice()));
            goodsCreateModel.setOutSkuCode(priceVO.getSkuNo());
            goodsCreateModelList.add(goodsCreateModel);
        }
        priceCreateModel.setSalesGoodsPriceAdjustmentList(goodsCreateModelList);
        return priceCreateModel;
    }

}
