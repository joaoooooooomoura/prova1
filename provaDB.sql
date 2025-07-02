-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               9.3.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
----------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `provaDB` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `provaDB`;

CREATE TABLE IF NOT EXISTS `pessoa` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `nome` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)
    ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `pessoa` (`id`, `nome`, `email`) VALUES
        (1, 'Lucas Pereira', 'lucas.pereira@email.com'),
        (2, 'Mariana Torres', 'mariana.torres@email.com'),
        (3, 'Rafael Nogueira', 'rafael.nogueira@email.com'),
        (4, 'Juliana Martins', 'juliana.martins@email.com'),
        (5, 'Thiago Barbosa', 'thiago.barbosa@email.com'),
        (6, 'Amanda Lopes', 'amanda.lopes@email.com'),
        (7, 'Diego Fernandes', 'diego.fernandes@email.com'),
        (8, 'Beatriz Faria', 'beatriz.faria@email.com'),
        (9, 'Vinícius Alves', 'vinicius.alves@email.com'),
        (10, 'Larissa Moura', 'larissa.moura@email.com');

CREATE TABLE IF NOT EXISTS `funcionario` (
                                             `id` int NOT NULL,
                                             `matricula` varchar(5) NOT NULL,
    `departamento` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `matricula` (`matricula`),
    CONSTRAINT `fk_funcionario_pessoa` FOREIGN KEY (`id`) REFERENCES `pessoa` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `funcionario` (`id`, `matricula`, `departamento`) VALUES
        (1, 'FX101', 'Logística'),
        (2, 'FX102', 'TI'),
        (3, 'FX103', 'Jurídico'),
        (4, 'FX104', 'Comercial'),
        (5, 'FX105', 'Marketing'),
        (6, 'FX106', 'Compras'),
        (7, 'FX107', 'RH'),
        (8, 'FX108', 'Financeiro'),
        (9, 'FX109', 'Suporte'),
        (10, 'FX110', 'Projetos');

CREATE TABLE IF NOT EXISTS `projeto` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `nome` varchar(100) NOT NULL,
    `descricao` text,
    `id_funcionario` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_projeto_funcionario` (`id_funcionario`),
    CONSTRAINT `fk_projeto_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `projeto` (`id`, `nome`, `descricao`, `id_funcionario`) VALUES
(1, 'Plataforma EAD', 'Desenvolvimento de plataforma de cursos online', 2),
        2, 'Sistema Jurídico', 'Software para gestão de contratos e prazos', 3),
        (3, 'Reestruturação Comercial', 'Mudança no processo de vendas B2B', 4),
        (4, 'Nova Identidade Visual', 'Projeto de branding e redes sociais', 5),
        (5, 'Controle de Estoque', 'Sistema para logística e inventário', 1),
        (6, 'Portal de Compras', 'Portal interno para solicitações e pedidos', 6),
        (7, 'Integração de Pessoal', 'Automatização do onboarding de novos colaboradores', 7),
        (8, 'Dashboard Financeiro', 'Painel de visualização de gastos e receitas', 8),
        (9, 'Central de Ajuda', 'Base de conhecimento e suporte interno', 9),
        (10, 'Gestão de Projetos', 'Ferramenta para organização de tarefas e entregas', 10);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
