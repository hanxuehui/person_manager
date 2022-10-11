package com.ricky.manager.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.ProfessionSubcontract;
import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.service.ProfessionSubcontractService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.utils.easyexcel.ExcelUtil;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.profession.ProfessionSubcontractExportVO;
import com.ricky.manager.vo.profession.ProfessionSubcontractImportVO;
import com.ricky.manager.vo.profession.ProfessionSubcontractQueryCond;
import com.ricky.manager.vo.profession.ProfessionSubcontractVO;
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
 * 专业分包 前端控制器
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-13
 */
@Slf4j
@RestController
@RequestMapping("/professionSubcontract")
@Api(value = "专业分包管理", tags = "专业分包管理接口")
public class ProfessionSubcontractController {
    /**
     * 服务对象
     */
    @Autowired
    private ProfessionSubcontractService professionSubcontractService;

    @ApiOperation(value = "分页查询专业分包信息", notes = "分页查询专业分包信息")
    @ApiImplicitParam(name = "vo", value = "专业分包查询对象", required = true, dataType = "ProfessionSubcontractQueryCond")
    @PostMapping("/queryPage")
    public PageInfo<ProfessionSubcontract> queryPage(@RequestBody ProfessionSubcontractQueryCond cond){
        return professionSubcontractService.queryPage(cond);
    }

    /**
     * 新增数据
     *
     * @param vo 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增专业分包", notes = "新增专业分包")
    @ApiImplicitParam(name = "vo", value = "专业分包", required = true, dataType = "ProfessionSubcontractVO")
    @PostMapping("/add")
    public BaseResponseBean add(@Validated @ModelAttribute ProfessionSubcontractVO vo) throws LogicException, IOException {
        professionSubcontractService.insert(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 编辑数据
     *
     * @param vo 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "新增专业分包", notes = "新增专业分包")
    @ApiImplicitParam(name = "vo", value = "专业分包", required = true, dataType = "ProfessionSubcontractVO")
    @PostMapping("/edit")
    public BaseResponseBean edit(@Validated @ModelAttribute ProfessionSubcontractVO vo) throws LogicException, IOException {
        professionSubcontractService.edit(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 删除数据
     *
     * @param vo 实体
     * @return 删除结果
     */
    @ApiOperation(value = "删除专业分包", notes = "删除专业分包")
    @ApiImplicitParam(name = "vo", value = "id集合", required = true, dataType = "BaseListVO")
    @PostMapping("/delete")
    public BaseResponseBean delete(@Validated @RequestBody BaseListVO vo) {
        professionSubcontractService.delete(vo);
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
            professionSubcontractService.importExcel(ExcelUtil.readExcel(file, new ProfessionSubcontractImportVO(), 1, 1));
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
    public String exportExcel(@RequestBody ProfessionSubcontractQueryCond cond, HttpServletResponse response){
        List<ProfessionSubcontract> professionSubcontractList = professionSubcontractService.queryList(cond);
        List<ProfessionSubcontractExportVO> exportList = new ArrayList<>();
        DtoVoTransform.populateList(professionSubcontractList, exportList, ProfessionSubcontractExportVO.class);
        try {
            response.setContentType("application/binary;charset=UTF-8");
            response.addHeader("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = EasyExcel.write(out).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "专业分包信息列表").head(ProfessionSubcontractExportVO.class).build();
            writer.write(exportList, writeSheet);
            writer.finish();
        } catch (IOException e) {
            return "专业分包信息列表Excel下载失败:" + e.getMessage();
        }
        return null;
    }
}
