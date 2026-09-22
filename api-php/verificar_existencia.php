<?PHP 
 include 'conexion.php';
  $n_user= $_GET['n'];
  $tipo_marca=$_GET['t_m'];
  $fecha=$_GET['f'];  
 $consulta="SELECT 	id_marca_de_entrada_salida,hora_marca_de_entrada_salida  FROM marcar_de_entrada_salida WHERE id_empleado_marca_de_entrada_salida='".$n_user."' AND estado_marca_de_entrada_salida='".$tipo_marca."' AND fecha_marca_de_entrada_salida='".$fecha."'";
 $resultado=$conexion->query($consulta);

    while($fila=$resultado->fetch_array())
    {
      $datos[]=$fila;
    }
    if(empty($datos))
    {
      echo "0:-1:valor:-1";
    }else
    {echo json_encode($datos);}
    

$resultado->close();

?>