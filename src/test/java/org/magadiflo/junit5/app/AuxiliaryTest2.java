package org.magadiflo.junit5.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class AuxiliaryTest2 {

    @Nested
    class OperatingSystemTest {
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
    }

    @Nested
    class JavaVersionTest {
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
    }

    @Nested
    class SystemPropertiesTest {
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

        @Test
        void failTheTest() {
            Assertions.fail("Fallando para ver el comportamiento");
        }
    }

    @Nested
    class EnvironmentVariablesTest {
        @Test
        @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = "C:\\\\Program Files\\\\Java\\\\jdk-17.0.4.1")
        void testJavaHome() {
            System.out.println("Ejecutando test porque cumple la condición de la variable de ambiente");
        }

        @Test
        @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "8")
        void processorsNumber() {
            System.out.println("Ejecutando test solo si tiene 8 procesadores");
        }

        @Test
        @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "dev")
        void testEnvironmentDev() {
            System.out.println("Ejecutando test solo si su variable de ambiente del SO es dev");
        }

        @Test
        @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "prod")
        void testEnvironmentProd() {
            System.out.println("Ejecutando test solo si su variable de ambiente del SO es prod");
        }
    }
}

