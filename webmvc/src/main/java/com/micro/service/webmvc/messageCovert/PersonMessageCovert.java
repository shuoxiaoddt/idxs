package com.micro.service.webmvc.messageCovert;

import com.micro.service.webmvc.entity.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 自定义Message转换器!!
 *
 * @author xiaos
 * @date : 2018/5/14
 */
public class PersonMessageCovert extends AbstractHttpMessageConverter<Person> {


    public PersonMessageCovert() {
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected Person readInternal(Class<? extends Person> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream body = httpInputMessage.getBody();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(body,getDefaultCharset()));
        Person p = new Person();
        p.setAge(Integer.valueOf(properties.get("person.age").toString()));
        p.setName(String.valueOf(properties.get("person.name")));
        return p;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream body = httpOutputMessage.getBody();
        Properties properties = new Properties();

        properties.setProperty("person.age",String.valueOf(person.getAge()));
        properties.setProperty("person.name",person.getName());

        properties.store(new OutputStreamWriter(body,getDefaultCharset()),"Written by web server");
    }
}
