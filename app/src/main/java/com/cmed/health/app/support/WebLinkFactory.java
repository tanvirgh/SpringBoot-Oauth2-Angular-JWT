package com.cmed.health.app.support;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebLinkFactory {
    @Autowired
    private HttpServletRequest request;

    private UriComponentsBuilder getUriBuilder() {
        try {
            return ServletUriComponentsBuilder.fromServletMapping(request);
        } catch (Exception e) {
            try {
                return UriComponentsBuilder.fromUriString(URLEncoder.encode(request.getRequestURL().toString(), "UTF-8"))
                        .replacePath(StringUtils.EMPTY)
                        .replaceQuery(StringUtils.EMPTY)
                        .path(request.getContextPath() + "/");
            } catch (UnsupportedEncodingException e1) {
                return UriComponentsBuilder.fromPath(request.getContextPath() + "/");
            }
        }
    }

    private String build(String path) {
        return getUriBuilder().path(path).build().toString();
    }


    public String imagesDir() {
        String urlPath =  build("/images/").toString();
        String uri = request.getScheme() + "://" +   // "http" + "://
                request.getServerName() +       // "myhost"
                ":" + request.getServerPort();

        urlPath = urlPath.replace(uri,"");
        return urlPath;
    }

}
