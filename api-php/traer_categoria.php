<?PHP
       include "conexion.php";
   
       $consulta="SELECT nombre_categoria FROM categorias_problema";
       $resultado=$conexion->query($consulta);
    
       while($fila=$resultado->fetch_array())
       {
        $datos[]=array_map("utf8_decode",$fila);
       }
    
      echo json_encode($datos);
   
 $resultado->close();

?>