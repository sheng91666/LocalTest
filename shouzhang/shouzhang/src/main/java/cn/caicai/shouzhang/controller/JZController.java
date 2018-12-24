package cn.caicai.shouzhang.controller;

import cn.caicai.shouzhang.entity.ZhangDan;
import cn.caicai.shouzhang.service.JZService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class JZController {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private JZService jZService;


    @RequestMapping("/")
    @ResponseBody
    public ModelAndView select() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "home");
        modelAndView.setViewName("home");
        return modelAndView;
    }

//    @RequestMapping("/")
//    public String index(){
//        return "static/jsp/home";
//    }


    /**
     * 保存账单
     *
     * @param
     * @return
     */
    @RequestMapping("saveZD")
    @ResponseBody
    public String saveZD(@RequestParam("money") String money,
                         @RequestParam("goodsName") String goodsName,
                         @RequestParam("lFlag") Integer lFlag,
                         @RequestParam("remark") String remark) {
        return jZService.saveZD(money, goodsName, lFlag, remark);
    }

    /**
     * 查询所有账单
     *
     * @return
     */
    @RequestMapping("queryAll")
    @ResponseBody
    public List<ZhangDan> queryAll(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum) {
        logger.info("查询所有账单----pageNum：{}", JSON.toJSON(pageNum));
        logger.info("查询所有账单----pageSize：{}", JSON.toJSON(pageSize));

        ZhangDan zhangDan = new ZhangDan();
        PageInfo<ZhangDan> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        PageInfo<ZhangDan> pageInfo1 = jZService.queryAll(zhangDan, pageInfo);
        List<ZhangDan> list = pageInfo1.getList();
        return list;
    }

    /**
     * 删除账单
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteZD")
    @ResponseBody
    public String deleteZD(@RequestParam("id") String id) {
        String response = jZService.deleteZD(id);
        return response;
    }

    /**
     * 更新账单
     *
     * @param
     * @return
     */
    @RequestMapping("updateZD")
    @ResponseBody
    public String updateZD(@RequestParam("money") String money,
                           @RequestParam("goodsName") String goodsName,
                           @RequestParam("lFlag") Integer lFlag,
                           @RequestParam("remark") String remark,
                           @RequestParam("id") Integer id) {
        String response = jZService.updateZD(money, goodsName, lFlag, remark, id);
        return response;
    }

}
