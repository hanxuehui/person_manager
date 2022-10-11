package com.ricky.manager.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.OverhaulPerson;
import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.service.OverhaulPersonService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.utils.easyexcel.ExcelUtil;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.overhaul.OverhaulPersonExportVO;
import com.ricky.manager.vo.overhaul.OverhaulPersonImportVO;
import com.ricky.manager.vo.overhaul.OverhaulPersonQueryCond;
import com.ricky.manager.vo.overhaul.OverhaulPersonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 检修人员 前端控制器
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
@Slf4j
@RestController
@RequestMapping("/overhaulPerson")
@Api(value = "检修人员管理", tags = "检修人员管理接口")
public class OverhaulPersonController {
    /**
     * 服务对象
     */
    @Autowired
    private OverhaulPersonService overhaulPersonService;

    @ApiOperation(value = "分页查询检修人员信息", notes = "分页查询检修人员信息")
    @ApiImplicitParam(name = "vo", value = "检修人员查询对象", required = true, dataType = "OverhaulPersonQueryCond")
    @PostMapping("/queryPage")
    public PageInfo<OverhaulPerson> queryPage(@RequestBody OverhaulPersonQueryCond cond){
        return overhaulPersonService.queryPage(cond);
    }

    /**
     * 新增数据
     *
     * @param vo 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增检修人员", notes = "新增检修人员")
    @ApiImplicitParam(name = "vo", value = "检修人员", required = true, dataType = "OverhaulPersonVO")
    @PostMapping("/add")
    public BaseResponseBean add(@Validated @RequestBody OverhaulPersonVO vo) throws LogicException {
        overhaulPersonService.insert(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 编辑数据
     *
     * @param vo 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "新增检修人员", notes = "新增检修人员")
    @ApiImplicitParam(name = "vo", value = "检修人员", required = true, dataType = "OverhaulPersonVO")
    @PostMapping("/edit")
    public BaseResponseBean edit(@Validated @RequestBody OverhaulPersonVO vo) throws LogicException {
        overhaulPersonService.edit(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 删除数据
     *
     * @param vo 实体
     * @return 删除结果
     */
    @ApiOperation(value = "删除检修人员", notes = "删除检修人员")
    @ApiImplicitParam(name = "vo", value = "id集合", required = true, dataType = "BaseListVO")
    @PostMapping("/delete")
    public BaseResponseBean delete(@Validated @RequestBody BaseListVO vo) {
        overhaulPersonService.delete(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * Excel数据导入
     *
     * @param file 实体
     * @return 导入结果
     */
    @ApiOperation(value = "Excel数据导入", notes = "Excel数据导入")
    @PostMapping("/importExcel")
    public BaseResponseBean importExcel(@RequestParam(value = "file") MultipartFile file) {
        try {
            overhaulPersonService.importExcel(ExcelUtil.readExcel(file, new OverhaulPersonImportVO(), 1, 1));
            return BaseResponseBean.ok("success");
        }catch (Exception e){
            log.error(e.getMessage());
            return BaseResponseBean.error(e.getMessage());
        }
    }

    /**
     * Excel数据导出
     *
     * @param cond 查询参数
     * @param response 响应参数            
     * @return 导出结果
     */
    @ApiOperation(value = "Excel数据导出", notes = "Excel数据导出")
    @PostMapping("/exportExcel")
    public String exportExcel(@RequestBody OverhaulPersonQueryCond cond, HttpServletResponse response){
        List<OverhaulPerson> overhaulPersonList = overhaulPersonService.queryList(cond);
        List<OverhaulPersonExportVO> exportList = new ArrayList<>();
        DtoVoTransform.populateList(overhaulPersonList, exportList, OverhaulPersonExportVO.class);
        try {
            response.setContentType("application/binary;charset=UTF-8");
            response.addHeader("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = EasyExcel.write(out).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "检修人员信息列表").head(OverhaulPersonExportVO.class).build();
            writer.write(exportList, writeSheet);
            writer.finish();
        } catch (IOException e) {
            return "检修人员信息列表Excel下载失败:" + e.getMessage();
        }
        return null;
    }
}
