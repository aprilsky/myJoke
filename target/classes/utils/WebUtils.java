package utils;

/**
 * @Author: caoxiao
 * @Date: 12-12-8 下午6:00
 */
/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Struts2 Utils类.
 *
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 *
 * @author calvin
 */
public class WebUtils {

    // -- header 常量定义 --//
    private static final String ENCODING_PREFIX = "encoding";
    private static final String NOCACHE_PREFIX = "no-cache";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final boolean NOCACHE_DEFAULT = true;

    // -- content-type 常量定义 --//
    private static final String TEXT_TYPE = "text/plain";
    private static final String JSON_TYPE = "application/json";
    private static final String XML_TYPE = "text/xml";
    private static final String HTML_TYPE = "text/html";
    private static final String JS_TYPE = "text/javascript";
    private static Log logger = LogFactory.getLog(WebUtils.class);



}

