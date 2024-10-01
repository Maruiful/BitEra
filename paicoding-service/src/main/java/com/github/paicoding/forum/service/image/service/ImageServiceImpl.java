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
    

    @Override
    public String saveImg(HttpServletRequest request) { return null; }

    @Override
    public String saveImg(String img) { return null; }

    
    public String mdImgReplace(String content) { return null; }

    
    public ByteArrayInputStream toByteArrayInputStream(InputStream inputStream) throws IOException  { return null; }
}
