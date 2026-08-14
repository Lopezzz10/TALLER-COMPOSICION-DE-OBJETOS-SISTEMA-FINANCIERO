package com.krakedev.financiero.entidades.testJUnit;
import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRetirar {

    @Test
    void deberiaRetirarMontoValidoYRetornarTrue() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1111111111", "Ana", "Lopez");
        Cuenta cuenta = banco.crearCuenta(cliente);
        banco.depositar(100.0, cuenta);

        boolean resultado = banco.retirar(40.0, cuenta);

        assertTrue(resultado);
        assertEquals(60.0, cuenta.getSaldoActual());
    }

    @Test
    void deberiaRetirarElSaldoTotalYDejarloEnCero() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("2222222222", "Luis", "Diaz");
        Cuenta cuenta = banco.crearCuenta(cliente);
        banco.depositar(50.0, cuenta);

        boolean resultado = banco.retirar(50.0, cuenta);

        assertTrue(resultado);
        assertEquals(0.0, cuenta.getSaldoActual());
    }

    @Test
    void deberiaRechazarMontoNegativoYRetornarFalse() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("3333333333", "Maria", "Gomez");
        Cuenta cuenta = banco.crearCuenta(cliente);
        banco.depositar(100.0, cuenta);

        boolean resultado = banco.retirar(-10.0, cuenta);

        assertFalse(resultado);
        assertEquals(100.0, cuenta.getSaldoActual());
    }

    @Test
    void deberiaRechazarMontoCeroYRetornarFalse() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("4444444444", "Pedro", "Torres");
        Cuenta cuenta = banco.crearCuenta(cliente);
        banco.depositar(100.0, cuenta);

        boolean resultado = banco.retirar(0.0, cuenta);

        assertFalse(resultado);
        assertEquals(100.0, cuenta.getSaldoActual());
    }

    @Test
    void deberiaRechazarMontoMayorAlSaldoYRetornarFalse() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("5555555555", "Sofia", "Vega");
        Cuenta cuenta = banco.crearCuenta(cliente);
        banco.depositar(30.0, cuenta);

        boolean resultado = banco.retirar(50.0, cuenta);

        assertFalse(resultado);
        assertEquals(30.0, cuenta.getSaldoActual());
    }
}