package com.tech.base.batch.email;

import java.util.ArrayList;
import java.util.List;

public class EmailObject {
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String content;


    public String getFrom() {
        return from;
    }

    public EmailObject setFrom(String from) {
        this.from = from;
        return this;
    }

    public String[] getTo() {
        return to;
    }

    public EmailObject setTo(String[] to) {
        this.to = to;
        return this;
    }

    public String[] getCc() {
        return cc;
    }

    public EmailObject setCc(String[] cc) {
        this.cc = cc;
        return this;
    }

    public String[] getBcc() {
        return bcc;
    }

    public EmailObject setBcc(String[] bcc) {
        this.bcc = bcc;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public EmailObject setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public EmailObject setContent(String content) {
        this.content = content;
        return this;
    }

}
