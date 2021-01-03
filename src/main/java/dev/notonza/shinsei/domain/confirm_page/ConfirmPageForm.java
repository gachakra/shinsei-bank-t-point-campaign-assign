package dev.notonza.shinsei.domain.confirm_page;

import dev.notonza.shinsei.domain.AbstractForm;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;


final class ConfirmPageForm extends AbstractForm {

    final static String SELECTOR = "form#point_program_confirm";

    static ConfirmPageForm fromResponseDocument(Document document) {
        Element form = document.select(SELECTOR).first();
        if (form.html().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return new ConfirmPageForm((FormElement) form);
    }

    private ConfirmPageForm(FormElement formElement) {
        super(formElement);
    }
}