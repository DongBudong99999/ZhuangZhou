package springboot_demo1.demo.domain;

import java.util.Date;

public class selectData {
    public String parameter;
    public String startDate;
    public String endDate;
    public String select_num;

    public String getSelect_num() {
        return select_num;
    }

    public void setSelect_num(String select_num) {
        this.select_num = select_num;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
