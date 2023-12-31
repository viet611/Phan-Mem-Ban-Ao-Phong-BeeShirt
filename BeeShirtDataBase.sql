USE [master]
GO
/****** Object:  Database [BeeShirt]    Script Date: 09/11/2023 11:59:49 CH ******/
create DATABASE [BeeShirt1]
GO
USE [BeeShirt1]
GO
/****** Object:  Table [dbo].[chat_lieu]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chat_lieu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_chat_lieu] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[chuc_vu]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chuc_vu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_chuc_vu] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hang_thanh_vien]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hang_thanh_vien](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_voucher] [int] NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[so_tien_toi_thieu] [money] NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_hang_thanh_vien] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hinh_anh]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hinh_anh](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[duong_dan] [nvarchar](1000) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_hinh_anh] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_khach_hang] [int] NULL,
	[id_voucher] [int] NULL,
	[id_nhan_vien] [int] NULL,
	[ma] [nvarchar](50) NULL,
	[so_tien_giam] [money] NULL,
	[tong_tien] [money] NULL,
	[tien_ship] [money] NULL,
	[ten_khach_hang] [nvarchar](50) NULL,
	[so_dien_thoai] [nvarchar](20) NULL,
	[dia_chi] [nvarchar](500) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_hoa_don] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don_chi_tiet]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don_chi_tiet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_hoa_don] [int] NULL,
	[id_spct] [int] NULL,
	[so_luong] [int] NULL,
	[thanh_tien] [money] NULL,
	[gia_tien] [money] NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_hoa_don_chi_tiet] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don_timeline]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don_timeline](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_hoa_don] [int] NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_hoa_don_timeline] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_tiet]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_tiet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_hoa_tiet] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[khach_hang]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[khach_hang](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_hang_thanh_vien] [int] NULL,
	ma UNIQUEIDENTIFIER  null,
	[ten] [nvarchar](50) NULL,
	[gioi_tinh] [bit] NULL,
	[ngay_sinh] [date] NULL,
	[sdt] [nvarchar](20) NULL,
	[dia_chi] [nvarchar](500) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_khach_hang] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kich_thuoc]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kich_thuoc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_kich_thuoc] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[kieu_dang]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[kieu_dang](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_kieu_dang] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[mau_sac]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[mau_sac](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_mau_sac] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[mua_phu_hop]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[mua_phu_hop](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_mua_phu_hop] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[muc_dich_su_dung]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[muc_dich_su_dung](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](50) NULL,
	[ma] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_muc_dich_su_dung] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nhan_vien]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nhan_vien](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_chuc_vu] [int] NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[gioi_tinh] [bit] NULL,
	[ngay_sinh] [date] NULL,
	[sdt] [nvarchar](20) NULL,
	[mat_khau] [nvarchar](50) NULL,
	[dia_chi] [nvarchar](500) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
	email [nvarchar](100) null,
	cccd [nvarchar](100) null,
 CONSTRAINT [PK_nhan_vien] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[phuong_thuc_thanh_toan]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[phuong_thuc_thanh_toan](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[mo_ta] [nvarchar](200) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_phuong_thuc_thanh_toan] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[pttt_chi_tiet]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pttt_chi_tiet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_hoa_don] [int] NULL,
	[id_pttt] [int] NULL,
	[so_tien] [money] NULL,
	[trang_thai] [int] NULL,
	[ma_giao_dich] [nvarchar](50) NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_pttt_chi_tiet] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[san_pham]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[san_pham](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_san_pham] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[san_pham_chi_tiet]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[san_pham_chi_tiet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_san_pham] [int] NULL,
	[id_mau_sac] [int] NULL,
	[id_thuong_hieu] [int] NULL,
	[id_kich_thuoc] [int] NULL,
	[id_kieu_dang] [int] NULL,
	[id_mua_phu_hop] [int] NULL,
	[id_mdsd] [int] NULL,
	[id_chat_lieu] [int] NULL,
	[id_hoa_tiet] [int] NULL,
	[ma] [nvarchar](50) NULL,
	[gioi_tinh] [bit] NULL,
	[so_luong_ton] [int] NOT NULL,
	[gia] [money] NULL,
	[mo_ta] [nvarchar](200) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
	[id_hinh_anh] [int] NULL,

 CONSTRAINT [PK_san_pham_chi_tiet] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[thuong_hieu]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[thuong_hieu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_thuong_hieu] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher]    Script Date: 09/11/2023 11:59:49 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [nvarchar](50) NULL,
	[ten] [nvarchar](50) NULL,
	[ngay_bat_dau] [date] NULL,
	[ngay_ket_thuc] [date] NULL,
	[so_tien_giam] [money] NULL,
	[so_tien_toi_thieu] [money] NULL,
	[trang_thai] [int] NULL,
	[ngay_tao] [date] NULL,
	[ngay_sua] [date] NULL,
	[tao_boi] [nvarchar](50) NULL,
	[sua_boi] [nvarchar](50) NULL,
	[da_xoa] [bit] NULL,
 CONSTRAINT [PK_voucher] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[chat_lieu] ADD  CONSTRAINT [DF_chat_lieu_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[chat_lieu] ADD  CONSTRAINT [DF_chat_lieu_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[chuc_vu] ADD  CONSTRAINT [DF_chuc_vu_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[chuc_vu] ADD  CONSTRAINT [DF_chuc_vu_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hang_thanh_vien] ADD  CONSTRAINT [DF_hang_thanh_vien_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hang_thanh_vien] ADD  CONSTRAINT [DF_hang_thanh_vien_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hinh_anh] ADD  CONSTRAINT [DF_hinh_anh_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hinh_anh] ADD  CONSTRAINT [DF_hinh_anh_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hoa_don] ADD  CONSTRAINT [DF_hoa_don_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hoa_don] ADD  CONSTRAINT [DF_hoa_don_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  CONSTRAINT [DF_hoa_don_chi_tiet_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] ADD  CONSTRAINT [DF_hoa_don_chi_tiet_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hoa_don_timeline] ADD  CONSTRAINT [DF_hoa_don_timeline_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hoa_don_timeline] ADD  CONSTRAINT [DF_hoa_don_timeline_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hoa_tiet] ADD  CONSTRAINT [DF_hoa_tiet_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[hoa_tiet] ADD  CONSTRAINT [DF_hoa_tiet_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[khach_hang] ADD  CONSTRAINT [DF_khach_hang_ngay_sinh]  DEFAULT (getdate()) FOR [ngay_sinh]
GO
ALTER TABLE [dbo].[khach_hang] ADD  CONSTRAINT [DF_khach_hang_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[khach_hang] ADD  CONSTRAINT [DF_khach_hang_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[kich_thuoc] ADD  CONSTRAINT [DF_kich_thuoc_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[kich_thuoc] ADD  CONSTRAINT [DF_kich_thuoc_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[kieu_dang] ADD  CONSTRAINT [DF_kieu_dang_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[kieu_dang] ADD  CONSTRAINT [DF_kieu_dang_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[mau_sac] ADD  CONSTRAINT [DF_mau_sac_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[mau_sac] ADD  CONSTRAINT [DF_mau_sac_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[mua_phu_hop] ADD  CONSTRAINT [DF_mua_phu_hop_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[mua_phu_hop] ADD  CONSTRAINT [DF_mua_phu_hop_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[muc_dich_su_dung] ADD  CONSTRAINT [DF_muc_dich_su_dung_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[muc_dich_su_dung] ADD  CONSTRAINT [DF_muc_dich_su_dung_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[nhan_vien] ADD  CONSTRAINT [DF_nhan_vien_ngay_sinh]  DEFAULT (getdate()) FOR [ngay_sinh]
GO
ALTER TABLE [dbo].[nhan_vien] ADD  CONSTRAINT [DF_nhan_vien_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[nhan_vien] ADD  CONSTRAINT [DF_nhan_vien_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[nhan_vien] ADD  CONSTRAINT [DF_nhan_vien_tao_boi]  DEFAULT (getdate()) FOR [tao_boi]
GO
ALTER TABLE [dbo].[nhan_vien] ADD  CONSTRAINT [DF_nhan_vien_sua_boi]  DEFAULT (getdate()) FOR [sua_boi]
GO
ALTER TABLE [dbo].[phuong_thuc_thanh_toan] ADD  CONSTRAINT [DF_phuong_thuc_thanh_toan_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[phuong_thuc_thanh_toan] ADD  CONSTRAINT [DF_phuong_thuc_thanh_toan_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[pttt_chi_tiet] ADD  CONSTRAINT [DF_pttt_chi_tiet_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[pttt_chi_tiet] ADD  CONSTRAINT [DF_pttt_chi_tiet_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[san_pham] ADD  CONSTRAINT [DF_san_pham_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[san_pham] ADD  CONSTRAINT [DF_san_pham_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  CONSTRAINT [DF_san_pham_chi_tiet_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] ADD  CONSTRAINT [DF_san_pham_chi_tiet_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[thuong_hieu] ADD  CONSTRAINT [DF_thuong_hieu_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[thuong_hieu] ADD  CONSTRAINT [DF_thuong_hieu_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[voucher] ADD  CONSTRAINT [DF_voucher_ngay_bat_dau]  DEFAULT (getdate()) FOR [ngay_bat_dau]
GO
ALTER TABLE [dbo].[voucher] ADD  CONSTRAINT [DF_voucher_ngay_ket_thuc]  DEFAULT (getdate()) FOR [ngay_ket_thuc]
GO
ALTER TABLE [dbo].[voucher] ADD  CONSTRAINT [DF_voucher_ngay_tao]  DEFAULT (getdate()) FOR [ngay_tao]
GO
ALTER TABLE [dbo].[voucher] ADD  CONSTRAINT [DF_voucher_ngay_sua]  DEFAULT (getdate()) FOR [ngay_sua]
GO
ALTER TABLE [dbo].[hang_thanh_vien]  WITH CHECK ADD  CONSTRAINT [FK_hang_thanh_vien_voucher] FOREIGN KEY([id_voucher])
REFERENCES [dbo].[voucher] ([id])
GO
ALTER TABLE [dbo].[hang_thanh_vien] CHECK CONSTRAINT [FK_hang_thanh_vien_voucher]
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_khach_hang] FOREIGN KEY([id_khach_hang])
REFERENCES [dbo].[khach_hang] ([id])
GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don_khach_hang]
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_nhan_vien] FOREIGN KEY([id_nhan_vien])
REFERENCES [dbo].[nhan_vien] ([id])
GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don_nhan_vien]
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_voucher] FOREIGN KEY([id_voucher])
REFERENCES [dbo].[voucher] ([id])
GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FK_hoa_don_voucher]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_chi_tiet_hoa_don] FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] CHECK CONSTRAINT [FK_hoa_don_chi_tiet_hoa_don]
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_chi_tiet_san_pham_chi_tiet] FOREIGN KEY([id_spct])
REFERENCES [dbo].[san_pham_chi_tiet] ([id])
GO
ALTER TABLE [dbo].[hoa_don_chi_tiet] CHECK CONSTRAINT [FK_hoa_don_chi_tiet_san_pham_chi_tiet]
GO
ALTER TABLE [dbo].[hoa_don_timeline]  WITH CHECK ADD  CONSTRAINT [FK_hoa_don_timeline_hoa_don] FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[hoa_don_timeline] CHECK CONSTRAINT [FK_hoa_don_timeline_hoa_don]
GO
ALTER TABLE [dbo].[khach_hang]  WITH CHECK ADD  CONSTRAINT [FK_khach_hang_hang_thanh_vien] FOREIGN KEY([id_hang_thanh_vien])
REFERENCES [dbo].[hang_thanh_vien] ([id])
GO
ALTER TABLE [dbo].[khach_hang] CHECK CONSTRAINT [FK_khach_hang_hang_thanh_vien]
GO
ALTER TABLE [dbo].[nhan_vien]  WITH CHECK ADD  CONSTRAINT [FK_nhan_vien_chuc_vu] FOREIGN KEY([id_chuc_vu])
REFERENCES [dbo].[chuc_vu] ([id])
GO
ALTER TABLE [dbo].[nhan_vien] CHECK CONSTRAINT [FK_nhan_vien_chuc_vu]
GO
ALTER TABLE [dbo].[pttt_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_pttt_chi_tiet_hoa_don] FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[pttt_chi_tiet] CHECK CONSTRAINT [FK_pttt_chi_tiet_hoa_don]
GO
ALTER TABLE [dbo].[pttt_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_pttt_chi_tiet_phuong_thuc_thanh_toan] FOREIGN KEY([id_pttt])
REFERENCES [dbo].[phuong_thuc_thanh_toan] ([id])
GO
ALTER TABLE [dbo].[pttt_chi_tiet] CHECK CONSTRAINT [FK_pttt_chi_tiet_phuong_thuc_thanh_toan]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_chat_lieu] FOREIGN KEY([id_chat_lieu])
REFERENCES [dbo].[chat_lieu] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_chat_lieu]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_hinh_anh] FOREIGN KEY([id_hinh_anh])
REFERENCES [dbo].[hinh_anh] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_hinh_anh]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_hoa_tiet] FOREIGN KEY([id_hoa_tiet])
REFERENCES [dbo].[hoa_tiet] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_hoa_tiet]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_kich_thuoc] FOREIGN KEY([id_kich_thuoc])
REFERENCES [dbo].[kich_thuoc] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_kich_thuoc]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_kieu_dang] FOREIGN KEY([id_kieu_dang])
REFERENCES [dbo].[kieu_dang] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_kieu_dang]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_mau_sac] FOREIGN KEY([id_mau_sac])
REFERENCES [dbo].[mau_sac] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_mau_sac]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_mua_phu_hop] FOREIGN KEY([id_mua_phu_hop])
REFERENCES [dbo].[mua_phu_hop] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_mua_phu_hop]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_muc_dich_su_dung] FOREIGN KEY([id_mdsd])
REFERENCES [dbo].[muc_dich_su_dung] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_muc_dich_su_dung]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_san_pham] FOREIGN KEY([id_san_pham])
REFERENCES [dbo].[san_pham] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_san_pham]
GO
ALTER TABLE [dbo].[san_pham_chi_tiet]  WITH CHECK ADD  CONSTRAINT [FK_san_pham_chi_tiet_thuong_hieu] FOREIGN KEY([id_thuong_hieu])
REFERENCES [dbo].[thuong_hieu] ([id])
GO
ALTER TABLE [dbo].[san_pham_chi_tiet] CHECK CONSTRAINT [FK_san_pham_chi_tiet_thuong_hieu]
GO
USE [master]
GO
ALTER DATABASE [BeeShirt1] SET  READ_WRITE 
GO
USE [BeeShirt1]
go

ALTER TABLE [dbo].[khach_hang] ADD  DEFAULT (newid()) FOR [ma]
go
ALTER TABLE san_pham_chi_tiet
ADD  qr_code nvarchar(200) NULL;
go 
insert into phuong_thuc_thanh_toan values ('PTTT1','Tiền mặt','ko',1,'2023-11-27','2023-11-27',NULL,NULL,0),('pTTT2','Chuyển khoản','ko',1,'2023-11-29','2023-11-29',NULL,NULL,0)
/*ALTER TABLE [dbo].[nhan_vien] ADD  DEFAULT (newid()) FOR [mat_khau]
go*/
/*select * from khach_hang*/
/*select * from nhan_vien*/
/*select * from chuc_vu*/
/*select * from nhan_vien
select * from hoa_don
DELETE FROM nhan_vien*/