package org.magadiflo.junit5.app;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.magadiflo.junit5.app.models.Account;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void printEnvironmentVariables() {
        Map<String, String> getenv = System.getenv();
        getenv.forEach((key, value) -> System.out.printf("%s : %s\n", key, value));
    }

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

    @Test
    void balanceAccountTestOnlyIfDev() {
        boolean isDev = "dev".equals(System.getProperty("ENV"));

        Assumptions.assumeTrue(isDev);

        Account account = new Account("Martín", new BigDecimal("2000"));
        assertEquals(2000D, account.getBalance().doubleValue());
    }

    @Test
    void balanceAccountTestOnlyIfDevWithAssumeThat() {
        boolean isDev = "dev".equals(System.getProperty("ENV"));

        Assumptions.assumingThat(isDev, () -> {
            Account account = new Account("Martín", new BigDecimal("2000"));
            assertEquals(2000D, account.getBalance().doubleValue());
        });
        System.out.println("Ejecutando algún otro test");
    }
}
