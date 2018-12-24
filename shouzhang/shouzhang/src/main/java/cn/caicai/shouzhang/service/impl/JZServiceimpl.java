package cn.caicai.shouzhang.service.impl;

import cn.caicai.shouzhang.entity.ZhangDan;
import cn.caicai.shouzhang.mapper.ZhangDanMapper;
import cn.caicai.shouzhang.service.JZService;
import cn.caicai.shouzhang.vo.ResponseView;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
@Service
public class JZServiceimpl implements JZService {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    ZhangDanMapper zhangDanMapper;

    ResponseView responseView = new ResponseView();


    @Override
    public String updateZD(String money, String goodsName, Integer lFlag, String remark, Integer id) {
        ZhangDan zd = new ZhangDan();
        zd.setMoney(ObjectUtils.isEmpty(money) ? BigDecimal.ZERO : new BigDecimal(money));
        zd.setlFlag(lFlag);
        zd.setId(id);
        zd.setDeleteFlag(1);
        zd.setCreateTime(new Date());
        zd.setGoodsName(goodsName);
        zd.setRemark(remark);
        try {
            int i = zhangDanMapper.updateByPrimaryKeySelective(zd);
            panDuan(i);
        } catch (Exception e) {
            logger.info("zhangDanMapper.updateByPrimaryKeySelective更新失败！" + e);
        }
        return JSONObject.toJSONString(responseView);
    }


    /**
     * 添加账单
     *
     * @param requset
     * @return
     */
    @Override
    public String saveZD(String money, String goodsName, Integer lFlag, String remark) {
        ZhangDan zd = new ZhangDan();

        zd.setMoney(ObjectUtils.isEmpty(money) ? BigDecimal.ZERO : new BigDecimal(money));
        zd.setlFlag(lFlag);
        zd.setDeleteFlag(1);
        zd.setCreateTime(new Date());
        zd.setGoodsName(goodsName);
        zd.setRemark(remark);

        try {
            int i = zhangDanMapper.insertSelective(zd);
            panDuan(i);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("zhangDanMapper.insertSelective失败！" + e);
        }
        return JSONObject.toJSONString(responseView);
    }

    /**
     * 查询所有账单
     *
     * @return
     */
    @Override
    public PageInfo<ZhangDan> queryAll(ZhangDan zhangDan, PageInfo pageInfo) {
        PageHelper.offsetPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<ZhangDan> zhangDans = zhangDanMapper.selectZhangDanByZhangDan(zhangDan);
        PageInfo<ZhangDan> page = new PageInfo<>(zhangDans);
        return page;
    }

    /**
     * 删除账单
     *
     * @param requset
     * @return
     */
    @Override
    public String deleteZD(String id) {
        try {
            int i = zhangDanMapper.deleteByPrimaryKey(Integer.valueOf(id));
            panDuan(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.info("zhangDanMapper.deleteByPrimaryKey 失败" + e);
        }
        return JSONObject.toJSONString(responseView);
    }

    /**
     * 判断i
     *
     * @param i
     */
    private void panDuan(int i) {
        if (i == 1) {
            responseView.setResultStatus("1");
            responseView.setResultMsg("成功");
        } else {
            responseView.setResultStatus("0");
            responseView.setResultMsg("失败！");
        }
    }
}
