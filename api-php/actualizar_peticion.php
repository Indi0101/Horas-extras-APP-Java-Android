<?PHP
 include 'conexion.php';

 $fecha=$_POST['fecha'];
 $hora=$_POST['hora'];
 $id_emple=$_POST['id_emple'];
 $estado=$_POST['estado'];
 $id_peticiones=$_GET['id_peticion'];

 $consulta="UPDATE peticiones SET emple_recibio_peticiones='".$id_emple."', estado_peticion='".$estado."', 
                  hora_final_peticion='".$hora."',fecha_final_peticion='".$fecha."' WHERE id_peticiones='".$id_peticiones."'";
 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>