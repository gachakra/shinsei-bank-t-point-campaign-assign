package dev.notonza.shinsei.domain.models.confirm_page;

import dev.notonza.shinsei.config.FormConfiguration;
import org.jsoup.Connection;

import java.io.IOException;

/**
 * @author gachakra
 * Created on 2020/12/08.
 */
public class ConfirmPage {

    private final Connection.Response confirmPageResponse;

    public ConfirmPage(Connection.Response confirmPageResponse) {
        this.confirmPageResponse = confirmPageResponse;
    }

    public Connection.Response submitForm(FormConfiguration config) throws IOException {

        return ConfirmPageForm.fromResponseDocument(confirmPageResponse.parse())
            .cookies(confirmPageResponse.cookies())
            .userAgent(config.form().userAgent())
            .submit();
    }
}
