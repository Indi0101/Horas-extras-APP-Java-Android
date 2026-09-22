<?PHP
 include 'conexion.php';
 $estado=$_POST['estado'];
 $id_emple=$_POST['id_emple'];
 $id_peticiones=$_GET['id'];

 $consulta="UPDATE peticiones SET 	emple_recibio_peticiones='".$id_emple."', estado_peticion='".$estado."' WHERE id_peticiones='".$id_peticiones."'";
 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>