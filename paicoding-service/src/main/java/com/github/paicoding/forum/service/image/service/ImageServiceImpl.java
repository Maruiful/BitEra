package com.github.paicoding.forum.service.image.service;

import com.github.hui.quick.plugin.base.constants.MediaType;
import com.github.hui.quick.plugin.base.file.FileReadUtil;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.core.async.AsyncExecute;
import com.github.paicoding.forum.core.async.AsyncUtil;
import com.github.paicoding.forum.core.mdc.MdcDot;
import com.github.paicoding.forum.core.util.MdImgLoader;
import com.github.paicoding.forum.service.image.oss.ImageUploader;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/** */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageUploader imageUploader;

    

    @Override
    public String saveImg(HttpServletRequest request) { return null; }

    @Override
    public String saveImg(String img) { if (imageUploader.uploadIgnore(img)) {
        // 已经转存过，不需要再次转存；非http图片，不处理
        return img;
    }

        try {
            InputStream stream = FileReadUtil.getStreamByFileName(img);
            URI uri = URI.create(img);
            String path = uri.getPath();
            int index = path.lastIndexOf(".");
            String fileType = null;
            if (index > 0) {
                // 从url中获取文件类型
                fileType = path.substring(index + 1);
            }
            String digest = calculateSHA256(stream);
            String ans = imgReplaceCache.getIfPresent(digest);
            if (StringUtils.isBlank(ans)) {
                ans = imageUploader.upload(stream, fileType);
                imgReplaceCache.put(digest, ans);
            }
            if (StringUtils.isBlank(ans)) {
                return buildUploadFailImgUrl(img);
            }
            return ans;
        } catch (Exception e) {
            log.error("外网图片转存异常! img:{}", img, e);
            return buildUploadFailImgUrl(img);
        } }

    
    public String mdImgReplace(String content) { return null; }

    
    public ByteArrayInputStream toByteArrayInputStream(InputStream inputStream) throws IOException  { return null; }
}
