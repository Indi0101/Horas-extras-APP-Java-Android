<?PHP 
 include "conexion.php";
$agencia=$_POST["agencia"];     $n_conduc=$_POST["n_conduc"];
$peti=$_POST["peti"];           $n_apo1=$_POST["n_apo1"];
$fecha=$_POST["fecha"];         $n_apo2=$_POST["n_apo2"];
$h_ini=$_POST["h_ini"];         $n_apo3=$_POST["n_apo3"];
$h_fin=$_POST["h_fin"];         $h_salida=$_POST["h_salida"];
$titulo=$_POST["titulo"];       $h_retorno=$_POST["h_retorno"];                               
$cate=$_POST["cate"];           $transporte=$_POST["transporte"];
$comen=$_POST["comen"];         $alimen=$_POST["alimen"];
$trabajo=$_POST["trabajo"];     $t_gastos=$_POST["t_gastos"];
$material=$_POST["material"];   $solu_trabajo=$_POST["solu_trabajo"];
$n_respon=$_POST["n_respon"];   $n_gerente_super=$_POST["n_geren_super"];
$sector=$_POST["sector"];       $hospe=$_POST["hospe"];

$consulta="INSERT INTO  hoja_de_servicio (agencia_hoja_de_servicio,id_peti_hoja_de_servicio,fecha_hoja_de_servicio,hora_inici_hoja_de_servicio,hora_finalizacion_hoja_de_servicio,
                        titulo_hoja_de_servicio,categoria_hoja_de_servicio,descrip_hoja_de_servicio,trabajo_r_hoja_de_servicio,materiales_hoja_de_servicio,id_emple_responsable_hoja_de_servicio,
                        id_emple_conductor_hoja_de_servicio,id_emple_apoyo1_hoja_de_servicio,id_emple_apoyo2_hoja_de_servicio,id_emple_apoyo3_hoja_de_servicio,hora_salida_hoja_de_servicio,
                        hora_retorno_hoja_de_servicio,sector_hoja_de_servicio,transporte_hoja_de_servicio,alimentacion_hoja_de_servicio,hospe_hoja_de_servicio,total_gastos_hoja_de_servicio,solucion_trabajo_hoja_de_servicio,nom_gerente_supervisor_hoja_de_servicio)
                        VALUES ('".$agencia."','".$peti."','".$fecha."','".$h_ini."','".$h_fin."','".$titulo."','".$cate."','".$comen."','".$trabajo."','".$material."','".$n_respon."'
                        ,'".$n_conduc."','".$n_apo1."','".$n_apo2."','".$n_apo3."','".$h_salida."','".$h_retorno."','".$sector."','".$transporte."','".$alimen."','".$hospe."','".$t_gastos."','".$solu_trabajo."','".$n_gerente_super."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>