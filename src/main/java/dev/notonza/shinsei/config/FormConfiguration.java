package dev.notonza.shinsei.config;


import com.google.inject.Singleton;

/**
 * @author gachakra
 * Created on 2020/02/26.
 */
@Singleton
final public class FormConfiguration implements YamlConfiguration {

    private Form form;
    private Site site;

    public Form form() {
        return form;
    }

    public Site site() {
        return site;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Configuration{" +
            "form=" + form +
            ", site=" + site +
            '}';
    }

    public static class Form {

        private String userAgent;
        private String tPointNumber16Digits;
        private String accountNumber;
        private String birthDate;
        private String email;

        public String userAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String tPointNumber16Digits() {
            return tPointNumber16Digits;
        }

        public void settPointNumber16Digits(String tPointNumber16Digits) {
            this.tPointNumber16Digits = tPointNumber16Digits;
        }

        public String accountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String birthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String email() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Form{" +
                "userAgent='" + userAgent + '\'' +
                ", tPointNumber16Digits='" + tPointNumber16Digits + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                '}';
        }
    }

    public static class Site {

        private String url;

        public String url() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Site{" +
                "url='" + url + '\'' +
                '}';
        }
    }
}