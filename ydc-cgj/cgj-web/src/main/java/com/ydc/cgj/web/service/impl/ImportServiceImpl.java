package com.ydc.cgj.web.service.impl;

import com.ydc.cgj.web.feignService.UserFeignService;
import com.ydc.cgj.web.service.ImportService;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.model.cgj.entity.IntegralEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gongjin
 * @create 2018-09-19 10:01
 **/
@Service
public class ImportServiceImpl implements ImportService {

    private static final Logger logger = LogManager.getLogger(ImportService.class);

    @Autowired
    UserFeignService userFeignService;


    @Override
    public String integralImport(String fileName, MultipartFile file, Integer userId) throws Exception {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ServiceRuntimeException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            throw new ServiceRuntimeException("导入数据为空");
        }
//        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
//            sheet = wb.getSheetAt(i);
//        }

        List<IntegralEntity> list = new ArrayList<>();
        IntegralEntity integralEntity = null;
        logger.info("当前sheet总行数" + sheet.getLastRowNum());
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null || row.getCell(0) == null) continue;
            integralEntity = new IntegralEntity();
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            String memberId = row.getCell(0).getStringCellValue();
            if (memberId == null || memberId.isEmpty()) {
                throw new ServiceRuntimeException("导入失败(第" + (r + 1) + "行,会员id未填写)");
            }
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String mobilePhone = row.getCell(1).getStringCellValue();
            if (mobilePhone == null || mobilePhone.isEmpty()) {
                throw new ServiceRuntimeException("导入失败(第" + (r + 1) + "行,会员手机号码未填写)");
            }
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            String usableIntegral = row.getCell(2).getStringCellValue();
            if (usableIntegral == null || usableIntegral.isEmpty()) {
                throw new ServiceRuntimeException("导入失败(第" + (r + 1) + "行,会员充值积分未填写)");
            }
            if (BigDecimal.valueOf(Double.valueOf(usableIntegral)).compareTo(BigDecimal.valueOf(0)) != 1) {
                throw new ServiceRuntimeException("导入积分不能存在负数");
            }
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            String integralTypeAcquire = row.getCell(3).getStringCellValue();
            if (integralTypeAcquire == null || integralTypeAcquire.isEmpty()) {
                throw new ServiceRuntimeException("导入失败(第" + (r + 1) + "行,会员充值类型未填写)");
            }
            integralEntity.setMemberId(Integer.valueOf(memberId));
            integralEntity.setMobilePhone(mobilePhone);
            integralEntity.setUsableIntegral(BigDecimal.valueOf(Double.valueOf(usableIntegral)));
            integralEntity.setOperatorId(userId);
            integralEntity.setIntegralTypeAcquire(Integer.valueOf(integralTypeAcquire));
            list.add(integralEntity);
        }
        is.close();
        if (list.isEmpty()) throw new ServiceRuntimeException("封装数据为空");
        return userFeignService.batchRecharge(list);
    }
}
