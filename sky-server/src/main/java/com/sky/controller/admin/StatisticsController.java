package com.sky.service.impl.admin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.sky.orther.DemoData;
import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据统计
 *
 * @author leapsss <a href="https://github.com/ThenLeap">GitHub Profile</a>
 * @since 2024-9-16 09:41:53
 */
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@Api(tags = "数据统计相关接口")
public class StatisticsController {

    private final ReportService reportService;

    // 导出Excel http://localhost:8081/report/export
    @GetMapping("export")
    @ApiOperation("导出Excel")
    public void export(HttpServletResponse response) {
        // TODO 取到需要导出数据（这里是假数据）
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setLocalDateTime(LocalDateTime.now());
            data.setDoubleData(0.56);
            list.add(data);
        }

        // 写Excel
        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表" + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 简单写法 传入要导入的类对象
            EasyExcel.write(response.getOutputStream(), DemoData.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 销量排名统计
     *
     * @param begin 开始日期
     * @param end   结束日期
     * @return 销量排名统计
     */
    @ApiOperation("销量排名统计")
    @GetMapping("top10")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        SalesTop10ReportVO salesTop10 = reportService.getSalesTop10(begin, end);
        return Result.success(salesTop10);
    }

    /**
     * 用户统计
     *
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("userStatistics")
    @ApiOperation("用户统计")
    public Result<Object> getUserStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        UserReportVO userStatistics = reportService.getUserStatistics(begin, end);
        return Result.success(userStatistics);
    }

    /**
     * 订单统计
     *
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("orderStatistics")
    @ApiOperation("订单统计")
    public Result<Object> getOrderStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        OrderReportVO orderStatistics = reportService.getOrderStatistics(begin, end);
        return Result.success(orderStatistics);
    }

    /**
     * 营业额统计
     *
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("orderDetailStatistics")
    @ApiOperation("营业额统计")
    public Result<Object> getOrderDetailStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        TurnoverReportVO turnover = reportService.getTurnover(begin, end);
        return Result.success(turnover);
    }
}
