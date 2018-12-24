package cn.caicai.shouzhang.vo;

import lombok.Data;

@Data
public class ResponseView {

    /**
     * 返回状态 1：成功 0：失败
     */
    private String resultStatus;

    /**
     * 返回内容
     */
    private String resultMsg;

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
