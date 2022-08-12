package com.example.shepin.controller;

import com.alibaba.fastjson.JSON;
import com.example.shepin.common.RequestMethod;
import com.example.shepin.model.goods.GoodsAllQueryModel;
import com.example.shepin.model.goods.GoodsCreateModel;
import com.example.shepin.model.goods.GoodsQueryModel;
import com.example.shepin.request.goods.GoodsQueryRequest;
import com.example.shepin.response.JackyunResponse;
import com.example.shepin.response.goods.GoodsQueryResponse;
import com.example.shepin.service.JackyunOpenClientService;
import com.example.shepin.utils.JsonUtils;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.goods.GoodsCreateVO;
import com.example.shepin.vo.goods.GoodsVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController{

    private static JackyunOpenClientService clientService = new JackyunOpenClientService();

    @GetMapping("query")
    public void getgoodsList(){
        //封装查询参数
        GoodsQueryModel model = getGoodsQueryModel();

        GoodsQueryRequest request = new GoodsQueryRequest();
        request.setBizModel(model);
        request.setRequestMethod(RequestMethod.POST);

        // 发送请求
        GoodsQueryResponse response = client.execute(request);
        if (response.isSuccess()) {
            CommonPageVO<GoodsVO> content = response.getContent();
            List<GoodsVO> list = content.getList();
            if (list != null) {
                System.out.println("返回结果:" + JSON.toJSONString(list));
//                GoodsCreateVO goodsCreateVO = getGoodsCreateModel(list);
//                JackyunResponse jackresponse = clientService.call("erp.goods.skuimportbatch", "v1.0", JsonUtils.toJson(goodsCreateVO.getGoodsCreateModelList()));
//                System.out.println(jackresponse);
            }

        } else {
            System.out.println("返回结果:" + JSON.toJSONString(response));
        }
    }

    @GetMapping("queryAll")
    public void getAllgoodsList(){
        //封装查询参数
        GoodsAllQueryModel model = new GoodsAllQueryModel();
        model.setChannelNo("3046");

        GoodsQueryRequest request = new GoodsQueryRequest();
        request.setBizModel(model);
        request.setRequestMethod(RequestMethod.POST);

        // 发送请求
        GoodsQueryResponse response = client.execute(request);
        if (response.isSuccess()) {
            CommonPageVO<GoodsVO> content = response.getContent();
            List<GoodsVO> list = content.getList();
            if (list != null) {
                System.out.println("返回结果:" + JSON.toJSONString(list));
                GoodsCreateVO goodsCreateVO = getGoodsCreateModel(list);
                JackyunResponse jackresponse = clientService.call("erp.goods.skuimportbatch", "v1.0", JsonUtils.toJson(goodsCreateVO.getGoodsCreateModelList()));
                System.out.println(jackresponse);
            }

        } else {
            System.out.println("返回结果:" + JSON.toJSONString(response));
        }
    }

    /**
     * 封装查询参数
     * @return
     */
    private GoodsQueryModel getGoodsQueryModel() {
        String startTime = "2022-06-13 00:00:00";
        String endTime = "2022-06-14 00:00:00";
        String channelNo = "3046";
        // 创建请求对象
        // 请求参数
        GoodsQueryModel model = new GoodsQueryModel();
        model.setPageNo(1);
        model.setPageSize(10);
        model.setStartTime(startTime);
        model.setEndTime(endTime);
        model.setChannelNo(channelNo);
        return model;
    }

    /**
     * 封装吉客云商品创建参数
     */
    private GoodsCreateVO getGoodsCreateModel(List<GoodsVO> list) {
        List<GoodsCreateModel> goodsCreateModelList = new ArrayList<>();
        // 创建请求对象
        GoodsCreateVO goodsCreateVO = new GoodsCreateVO();
        for(GoodsVO goodsVO : list){
            GoodsCreateModel goodsCreateModel =new GoodsCreateModel();
            goodsCreateModel.setGoodsNo(goodsVO.getGoodsModel());
            goodsCreateModel.setGoodsName(goodsVO.getGoodsName());
            goodsCreateModel.setCateCode("04");
            goodsCreateModel.setCateName("奢品");
            goodsCreateModel.setUnitName("PCS");
            goodsCreateModel.setOutSkuCode(goodsVO.getSkuNo());
            goodsCreateModel.setSkuName(goodsVO.getSalePropertiesName());
            goodsCreateModel.setSkuBarcode(goodsVO.getBarcode());
            goodsCreateModel.setOwnerCode("101");
            goodsCreateModel.setMainGoodsUrl(goodsVO.getGoodsMainImage());
            goodsCreateModelList.add(goodsCreateModel);
        }
        goodsCreateVO.setGoodsCreateModelList(goodsCreateModelList);
        return goodsCreateVO;
    }

}
