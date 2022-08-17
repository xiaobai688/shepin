package com.example.shepin.controller;

import com.alibaba.fastjson.JSON;
import com.example.shepin.common.RequestMethod;
import com.example.shepin.model.goods.GoodsAllQueryModel;
import com.example.shepin.model.goods.GoodsCreateModel;
import com.example.shepin.model.goods.GoodsQueryModel;
import com.example.shepin.request.goods.GoodsAllQueryRequest;
import com.example.shepin.request.goods.GoodsQueryRequest;
import com.example.shepin.response.JackyunResponse;
import com.example.shepin.response.goods.GoodsAllQueryResponse;
import com.example.shepin.response.goods.GoodsQueryResponse;
import com.example.shepin.service.GoodsService;
import com.example.shepin.service.JackyunOpenClientService;
import com.example.shepin.utils.JsonUtils;
import com.example.shepin.vo.CommonAllPageVO;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.goods.GoodsCreateVO;
import com.example.shepin.vo.goods.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController{
    @Autowired
    private GoodsService goodsService;

    private static JackyunOpenClientService clientService = new JackyunOpenClientService();

    @Scheduled(cron ="0 0 0/6 * * ?")
    @GetMapping("query")
    public void getGoodsList(){
        //封装查询参数
         GoodsQueryModel model = getGoodsQueryModel();

        GoodsQueryRequest request = new GoodsQueryRequest();
        request.setBizModel(model);
        request.setRequestMethod(RequestMethod.POST);

        // 发送请求
        GoodsQueryResponse response = client.execute(request);
        List<GoodsVO> newGoodsList=new ArrayList<>();
        int size=0;
        if (response.isSuccess()) {
            CommonPageVO<GoodsVO> content = response.getContent();
            List<GoodsVO> list = content.getList();
            newGoodsList.addAll(list);
            if (list != null) {
                size=list.size();
                int i=2;
                while (size==100){
                    model.setPageNo(i);
                    i++;
                    GoodsQueryRequest newRequest = new GoodsQueryRequest();
                    newRequest.setBizModel(model);
                    newRequest.setRequestMethod(RequestMethod.POST);
                    GoodsQueryResponse newResponse = client.execute(newRequest);
                    if (newResponse.isSuccess()) {
                        CommonPageVO<GoodsVO> newContent = newResponse.getContent();
                        List<GoodsVO> newList = newContent.getList();
                        size=newList.size();
                        newGoodsList.addAll(newList);
                    }
                }
            }
        } else {
            System.out.println("返回结果:" + JSON.toJSONString(response));
        }
        //写入数据库
        if(newGoodsList.size()>0) {
            goodsService.batchUpdate(newGoodsList);
        }
    }

    @GetMapping("queryAll")
    public void getAllGoodsList(){
        //封装查询参数
        GoodsAllQueryModel model = getGoodsAllQueryModel();


        GoodsAllQueryRequest request = new GoodsAllQueryRequest();
        request.setBizModel(model);
        request.setRequestMethod(RequestMethod.POST);

        // 发送请求
        List<GoodsVO> newGoodsList=new ArrayList<>();
        GoodsAllQueryResponse response = client.execute(request);
        int size=0;
        if (response.isSuccess()) {
            CommonAllPageVO<GoodsVO> content = response.getContent();
            List<GoodsVO> list = content.getList();
            newGoodsList.addAll(list);
            size=list.size();
            int i=2;
            while (size==100){
                model.setPageNo(i);
                i++;
                GoodsAllQueryRequest newRequest = new GoodsAllQueryRequest();
                newRequest.setBizModel(model);
                newRequest.setRequestMethod(RequestMethod.POST);
                GoodsAllQueryResponse newResponse = client.execute(newRequest);
                if (newResponse.isSuccess()) {
                    CommonAllPageVO<GoodsVO> newContent = newResponse.getContent();
                    List<GoodsVO> newList = newContent.getList();
                    size=newList.size();
                    newGoodsList.addAll(newList);
                }
            }
            if (list != null) {
                System.out.println("返回结果:" + JSON.toJSONString(list));
            }

        } else {
            System.out.println("返回结果:" + JSON.toJSONString(response));
        }
        System.out.println("返回结果:" + JSON.toJSONString(newGoodsList));
        //写入数据库
        if(newGoodsList.size()>0) {
            goodsService.batchUpdate(newGoodsList);
        }

    }

    @GetMapping("create")
    public void createGoodsList(){
        List<GoodsVO> all = goodsService.getAll();
        List<GoodsVO> goodsVOS = all.subList(0, 99);
        for(GoodsVO goodsVO : goodsVOS) {
            List<GoodsCreateModel> goodsCreateModel = getGoodsCreateModel(goodsVO);
            JackyunResponse jackresponse = clientService.call("erp.goods.skuimportbatch", "v1.0", JsonUtils.toJson(goodsCreateModel));
            System.out.println(jackresponse.getResult());
        }
    }


    /**
     * 封装查询参数
     * @return
     */
    private GoodsQueryModel getGoodsQueryModel() {
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
        GoodsQueryModel model = new GoodsQueryModel();
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
    private GoodsAllQueryModel getGoodsAllQueryModel() {
        String channelNo = "3046";
        // 创建请求对象
        // 请求参数
        GoodsAllQueryModel model = new GoodsAllQueryModel();
        model.setChannelNo(channelNo);
        model.setPageSize(100);
//        if(scrollNo!=null && !scrollNo.isEmpty()) {
//            model.setScrollNo(scrollNo);
//        }
        return model;
    }


    /**
     * 封装吉客云商品创建参数
     */
    private List<GoodsCreateModel> getGoodsCreateModel(GoodsVO goodsVO) {
        List<GoodsCreateModel> goodsCreateModelList=new ArrayList<>();
        // 创建请求对象
            GoodsCreateModel goodsCreateModel =new GoodsCreateModel();
            goodsCreateModel.setGoodsNo(goodsVO.getGoodsModel());
            goodsCreateModel.setGoodsName(goodsVO.getGoodsName().replaceAll("\\+"," ").replaceAll("&"," "));
            goodsCreateModel.setCateCode("04");
            goodsCreateModel.setCateName("奢品");
            goodsCreateModel.setUnitName("PCS");
            goodsCreateModel.setOutSkuCode(goodsVO.getSkuNo());
            goodsCreateModel.setSkuName(goodsVO.getSalePropertiesName().replaceAll("\\+"," ").replaceAll("&"," "));
            goodsCreateModel.setSkuBarcode(goodsVO.getBarcode());
            goodsCreateModel.setOwnerCode("101");
            goodsCreateModel.setMainGoodsUrl(goodsVO.getGoodsMainImage());
        goodsCreateModelList.add(goodsCreateModel);
        return goodsCreateModelList;
    }

}
