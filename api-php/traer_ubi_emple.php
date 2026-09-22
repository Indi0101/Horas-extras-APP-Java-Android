<?PHP
    include "conexion.php";
    $estado=$_GET["estado"];
  $consulta="SELECT fecha_marca_de_entrada_salida,codenadas_marca_de_entrada_salida,nombre_completo_empleado FROM marcar_de_entrada_salida,empleado WHERE marcar_de_entrada_salida.id_empleado_marca_de_entrada_salida=empleado.id_empleado AND marcar_de_entrada_salida.estado_marca_de_entrada_salida='".$estado."'";
  $resultado=$conexion->query($consulta);
    
  while($fila=$resultado->fetch_array())
  {
   
   /// $datos[]=array_map("utf8_decode",$fila);
   $datos[]=$fila;
       
  }
    echo json_encode($datos);
$resultado->close();
?>