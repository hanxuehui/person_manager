package com.ricky.manager.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.auth.annotation.IgnoreUserToken;
import com.ricky.manager.config.fdfs.FdfsClientProperies;
import com.ricky.manager.config.fdfs.FdfsConfig;
import com.ricky.manager.entity.InnerPerson;
import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.service.InnerPersonService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.utils.easyexcel.ExcelUtil;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.inner.InnerPersonExportVO;
import com.ricky.manager.vo.inner.InnerPersonImportVO;
import com.ricky.manager.vo.inner.InnerPersonQueryCond;
import com.ricky.manager.vo.inner.InnerPersonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 内部人员(InnerPerson)表控制层
 *
 * @author makejava
 * @since 2022-05-09 10:26:49
 */
@Slf4j
@RestController
@RequestMapping(value = "innerPerson")
@Api(value = "内部人员管理", tags = "内部人员管理接口")
public class InnerPersonController {
    /**
     * 服务对象
     */
    @Autowired
    private InnerPersonService innerPersonService;

    @ApiOperation(value = "分页查询内部人员信息", notes = "分页查询内部人员信息")
    @ApiImplicitParam(name = "vo", value = "内部人员查询对象", required = true, dataType = "InnerPersonQueryCond")
    @PostMapping("/queryPage")
    public PageInfo<InnerPerson> queryPage(@RequestBody InnerPersonQueryCond cond){
        return innerPersonService.queryPage(cond);
    }

    /**
     * 新增数据
     *
     * @param vo 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增内部人员", notes = "新增内部人员")
    @ApiImplicitParam(name = "vo", value = "内部人员", required = true, dataType = "InnerPersonVO")
    @PostMapping("/add")
    public BaseResponseBean add(@Validated @ModelAttribute InnerPersonVO vo) throws LogicException, IOException {
        innerPersonService.insert(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 编辑数据
     *
     * @param vo 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑内部人员", notes = "编辑内部人员")
    @ApiImplicitParam(name = "vo", value = "内部人员", required = true, dataType = "InnerPersonVO")
    @PostMapping("/edit")
    public BaseResponseBean edit(@Validated @ModelAttribute InnerPersonVO vo) throws LogicException, IOException {
        innerPersonService.edit(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 删除数据
     *
     * @param vo 实体
     * @return 删除结果
     */
    @ApiOperation(value = "删除内部人员", notes = "删除内部人员")
    @ApiImplicitParam(name = "vo", value = "id集合", required = true, dataType = "BaseListVO")
    @PostMapping("/delete")
    public BaseResponseBean delete(@Validated @RequestBody BaseListVO vo) {
        innerPersonService.delete(vo);
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
            innerPersonService.importExcel(ExcelUtil.readExcel(file, new InnerPersonImportVO(), 1, 1));
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
    public String exportExcel(@RequestBody InnerPersonQueryCond cond, HttpServletResponse response){
        List<InnerPerson> innerPersonList = innerPersonService.queryList(cond);
        List<InnerPersonExportVO> exportList = new ArrayList<>();
        DtoVoTransform.populateList(innerPersonList, exportList, InnerPersonExportVO.class);
        try {
            response.setContentType("application/binary;charset=UTF-8");
            response.addHeader("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = EasyExcel.write(out).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "内部人员信息列表").head(InnerPersonExportVO.class).build();
            writer.write(exportList, writeSheet);
            writer.finish();
        } catch (IOException e) {
            return "内部人员信息列表Excel下载失败:" + e.getMessage();
        }
        return null;
    }


}

