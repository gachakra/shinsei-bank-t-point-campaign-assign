package dev.notonza.shinsei.config;


import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author gachakra
 * Created on 2020/02/26.
 */
final public class LoadConfiguration<T extends YamlConfiguration> {

    private final String configYmlPath;
    private final Class<T> configClass;

    public LoadConfiguration(String configYmlPath, Class<T> configClass) {
        this.configYmlPath = configYmlPath;
        this.configClass = configClass;
    }

    public T execute() {

        Yaml yaml = new Yaml(new Constructor(configClass));
        AtomicReference<InputStream> inputStream = new AtomicReference<>(
                this.getClass()
                        .getClassLoader()
                        .getResourceAsStream(configYmlPath));

        return yaml.load(inputStream.get());
    }
}
