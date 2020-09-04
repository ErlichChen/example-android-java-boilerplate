package com.example.java.binder.compiler;

import com.example.java.binder.annotations.BindView;
import com.example.java.binder.annotations.OnClick;
import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;

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
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        messager.printMessage(Diagnostic.Kind.NOTE, "process ---> ");
        messager.printMessage(Diagnostic.Kind.NOTE, env.toString());

        findAndParseTargets(env);

        for (Element element : env.getElementsAnnotatedWith(BindView.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
        }
        for (Element element : env.getElementsAnnotatedWith(OnClick.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
        }
        return false;
    }

    private void findAndParseTargets(RoundEnvironment env) {
        for (Element element : env.getElementsAnnotatedWith(BindView.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "findAndParseTargets: " + element.toString());
//            if (!SuperficialValidation.validateElement(element)) continue;
//            try {
//                parseResourceString(element, builderMap, erasedTargetNames);
//            } catch (Exception e) {
//                logParsingError(element, BindString.class, e);
//            }
            try {
                parseBindView(element);
            } catch (Exception e) {
                System.out.println(e.toString());
//                logParsingError(element, BindView.class, e);
            }
        }
    }

    private Trees trees;
    private void parseBindView(Element element) {
        String name = element.getSimpleName().toString();
        int id = element.getAnnotation(BindView.class).value();
        messager.printMessage(Diagnostic.Kind.NOTE, "parseBindView --> name: " + name);
        messager.printMessage(Diagnostic.Kind.NOTE, "parseBindView --> id: " + id);
//        Id resourceId = elementToId(element, BindView.class, id);
        JCTree tree = (JCTree) trees.getTree(element);
        messager.printMessage(Diagnostic.Kind.NOTE, "parseBindView --> " + tree.toString());

    }
}
