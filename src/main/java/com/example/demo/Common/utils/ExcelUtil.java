//package com.example.demo.Common.utils;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.ExcelImportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.ImportParams;
//import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.lang.StringUtils;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//public class ExcelUtil {
//    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName,boolean isCreateHeader, HttpServletResponse response){
//        ExportParams exportParams = new ExportParams(title, sheetName);
//        exportParams.setCreateHeadRows(isCreateHeader);
//        defaultExport(list, pojoClass, fileName, response, exportParams);
//
//    }
//    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
//        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
//    }
//    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
//        defaultExport(list, fileName, response);
//    }
//
//    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
//        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
//        if (workbook != null);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            workbook.write(response.getOutputStream());
//        } catch (IOException e) {
//            log.error("[monitor][IO][表单功能]", e);
//        }
//    }
//    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
//        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
//        if (workbook != null);
//        downLoadExcel(fileName, response, workbook);
//    }
//
//    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
//        if (StringUtils.isBlank(filePath)){
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        List<T> list = null;
//        try {
//            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
//        }catch (NoSuchElementException e){
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return list;
//    }
//    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
//        if (file == null){
//            return null;
//        }
//        ImportParams params = new ImportParams();
//        params.setTitleRows(titleRows);
//        params.setHeadRows(headerRows);
//        List<T> list = null;
//        try {
//            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
//        }catch (NoSuchElementException e){
//            throw e;
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("[monitor][表单功能]", e);
//        }
//        return list;
//    }
//
//    public static void main(String[] args) {
//        int rowNum = 0;
//        final short titleRowHeight = (short) (20 * 20);
//        final short rowHeight = (short) (28 * 20);
//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet();
//        sheet.createFreezePane( 0, 1, 0, 1 );
//        HSSFCellStyle titleCellStyle = getTitleStyle(wb);
//        HSSFCellStyle contentCellStyle = getContentStyle(wb);
//
//        //30+字段
//        String[] rowName = new String[]{"商品编号", "商品名称", "拍卖编号", "发布日期", "拍品拍卖开始时间", "拍品拍卖结束时间",
//                "上拍机构id", "上拍机构名称", "拍品状态", "商品发布人", "案号", "三级类目名称",
//                "拍品所在地_省", "拍品所在地_市", "拍品所在地_区县", "评估价", "起拍价", "参拍需缴保证金",
//                "出价次数", "出价人数", "参拍人数", "是否已结算", "保证金订单号", "实际缴纳保证金",
//                "结算时间", "获拍用户pin", "拍卖交易时间", "拍卖成交价", "订单状态", "溢价率",
//                "渠道", "是否带VR", "是否可贷款", "是否限购", "处置单位", "监管机构",
//                "处置次数", "围观人数", "加价幅度"};
//        HSSFRow titleRow = sheet.createRow(rowNum++);
//        titleRow.setHeight(rowHeight);
//        for (int colNum = 0; colNum < rowName.length; colNum++) {
//            HSSFCell cell = titleRow.createCell(colNum);
//            cell.setCellValue(rowName[colNum]);
//            cell.setCellStyle(titleCellStyle);
//        }
//        File file = new File("D:\\测试二维码\\photo\\second.xls");
//        OutputStream out = null;
//        try {
//            out = new FileOutputStream(file);
//            //2.调用write方法导出xls文件
//            wb.write(out);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(null != out){
//                try {
//                    out.flush();
//                    out.close();
//                } catch(IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    private static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
//        //设置字体
//        HSSFFont font = workbook.createFont();
//        //设置字体名字
//        font.setFontName("微软雅黑");
//        //设置字体大小
//        font.setFontHeightInPoints((short) 11);
//        //设置样式;
//        HSSFCellStyle style = workbook.createCellStyle();
//        //在样式用应用设置的字体;
//        style.setFont(font);
//        //设置自动换行;
//        style.setWrapText(false);
//        return style;
//    }
//    private static HSSFCellStyle getContentStyle(HSSFWorkbook workbook) {
//        //设置字体
//        HSSFFont font = workbook.createFont();
//        //设置字体名字
//        font.setFontName("微软雅黑");
//        //设置字体大小
//        font.setFontHeightInPoints((short) 11);
//        //字体颜色
//        font.setColor(HSSFFont.COLOR_NORMAL);
//        //设置样式;
//        HSSFCellStyle style = workbook.createCellStyle();
//        //在样式用应用设置的字体;
//        style.setFont(font);
//        //设置自动换行;
//        style.setWrapText(true);
//        //设置水平对齐的样式为居中对齐;
//        return style;
//    }
//}
