package dev.notonza.shinsei.domain;

import org.jsoup.Connection;

import java.util.Map;

/**
 * @author gachakra
 * Created on 2020/12/06.
 */
public interface Form<I extends Form.Input> {

    <T> Form value(I inputElement, T value);

    Form cookies(Map<String, String> cookies);

    Form userAgent(String userAgent);

    Connection.Response submit();

    interface Input {

        String selector();

        Class<?> valueType();
    }
}
