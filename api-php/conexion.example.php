<?php
    $servidor = "localhost";
    $usuario = "TU_USUARIO"; 
    $contrasena = "TU_PASSWORD";  
    $BD = "bd_control_horas_extra";
    
    $conexion=new mysqli($servidor,$usuario,$contrasena,$BD);
    if($conexion->connect_errno){
            echo "fallo la conexion";

    }
?>