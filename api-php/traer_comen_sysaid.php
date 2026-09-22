<?PHP

include "conexion.php";
    $id_peti=$_GET["id_peti"];
  $consulta="SELECT descrip_hoja_de_servicio FROM hoja_de_servicio WHERE id_peti_hoja_de_servicio = '".$id_peti."' ";
  $resultado=$conexion->query($consulta);

  while($fila=$resultado->fetch_array())
  {
      $u[]=$fila;
   /// $datos[]=array_map("utf8_decode",$fila);
  }
  
echo json_encode($u);
$resultado->close();
?>