<?PHP 
    include "conexion.php";
    $id_empleado=$_GET["id"];
    $consulta="SELECT id_peticiones,estado_peticion,fecha_final_peticion FROM peticiones WHERE emple_recibio_peticiones='".$id_empleado."' AND estado_peticion='Finalizada'";
    
    $resultado=$conexion->query($consulta);
    while($fila=$resultado->fetch_array())
      {
        $datos[]=array_map("utf8_decode",$fila);
      }
     
        echo json_encode($datos);
    
$resultado->close();
?>
