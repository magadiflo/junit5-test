package org.magadiflo.junit5.app;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AuxiliaryTest5 {

    @Tag("param")
    @Test
    void paramTest() {
        System.out.println("Ejecutando test con tag param");
    }

    @Tag("param")
    @Test
    void paramTest2() {
        System.out.println("Ejecutando test con tag param");
    }

    @Test
    void noParamTest() {
        System.out.println("Este test unitario no tiene el tag param!");
    }

    @Tag("account")
    @Test
    void accountTest() {
        System.out.println("Ejecutando test de cuenta 1");
    }

    @Tag("account")
    @Test
    void accountTest2() {
        System.out.println("Ejecutando test de cuenta 2");
    }
}
