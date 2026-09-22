<?PHP 
    include "conexion.php";

    $consulta="SELECT id_peticiones,emple_recibio_peticiones,descripcion_peticion,hora_peticion,fecha_peticion,estado_peticion,nombre_problema,fecha_final_peticion,hora_final_peticion,nombre_agencias,direccion_agencias,cordenada_agencias FROM peticiones,agencias WHERE peticiones.n_agencia_peticion=agencias.Id_agencias AND estado_peticion<>'Finalizada'";
  
   
    $resultado=$conexion->query($consulta);
    while($fila=$resultado->fetch_array())
    {
       $u[]=$fila;
      ///  $datos[]=array_map("utf8_decode",$fila);
    }
   echo json_encode($u);
    $resultado->close();
?>