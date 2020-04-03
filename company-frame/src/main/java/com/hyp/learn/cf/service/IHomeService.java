package com.hyp.learn.cf.service;


import com.hyp.learn.cf.vo.resp.HomeRespVO;

public interface IHomeService {

    HomeRespVO getHomeInfo(String userId);
}
