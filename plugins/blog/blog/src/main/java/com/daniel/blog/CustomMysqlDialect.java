package com.daniel.blog;

import org.hibernate.dialect.MySQLDialect;

public class CustomMysqlDialect extends MySQLDialect {
    @Override
    public String getTableTypeString() {
        return " DEFAULT CHARSET=utf8";
    }
}