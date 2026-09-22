<?PHP
    include "conexion.php";

  $consulta="SELECT nombre_agencias,direccion_agencias,cordenada_agencias FROM agencias";
  $resultado=$conexion->query($consulta);
    
  while($fila=$resultado->fetch_array())
  {
   
   /// $datos[]=array_map("utf8_decode",$fila);
   $datos[]=$fila;
       
  }
    echo json_encode($datos);
$resultado->close();
?>