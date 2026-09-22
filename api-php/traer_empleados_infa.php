<?PHP
    include "conexion.php";
    $email=isset($_GET["email"]) ? $_GET["email"] : "";
    $con=isset($_GET["con"]) ? $_GET["con"]: "";

    $datos = [];
  
    $consulta="SELECT 
        id_empleado,
        nombre_completo_empleado 
      FROM empleado 
      WHERE correo__empleado=? 
      AND contra_empleado=? 
      AND depar_trabajo='Infatlan'
      ";
    

  $stmt = $conexion->prepare($consulta);
  $stmt->bind_param("ss", $email, $con);
  $stmt->execute();

  $resultado=$stmt->get_result();

  while($fila = $resultado->fetch_assoc())
  {
    $datos[] = $fila;
  }
  
  if(count($datos) > 0){
    setcookie("ID", $datos[0]["id_empleado"], time() + 30 * 24 * 60 * 60);
  }

  header('Content-Type: application/json; charset=utf-8');

  
  echo json_encode($datos);

  $stmt->close();
  $conexion->close();
?>