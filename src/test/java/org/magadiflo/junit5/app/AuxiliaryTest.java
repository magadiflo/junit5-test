package org.magadiflo.junit5.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.util.Properties;

public class AuxiliaryTest {
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void onlyWindowsTest() {
        System.out.println("Ejecutando test para Windows!");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void onlyLinuxTest() {
        System.out.println("Ejecutando test para Linux!");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void noExecuteInWindows() {
        System.out.println("Este test no se está ejecutando en Windows");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyJava8() {
        System.out.println("Este test solo debe ejecutarse si usa java 8");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void onlyJava17() {
        System.out.println("Este test solo debe ejecutarse si usa java 17");
    }

    @Test
    void printSystemProperties() {
        Properties properties = System.getProperties();
        properties.forEach((key, value) -> System.out.printf("%s : %s\n", key, value));
    }

    @Test
    @EnabledIfSystemProperty(named = "java.version", matches = "17.0.4.1")
    void javaVersion() {
        System.out.println("Ejecutando test para la versión exacta de java 17.0.4.1");
    }

    @Test
    @DisabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
    void archOsVersion() {
        System.out.println("Solo se ejecutará si la arquitectura del SO no es de 32bits");
    }

    @Test
    @EnabledIfSystemProperty(named = "ENV", matches = "dev")
    void devTest() {
        System.out.println("Test ejecutado solo si existe la propiedad de sistema DEV con valor dev");
    }
}
