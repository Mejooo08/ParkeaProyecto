function tipo_de_parqueadero() {
    var bienvenida = document.getElementById("grafica");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var documento = document.getElementById("tipo_Documento");
    var telefono = document.getElementById("Telefono");
    var usuario = document.getElementById("usuarios");

    if (tipo_de_parqueadero.style.display === "none") {
        bienvenida.style.display = "none";
        tipo_de_parqueadero.style.display = "block";
        documento.style.display = "none";
        telefono.style.display = 'none';
        usuario.style.display = 'none';
    } else {
        bienvenida.style.display = "block";
        tipo_de_parqueadero.style.display = "none";
        documento.style.display = "none";
    }
    telefono.style.display = 'none';
    usuario.style.display = 'none';
};

function carros() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var telefono = document.getElementById("Telefono");
    var usuario = document.getElementById("usuarios");

    if (carro.style.display === "none") {
        bienvenida.style.display = "none";
        carro.style.display = "block";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        telefono.style.display = 'none';
        usuario.style.display = 'none';
    } else {
        bienvenida.style.display = "block";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        telefono.style.display = 'none';
        usuario.style.display = 'none';
    }
};

function reserva() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var reserva = document.getElementById("Reserva");
    var telefono = document.getElementById("Telefono");
    var usuario = document.getElementById("usuarios");

    if (reserva.style.display === "none") {
        bienvenida.style.display = "none";
        reserva.style.display = "block";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        telefono.style.display = 'none';
        usuario.style.display = 'none';
    } else {
        bienvenida.style.display = "block";
        reserva.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        telefono.style.display = 'none';
        usuario.style.display = 'none';
    }
};
function telefono() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var reserva = document.getElementById("Reserva");
    var telefono = document.getElementById("Telefono");
    var usuario = document.getElementById("usuarios");

    if (telefono.style.display === "none") {
        telefono.style.display = 'block';
        usuario.style.display = 'none';
        bienvenida.style.display = "none";
        reserva.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        usuario.style.display = 'none';

    } else {
        bienvenida.style.display = "block";
        reserva.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        telefono.style.display = 'none';
        usuario.style.display = 'none';
    }
};
function usuario() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var reserva = document.getElementById("Reserva");
    var telefono = document.getElementById("Telefono");
    var usuario = document.getElementById("usuarios");

    if (usuario.style.display === "none") {
        usuario.style.display = 'block';
        bienvenida.style.display = "none";
        reserva.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        telefono.style.display = 'none';

    } else {
        bienvenida.style.display = "block";
        reserva.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        telefono.style.display = 'none';
        usuario.style.display = 'none';
    }
};