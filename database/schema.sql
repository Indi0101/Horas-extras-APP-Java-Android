-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-09-2026 a las 03:35:00
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_control_horas_extra`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agencias`
--

CREATE TABLE `agencias` (
  `Id_agencias` int(11) NOT NULL,
  `nombre_agencias` varchar(150) NOT NULL,
  `departamento_agencias` varchar(150) NOT NULL,
  `municipio_agencias` varchar(150) NOT NULL,
  `direccion_agencias` varchar(150) NOT NULL,
  `cordenada_agencias` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_problema`
--

CREATE TABLE `categorias_problema` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias_problema`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento_trabajo`
--

CREATE TABLE `departamento_trabajo` (
  `departamento_trabajo` int(11) NOT NULL,
  `nombre_depa_trabajo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `departamento_trabajo`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `nombre_completo_empleado` varchar(100) NOT NULL,
  `identidad_empleado` varchar(255) NOT NULL,
  `direccion_empleado` varchar(30) NOT NULL,
  `cel_empleado` varchar(150) NOT NULL,
  `correo__empleado` varchar(30) NOT NULL,
  `rtn__empleado` varchar(30) NOT NULL,
  `estado_civil_empleado` varchar(50) NOT NULL,
  `genero_empleado` varchar(30) NOT NULL,
  `salario_empleado` decimal(12,2) NOT NULL,
  `fecha_nacimiento_empleado` varchar(30) NOT NULL,
  `puesto_empleado` varchar(100) NOT NULL,
  `depar_trabajo` varchar(100) NOT NULL,
  `fecha_de_ingreso_empleado` varchar(30) NOT NULL,
  `hora_de_ingreso_empleado` varchar(30) NOT NULL,
  `contra_empleado` varchar(250) NOT NULL,
  `firma_emple` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hoja_de_servicio`
--

CREATE TABLE `hoja_de_servicio` (
  `id_hoja_de_servicio` int(11) NOT NULL,
  `agencia_hoja_de_servicio` varchar(150) NOT NULL,
  `id_peti_hoja_de_servicio` varchar(50) NOT NULL,
  `fecha_hoja_de_servicio` varchar(50) NOT NULL,
  `hora_inici_hoja_de_servicio` varchar(50) NOT NULL,
  `hora_finalizacion_hoja_de_servicio` varchar(50) NOT NULL,
  `titulo_hoja_de_servicio` varchar(255) NOT NULL,
  `categoria_hoja_de_servicio` varchar(150) NOT NULL,
  `descrip_hoja_de_servicio` text NOT NULL,
  `trabajo_r_hoja_de_servicio` text NOT NULL,
  `materiales_hoja_de_servicio` text NOT NULL,
  `id_emple_responsable_hoja_de_servicio` varchar(150) NOT NULL,
  `id_emple_conductor_hoja_de_servicio` varchar(150) NOT NULL,
  `id_emple_apoyo1_hoja_de_servicio` varchar(150) DEFAULT NULL,
  `id_emple_apoyo2_hoja_de_servicio` varchar(150) DEFAULT NULL,
  `id_emple_apoyo3_hoja_de_servicio` varchar(150) DEFAULT NULL,
  `hora_salida_hoja_de_servicio` varchar(30) NOT NULL,
  `hora_retorno_hoja_de_servicio` varchar(30) NOT NULL,
  `sector_hoja_de_servicio` varchar(150) NOT NULL,
  `transporte_hoja_de_servicio` varchar(150) NOT NULL,
  `alimentacion_hoja_de_servicio` varchar(150) NOT NULL,
  `hospe_hoja_de_servicio` varchar(150) NOT NULL,
  `total_gastos_hoja_de_servicio` decimal(12,2) NOT NULL,
  `solucion_trabajo_hoja_de_servicio` text NOT NULL,
  `nom_gerente_supervisor_hoja_de_servicio` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hoja_de_servicio`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingreso_hora_extra`
--

CREATE TABLE `ingreso_hora_extra` (
  `id_ingreso_hora_extra` int(11) NOT NULL,
  `hora_inicio_ingreso_hora_extra` varchar(30) NOT NULL,
  `hora_final_ingreso_hora_extra` varchar(30) NOT NULL,
  `fecha_inicio_ingreso_hora_extra` varchar(30) NOT NULL,
  `fecha_final_ingreso_hora_extra` varchar(30) NOT NULL,
  `nombre_empeleado_ingreso_hora_extra` varchar(150) NOT NULL,
  `id_sysaid` varchar(50) NOT NULL,
  `comentario_ingreso_hora_extra` text NOT NULL,
  `fecha__ingreso_hora_extra` varchar(50) NOT NULL,
  `hora__ingreso_hora_extra` varchar(50) NOT NULL,
  `total_horas_extras_ingreso_horas_extras` varchar(50) NOT NULL,
  `estado_ingreso_hora_extra` varchar(50) NOT NULL,
  `pago_total_ingreso_hora_extra` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ingreso_hora_extra`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcar_de_entrada_salida`
--

CREATE TABLE `marcar_de_entrada_salida` (
  `id_marca_de_entrada_salida` int(11) NOT NULL,
  `estado_marca_de_entrada_salida` varchar(30) NOT NULL,
  `hora_marca_de_entrada_salida` varchar(30) NOT NULL,
  `fecha_marca_de_entrada_salida` varchar(30) NOT NULL,
  `id_empleado_marca_de_entrada_salida` int(11) NOT NULL,
  `codenadas_marca_de_entrada_salida` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `marcar_de_entrada_salida`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peticiones`
--

CREATE TABLE `peticiones` (
  `id_peticiones` int(11) NOT NULL,
  `emple_envio_peticiones` varchar(150) NOT NULL,
  `emple_recibio_peticiones` varchar(150) NOT NULL,
  `descripcion_peticion` text NOT NULL,
  `hora_peticion` varchar(30) NOT NULL,
  `fecha_peticion` varchar(30) NOT NULL,
  `n_agencia_peticion` int(11) NOT NULL,
  `nombre_problema` varchar(50) NOT NULL,
  `estado_peticion` varchar(50) NOT NULL,
  `hora_final_peticion` varchar(50) NOT NULL,
  `fecha_final_peticion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `peticiones`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peticiones_finalizadas`
--

CREATE TABLE `peticiones_finalizadas` (
  `id_peticiones` int(11) NOT NULL,
  `ubicacion_peticiones_finalizadas` varchar(150) NOT NULL,
  `fecha_final__peticiones_finalizadas` varchar(30) NOT NULL,
  `hora_final_peticiones_finalizadas` varchar(30) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `id_empleado_encargado` int(11) NOT NULL,
  `emple_a_1_peticion_finalizada` int(11) NOT NULL,
  `emple_a_2_peticion_finalizada` int(11) NOT NULL,
  `emple_a_3_peticion_finalizada` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `problema`
--

CREATE TABLE `problema` (
  `id_problema` int(11) NOT NULL,
  `nombre_problema` varchar(150) NOT NULL,
  `nombre_categoria` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `problema`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puesto_departamento`
--

CREATE TABLE `puesto_departamento` (
  `id_puesto_departamento` int(11) NOT NULL,
  `nombre_depa_trabajo` varchar(100) NOT NULL,
  `nombre_puesto_departamento` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `puesto_departamento`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporal_horas_extra`
--

CREATE TABLE `temporal_horas_extra` (
  `id_registro` varchar(255) NOT NULL,
  `hora_inicio` varchar(255) NOT NULL,
  `hora_final` varchar(255) NOT NULL,
  `fecha_inicio` varchar(255) NOT NULL,
  `fecha_final` varchar(255) NOT NULL,
  `nombre_emple` varchar(255) NOT NULL,
  `sysaid` varchar(255) NOT NULL,
  `comentario` varchar(255) NOT NULL,
  `fecha_ingreso` varchar(255) NOT NULL,
  `hora_ingreso` varchar(255) NOT NULL,
  `total_horas` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `total_pago` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporal_marca`
--

CREATE TABLE `temporal_marca` (
  `id_registro` varchar(255) NOT NULL,
  `estado_registro` varchar(255) NOT NULL,
  `hora_registro` varchar(255) NOT NULL,
  `fecha_registro` varchar(255) NOT NULL,
  `nombre_registro` varchar(255) NOT NULL,
  `corde_registro` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporal_peti`
--

CREATE TABLE `temporal_peti` (
  `id_registro` varchar(255) NOT NULL,
  `hora_ingre` varchar(255) NOT NULL,
  `fecha_ingre` varchar(255) NOT NULL,
  `ingresado_por` varchar(255) NOT NULL,
  `agencia` varchar(255) NOT NULL,
  `empleado` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `problema` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `hora_final` varchar(255) NOT NULL,
  `fecha_final` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporal_sysaid`
--

CREATE TABLE `temporal_sysaid` (
  `id_registro` varchar(255) NOT NULL,
  `agencia_registro` varchar(255) NOT NULL,
  `peti_registro` varchar(255) NOT NULL,
  `fecha_registro` varchar(255) NOT NULL,
  `hora_ini_registro` varchar(255) NOT NULL,
  `hora_fin_registro` varchar(255) NOT NULL,
  `titulo_registro` varchar(255) NOT NULL,
  `cate_registro` varchar(255) NOT NULL,
  `descrip_registro` text NOT NULL,
  `trabajo_registro` text NOT NULL,
  `mate_registro` text NOT NULL,
  `emple_res_registro` varchar(255) NOT NULL,
  `emple_con__registro` varchar(255) NOT NULL,
  `emple_apo1_registro` varchar(255) NOT NULL,
  `emple_apo2_registro` varchar(255) NOT NULL,
  `emple_apo3_registro` varchar(255) NOT NULL,
  `h_salida_registro` varchar(255) NOT NULL,
  `h_retorno_registro` varchar(255) NOT NULL,
  `sector_registro` varchar(255) NOT NULL,
  `trans_registro` varchar(255) NOT NULL,
  `alimen_registro` varchar(255) NOT NULL,
  `hospe_registro` varchar(255) NOT NULL,
  `total_gasto_registro` varchar(255) NOT NULL,
  `solu_registro` text NOT NULL,
  `nom_geren_registro` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agencias`
--
ALTER TABLE `agencias`
  ADD PRIMARY KEY (`Id_agencias`);

--
-- Indices de la tabla `categorias_problema`
--
ALTER TABLE `categorias_problema`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `departamento_trabajo`
--
ALTER TABLE `departamento_trabajo`
  ADD PRIMARY KEY (`departamento_trabajo`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`);

--
-- Indices de la tabla `hoja_de_servicio`
--
ALTER TABLE `hoja_de_servicio`
  ADD PRIMARY KEY (`id_hoja_de_servicio`);

--
-- Indices de la tabla `ingreso_hora_extra`
--
ALTER TABLE `ingreso_hora_extra`
  ADD PRIMARY KEY (`id_ingreso_hora_extra`);

--
-- Indices de la tabla `marcar_de_entrada_salida`
--
ALTER TABLE `marcar_de_entrada_salida`
  ADD PRIMARY KEY (`id_marca_de_entrada_salida`);

--
-- Indices de la tabla `peticiones`
--
ALTER TABLE `peticiones`
  ADD PRIMARY KEY (`id_peticiones`);

--
-- Indices de la tabla `peticiones_finalizadas`
--
ALTER TABLE `peticiones_finalizadas`
  ADD PRIMARY KEY (`id_peticiones`);

--
-- Indices de la tabla `problema`
--
ALTER TABLE `problema`
  ADD PRIMARY KEY (`id_problema`);

--
-- Indices de la tabla `puesto_departamento`
--
ALTER TABLE `puesto_departamento`
  ADD PRIMARY KEY (`id_puesto_departamento`);

--
-- Indices de la tabla `temporal_marca`
--
ALTER TABLE `temporal_marca`
  ADD PRIMARY KEY (`id_registro`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agencias`
--
ALTER TABLE `agencias`
  MODIFY `Id_agencias` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `categorias_problema`
--
ALTER TABLE `categorias_problema`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `departamento_trabajo`
--
ALTER TABLE `departamento_trabajo`
  MODIFY `departamento_trabajo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `hoja_de_servicio`
--
ALTER TABLE `hoja_de_servicio`
  MODIFY `id_hoja_de_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ingreso_hora_extra`
--
ALTER TABLE `ingreso_hora_extra`
  MODIFY `id_ingreso_hora_extra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `marcar_de_entrada_salida`
--
ALTER TABLE `marcar_de_entrada_salida`
  MODIFY `id_marca_de_entrada_salida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `peticiones`
--
ALTER TABLE `peticiones`
  MODIFY `id_peticiones` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `peticiones_finalizadas`
--
ALTER TABLE `peticiones_finalizadas`
  MODIFY `id_peticiones` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `problema`
--
ALTER TABLE `problema`
  MODIFY `id_problema` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `puesto_departamento`
--
ALTER TABLE `puesto_departamento`
  MODIFY `id_puesto_departamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
