package springboot_demo1.demo.tools;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date 2018/6/27 11:47
 * @desc
 */
public class ExcelToHTML {

    /**
     * @param filePath    excel源文件文件的路径
     * @param htmlPositon 生成的html文件的路径
     * @param isWithStyle 是否需要表格样式 包含 字体 颜色 边框 对齐方式
     */
    public static String readExcelToHtml(String filePath, String htmlPositon, boolean isWithStyle) {

        InputStream is = null;
        String htmlExcel = null;
        try {
            File sourcefile = new File(filePath);
            is = new FileInputStream(sourcefile);
            Workbook wb = WorkbookFactory.create(is);
            if (wb instanceof XSSFWorkbook) {   //03版excel处理方法
                XSSFWorkbook xWb = (XSSFWorkbook) wb;
                htmlExcel = ExcelToHTML.getExcelInfo(xWb, isWithStyle);
            } else if (wb instanceof HSSFWorkbook) {  //07及10版以后的excel处理方法
                HSSFWorkbook hWb = (HSSFWorkbook) wb;
                htmlExcel = ExcelToHTML.getExcelInfo(hWb, isWithStyle);
            }
            writeFile(htmlExcel, htmlPositon);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return htmlPositon;
    }


    private static String getExcelInfo(Workbook wb, boolean isWithStyle) {

        StringBuffer sb = new StringBuffer();
        Sheet sheet = wb.getSheetAt(0);//获取第一个Sheet的内容
        int lastRowNum = sheet.getLastRowNum();
        Map<String, String> map[] = getRowSpanColSpanMap(sheet);
        sb.append("<table style='border-collapse:collapse;' width='100%'>");
        Row row = null;        //兼容
        Cell cell = null;    //兼容

        for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
            row = sheet.getRow(rowNum);
//-----------------
//            if (rowNum == 1) {
//                if (row == null) {
//                    sb.append("<tr><td ><nobr> </nobr></td></tr>");
//                    continue;
//                }
//                sb.append("<tr>");
//                int lastColNum1 = row.getLastCellNum();
//                for (int colNum = 0; colNum < lastColNum1; colNum++) {
//                    cell = row.getCell(colNum);
//                    if (cell == null) {    //特殊情况 空白的单元格会返回null
//                        sb.append("<td> </td>");
//                        continue;
//                    }
//
//                    String stringValue = getCellValue(cell);
//                    if (map[0].containsKey(rowNum + "," + colNum)) {
//                        String pointString = map[0].get(rowNum + "," + colNum);
//                        map[0].remove(rowNum + "," + colNum);
//                        int bottomeRow = Integer.valueOf(pointString.split(",")[0]);
//                        int bottomeCol = Integer.valueOf(pointString.split(",")[1]);
//                        int rowSpan = bottomeRow - rowNum + 1;
//                        int colSpan = bottomeCol - colNum + 1;
//                        sb.append("<td rowspan= '" + rowSpan + "' colspan= '" + colSpan + "' ");
//                    } else if (map[1].containsKey(rowNum + "," + colNum)) {
//                        map[1].remove(rowNum + "," + colNum);
//                        continue;
//                    } else {
//                        sb.append("<td ");
//                    }
//
//                }
//            }
//----------------
            if (row == null) {
                sb.append("<tr><td ></td></tr>");//<nobr> </nobr>
                continue;
            }
            sb.append("<tr>");
            int lastColNum = row.getLastCellNum();
            for (int colNum = 0; colNum < lastColNum; colNum++) {
                cell = row.getCell(colNum);
                if (cell == null) {    //特殊情况 空白的单元格会返回null
                    sb.append("<td> </td>");
                    continue;
                }

                String stringValue = getCellValue(cell);
                if (map[0].containsKey(rowNum + "," + colNum)) {
                    String pointString = map[0].get(rowNum + "," + colNum);
                    map[0].remove(rowNum + "," + colNum);
                    int bottomeRow = Integer.valueOf(pointString.split(",")[0]);
                    int bottomeCol = Integer.valueOf(pointString.split(",")[1]);
                    int rowSpan = bottomeRow - rowNum + 1;
                    int colSpan = bottomeCol - colNum + 1;
                    sb.append("<td rowspan= '" + rowSpan + "' colspan= '" + colSpan + "' ");
                } else if (map[1].containsKey(rowNum + "," + colNum)) {
                    map[1].remove(rowNum + "," + colNum);
                    continue;
                } else {
                    sb.append("<td ");
                }
                //


                //判断是否需要样式
                if (isWithStyle) {
                    dealExcelStyle(wb, sheet, cell, sb);//处理单元格样式
                }
                if (rowNum != 2) {
                    sb.append(">");//<nobr>
                } else {

                    sb.append(">\n" +
                            "                <div class=\"css1\">\n" +
                            "                    <input type=\"checkbox\" style=\"color:black\" id='input" + colNum + "'/>\n" +
                            "                    <label for='input" + colNum + "'>");
                }
                //sb.append("><nobr>")
                if (stringValue == null || "".equals(stringValue.trim())) {
                    sb.append("   ");
                } else {
                    // 将ascii码为160的空格转换为html下的空格（ ）
                    sb.append(stringValue.replace(String.valueOf((char) 160), " "));
                }
//                sb.append("</nobr></td>");
                if (rowNum != 2) {
                    sb.append("</td>");//</nobr>
                } else {
                    sb.append("</label>\n" +
                            "                </div>\n" +
                            "            </td>");
                }
            }
            sb.append("</tr>");
        }

        sb.append("</table>");
        return sb.toString();
    }

    private static Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {

        Map<String, String> map0 = new HashMap<String, String>();//单独存放未合并单元格的行列信息
        Map<String, String> map1 = new HashMap<String, String>();//单独存放合并单元格的行列信息
        int mergedNum = sheet.getNumMergedRegions();//获得（包括合并的）单元格个数
        CellRangeAddress range = null;
        for (int i = 0; i < mergedNum; i++) {
            range = sheet.getMergedRegion(i);
            int topRow = range.getFirstRow();
            int topCol = range.getFirstColumn();
            int bottomRow = range.getLastRow();
            int bottomCol = range.getLastColumn();//以上获得每一个合并的单元格的区域范围
            map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);
            // System.out.println(topRow + "," + topCol + "," + bottomRow + "," + bottomCol);
            int tempRow = topRow;
            while (tempRow <= bottomRow) {
                int tempCol = topCol;
                while (tempCol <= bottomCol) {
                    map1.put(tempRow + "," + tempCol, "");
                    tempCol++;
                }
                tempRow++;
            }
            map1.remove(topRow + "," + topCol);
        }
        Map[] map = {map0, map1};
        return map;
    }


    /**
     * 获取表格单元格Cell内容
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {

        String result = new String();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = DateUtil
                            .getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case Cell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_BLANK:
                result = "";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    /**
     * 处理表格样式
     *
     * @param wb
     * @param sheet
     * @param sb
     */
    private static void dealExcelStyle(Workbook wb, Sheet sheet, Cell cell, StringBuffer sb) {

        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle != null) {
            short alignment = cellStyle.getAlignment();
            //    sb.append("align='" + convertAlignToHtml(alignment) + "' ");//单元格内容的水平对齐方式
            short verticalAlignment = cellStyle.getVerticalAlignment();
            sb.append("valign='" + convertVerticalAlignToHtml(verticalAlignment) + "' ");//单元格中内容的垂直排列方式

            if (wb instanceof XSSFWorkbook) {

                XSSFFont xf = ((XSSFCellStyle) cellStyle).getFont();
                short boldWeight = xf.getBoldweight();
                String align = convertAlignToHtml(alignment);
                sb.append("style='");
                sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                sb.append("font-size: " + xf.getFontHeight() / 2 + "%;"); // 字体大小
                int columnWidth = sheet.getColumnWidth(cell.getColumnIndex());
                sb.append("width:" + columnWidth + "px;");
                sb.append("text-align:" + align + ";");//表头排版样式
                XSSFColor xc = xf.getXSSFColor();
                if (xc != null && !"".equals(xc)) {
                    sb.append("color:#" + xc.getARGBHex().substring(2) + ";"); // 字体颜色
                }

                XSSFColor bgColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
                if (bgColor != null && !"".equals(bgColor)) {
                    sb.append("background-color:#" + bgColor.getARGBHex().substring(2) + ";"); // 背景颜色
                }
                sb.append(getBorderStyle(0, cellStyle.getBorderTop(), ((XSSFCellStyle) cellStyle).getTopBorderXSSFColor()));
                sb.append(getBorderStyle(1, cellStyle.getBorderRight(), ((XSSFCellStyle) cellStyle).getRightBorderXSSFColor()));
                sb.append(getBorderStyle(2, cellStyle.getBorderBottom(), ((XSSFCellStyle) cellStyle).getBottomBorderXSSFColor()));
                sb.append(getBorderStyle(3, cellStyle.getBorderLeft(), ((XSSFCellStyle) cellStyle).getLeftBorderXSSFColor()));

            } else if (wb instanceof HSSFWorkbook) {

                HSSFFont hf = ((HSSFCellStyle) cellStyle).getFont(wb);
                short boldWeight = hf.getBoldweight();
                short fontColor = hf.getColor();
                sb.append("style='");
                HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette(); // 类HSSFPalette用于求的颜色的国际标准形式
                HSSFColor hc = palette.getColor(fontColor);
                sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                sb.append("font-size: " + hf.getFontHeight() / 2 + "%;"); // 字体大小
                String align = convertAlignToHtml(alignment);
                sb.append("text-align:" + align + ";");//表头排版样式
                String fontColorStr = convertToStardColor(hc);
                if (fontColorStr != null && !"".equals(fontColorStr.trim())) {
                    sb.append("color:" + fontColorStr + ";"); // 字体颜色
                }
                int columnWidth = sheet.getColumnWidth(cell.getColumnIndex());
                sb.append("width:" + columnWidth + "px;");
                short bgColor = cellStyle.getFillForegroundColor();
                hc = palette.getColor(bgColor);
                String bgColorStr = convertToStardColor(hc);
                if (bgColorStr != null && !"".equals(bgColorStr.trim())) {
                    sb.append("background-color:" + bgColorStr + ";"); // 背景颜色
                }
                sb.append(getBorderStyle(palette, 0, cellStyle.getBorderTop(), cellStyle.getTopBorderColor()));
                sb.append(getBorderStyle(palette, 1, cellStyle.getBorderRight(), cellStyle.getRightBorderColor()));
                sb.append(getBorderStyle(palette, 3, cellStyle.getBorderLeft(), cellStyle.getLeftBorderColor()));
                sb.append(getBorderStyle(palette, 2, cellStyle.getBorderBottom(), cellStyle.getBottomBorderColor()));
            }

            sb.append("' ");
        }
    }

    /**
     * 单元格内容的水平对齐方式
     *
     * @param alignment
     * @return
     */
    private static String convertAlignToHtml(short alignment) {

        String align = "center";
        switch (alignment) {
            case CellStyle.ALIGN_LEFT:
                align = "left";
                break;
            case CellStyle.ALIGN_CENTER:
                align = "center";
                break;
            case CellStyle.ALIGN_RIGHT:
                align = "right";
                break;
            default:
                break;
        }
        return align;
    }

    /**
     * 单元格中内容的垂直排列方式
     *
     * @param verticalAlignment
     * @return
     */
    private static String convertVerticalAlignToHtml(short verticalAlignment) {

        String valign = "middle";
        switch (verticalAlignment) {
            case CellStyle.VERTICAL_BOTTOM:
                valign = "bottom";
                break;
            case CellStyle.VERTICAL_CENTER:
                valign = "center";
                break;
            case CellStyle.VERTICAL_TOP:
                valign = "top";
                break;
            default:
                break;
        }
        return valign;
    }

    private static String convertToStardColor(HSSFColor hc) {

        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            if (HSSFColor.AUTOMATIC.index == hc.getIndex()) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                sb.append(fillWithZero(Integer.toHexString(hc.getTriplet()[i])));
            }
        }

        return sb.toString();
    }

    private static String fillWithZero(String str) {
        if (str != null && str.length() < 2) {
            return "0" + str;
        }
        return str;
    }

    static String[] bordesr = {"border-top:", "border-right:", "border-bottom:", "border-left:"};
    static String[] borderStyles = {"solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid ", "solid", "solid", "solid", "solid", "solid"};

    private static String getBorderStyle(HSSFPalette palette, int b, short s, short t) {

        if (s == 0) return bordesr[b] + borderStyles[s] + "#d0d7e5 1px;";
        ;
        String borderColorStr = convertToStardColor(palette.getColor(t));
        borderColorStr = borderColorStr == null || borderColorStr.length() < 1 ? "#000000" : borderColorStr;
        return bordesr[b] + borderStyles[s] + borderColorStr + " 1px;";

    }

    private static String getBorderStyle(int b, short s, XSSFColor xc) {

        if (s == 0) return bordesr[b] + borderStyles[s] + "#d0d7e5 1px;";
        ;
        if (xc != null && !"".equals(xc)) {
            String borderColorStr = xc.getARGBHex();//t.getARGBHex();
            borderColorStr = borderColorStr == null || borderColorStr.length() < 1 ? "#000000" : borderColorStr.substring(2);
            return bordesr[b] + borderStyles[s] + borderColorStr + " 1px;";
        }

        return "";
    }

    /*
     * @param content 生成的excel表格标签
     * @param htmlPath 生成的html文件地址
     */
    private static void writeFile(String content, String htmlPath) {
        File file2 = new File(htmlPath);
//
        if(file2.exists()){
            file2.delete();
        }
//


        StringBuilder sb = new StringBuilder();
        try {
            file2.createNewFile();//创建文件

//            sb.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><title>Html Test</title></head><body>");
            sb.append("<html>\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                    "    <title>Html Test</title>\n" +
                    "\n" +
                    "    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\"/>\n" +
                    "    <link href=\"css/bootstrap-responsive.min.css\" rel=\"stylesheet\"/>\n" +
                    "    <link href=\"css/style.min.css\" rel=\"stylesheet\"/>\n" +
                    "    <link href=\"css/style-responsive.min.css\" rel=\"stylesheet\"/>\n" +
                    "    <link href=\"css/retina.css\" rel=\"stylesheet\"/>\n" +
                    "\n" +
                    "    <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n" +
                    "    <link id=\"ie-style\" href=\"css/ie.css\" rel=\"stylesheet\">\n" +
                    "    <!---->\n" +
                    "\n" +
                    "\n" +
                    "    <link id=\"ie9style\" href=\"css/ie9.css\" rel=\"stylesheet\">\n" +
                    "\n" +
                    "    <script type=\"text/javascript\" src=\"static/js/jquery-3.2.1.js\"></script>\n" +
                    "    <script type=\"text/javascript\" src=\"js/jquery.form.js\"></script>\n" +
                    "    <!--<script type=\"text/javascript\" src=\"http://malsup.github.io/jquery.form.js\"></script>-->\n" +
                    "\n" +
                    "    <style type=\"text/css\">\n" +
                    "\n" +
                    "        .css1 input {\n" +
                    "            display: none;\n" +
                    "        }\n" +
                    "        .css1 label {\n" +
                    "            /*width: 270px; !* 宽度 *!*/\n" +
                    "            height: 40px; /* 高度 */\n" +
                    "            border-width: 0px; /* 边框宽度 */\n" +
                    "            border-radius: 3px; /* 边框半径 */\n" +
                    "            background: #e4f5dc; /* 背景颜色 */\n" +
                    "            cursor: pointer; /* 鼠标移入按钮范围时出现手势 */\n" +
                    "            outline: none; /* 不显示轮廓线 */\n" +
                    "            font-family: Microsoft YaHei; /* 设置字体 */\n" +
                    "            color: white; /* 字体颜色 */\n" +
                    "            font-size: 17px;\n" +
                    "\n" +
                    "        }\n" +
                    "        .css1 input:checked + label {\n" +
                    "            background: url(images/ico_checkon.svg) no-repeat right bottom;\n" +
                    "            border: 1px solid #00a4ff;\n" +
                    "            background-size: 21px 21px;\n" +
                    "            color: #00a4ff;\n" +
                    "\n" +
                    "        }\n" +
                    "    </style>\n" +" <script type=\"text/javascript\">\n" +
                    "\n" +
                    "alert('表格读取中，请稍后...')"+
                    "        document.onreadystatechange = loadingChange;//当页面加载状态改变的时候执行这个方法.\n" +
                    "        function loadingChange()\n" +
                    "        {\n" +
                    "            if(document.readyState == \"complete\"){\n" +
                    "                alert(\"页面加载完毕\")//当页面加载状态为完全结束时进入\n" +
                    "                // $(\".loading\").hide();//当页面加载完成后将loading页隐藏\n" +
                    "            }\n" +
                    "        }\n" +
                    "\n" +
                    "    </script>"+
                    "\n" +
                    "</head><body>");
            sb.append("<button onclick=\"window.location.href('upload.html')\"> 返回首页</button><div>");
            sb.append(content);
            sb.append("</div>");
            sb.append("</body></html>");

            PrintStream printStream = new PrintStream(new FileOutputStream(file2));

            printStream.println(sb.toString());//将字符串写入文件

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}