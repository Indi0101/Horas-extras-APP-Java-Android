<?PHP 
    include "conexion.php";
    $n_empleado=$_GET["n_emple"];
    $consulta="SELECT id_ingreso_hora_extra,estado_ingreso_hora_extra,pago_total_ingreso_hora_extra FROM  ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='".$n_empleado."' AND estado_ingreso_hora_extra='Aprobada'";
    
    $resultado=$conexion->query($consulta);
    while($fila=$resultado->fetch_array())
      {
        $u=$fila;
        $datos[]=array_map("utf8_decode",$fila);
      }
     if(empty($datos))
      {
        echo -1;
      }else{
        echo json_encode($datos);
      }
   
$resultado->close();
?>
