package com.example.java.binder.compiler;

import com.example.java.binder.annotations.BindView;
import com.example.java.binder.annotations.OnClick;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public class AnnotationsProcessor extends AbstractProcessor {

    private Map<Element, List<Element>> elementsMap = new LinkedHashMap<>();

    private Filer mFiler;
    private Messager messager;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        messager = env.getMessager();
        mFiler = env.getFiler();
        mElementUtils = env.getElementUtils();
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
        for (Element element : env.getElementsAnnotatedWith(BindView.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
            Element enclosingElement = element.getEnclosingElement();

            List<Element> viewBindElements = elementsMap.get(enclosingElement);
            if (viewBindElements == null) {
                viewBindElements = new ArrayList<>();
                elementsMap.put(enclosingElement, viewBindElements);
            }

            viewBindElements.add(element);
        }
//        findAndParseTargets(env);
//        for (Element element : env.getElementsAnnotatedWith(OnClick.class)) {
//            messager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
//        }
        generate();
        return false;
    }

    private void findAndParseTargets(RoundEnvironment env) {
        for (Element element : env.getElementsAnnotatedWith(BindView.class)) {
//            messager.printMessage(Diagnostic.Kind.NOTE, "findAndParseTargets: " + element.toString());
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

    private void generate() {
        for (Map.Entry<Element,List<Element>> entry : elementsMap.entrySet()) {
            Element enclosingElement  = entry.getKey();
            List<Element> bindViewElements = entry.getValue();

            ClassName unbinderClassName = ClassName.get("com.example.java.binder","Unbinder");
            System.out.println("------------Unbinder-----------"+unbinderClassName.simpleName());
            //得到类名的字符串
            String activityName = enclosingElement.getSimpleName().toString();
            ClassName activityClassName = ClassName.bestGuess(activityName);
            //拼装这一行代码：public final class xxx_ViewBinding implements Unbinder
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(activityName+"_ViewBinding")
                    //类名前添加public final
                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                    //添加类的实现接口
                    .addSuperinterface(unbinderClassName)
                    //添加一个成员变量，这个名字target是仿照butterknife
                    .addField(activityClassName,"target",Modifier.PRIVATE);

            //实现Unbinder的方法
            //CallSuper这个注解不像Override可以直接拿到，需要用这种方式
            ClassName callSuperClass = ClassName.get("androidx.annotation","CallSuper");
            MethodSpec.Builder unbindMethod = MethodSpec.methodBuilder("unbind")//和你创建的Unbinder中的方法名保持一致
                    .addAnnotation(Override.class)
                    .addAnnotation(callSuperClass)
                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC);

            //添加构造函数
            MethodSpec.Builder constructMethodBuilder = MethodSpec.constructorBuilder()
                    .addParameter(activityClassName,"target");
            constructMethodBuilder.addStatement("this.target = target");
            for (Element bindViewElement : bindViewElements) {
                String fieldName = bindViewElement.getSimpleName().toString();

                //在构造方法中添加初始化代码
                ClassName utilsClassName = ClassName.get("com.example.java.binder", "Utils");
                BindView annotation = bindViewElement.getAnnotation(BindView.class);
                int resId = annotation.value();
                constructMethodBuilder.addStatement("target.$L = $T.findViewById(target,$L)",fieldName,utilsClassName,resId);

                //在unbind方法中添加代码 target.textView1 = null;
                //不能用addCode,因为它不会在每一行代码后加分号和换行
                unbindMethod.addStatement("target.$L = null",fieldName);
            }
            classBuilder.addMethod(constructMethodBuilder.build());

            classBuilder.addMethod(unbindMethod.build());

            //开始生成
            try {

                //得到包名
                String packageName = mElementUtils.getPackageOf(enclosingElement).getQualifiedName().toString();

                JavaFile.builder(packageName,classBuilder.build())
                        //添加类的注释
                        .addFileComment("binder 自动生成")
                        .build().writeTo(mFiler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
