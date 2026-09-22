<?PHP
include 'conexion.php';
$nombre=$_GET['nombre'];
$con=$_GET['con'];
$puesto=$_GET['puesto'];

$consulta="SELECT id_empleado,nombre_completo_empleado FROM empleado WHERE nombre_completo_empleado='".$nombre."' AND contra_empleado='".$con."' AND puesto_empleado='".$puesto."'";
$resultado=$conexion->query($consulta);


while($fila=$resultado->fetch_array())
{
  
  $u=$fila;
   /// print($fila);
    $datos[]=array_map("utf8_decode",$fila);
 
}
if(empty($datos)){
  echo -1;
}else{
  echo json_encode($datos);

}


$resultado->close();

?>