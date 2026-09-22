<?php

ob_start();

header('Content-Type: application/json; charset=utf-8');

ini_set('display_errors', '0');

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

require_once "conexion.php";

$respuesta = [
    "ok" => false,
    "mensaje" => ""
];

$transaccionIniciada = false;

try {

    if (!isset($conexion)) {
        throw new Exception("No existe la conexión a la base de datos.");
    }

    $agencia = $_POST["agencia"] ?? "";
    $peti = $_POST["peti"] ?? "";
    $fecha = $_POST["fecha"] ?? "";
    $h_ini = $_POST["h_ini"] ?? "";
    $h_fin = $_POST["h_fin"] ?? "";

    $titulo = $_POST["titulo"] ?? "";
    $cate = $_POST["cate"] ?? "";
    $comen = $_POST["comen"] ?? "";
    $trabajo = $_POST["trabajo"] ?? "";
    $material = $_POST["material"] ?? "";

    $n_respon = trim($_POST["n_respon"] ?? "");
    $n_conduc = trim($_POST["n_conduc"] ?? "");
    $n_apo1 = trim($_POST["n_apo1"] ?? "");
    $n_apo2 = trim($_POST["n_apo2"] ?? "");
    $n_apo3 = trim($_POST["n_apo3"] ?? "");

    $id_emple = trim($_POST["id_emple"] ?? "");

    $h_salida = $_POST["h_salida"] ?? "";
    $h_retorno = $_POST["h_retorno"] ?? "";
    $sector = $_POST["sector"] ?? "";

    $transporte = $_POST["transporte"] ?? "0";
    $alimen = $_POST["alimen"] ?? "0";
    $hospe = $_POST["hospe"] ?? "0";
    $t_gastos = $_POST["t_gastos"] ?? "0";

    $solu_trabajo = $_POST["solu_trabajo"] ?? "";
    $n_gerente_super = $_POST["n_geren_super"] ?? "";

    $estado = $_POST["estado"] ?? "Disponible";


    // VALIDACIONES BÁSICAS

    if ($peti === "") {
        throw new Exception("No se recibió el ID de la petición.");
    }

    if ($n_respon === "") {
        throw new Exception("No se recibió el empleado responsable.");
    }

    if ($id_emple === "") {
        throw new Exception("No se recibió el ID del empleado que realiza la tarea.");
    }
    if ($n_gerente_super === "") {
        throw new Exception("No se recibió el gerente que autorizó.");
    }


    // INICIAMOS TRANSACCIÓN

    $conexion->begin_transaction();

    $transaccionIniciada = true;

    function obtenerIdEmpleadoPorNombre($conexion, $nombre)
    {
        $nombre = trim((string)$nombre);

        // Campos opcionales
        if ($nombre === "" || strtolower($nombre) === "null") {
            return null;
        }

        $sql = "
            SELECT id_empleado
            FROM empleado
            WHERE nombre_completo_empleado = ?
            LIMIT 1
        ";

        $stmt = $conexion->prepare($sql);

        $stmt->bind_param(
            "s",
            $nombre
        );

        $stmt->execute();

        $resultado = $stmt->get_result();

        if ($resultado->num_rows === 0) {

            throw new Exception(
                "No se encontró el empleado: " . $nombre
            );
        }

        $fila = $resultado->fetch_assoc();

        return (string)$fila["id_empleado"];
    }

    $id_respon = obtenerIdEmpleadoPorNombre($conexion,$n_respon);

    $id_conduc = obtenerIdEmpleadoPorNombre( $conexion,$n_conduc);

    $id_apo1 = obtenerIdEmpleadoPorNombre($conexion,$n_apo1);

    $id_apo2 = obtenerIdEmpleadoPorNombre($conexion,$n_apo2);

    $id_apo3 = obtenerIdEmpleadoPorNombre($conexion,$n_apo3);

    // INSERTAR HOJA DE SERVICIO

    $sqlHoja = "
        INSERT INTO hoja_de_servicio
        (
            agencia_hoja_de_servicio,
            id_peti_hoja_de_servicio,
            fecha_hoja_de_servicio,
            hora_inici_hoja_de_servicio,
            hora_finalizacion_hoja_de_servicio,
            titulo_hoja_de_servicio,
            categoria_hoja_de_servicio,
            descrip_hoja_de_servicio,
            trabajo_r_hoja_de_servicio,
            materiales_hoja_de_servicio,
            id_emple_responsable_hoja_de_servicio,
            id_emple_conductor_hoja_de_servicio,
            id_emple_apoyo1_hoja_de_servicio,
            id_emple_apoyo2_hoja_de_servicio,
            id_emple_apoyo3_hoja_de_servicio,
            hora_salida_hoja_de_servicio,
            hora_retorno_hoja_de_servicio,
            sector_hoja_de_servicio,
            transporte_hoja_de_servicio,
            alimentacion_hoja_de_servicio,
            hospe_hoja_de_servicio,
            total_gastos_hoja_de_servicio,
            solucion_trabajo_hoja_de_servicio,
            nom_gerente_supervisor_hoja_de_servicio
        )
        VALUES
        (
            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
        )
    ";

    $stmtHoja = $conexion->prepare($sqlHoja);

    $stmtHoja->bind_param(
        "ssssssssssssssssssssssss",
        $agencia,
        $peti,
        $fecha,
        $h_ini,
        $h_fin,
        $titulo,
        $cate,
        $comen,
        $trabajo,
        $material,
        $id_respon,
        $id_conduc,
        $id_apo1,
        $id_apo2,
        $id_apo3,
        $h_salida,
        $h_retorno,
        $sector,
        $transporte,
        $alimen,
        $hospe,
        $t_gastos,
        $solu_trabajo,
        $n_gerente_super
    );

    $stmtHoja->execute();

    $idHoja = $conexion->insert_id;


    // ACTUALIZAR PETICIÓN

    $sqlPeticion = "
        UPDATE peticiones
        SET
            emple_recibio_peticiones = ?,
            estado_peticion = ?,
            hora_final_peticion = ?,
            fecha_final_peticion = ?
        WHERE id_peticiones = ?
    ";

    $stmtPeticion = $conexion->prepare($sqlPeticion);

    $idPeticion = (int) $peti;

    $stmtPeticion->bind_param(
        "ssssi",
        $id_emple,
        $estado,
        $h_fin,
        $fecha,
        $idPeticion
    );

    $stmtPeticion->execute();


    // COMPROBAR QUE LA PETICIÓN EXISTE

    if ($stmtPeticion->affected_rows === 0) {

        $stmtExiste = $conexion->prepare(
            "SELECT id_peticiones
             FROM peticiones
             WHERE id_peticiones = ?
             LIMIT 1"
        );

        $stmtExiste->bind_param(
            "i",
            $idPeticion
        );

        $stmtExiste->execute();

        $resultado = $stmtExiste->get_result();

        if ($resultado->num_rows === 0) {
            throw new Exception(
                "No existe la petición indicada."
            );
        }
    }


    // TODO CORRECTO

    $conexion->commit();

    $transaccionIniciada = false;

    $respuesta["ok"] = true;

    $respuesta["mensaje"] =
        "Tarea procesada correctamente.";

    $respuesta["id_hoja"] = $idHoja;

    $respuesta["estado"] = $estado;


} catch (Throwable $e) {

    if ($transaccionIniciada) {

        try {
            $conexion->rollback();
        } catch (Throwable $ignore) {
        }
    }

    //http_response_code(500);

    $respuesta["ok"] = false;
    $respuesta["mensaje"] = $e->getMessage();
}


// LIMPIA CUALQUIER SALIDA DE conexion.php

if (ob_get_length()) {
    ob_clean();
}

echo json_encode(
    $respuesta,
    JSON_UNESCAPED_UNICODE
);

exit;