package cn.caicai.shouzhang.mapper;

import cn.caicai.shouzhang.entity.ZhangDan;

import java.util.List;

public interface ZhangDanMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(ZhangDan record);

    int insertSelective(ZhangDan record);

    int updateByPrimaryKeySelective(ZhangDan record);

    int updateByPrimaryKey(ZhangDan record);

    List<ZhangDan> selectZhangDanByZhangDan(ZhangDan record);
}