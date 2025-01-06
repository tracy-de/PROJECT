DROP DATABASE project;
CREATE DATABASE project CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use project;

CREATE TABLE IF NOT EXISTS admin (
    admi_mail VARCHAR(100) PRIMARY KEY,
    admi_pwd VARCHAR(100), 
    company_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO admin (admi_mail, admi_pwd)
VALUES
('admin1@example.com', 'adminpassword123'),
('admin2@example.com', 'adminpassword456');

create table if not exists event (
event_no int auto_increment primary key,
event_date date,
event_name varchar(50),
event_info varchar(100),
created_at timestamp default CURRENT_TIMESTAMP,
admin_mail VARCHAR(100),
FOREIGN KEY (admin_mail) REFERENCES admin(admi_mail)
ON UPDATE CASCADE
ON DELETE CASCADE
)engine=innodb default charset=utf8mb4;

insert into event (event_date,event_name,event_info,admin_mail) 
values
('2024-12-01', '甜點嘉年華', '每年一度的甜點嘉年華，邀請多家甜點店參加，提供各式甜品', 'admin1@example.com'),

('2024-12-02', '異國甜點夜', '舉辦異國甜點之夜，品嚐來自世界各地的經典甜品', 'admin2@example.com'),

('2024-12-03', '素食甜點節', '探索創新的素食甜點料理，為素食者提供多樣的甜品選擇', 'admin1@example.com'),

('2024-12-04', '甜點大賞', '一場專注於甜點的活動，帶來各式手工甜品的展示與品嚐', 'admin1@example.com'),

('2024-12-05', '巧克力品鑑會', '來自全球的優質巧克力，讓您品味不同的巧克力風味', 'admin1@example.com'),

('2024-12-06', '街頭甜點節', '展示來自世界各地的街頭甜點，讓您大快朵頤', 'admin1@example.com'),

('2024-12-07', '海洋風味甜點盛宴', '融合新鮮海洋風味的甜點，帶來獨特的海鮮甜品體驗', 'admin2@example.com'),

('2024-12-08', '亞洲甜點探索', '一起品味來自亞洲各地的特色甜點，探索多元的風味', 'admin1@example.com'),

('2024-12-09', '鄉村手工甜點市集', '來自當地農場的新鮮食材，製作的手工甜點，現場販售與試吃', 'admin1@example.com'),

('2024-12-10', '巧克力與甜點晚宴', '品嚐最美味的巧克力與甜點搭配，享受浪漫的晚宴氛圍', 'admin2@example.com');



create table if not exists client (
client_mail varchar(100) primary key,
client_pwd varchar(100), 
client_name varchar(50),
client_gender enum('F','M'),
client_tel varchar(20),
client_addr varchar(100),
client_credit_card varchar(30),
client_create timestamp default current_timestamp
)engine=innodb default charset=utf8mb4;

INSERT INTO client (client_mail,client_pwd,client_name,client_gender,client_tel,client_addr,client_credit_card) 

VALUES

('alice01@example.com', 'password123', '王小美', 'F', '0912345678', '台北市中正區忠孝東路100號','1234-1234-1234-1234'),

('bob02@example.com', 'mypassword456', '陳大明', 'M', '0922345678', '新北市板橋區文化路200號','1234-1234-1234-1234'),

('charlie03@example.com', 'secure789', '林志強', 'M', '0932345678', '台中市西屯區台灣大道三段300號','1234-1234-1234-1234'),

('diana04@example.com', 'password000', '李麗華', 'F', '0942345678', '台南市東區成功路400號','1234-1234-1234-1234'),

('edward05@example.com', 'pass123abc', '吳建國', 'M', '0952345678', '高雄市左營區自由路500號','1234-1234-1234-1234'),

('fiona06@example.com', 'fiona789xyz', '張美芳', 'F', '0962345678', '新竹市東區光復路600號','1234-1234-1234-1234'),

('george07@example.com', 'george123', '蔡英文', 'M', '0972345678', '基隆市中正區海濱路700號','1234-1234-1234-1234'),

('hannah08@example.com', 'hannah456', '劉玉珍', 'F', '0982345678', '宜蘭縣羅東鎮中山路800號','1234-1234-1234-1234'),

('ian09@example.com', 'ian000abc', '黃建民', 'M', '0992345678', '花蓮縣花蓮市國聯一路900號','1234-1234-1234-1234'),

('jenny10@example.com', 'jenny111', '何雅婷', 'F', '0911345678', '台東縣台東市更生路1000號','1234-1234-1234-1234'),

('kevin11@example.com', 'kevin999', '周榮輝', 'M', '0921345678', '苗栗縣苗栗市中正路1100號','1234-1234-1234-1234'),

('linda12@example.com', 'lindaabc', '方惠如', 'F', '0931345678', '嘉義市西區文化路1200號','1234-1234-1234-1234'),

('mike13@example.com', 'mikepass', '蘇振興', 'M', '0941345678', '澎湖縣馬公市中山路1300號','1234-1234-1234-1234'),

('nina14@example.com', 'ninapass', '程麗君', 'F', '0951345678', '金門縣金城鎮民生路1400號','1234-1234-1234-1234'),

('oscar15@example.com', 'oscar999', '呂國強', 'M', '0961345678', '連江縣南竿鄉仁愛路1500號','1234-1234-1234-1234');


create table if not exists company (
company_mail varchar(100) primary key,
company_pwd varchar(100), 
company_name varchar(50),
company_id VARCHAR(10),
company_tel varchar(20),
company_addr varchar(100),
company_type varchar(10),
company_create timestamp default current_timestamp
)engine=innodb default charset=utf8mb4;

INSERT INTO company (company_mail, company_pwd, company_name, company_id, company_tel, company_addr, company_type)
VALUES
('company1@example.com', 'dessert123', '甜點店 Sweet Delights', '01234568', '0911345671', '台東縣台東市幸福路123號', '甜點'),
('company2@example.com', 'dessert234', '甜點屋 Dessert House', '01234569', '0911345672', '台東縣台東市明亮路234號', '甜點'),
('company3@example.com', 'sweet123', '甜點坊 Sweet Spot', '01234570', '0911345673', '台東縣台東市和平路345號', '甜點'),
('company4@example.com', 'sweets456', '甜蜜之家 Sweet Home', '01234571', '0911345674', '台東縣台東市自由路456號', '甜點'),
('company5@example.com', 'dessert789', '巧克力工房 Chocolate Factory', '01234572', '0911345675', '台東縣台東市藝術路567號', '甜點');


create table if not exists product (
product_no INT primary key,
product_name varchar(100),
product_price FLOAT,
seller_mail varchar(100), 
product_status varchar(10),
product_photo VARCHAR(50),
sale_create timestamp default CURRENT_TIMESTAMP,
FOREIGN KEY (seller_mail) REFERENCES company(company_mail)
ON UPDATE CASCADE 
ON DELETE cascade
)engine=innodb default charset=UTF8MB4;
INSERT INTO product (product_no, product_name, seller_mail, product_price, product_status,product_photo)
VALUES
(1, '甜點嘉年華餐券', 'company1@example.com', 500.00, '上架中','coupon1.png'),
(2, '異國甜點夜餐券', 'company2@example.com', 600.00, '上架中','coupon3.png'),
(3, '素食甜點節餐券', 'company3@example.com', 450.00, '已售完','coupon4.png'),
(4, '甜點大賞餐券', 'company4@example.com', 350.00, '上架中','coupon5.png'),
(5, '巧克力品鑑會餐券', 'company5@example.com', 550.00, '上架中','coupon6.png'),
(6, '街頭甜點節餐券', 'company1@example.com', 400.00, '已售完','coupon7.png'),
(7, '海洋風味甜點盛宴餐券', 'company2@example.com', 700.00, '上架中','coupon8.png'),
(8, '亞洲甜點探索餐券', 'company3@example.com', 650.00, '上架中','coupon9.png'),
(9, '鄉村手工甜點市集餐券', 'company4@example.com', 300.00, '上架中','coupon10.png'),
(10, '巧克力與甜點晚宴餐券', 'company5@example.com', 800.00, '已售完','coupon11.png');



create table if not exists trade (
trade_no int auto_increment primary KEY,
company_mail varchar(100),
client_mail varchar(100), 
trade_status varchar(20),
trade_create timestamp default CURRENT_TIMESTAMP,
FOREIGN KEY (company_mail) REFERENCES company(company_mail)
ON UPDATE CASCADE 
ON DELETE CASCADE,
FOREIGN KEY (client_mail) REFERENCES client(client_mail)
ON UPDATE CASCADE 
ON DELETE CASCADE
)engine=innodb default charset=UTF8MB4;

create table if not exists trade_detail (
detail_no int auto_increment primary KEY,
product_no INT,
trade_qua INT, 
company_mail varchar(100),
client_mail varchar(100), 
trade_no INT,
trade_create timestamp default CURRENT_TIMESTAMP,
FOREIGN KEY (company_mail) REFERENCES company(company_mail)
ON UPDATE CASCADE 
ON DELETE CASCADE,
FOREIGN KEY (client_mail) REFERENCES client(client_mail)
ON UPDATE CASCADE 
ON DELETE CASCADE,
FOREIGN KEY (product_no) REFERENCES product(product_no)
ON UPDATE CASCADE 
ON DELETE CASCADE,
FOREIGN KEY (trade_no) REFERENCES trade(trade_no)
ON UPDATE CASCADE 
ON DELETE cascade
)engine=innodb default charset=UTF8MB4;


create table if not EXISTS  message (
msg_no INT auto_increment primary key,
msg_name VARCHAR(20),
msg_mail VARCHAR(100),
msg_text VARCHAR(300), 
msg_create timestamp default current_timestamp
)engine=innodb default charset=UTF8MB4;