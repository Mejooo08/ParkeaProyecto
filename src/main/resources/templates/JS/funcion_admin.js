function tipo_de_parqueadero() {
    var bienvenida = document.getElementById("grafica");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var documento = document.getElementById("tipo_Documento");


    if (tipo_de_parqueadero.style.display === "none") {
        bienvenida.style.display = "none";
        tipo_de_parqueadero.style.display = "block";
        documento.style.display = "none";
    } else {
        bienvenida.style.display = "block";
        tipo_de_parqueadero.style.display = "none";
        documento.style.display = "none";
    }
};

function tipo_de_documento() {
    var bienvenida = document.getElementById("grafica");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");

    if (documento.style.display === "none") {
        bienvenida.style.display = "none";
        documento.style.display = "block";
        tipo_de_parqueadero.style.display = 'none';
    } else {
        bienvenida.style.display = "block";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';

    }
};
function carros() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");

    if (carro.style.display === "none") {
        bienvenida.style.display = "none";
        carro.style.display = "block";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
    } else {
        bienvenida.style.display = "block";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
    }
};

function auditoria() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var auditoria = document.getElementById("Auditoria");

    if (auditoria.style.display === "none") {
        bienvenida.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
        auditoria.style.display = "block";
    } else {
        bienvenida.style.display = "block";
        auditoria.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
    }
};

function reserva() {
    var bienvenida = document.getElementById("grafica");
    var carro = document.getElementById("carros");
    var documento = document.getElementById("tipo_Documento");
    var tipo_de_parqueadero = document.getElementById("tipo_parqueadero");
    var auditoria = document.getElementById("Auditoria");
    var reserva = document.getElementById("Reserva");

    if (reserva.style.display === "none") {
        bienvenida.style.display = "none";
        reserva.style.display = "block";
        auditoria.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
    } else {
        bienvenida.style.display = "block";
        reserva.style.display = "none";
        auditoria.style.display = "none";
        carro.style.display = "none";
        documento.style.display = "none";
        tipo_de_parqueadero.style.display = 'none';
    }
};