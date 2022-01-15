package javabean;

public class Case {
    /*
       股东数据展示对象;数据展示给股东看的;
     */

    private String customer;    //客户名称
    private String casetype;    //案件类型
    private String casename;    //案件名称
    private String duraltheconveyancer;  //公司处理人;
    private String outsourcer;  //外包人;
    private String nameofshareholder; //股东名称：
    private String caseReceivingperiod;  //接案时期-年月日;


    public String getCustomer() {
        return customer;
    }

    public Case setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public String getCasetype() {
        return casetype;
    }

    public Case setCasetype(String casetype) {
        this.casetype = casetype;
        return this;
    }

    public String getCasename() {
        return casename;
    }

    public Case setCasename(String casename) {
        this.casename = casename;
        return this;
    }

    public String getDuraltheconveyancer() {
        return duraltheconveyancer;
    }

    public Case setDuraltheconveyancer(String duraltheconveyancer) {
        this.duraltheconveyancer = duraltheconveyancer;
        return this;
    }

    public String getOutsourcer() {
        return outsourcer;
    }

    public Case setOutsourcer(String outsourcer) {
        this.outsourcer = outsourcer;
        return this;
    }

    public String getNameofshareholder() {
        return nameofshareholder;
    }

    public Case setNameofshareholder(String nameofshareholder) {
        this.nameofshareholder = nameofshareholder;
        return this;
    }

    public String getCaseReceivingperiod() {
        return caseReceivingperiod;
    }

    public Case setCaseReceivingperiod(String caseReceivingperiod) {
        this.caseReceivingperiod = caseReceivingperiod;
        return this;
    }

    @Override
    public String toString() {
        return "Case{" +
                "customer='" + customer + '\'' +
                ", casetype='" + casetype + '\'' +
                ", casename='" + casename + '\'' +
                ", duraltheconveyancer='" + duraltheconveyancer + '\'' +
                ", outsourcer='" + outsourcer + '\'' +
                ", nameofshareholder='" + nameofshareholder + '\'' +
                ", caseReceivingperiod='" + caseReceivingperiod + '\'' +
                '}';
    }
}
