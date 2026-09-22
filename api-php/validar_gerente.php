<?php

    header('Content-Type: application/json; charset=utf-8');

    include "conexion.php";

    $respuesta = [
        "ok" => false,
        "mensaje" => "Credenciales incorrectas"
    ];

    $nombre = $_POST["nombre"] ?? "";
    $password = $_POST["password"] ?? "";

    if (empty($nombre) || empty($password)) {

        $respuesta["mensaje"] = "Debe ingresar usuario y contraseña.";

        echo json_encode($respuesta);
        exit;
    }

    $stmt = $conexion->prepare(
        "SELECT id_empleado, nombre_completo_empleado
        FROM empleado
        WHERE nombre_completo_empleado = ?
        AND contra_empleado = ?
        AND depar_trabajo = 'Gerencia'
        LIMIT 1"
    );

    $stmt->bind_param(
        "ss",
        $nombre,
        $password
    );

    $stmt->execute();

    $resultado = $stmt->get_result();

    if ($fila = $resultado->fetch_assoc()) {

        $respuesta["ok"] = true;

        $respuesta["mensaje"] =
            "Gerente autorizado correctamente.";

        $respuesta["nombre"] =
            $fila["nombre_completo_empleado"];
    }

    echo json_encode($respuesta);

    $stmt->close();
    $conexion->close();

?>