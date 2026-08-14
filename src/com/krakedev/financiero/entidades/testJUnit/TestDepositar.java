package com.krakedev.financiero.entidades.testJUnit;
import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDepositar {

    @Test
    void deberiaDepositarMontoValidoYRetornarTrue() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1111111111", "Ana", "Lopez");
        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.depositar(100.0, cuenta);

        assertTrue(resultado);
        assertEquals(100.0, cuenta.getSaldoActual());
    }

    @Test
    void deberiaAcumularVariosDepositosEnElSaldo() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("2222222222", "Luis", "Diaz");
        Cuenta cuenta = banco.crearCuenta(cliente);

        banco.depositar(50.0, cuenta);
        banco.depositar(25.5, cuenta);

        assertEquals(75.5, cuenta.getSaldoActual());
    }

    @Test
    void deberiaRechazarMontoNegativoYRetornarFalse() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("3333333333", "Maria", "Gomez");
        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.depositar(-10.0, cuenta);

        assertFalse(resultado);
        assertEquals(0.0, cuenta.getSaldoActual());
    }

    @Test
    void deberiaRechazarMontoCeroYRetornarFalse() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("4444444444", "Pedro", "Torres");
        Cuenta cuenta = banco.crearCuenta(cliente);

        boolean resultado = banco.depositar(0.0, cuenta);

        assertFalse(resultado);
        assertEquals(0.0, cuenta.getSaldoActual());
    }
}