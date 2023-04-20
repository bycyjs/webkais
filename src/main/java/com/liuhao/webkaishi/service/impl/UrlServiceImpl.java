package com.liuhao.webkaishi.service.impl;

import com.liuhao.webkaishi.entity.Pager;
import com.liuhao.webkaishi.entity.Url;
import com.liuhao.webkaishi.mapper.UrlMapper;
import com.liuhao.webkaishi.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    UrlMapper urlMapper;
    @Override
    public Pager<Url> imgUrlFindByUserPager(int page, int size) {
        Map<String,Object> map=new HashMap<>();
        map.put("page", (page - 1) * size);
        map.put("size", size);
        List<Url> userByPage = urlMapper.imgfindUserByPage(map);
        Pager<Url> pager = new Pager<Url>();
        pager.setData(userByPage);
        pager.setTotal(urlMapper.imgfindUserCount());
        return pager;
    }
}
