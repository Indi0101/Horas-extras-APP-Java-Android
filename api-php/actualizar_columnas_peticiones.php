<?PHP 
 include "conexion.php";
$id_peticion=$_POST["id_peticion"];
$hora_f=$_POST["hora_final"];
$fecha_f=$_POST["fecha_final"];
$id_empleado=$_POST["id_empleado"];

$consulta="UPDATE peticiones SET hora_final_peticion='".$hora_f."',fecha_final_peticion='".$fecha_f."',emple_recibio_peticiones='".$id_empleado."' WHERE id_peticiones ='".$id_peticion."'";
mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>