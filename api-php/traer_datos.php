<?PHP
    include "conexion.php";
   
      $consulta="SELECT id_peticiones,descripcion_peticion,hora_peticion,fecha_peticion,n_agencia_peticion FROM peticiones";
      $resultado=$conexion->query($consulta);

      while($fila=$resultado->fetch_array())
      {
        $u=$fila;
        $datos[]=array_map("utf8_decode",$fila);
      }
     
     echo json_encode($u);
  
$resultado->close();
?>