package dev.notonza.shinsei.domain.models.first_page;

import dev.notonza.shinsei.domain.models.AbstractForm;
import dev.notonza.shinsei.domain.models.Form;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;


final class FirstPageForm extends AbstractForm<FirstPageForm.Input> {

    final static String SELECTOR = "form#point_program_form";

    static FirstPageForm fromResponseDocument(Document document) {
        Element form = document.select(SELECTOR).first();
        if (form.html().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return new FirstPageForm((FormElement) form);
    }

    private FirstPageForm(FormElement formElement) {
        super(formElement);
    }

    /**
     * @author gachakra
     * Created on 2020/12/06.
     */
    enum Input implements Form.Input {

        ACCOUNT_NUMBER("input#fi_account_num", String.class),
        BIRTH_YEAR("input#fi_birth_year", Integer.class),
        BIRTH_MONTH("input#fi_birth_month", Integer.class),
        BIRTH_DAY("input#fi_birth_day", Integer.class),
        IS_TYPE_T_POINT("input#r-point_t", Boolean.class),
        IS_T_POINT_NUMBER_16_DIGITS("input#point_t_16", Boolean.class),
        T_POINT_NUMBER_16_DIGITS("input#fi_point_t16", String.class),
        EMAIL("input#fi_mail", String.class),
        ACCEPTS_POLICY("input#fi_policy", Boolean.class);

        private String selector;
        private Class<?> valueType;

        Input(String selector, Class<?> valueType) {
            this.selector = selector;
            this.valueType = valueType;
        }

        @Override
        public String selector() {
            return this.selector;
        }

        @Override
        public Class<?> valueType() {
            return this.valueType;
        }
    }
}
