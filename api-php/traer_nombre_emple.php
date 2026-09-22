<?PHP
       include "conexion.php";
   
       $consulta="SELECT id_empleado,nombre_completo_empleado FROM empleado";
       $resultado=$conexion->query($consulta);
       $datos=[];
       $d=[];
       while($fila=$resultado->fetch_array())
       {
         $datos[]=array_map("utf8_decode",$fila);
        
       }
       echo json_encode($datos);
       ////array_push($datos,$d);
      
   
 $resultado->close();

?>