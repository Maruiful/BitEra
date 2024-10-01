package com.github.paicoding.forum.core.autoconf;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.boot.context.properties.bind.handler.IgnoreErrorsBindHandler;
import org.springframework.boot.context.properties.bind.handler.IgnoreTopLevelConverterNotFoundBindHandler;
import org.springframework.boot.context.properties.bind.handler.NoUnboundElementsBindHandler;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.context.properties.source.UnboundElementsSourceFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.PropertySources;

import java.util.function.Consumer;

/**
 * 自定义动态配置绑定
 * */
public class DynamicConfigBinder {
    private final ApplicationContext applicationContext;
    private PropertySources propertySource;

    private volatile Binder binder;

    public DynamicConfigBinder(ApplicationContext applicationContext, PropertySources propertySource) {
        this.applicationContext = applicationContext;
        this.propertySource = propertySource;
    }

    public <T> void bind(Bindable<T> bindable) {
        ConfigurationProperties propertiesAno = bindable.getAnnotation(ConfigurationProperties.class);
        if (propertiesAno != null) {
            BindHandler bindHandler = getBindHandler(propertiesAno);
            getBinder().bind(propertiesAno.prefix(), bindable, bindHandler);
        }
    }

    public <T> void bind(String prefix, Bindable<T> bindable, BindHandler bindHandler) {
        getBinder().bind(prefix, bindable, bindHandler);
    }

    private BindHandler getBindHandler(ConfigurationProperties annotation)  { return null; }

    private Binder getBinder() {
        if (this.binder == null) {
            synchronized (this) {
                if (this.binder == null) {
                    this.binder = new Binder(getConfigurationPropertySources(),
                            getPropertySourcesPlaceholdersResolver(), getConversionService(),
                            getPropertyEditorInitializer());
                }
            }
        }
        return this.binder;
    }

    private Iterable<ConfigurationPropertySource> getConfigurationPropertySources()  { return null; }

    /**
     * 指定占位符的前缀、后缀、默认值分隔符、未解析忽略、环境变量容器
     *
     * @return
     */
    private PropertySourcesPlaceholdersResolver getPropertySourcesPlaceholdersResolver()  { return null; }

    /**
     * 类型转换
     *
     * @return
     */
    private ConversionService getConversionService()  { return null; }

    private Consumer<PropertyEditorRegistry> getPropertyEditorInitializer()  { return null; }
}
