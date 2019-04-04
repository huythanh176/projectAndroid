package com.example.huythanh.Model;



public class Music  {
     private String Ma;
    private String Tenbaihat;
    private String Casy;
    private boolean thich;



    public Music(String ma, String tenbaihat, String casy, boolean thich) {
            this.Ma = ma;
            this.Tenbaihat = tenbaihat;
            this.Casy = casy;
            this.thich = thich;
        }

        public String getMa() {
            return Ma;
        }

        public void setMa(String ma) {
            this.Ma = ma;
    }

    public String getTenbaihat() {
        return Tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.Tenbaihat = tenbaihat;
    }

    public String getCasy() {
        return Casy;
    }

    public void setCasy(String casy) {
        this.Casy = casy;
    }

    public boolean isThich() {
        return thich;
    }

    public void setThich(boolean thich) {
        this.thich = thich;
    }
}
