package com.krakedev.financiero.entidades.testJUnit;
import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTransferir {

    @Test
    void deberiaTransferirMontoValidoYRetornarTrue() {
        Banco banco = new Banco();
        Cuenta origen = banco.crearCuenta(new Cliente("1111111111", "Ana", "Lopez"));
        Cuenta destino = banco.crearCuenta(new Cliente("2222222222", "Luis", "Diaz"));
        banco.depositar(100.0, origen);

        boolean resultado = banco.transferir(40.0, origen, destino);

        assertTrue(resultado);
        assertEquals(60.0, origen.getSaldoActual());
        assertEquals(40.0, destino.getSaldoActual());
    }

    @Test
    void deberiaRechazarTransferenciaConMontoMayorAlSaldoOrigen() {
        Banco banco = new Banco();
        Cuenta origen = banco.crearCuenta(new Cliente("3333333333", "Maria", "Gomez"));
        Cuenta destino = banco.crearCuenta(new Cliente("4444444444", "Pedro", "Torres"));
        banco.depositar(30.0, origen);

        boolean resultado = banco.transferir(50.0, origen, destino);

        assertFalse(resultado);
        assertEquals(30.0, origen.getSaldoActual());
        assertEquals(0.0, destino.getSaldoActual());
    }

    @Test
    void deberiaRechazarTransferenciaConMontoNegativo() {
        Banco banco = new Banco();
        Cuenta origen = banco.crearCuenta(new Cliente("5555555555", "Sofia", "Vega"));
        Cuenta destino = banco.crearCuenta(new Cliente("6666666666", "Carlos", "Ramirez"));
        banco.depositar(100.0, origen);

        boolean resultado = banco.transferir(-10.0, origen, destino);

        assertFalse(resultado);
        assertEquals(100.0, origen.getSaldoActual());
        assertEquals(0.0, destino.getSaldoActual());
    }

    @Test
    void deberiaRechazarTransferenciaConMontoCero() {
        Banco banco = new Banco();
        Cuenta origen = banco.crearCuenta(new Cliente("7777777777", "Diana", "Ruiz"));
        Cuenta destino = banco.crearCuenta(new Cliente("8888888888", "Jorge", "Salas"));
        banco.depositar(100.0, origen);

        boolean resultado = banco.transferir(0.0, origen, destino);

        assertFalse(resultado);
        assertEquals(100.0, origen.getSaldoActual());
        assertEquals(0.0, destino.getSaldoActual());
    }

    @Test
    void deberiaTransferirTodoElSaldoDisponible() {
        Banco banco = new Banco();
        Cuenta origen = banco.crearCuenta(new Cliente("9999999999", "Elena", "Nuñez"));
        Cuenta destino = banco.crearCuenta(new Cliente("1010101010", "Marco", "Silva"));
        banco.depositar(75.0, origen);

        boolean resultado = banco.transferir(75.0, origen, destino);

        assertTrue(resultado);
        assertEquals(0.0, origen.getSaldoActual());
        assertEquals(75.0, destino.getSaldoActual());
    }
}