<?PHP
    include 'conexion.php';
    $estado=$_POST['estado'];
    $hora=$_POST['hora'];
    $fecha=$_POST['fecha'];
    $idEmple=$_POST['emple_id'];
    $ubi=$_POST['ubi'];

    $consulta="INSERT INTO  marcar_de_entrada_salida (estado_marca_de_entrada_salida,hora_marca_de_entrada_salida,fecha_marca_de_entrada_salida,id_empleado_marca_de_entrada_salida,codenadas_marca_de_entrada_salida) VALUES ('".$estado."','".$hora."','".$fecha."','".$idEmple."','".$ubi."')";
    mysqli_query($conexion,$consulta) or die (mysqli_error());
    mysqli_close($conexion);

?>