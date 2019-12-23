package com.zhku.mh.controller;

import com.qiniu.common.QiniuException;
import com.zhku.mh.bean.EChartResult;
import com.zhku.mh.bean.EChart;
import com.zhku.mh.bean.EditorResult;
import com.zhku.mh.util.QiNiuFinal;
import com.zhku.mh.util.QiNiuYunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：
 * Time：2019/12/20 11:17
 * Description：
 * Author： mh
 */
@Controller
public class EditorController {

    @Autowired
    private QiNiuYunUtil qiNiuYunUtil;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("text","<p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p><p><img src=\"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2229864841,4232235061&fm=27&gp=0.jpg\" style=\"max-width:100%;\"><br></p>");
        return "editor";
    }

    @RequestMapping("/index2")
    public String index2(Model model) {
        model.addAttribute("text","<p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p><p><img src=\"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2229864841,4232235061&fm=27&gp=0.jpg\" style=\"max-width:100%;\"><br></p>");
        return "editor2";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public EditorResult upload(@RequestParam("imageFile") MultipartFile multipartFile) throws QiniuException {
        EditorResult result = new EditorResult();
        List<String> urlList = new ArrayList<>();

        if (multipartFile == null) {
            result.setErrno(1);
            return result;
        }

        String path = qiNiuYunUtil.upload(multipartFile, "system/images/");
        urlList.add(QiNiuFinal.QiNiuPerfix + path);
        result.setErrno(0);
        result.setData(urlList);
        return result;
    }

    @RequestMapping("/echart")
    public String echart(){
        return "echart";
    }

    @RequestMapping("/echart2")
    public String echart2(){
        return "echart2";
    }

    @RequestMapping("/getEchart")
    @ResponseBody
    public EChartResult getEchart(){
        EChartResult eChartResult = new EChartResult();

        List<String> legendList = new ArrayList<>();
        legendList.add("销量");
        legendList.add("库存");

        List<String> cateList = new ArrayList<>();
//        "衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"
        cateList.add("衬衫");
        cateList.add("羊毛衫");
        cateList.add("雪纺衫");
        cateList.add("裤子");
        cateList.add("高跟鞋");
        cateList.add("袜子");

        List<EChart> resultList = new ArrayList<>();

        EChart sale = new EChart();
        sale.setName("销量");
        sale.setType("line");
        List<Integer> saleList = new ArrayList<>();
        saleList.add(123);
        saleList.add(345);
        saleList.add(566);
        saleList.add(456);
        saleList.add(423);
        saleList.add(466);
        sale.setData(saleList);

        EChart stock = new EChart();
        stock.setName("库存");
        stock.setType("bar");
        List<Integer> stockList = new ArrayList<>();
        stockList.add(10);
        stockList.add(20);
        stockList.add(56);
        stockList.add(46);
        stockList.add(23);
        stockList.add(16);
        stock.setData(stockList);

       resultList.add(sale);
       resultList.add(stock);

       eChartResult.setLegend(legendList);
       eChartResult.setCategories(cateList);
       eChartResult.setSeriesList(resultList);

        return eChartResult;
    }


}
