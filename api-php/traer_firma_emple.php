<?PHP
       include "conexion.php";
   
       $consulta="SELECT nombre_completo_empleado,firma_emple,contra_empleado FROM empleado WHERE depar_trabajo='Gerencia'";
       $resultado=$conexion->query($consulta);
    
       while($fila=$resultado->fetch_array())
       {
        $datos[]=array_map("utf8_decode",$fila);
       }
    
      echo json_encode($datos);
   
 $resultado->close();

?>