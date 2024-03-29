package dev.notonza.shinsei.domain.models;

import com.google.common.base.Strings;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author gachakra
 * Created on 2020/12/07.
 */
public abstract class AbstractForm<I extends Form.Input> implements Form<I> {

    protected FormElement formElement;
    protected Map<String, String> cookies;
    protected String userAgent;

    protected AbstractForm(FormElement formElement) {
        this.formElement = formElement;
    }

    @Override
    public <T> Form value(I inputElement, T value) {
        Class<?> inputValueType = inputElement.valueType();
        if (!value.getClass().isAssignableFrom(inputValueType)) {
            throw new IllegalArgumentException(
                "Mismatched value types with selector [" + inputElement.selector() + "]\n"
                    + "Input type [" + inputValueType.getName() + "]\n"
                    + "Value type [" + value.getClass().getName() + "]"
            );
        }

        Element input = formElement.select(inputElement.selector()).first();
        if (Objects.isNull(input) || Strings.isNullOrEmpty(input.toString())) {
            throw new IllegalArgumentException("Input html not found with selector [" + inputElement.selector() + "]");
        }

        if (inputValueType == Integer.class) {
            input.val(Integer.toString((int) value));
            return this;
        }

        if (inputValueType == String.class) {
            input.val((String) value);
            return this;
        }

        if (inputValueType == Boolean.class
            && (Boolean) value) {
            input.attr("checked", "checked");
            return this;
        }

        throw new AssertionError(
            "Unexpected value type with selector [" + inputElement.selector() + "]\n"
                + "Value type [" + inputValueType.getName() + "]"
        );
    }

    @Override
    public Form cookies(Map<String, String> cookies) {
        this.cookies = cookies;
        return this;
    }

    @Override
    public Form userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public Connection.Response submit() {
        try {
            return this.formElement
                .submit()
                .cookies(this.cookies)
                .userAgent(this.userAgent)
                .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}