package com.zhku.mh.controller;

import com.qiniu.common.QiniuException;
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
        model.addAttribute("text","<p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p><p><img src=\"http://image.hymbb.cn/system/images/d7e2a371640e4cc590e2474667eda969\" style=\"max-width:100%;\"><br></p>");
        return "editor";
    }

    @RequestMapping("/index2")
    public String index2(Model model) {
        model.addAttribute("text","<p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p><p><img src=\"http://image.hymbb.cn/system/images/d7e2a371640e4cc590e2474667eda969\" style=\"max-width:100%;\"><br></p>");
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
}
