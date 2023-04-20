package com.liuhao.webkaishi.service;

import com.liuhao.webkaishi.entity.Pager;
import com.liuhao.webkaishi.entity.Url;


public interface UrlService {

    Pager<Url> imgUrlFindByUserPager(int page, int size);
}
