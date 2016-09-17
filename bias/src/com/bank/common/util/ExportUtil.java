package com.bank.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;

import com.bank.common.AppConstants;


public class ExportUtil {

    public static String generateExcel(String exportTemplateExcel, Map<String, Object> beans, String generateExcelName)
            throws Exception {
//        String serverAbsolutePath = FilesUtils.getServerAbsolutePath() + File.separator;
//        XLSTransformer transformer = new XLSTransformer();
//        String exportTemplateExcelURL = serverAbsolutePath + exportTemplateExcel;
//        String generateExcelURL = serverAbsolutePath + AppConstants.EXPORT_TEMP_DATA_PATH + generateExcelName
//                + AppConstants.EXPORT_EXCEL_SUFFIX;
//        try {
//            transformer.transformXLS(exportTemplateExcelURL, beans, generateExcelURL);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return generateExcelURL;
        return null;
    }

    public static void downloadExcel(String name, HttpServletResponse response) throws IOException, NamingException {
        // delete downLoadFileName last 6 number about hour minute second
        String downloadFileName = name.substring(0, name.length() - 6);
        downloadFileName = new String(downloadFileName.getBytes("utf-8"), "ISO-8859-1");
        response.reset();
        response.setHeader("Content-disposition", "attachment;success=true;filename =" + downloadFileName
                + AppConstants.EXPORT_EXCEL_SUFFIX);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;
        //File uploadFile = new File(FilesUtils.getServerAbsolutePath() + File.separator
//               // + AppConstants.EXPORT_TEMP_DATA_PATH + name + AppConstants.EXPORT_EXCEL_SUFFIX);
        try {
//            //fis = new FileInputStream(uploadFile);
//            bis = new BufferedInputStream(fis);
//            fos = response.getOutputStream();
//            bos = new BufferedOutputStream(fos);
//            int bytesRead = 0;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
//                bos.write(buffer, 0, bytesRead);
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            bos.flush();
            fis.close();
            bis.close();
            fos.close();
            bos.close();
            //uploadFile.delete();
        }
    }
}
