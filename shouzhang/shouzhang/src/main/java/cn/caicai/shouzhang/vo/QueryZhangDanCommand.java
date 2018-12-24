package cn.caicai.shouzhang.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QueryZhangDanCommand implements Serializable {

    private String pageSize;
    private String pageNum;
}
