-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 12, 2019 lúc 09:34 AM
-- Phiên bản máy phục vụ: 10.1.38-MariaDB
-- Phiên bản PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `appbanhang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `Id` int(11) NOT NULL,
  `Name` varchar(200) NOT NULL,
  `Image` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`Id`, `Name`, `Image`) VALUES
(1, 'Áo sơ mi nam', 'https://img.ltwebstatic.com/images2_pi/2019/06/10/1560159638758743579_thumbnail_600x799.webp'),
(2, 'Quần nam', 'https://img.ltwebstatic.com/images3_pi/2019/10/23/1571809282550b995bfd28814c371ce3c8e77044e5_thumbnail_405x552.webp'),
(3, 'Giày nam', 'https://img.ltwebstatic.com/images3_pi/2019/10/23/1571820151170e0a10415c189647cfe343b795cb36_thumbnail_600x.webp');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `Id` int(11) NOT NULL,
  `Name` varchar(200) DEFAULT NULL,
  `Price` int(15) DEFAULT NULL,
  `Discount` int(15) DEFAULT '0',
  `Image` varchar(200) DEFAULT NULL,
  `Desciption` varchar(1000) DEFAULT NULL,
  `Id_Category` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`Id`, `Name`, `Price`, `Discount`, `Image`, `Desciption`, `Id_Category`) VALUES
(1, 'Men Shirts Hình Học Màu Xanh Lam Casual', 400000, 0, 'https://img.ltwebstatic.com/images3_pi/2019/09/24/1569306363dbff5236ffe72e7a50def8678c9c836a_thumbnail_600x.webp', 'Phong cách:	Giải trí\r\nMàu sắc :	Màu xanh lam\r\nKiểu mẫu:	Hình học\r\nVòng cổ:	Vòng cổ\r\nChiều dài:	Thường xuyên\r\nLoại áo sơ mi:	Áo sơ mi\r\nChiều dài tay:	Tay áo dài\r\nLoại tay áo:	Tay thường\r\nMùa:	Mùa Xuân/ Mùa Thu', 1),
(2, 'SHEIN Men Shirts Nút Sọc Xám Giải Trí', 30000, 0, 'https://img.ltwebstatic.com/images3_pi/2019/11/26/15747700401991fa1acf96890716162d8a63fc7df0_thumbnail_600x.webp', 'Màu sắc :	Xám\r\nKiểu mẫu:	Sọc\r\nVòng cổ:	Đứng cổ áo\r\nChiều dài:	Thường xuyên\r\nLoại áo sơ mi:	Áo sơ mi', 1),
(3, 'SHEIN Men Shirts Sọc Bên Kẻ Sọc Màu Vàng Phong Cách Campus', 500000, 10, 'https://img.ltwebstatic.com/images2_pi/2019/08/19/1566209106206349541_thumbnail_600x799.webp', 'Phong cách:	Sẵn sàng\r\nMàu sắc :	Màu vàng, Bright\r\nKiểu mẫu:	kẻ sọc\r\nVòng cổ:	Vòng cổ\r\nChiều dài:	Thường xuyên\r\nLoại áo sơ mi:	Áo sơ mi\r\n', 1),
(4, 'SHEIN Men Shirts Nút Phía Trước Màu Trơn Burgundy Giải Trí', 300000, 0, 'https://img.ltwebstatic.com/images3_pi/2019/10/17/15713043973c743fbd7fa792bf8773c1517253ffc7_thumbnail_600x.webp', 'Phong cách:	Giải trí\r\nMàu sắc :	Burgundy\r\nKiểu mẫu:	màu trơn\r\nVòng cổ:	Vòng cổ\r\nChiều dài:	Thường xuyên', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_Category` (`Id_Category`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`Id_Category`) REFERENCES `category` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
