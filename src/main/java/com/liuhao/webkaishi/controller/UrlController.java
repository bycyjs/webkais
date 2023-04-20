package com.liuhao.webkaishi.controller;

import com.liuhao.webkaishi.entity.Pager;
import com.liuhao.webkaishi.entity.Url;
import com.liuhao.webkaishi.service.impl.UrlServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    UrlServiceImpl urlService;

    @GetMapping("/imgUrlFindByUserPager")
    Pager<Url> imgUrlFindByUserPager(int page, int size){

        return urlService.imgUrlFindByUserPager(page,size);
    };
}
