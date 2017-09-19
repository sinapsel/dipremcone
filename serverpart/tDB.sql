-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Сен 19 2017 г., 06:16
-- Версия сервера: 10.1.21-MariaDB
-- Версия PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `RCuDB`
--

-- --------------------------------------------------------

--
-- Структура таблицы `tDB`
--

CREATE TABLE `tDB` (
  `id` int(11) NOT NULL,
  `UID` varchar(16) NOT NULL,
  `PCUser` varchar(256) NOT NULL,
  `IP` varchar(16) NOT NULL,
  `FIRSTDATE` int(10) NOT NULL COMMENT 'UNIXTIMESTAMP',
  `LASTUPSIGNAL` int(10) NOT NULL COMMENT 'UNIXTIMESTAMP',
  `CMD` varchar(512) NOT NULL,
  `CMDRes` varchar(2048) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tDB`
--

INSERT INTO `tDB` (`id`, `UID`, `PCUser`, `IP`, `FIRSTDATE`, `LASTUPSIGNAL`, `CMD`, `CMDRes`) VALUES
(1, '08:3e:8e:a8:90:e', 'alin', '', 0, 0, '', ''),
(9, 'FHNLYSEPEZSGCRDZ', 'dementor', '127.0.0.1', 1502921389, 1505791976, '', '');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `tDB`
--
ALTER TABLE `tDB`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `tDB`
--
ALTER TABLE `tDB`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
