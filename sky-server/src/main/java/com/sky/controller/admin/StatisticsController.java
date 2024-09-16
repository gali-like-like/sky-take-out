package com.sky.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.sky.orther.DemoData;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

    // 导出Excel http://localhost:8081/report/export
    @GetMapping("export")
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
            String fileName = "导出列表"+ ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
            // 简单写法 传入要导入的类对象
            EasyExcel.write(response.getOutputStream(), DemoData.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
