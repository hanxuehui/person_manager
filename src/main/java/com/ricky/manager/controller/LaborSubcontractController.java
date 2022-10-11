package com.ricky.manager.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageInfo;
import com.ricky.manager.entity.LaborSubcontract;
import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.service.LaborSubcontractService;
import com.ricky.manager.utils.DtoVoTransform;
import com.ricky.manager.utils.easyexcel.ExcelUtil;
import com.ricky.manager.vo.base.BaseListVO;
import com.ricky.manager.vo.labor.LaborSubcontractExportVO;
import com.ricky.manager.vo.labor.LaborSubcontractImportVO;
import com.ricky.manager.vo.labor.LaborSubcontractQueryCond;
import com.ricky.manager.vo.labor.LaborSubcontractVO;
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
 * 劳务分包(LaborSubcontract)表控制层
 *
 * @author makejava
 * @since 2022-05-12 14:54:29
 */
@Slf4j
@RestController
@RequestMapping("laborSubcontract")
@Api(value = "劳务分包管理", tags = "劳务分包管理接口")
public class LaborSubcontractController {
    /**
     * 服务对象
     */
    @Autowired
    private LaborSubcontractService laborSubcontractService;

    @ApiOperation(value = "分页查询劳务分包信息", notes = "分页查询劳务分包信息")
    @ApiImplicitParam(name = "vo", value = "劳务分包查询对象", required = true, dataType = "LaborSubcontractQueryCond")
    @PostMapping("/queryPage")
    public PageInfo<LaborSubcontract> queryPage(@RequestBody LaborSubcontractQueryCond cond){
        return laborSubcontractService.queryPage(cond);
    }

    /**
     * 新增数据
     *
     * @param vo 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增劳务分包", notes = "新增劳务分包")
    @ApiImplicitParam(name = "vo", value = "劳务分包", required = true, dataType = "LaborSubcontractVO")
    @PostMapping("/add")
    public BaseResponseBean add(@Validated @RequestBody LaborSubcontractVO vo) throws LogicException {
        laborSubcontractService.insert(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 编辑数据
     *
     * @param vo 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "新增劳务分包", notes = "新增劳务分包")
    @ApiImplicitParam(name = "vo", value = "劳务分包", required = true, dataType = "LaborSubcontractVO")
    @PostMapping("/edit")
    public BaseResponseBean edit(@Validated @RequestBody LaborSubcontractVO vo) throws LogicException {
        laborSubcontractService.edit(vo);
        return BaseResponseBean.ok("success");
    }

    /**
     * 删除数据
     *
     * @param vo 实体
     * @return 删除结果
     */
    @ApiOperation(value = "删除劳务分包", notes = "删除劳务分包")
    @ApiImplicitParam(name = "vo", value = "id集合", required = true, dataType = "BaseListVO")
    @PostMapping("/delete")
    public BaseResponseBean delete(@Validated @RequestBody BaseListVO vo) {
        laborSubcontractService.delete(vo);
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
            laborSubcontractService.importExcel(ExcelUtil.readExcel(file, new LaborSubcontractImportVO(), 1, 1));
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
    public String exportExcel(@RequestBody LaborSubcontractQueryCond cond, HttpServletResponse response){
        List<LaborSubcontract> laborSubcontractList = laborSubcontractService.queryList(cond);
        List<LaborSubcontractExportVO> exportList = new ArrayList<>();
        DtoVoTransform.populateList(laborSubcontractList, exportList, LaborSubcontractExportVO.class);
        try {
            response.setContentType("application/binary;charset=UTF-8");
            response.addHeader("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = EasyExcel.write(out).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "劳务外包信息列表").head(LaborSubcontractExportVO.class).build();
            writer.write(exportList, writeSheet);
            writer.finish();
        } catch (IOException e) {
            return "劳务外包信息列表Excel下载失败:" + e.getMessage();
        }
        return null;
    }
}

