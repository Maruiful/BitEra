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

/**
 *
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageUploader imageUploader;


    @Override
    public String saveImg(HttpServletRequest request) {
        return null;
    }

    @Override
    public String saveImg(String img) {
        if (imageUploader.uploadIgnore(img)) {
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
        }
    }


    /**
     * 外网图片自动转存，添加了执行日志，超时限制；避免出现因为超时导致发布文章异常
     *
     * @param content
     * @return
     */
    @Override
    @MdcDot
    @AsyncExecute(timeOutRsp = "#content")
    public String mdImgReplace(String content) {
        List<MdImgLoader.MdImg> imgList = MdImgLoader.loadImgs(content);
        if (CollectionUtils.isEmpty(imgList)) {
            return content;
        }

        if (imgList.size() == 1) {
            // 只有一张图片时，没有必要走异步，直接转存并返回
            MdImgLoader.MdImg img = imgList.get(0);
            String newImg = saveImg(img.getUrl());
            return StringUtils.replace(content, img.getOrigin(), "![" + img.getDesc() + "](" + newImg + ")");
        }

        // 超过1张图片时，做并发的图片转存，提升性能
        Map<MdImgLoader.MdImg, String> imgReplaceMap = new ConcurrentHashMap<>();
        try (AsyncUtil.CompletableFutureBridge bridge = AsyncUtil.concurrentExecutor("MdImgReplace")) {
            for (MdImgLoader.MdImg img : imgList) {
                bridge.async(() -> {
                    imgReplaceMap.put(img, saveImg(img.getUrl()));
                }, img.getUrl());
            }
        }

        // 图片替换
        for (Map.Entry<MdImgLoader.MdImg, String> entry : imgReplaceMap.entrySet()) {
            MdImgLoader.MdImg img = entry.getKey();
            String newImg = entry.getValue();
            content = StringUtils.replace(content, img.getOrigin(), "![" + img.getDesc() + "](" + newImg + ")");
        }
        return content;
    }


    public ByteArrayInputStream toByteArrayInputStream(InputStream inputStream) throws IOException {
        return null;
    }
}
