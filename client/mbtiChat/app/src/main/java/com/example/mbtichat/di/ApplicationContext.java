package com.example.mbtichat.di;

import javax.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


//어플리케이션 Context 한정자
@Qualifier
@Retention (RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
