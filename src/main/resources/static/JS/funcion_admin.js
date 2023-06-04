function tipo_de_parqueadero() {
    var bienvenida = document.getElementById("grafica");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");


    if (tipo_de_parqueadero.style.display === "none") {
        bienvenida.style.display = "none";
        tipo_de_parqueadero.style.display = "block";

    } else {
        bienvenida.style.display = "block";
        tipo_de_parqueadero.style.display = "none";
    }
};

function carros() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");

    if (carro.style.display === "none") {
        bienvenida.style.display = "none";
        carro.style.display = "block";

    } else {
        bienvenida.style.display = "block";
        carro.style.display = "none";
    }
};

function reserva() {
    var bienvenida = document.getElementById("grafica");
    var reserva = document.getElementById("Reserva");



    if (reserva.style.display === "none") {
        bienvenida.style.display = "none";
        reserva.style.display = "block";

    } else {
        bienvenida.style.display = "block";
        reserva.style.display = "none";
    }
};
function telefono() {
    var bienvenida = document.getElementById("grafica");
    var telefono = document.getElementById("Telefono");


    if (telefono.style.display === "none") {
        telefono.style.display = 'block';
        bienvenida.style.display = "none";
    } else {
        bienvenida.style.display = "block";
        telefono.style.display = 'none';

    }
};
function usuario() {
    var bienvenida = document.getElementById("grafica");
    var usuario = document.getElementById("usuarios");


    if (usuario.style.display === "none") {
        usuario.style.display = 'block';
        bienvenida.style.display = "none";


    } else {
        bienvenida.style.display = "block";
        usuario.style.display = 'none';
    }
};
function auditoria() {
    var bienvenida = document.getElementById("grafica");
    var auditoria = document.getElementById("auditoria");

    if (auditoria.style.display === "none") {
        auditoria.style.display = 'block';
        bienvenida.style.display = "none";

    } else {
        bienvenida.style.display = "block";
        auditoria.style.display = 'none';
    }
};