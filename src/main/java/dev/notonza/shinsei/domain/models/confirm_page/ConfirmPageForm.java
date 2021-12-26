package dev.notonza.shinsei.domain.models.confirm_page;

import dev.notonza.shinsei.domain.models.AbstractForm;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.IOException;


final class ConfirmPageForm extends AbstractForm {

    final static String SELECTOR = "form#point_program_confirm";

    static ConfirmPageForm fromResponseDocument(Document document) {
        Element form = document.select(SELECTOR).first();
        if (form.html().isEmpty()) {
            throw new IllegalArgumentException("Form html not found on the confirm page with selector [" + SELECTOR + "]");
        }

        return new ConfirmPageForm((FormElement) form);
    }

    private ConfirmPageForm(FormElement formElement) {
        super(formElement);
    }

    /**
     * Overrode after failed to send referrer
     * @return
     */
    @Override
    public Connection.Response submit() {
        try {
            return this.formElement
                .submit()
                .cookies(this.cookies)
                .referrer("https://webform.shinseibank.com/webapp/form/19913_xldb_4/setParameters.do") // added after failed TODO check
                .userAgent(this.userAgent)
                .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}