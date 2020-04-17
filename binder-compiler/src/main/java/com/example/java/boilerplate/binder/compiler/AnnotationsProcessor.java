package com.example.java.boilerplate.binder.compiler;

import com.example.java.boilerplate.binder.annotations.BindView;
import com.example.java.boilerplate.binder.annotations.OnClick;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

public class AnnotationsProcessor extends AbstractProcessor {

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        messager = env.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "init ---> ");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        types.add(OnClick.class.getCanonicalName());
        messager.printMessage(Diagnostic.Kind.NOTE, "getSupportedAnnotationTypes ---> ");
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        messager.printMessage(Diagnostic.Kind.NOTE, "process ---> ");
        for (Element element : env.getElementsAnnotatedWith(BindView.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
        }
        for (Element element : env.getElementsAnnotatedWith(OnClick.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
        }
        return false;
    }
}
