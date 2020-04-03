package com.hyp.learn.cf.utils;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.cf.commons.object.PageVO;

/**
 * 分页工具类
 */
public class PageUtils {
    private PageUtils() {
    }

    public static <T> PageVO<T> getPageVO(IPage<T> list) {
        PageVO<T> result = new PageVO<>();
        Page<T> page = (Page<T>) list;
        result.setTotalRows(page.getTotal());
        result.setTotalPages(page.getPages());
        result.setPageNum(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setCurPageSize(page.getRecords().size());
        result.setList(page.getRecords());
        return result;
    }
}
