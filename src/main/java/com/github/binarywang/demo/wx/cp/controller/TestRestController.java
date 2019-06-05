package com.github.binarywang.demo.wx.cp.controller;

import com.github.binarywang.demo.wx.cp.config.WxCpConfiguration;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RestController
public class TestRestController
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping({"/wx"})
    public String testOne()
    {
        return "home";
    }

    @RequestMapping({"/jspapi/{agentId}"})
    @CrossOrigin
    public WxJsapiSignature jsapiTest(@PathVariable Integer agentId, ServletRequest req)
    {
        String url_p = req.getParameter("url");
        if(url_p !=null)
            System.out.printf(url_p);

        final WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);
        WxJsapiSignature wxJsapiSignature = null;
        try {
            wxJsapiSignature = wxCpService.createJsapiSignature(url_p);

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return wxJsapiSignature;
    }
}
