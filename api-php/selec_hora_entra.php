<?PHP 
    include "conexion.php";
    $id_empleado=$_GET["id"];
    $fecha=$_GET["fecha"];
    $consulta="SELECT hora_marca_de_entrada_salida FROM  marcar_de_entrada_salida WHERE id_empleado_marca_de_entrada_salida='".$id_empleado."' AND fecha_marca_de_entrada_salida='".$fecha."'";

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