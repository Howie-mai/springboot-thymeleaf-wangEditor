package com.zhku.mh.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName:
 * @description
 * @author: mh
 * @create: 2019-09-06 14:25
 */
@Component
public class QiNiuYunUtil {
    /**
     * 七牛云密钥
     */
    private final String accessKey = "xxxx";
    /**
     * 密码
     */
    private final String secretKey = "xxxx";
    /**
     * 仓库名字
     */
    private final String bucketName = "xxxx";

    Auth auth = Auth.create(accessKey, secretKey);
    /**
     * 七牛云空间的地区，我的是华南
     */
    Configuration cfg = new Configuration(Zone.zone2());
    private UploadManager uploadManager = new UploadManager(cfg);

    BucketManager bucketManager = new BucketManager(auth, cfg);

    private String getUploadToken() {
        //密钥配置
        String uploadToken = auth.uploadToken(bucketName);
        return uploadToken;
    }

    public String upload(MultipartFile multipartFile, String prefix) throws QiniuException {
        @SuppressWarnings("unused")
        String fileName = multipartFile.getOriginalFilename();
        /*
         * MultipartFile 转为 File
         */
        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Response res = uploadManager.put(file, prefix + uuid, getUploadToken());

        /*
         * 解析上传成功的结果
         */
        DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);

        return putRet.key;
    }

    public String download(String Url) {
        return auth.privateDownloadUrl(Url);
    }

    public BucketManager.FileListIterator query(String prefix, Integer limit, String delimiter) {
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucketName, prefix, limit, delimiter);
        return fileListIterator;
    }

    public void delete(String fileName) throws QiniuException {
        bucketManager.delete(bucketName, fileName);
    }
}
