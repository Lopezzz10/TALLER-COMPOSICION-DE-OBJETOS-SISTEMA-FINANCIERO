package com.krakedev.financiero.entidades.testJUnit;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBanco {

    @Test
    void deberiaCrearCuentaConCodigoInicial() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1234567890", "Juan", "Perez");

        Cuenta cuenta = banco.crearCuenta(cliente);

        assertEquals("1000", cuenta.getId());
        assertEquals("A", cuenta.getTipo());
        assertEquals(cliente, cuenta.getPropietario());
    }

    @Test
    void deberiaIncrementarUltimoCodigoDespuesDeCrearCuenta() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("1234567890", "Juan", "Perez");

        banco.crearCuenta(cliente);

        assertEquals(1001, banco.getUltimoCodigo());
    }

    @Test
    void deberiaCrearCuentasConCodigosConsecutivos() {
        Banco banco = new Banco();
        Cliente cliente1 = new Cliente("1111111111", "Ana", "Lopez");
        Cliente cliente2 = new Cliente("2222222222", "Luis", "Diaz");
        Cliente cliente3 = new Cliente("3333333333", "Maria", "Gomez");

        Cuenta cuenta1 = banco.crearCuenta(cliente1);
        Cuenta cuenta2 = banco.crearCuenta(cliente2);
        Cuenta cuenta3 = banco.crearCuenta(cliente3);

        assertEquals("1000", cuenta1.getId());
        assertEquals("1001", cuenta2.getId());
        assertEquals("1002", cuenta3.getId());
    }

    @Test
    void cuentaCreadaDebeTenerElClienteAsignado() {
        Banco banco = new Banco();
        Cliente cliente = new Cliente("0987654321", "Carlos", "Ramirez");

        Cuenta cuenta = banco.crearCuenta(cliente);

        assertNotNull(cuenta.getPropietario());
        assertEquals("Carlos", cuenta.getPropietario().getNombre());
    }
}