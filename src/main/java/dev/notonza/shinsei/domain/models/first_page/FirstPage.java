package dev.notonza.shinsei.domain.models.first_page;

import dev.notonza.shinsei.config.FormConfiguration;
import org.jsoup.Connection;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public class FirstPage {

    private Connection.Response firstPageResponse;

    public FirstPage(Connection.Response firstPageResponse) {
        this.firstPageResponse = firstPageResponse;
    }

    public Connection.Response submitForm(FormConfiguration config) throws IOException {

        LocalDate birthDate = LocalDate.parse(
            config.form().birthDate(),
            DateTimeFormatter
                .ISO_LOCAL_DATE
                .withResolverStyle(ResolverStyle.STRICT));

        return FirstPageForm.fromResponseDocument(firstPageResponse.parse())
            .value(FirstPageForm.Input.ACCOUNT_NUMBER, config.form().accountNumber())
            .value(FirstPageForm.Input.BIRTH_YEAR, birthDate.getYear())
            .value(FirstPageForm.Input.BIRTH_MONTH, birthDate.getMonthValue())
            .value(FirstPageForm.Input.BIRTH_DAY, birthDate.getDayOfMonth())
            .value(FirstPageForm.Input.IS_TYPE_T_POINT, true)
            .value(FirstPageForm.Input.IS_T_POINT_NUMBER_16_DIGITS, true)
            .value(FirstPageForm.Input.T_POINT_NUMBER_16_DIGITS, config.form().tPointNumber16Digits())
            .value(FirstPageForm.Input.EMAIL, config.form().email())
            .value(FirstPageForm.Input.EMAIL_HIDDEN, config.form().email()) // added after filed
            .value(FirstPageForm.Input.ACCEPTS_POLICY, true)
            .cookies(firstPageResponse.cookies())
            .userAgent(config.form().userAgent())
            .submit();
    }
}
