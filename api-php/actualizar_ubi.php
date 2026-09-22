<?PHP
    include "conexion.php";
    
    $ubi=$_POST["ubi"];
    $id=$_POST["id"];    
    $consulta="UPDATE marcar_de_entrada_salida SET codenadas_marca_de_entrada_salida='".$ubi."' WHERE id_marca_de_entrada_salida='".$id."'";
    mysqli_query($conexion,$consulta) or die (mysqli_error());
    mysqli_close($conexion);
?>      