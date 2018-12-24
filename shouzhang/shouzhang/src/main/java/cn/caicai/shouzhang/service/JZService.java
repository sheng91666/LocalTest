package cn.caicai.shouzhang.service;

import cn.caicai.shouzhang.entity.ZhangDan;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface JZService {

    PageInfo<ZhangDan> queryAll(ZhangDan zhangDan, PageInfo pageInfo);

    String deleteZD(String id);

    String saveZD(String money, String goodsName, Integer lFlag, String remark);

    String updateZD(String money, String goodsName, Integer lFlag, String remark, Integer id);
}
