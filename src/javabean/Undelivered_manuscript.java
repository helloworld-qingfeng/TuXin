package javabean;

public class Undelivered_manuscript {
        /*
       1.流程人员查看 未交稿案件的状态;
        */
    private String casetype;   //专利类型
    private String comittime;  //交稿时间
    private String casename;   //专利名称
    private String neibu;      //内部撰写人
    private String waibu;      //外部撰写人
    private String status;     //完成状态
    private String gudong;     //案件交接负责人；

    public String getCasetype() {
        return casetype;
    }

    public Undelivered_manuscript setCasetype(String casetype) {
        this.casetype = casetype;
        return this;
    }

    public String getComittime() {
        return comittime;
    }

    public Undelivered_manuscript setComittime(String comittime) {
        this.comittime = comittime;
        return this;
    }

    public String getCasename() {
        return casename;
    }

    public Undelivered_manuscript setCasename(String casename) {
        this.casename = casename;
        return this;
    }

    public String getNeibu() {
        return neibu;
    }

    public Undelivered_manuscript setNeibu(String neibu) {
        this.neibu = neibu;
        return this;
    }

    public String getWaibu() {
        return waibu;
    }

    public Undelivered_manuscript setWaibu(String waibu) {
        this.waibu = waibu;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Undelivered_manuscript setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getGudong() {
        return gudong;
    }

    public Undelivered_manuscript setGudong(String gudong) {
        this.gudong = gudong;
        return this;
    }

    @Override
    public String toString() {
        return "Undelivered_manuscript{" +
                "casetype='" + casetype + '\'' +
                ", comittime='" + comittime + '\'' +
                ", casename='" + casename + '\'' +
                ", neibu='" + neibu + '\'' +
                ", waibu='" + waibu + '\'' +
                ", status='" + status + '\'' +
                ", gudong='" + gudong + '\'' +
                '}';
    }
}
