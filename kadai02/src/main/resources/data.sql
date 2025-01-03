<<<<<<< HEAD
-- Categoriesテーブルにデータを挿入
INSERT INTO categories (id, category_name) VALUES (1, '和食')
ON DUPLICATE KEY UPDATE category_name = '和食';

INSERT INTO categories (id, category_name) VALUES (2, '洋食')
ON DUPLICATE KEY UPDATE category_name = '洋食';

INSERT INTO categories (id, category_name) VALUES (3, '中華')
ON DUPLICATE KEY UPDATE category_name = '中華';

INSERT INTO categories (id, category_name) VALUES (4, 'カフェ')
ON DUPLICATE KEY UPDATE category_name = 'カフェ';

INSERT INTO categories (id, category_name) VALUES (5, 'その他')
ON DUPLICATE KEY UPDATE category_name = 'その他';

-- 外部キー制約を無効化
SET FOREIGN_KEY_CHECKS = 0;

-- テーブルを初期化
TRUNCATE TABLE reservations;
TRUNCATE TABLE shop;

-- 外部キー制約を再度有効化
SET FOREIGN_KEY_CHECKS = 1;

-- shop テーブルにデータを挿入

INSERT INTO shop (
   category_id, shop_name, image_name, description, price_upper, price_lower,
   hours_open, hours_close, closed_day, postal_code, address, phone_number,
   retry_count, created_at, updated_at
) VALUES
(1, 'SAMURAIの宿', 'house01.jpg', '自然豊かで閑静な場所にあります。ゆったりとお食事できます。', 3000, 6000, '11:00:00', '21:00:00', 'WEDNESDAY', '073-0145', '北海道砂川市西五条南X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(1, '居酒屋 SAMURAI', 'house02.jpg', '数多いメニューでファミリーでも楽しめます。', 2000, 6000, '17:00:00', '23:00:00', 'MONDAY', '030-0945', '青森県青森市桜川X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(1, 'SAMURAI屋', 'house03.jpg', '土佐料理店。地元料理が楽しめます。', 3000, 8000, '11:00:00', '22:00:00', 'TUESDAY', '029-5618', '岩手県和賀郡西和賀町沢内両沢X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(1, 'SAMURAI座衛門', 'house04.jpg', '肉飯の店。ローストビーフ丼が絶品。', 1000, 3000, '11:00:00', '21:00:00', 'TUESDAY', '989-0555', '宮城県刈田郡七ヶ宿町滝ノ上X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(4, 'SAMURAIcafe', 'house05.jpg', '自然豊かな場所でゆったりとした時間を過ごせます。', 1000, 3000, '08:00:00', '20:00:00', 'WEDNESDAY', '018-2661', '秋田県山本郡八峰町八森浜田X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'レストラン SAMURAI', 'house06.jpg', 'カジュアルレストラン。ファミリーに最適。', 3000, 8000, '10:00:00', '23:00:00', 'TUESDAY', '999-6708', '山形県酒田市泉興野X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(3, 'SAMURAI天津', 'house07.jpg', '自然豊かな場所で本場の中国料理を提供。', 5000, 10000, '12:00:00', '22:00:00', 'MONDAY', '969-5147', '福島県会津若松市大戸町芦牧X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(1, 'SAMURAIラーメン', 'house08.jpg', '隠れ家的なラーメン店。豚骨ラーメンが人気。', 1000, 3000, '11:00:00', '00:00:00', 'SUNDAY', '310-0021', '茨城県水戸市南町X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(1, 'SAMURAI鳥や', 'house09.jpg', '地鶏を提供。水炊きがおすすめ。', 3000, 10000, '18:00:00', '23:00:00', 'WEDNESDAY', '323-1101', '栃木県下都賀郡藤岡町大前X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(1, 'シーサイドSAMURAI', 'house10.jpg', '魚介料理を中心に提供するお店。', 5000, 10000, '12:00:00', '21:00:00', 'MONDAY', '370-0806', '群馬県高崎市上和田町X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'イタ飯SAMURAI', 'house11.jpg', '本格イタリアン料理のお店。', 6000, 20000, '11:00:00', '21:00:00', 'THURSDAY', '344-0125', '埼玉県春日部市飯沼X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'ヴィラSAMURAI', 'house12.jpg', '閑静な場所でゆったり過ごせます。', 3000, 10000, '11:00:00', '22:00:00', 'WEDNESDAY', '272-0011', '千葉県市川市高谷新町X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'SAMURAIパレス', 'house13.jpg', 'パーティーやウエディング向け。', 8000, 20000, '10:00:00', '20:00:00', 'WEDNESDAY', '130-0023', '東京都墨田区立川X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'ロッジ SAMURAI', 'house14.jpg', '自然豊かな場所でゆったり食事。', 3000, 15000, '11:00:00', '21:00:00', 'SUNDAY', '240-0006', '神奈川県横浜市保土ヶ谷区星川X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'SAMURAI館', 'house15.jpg', 'スペイン料理とワインが自慢。', 5000, 10000, '12:00:00', '22:00:00', 'THURSDAY', '950-0201', '新潟県新潟市駒込X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'SAMURAI山荘', 'house16.jpg', '山小屋風の建物でゆったり過ごせます。', 3000, 6000, '08:00:00', '19:00:00', 'WEDNESDAY', '939-8155', '富山県富山市江本X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(1, 'SAMURAIの家', 'house17.jpg', '最寄り駅から徒歩10分。おもわずただいまと言いたくなるような家庭的な雰囲気の店。一品料理が数多く並んでいます。', 3000, 8000, '18:00:00', '01:00:00', 'MONDAY', '929-0111', '石川県能美市吉原町X-XX-XX', '012-345-678', 0, NOW(), NOW()),
(2, 'SAMURAIPOR', 'house18.jpg', 'ポルトガル料理の店。家庭料理が楽しめます。', 8000, 15000, '17:00:00', '23:00:00', 'MONDAY', '910-2354', '福井県福井市東天田町X-XX-XX', '012-345-678', 0, NOW(), NOW());


-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');



-- Usersテーブルへのデータ挿入
-- 管理者（admin）ID: 1
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, customer_id, role_id, enabled) 
VALUES 
(1, 'admin', 'アドミン', '673-5555', '兵庫県姫路市XXXX-XXXX-XXX', '090-1234-5678', 'admin@example.com', '$2a$10$2MOPdwbKhhgDP94vgxwmOuck2tRLsK9cifw10z3djog2EgofbE0IW', 'CUST001', 2, true);

-- 一般ユーザー（ROLE_GENERAL）ID: 2-13
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, customer_id, role_id, enabled) 
VALUES 
(2, '侍 太郎', 'サムライ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 'CUST002', 1, true),
(3, '侍 花子', 'サムライ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 'CUST003', 1, true),
(4, '侍 義勝', 'サムライ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.samurai@example.com', '$2a$10$abc123...', 'CUST004', 1, true),
(5, '侍 幸美', 'サムライ サチミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachimi.samurai@example.com', '$2a$10$abc123...', 'CUST005', 1, true),
(6, '侍 雅', 'サムライ ミヤビ', '527-0209', '滋賀県東近江市佐目町X-XX-XX', '090-1234-5678', 'miyabi.samurai@example.com', '$2a$10$abc123...', 'CUST006', 1, true),
(7, '侍 正保', 'サムライ マサヤス', '989-1203', '宮城県柴田郡大河原町旭町X-XX-XX', '090-1234-5678', 'masayasu.samurai@example.com', '$2a$10$abc123...', 'CUST007', 1, true),
(8, '侍 真由美', 'サムライ マユミ', '951-8015', '新潟県新潟市松岡町X-XX-XX', '090-1234-5678', 'mayumi.samurai@example.com', '$2a$10$abc123...', 'CUST008', 1, true),
(9, '侍 安民', 'サムライ ヤスタミ', '241-0033', '神奈川県横浜市旭区今川町X-XX-XX', '090-1234-5678', 'yasutami.samurai@example.com', '$2a$10$abc123...', 'CUST009', 1, true),
(10, '侍 章緒', 'サムライ アキオ', '739-2103', '広島県東広島市高屋町宮領X-XX-XX', '090-1234-5678', 'akio.samurai@example.com', '$2a$10$abc123...', 'CUST010', 1, true),
(11, '侍 祐子', 'サムライ ユウコ', '601-0761', '京都府南丹市美山町高野X-XX-XX', '090-1234-5678', 'yuko.samurai@example.com', '$2a$10$abc123...', 'CUST011', 1, true),
(12, '侍 秋美', 'サムライ アキミ', '606-8235', '京都府京都市左京区田中西春菜町X-XX-XX', '090-1234-5678', 'akimi.samurai@example.com', '$2a$10$abc123...', 'CUST012', 1, true),
(13, '侍 信平', 'サムライ シンペイ', '673-1324', '兵庫県加東市新定X-XX-XX', '090-1234-5678', 'shinpei.samurai@example.com', '$2a$10$abc123...', 'CUST013', 1, true);

-- 課金ユーザー（ROLE_PREMIUM_USER）ID: 14, 15
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, customer_id, role_id, enabled) 
VALUES 
(14, '侍 プレミアム', 'サムライ プレミアム', '673-5555', '兵庫県姫路市XXXX-XXXX-XXX', '090-1234-5678', 'premium.samurai@example.com', '$2a$10$foE.9LGl.I8dl6MXFilCNuWwE8.NKQganl.TbjFt3RFWCPPtaW9qy', 'CUST014', 3, true),
(15, '侍 裕美', 'サムライ ユミ', '673-5555', '兵庫県姫路市XXXX-XXXX-XXX', '090-1234-5678', 'yumi.samurai@example.com', '$2a$10$iaxAHjst40rl3LcoYBRlNeDoYmshQAS28lcxY4hEhZHOuPN55d92G', 'CUST015', 3, true);


-- user_roles テーブルデータ
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES
(1, 2), -- 管理者に ROLE_ADMIN を割り当て
(2, 1), -- 一般ユーザー (侍 太郎) に ROLE_GENERAL を割り当て
(3, 1), -- 一般ユーザー (侍 花子) に ROLE_GENERAL を割り当て
(4, 1), -- 一般ユーザー (侍 義勝) に ROLE_GENERAL を割り当て
(5, 1), -- 一般ユーザー (侍 幸美) に ROLE_GENERAL を割り当て
(6, 1), -- 一般ユーザー (侍 雅) に ROLE_GENERAL を割り当て
(7, 1), -- 一般ユーザー (侍 正保) に ROLE_GENERAL を割り当て
(8, 1), -- 一般ユーザー (侍 真由美) に ROLE_GENERAL を割り当て
(9, 1), -- 一般ユーザー (侍 安民) に ROLE_GENERAL を割り当て
(10, 1), -- 一般ユーザー (侍 章緒) に ROLE_GENERAL を割り当て
(11, 1), -- 一般ユーザー (侍 祐子) に ROLE_GENERAL を割り当て
(12, 1), -- 一般ユーザー (侍 秋美) に ROLE_GENERAL を割り当て
(13, 1), -- 一般ユーザー (侍 信平) に ROLE_GENERAL を割り当て
(14, 3), -- 課金ユーザー (侍 プレミアム) に ROLE_PREMIUM_USER を割り当て
(15, 3); -- 課金ユーザー (侍 裕美) に ROLE_PREMIUM_USER を割り当て


-- reservationsテーブル
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (1, 1, 1, '2023-04-01', '18:00:00', 2);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (2, 2, 1, '2023-04-01', '19:00:00', 3);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (3, 3, 1, '2023-04-01', '20:30:00', 4);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (4, 4, 1, '2023-04-01', '19:00:00', 5);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (5, 5, 1, '2023-04-01', '09:30:00', 6);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (6, 6, 1, '2023-04-01', '10:00:00', 2);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (7, 7, 1, '2023-04-01', '14:00:00', 3);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (8, 8, 1, '2023-04-01', '23:00:00', 4);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (9, 9, 1, '2023-04-01', '21:00:00', 5);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (10, 10, 1, '2023-04-01', '13:00:00', 6);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people) VALUES  (11, 11, 1, '2023-04-01', '15:00:00', 2);



--reviewテーブル
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (1, 1, 1, 0, 'きれいで快適に過ごせました。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (2, 2, 1, 1, 'アクセスがよく、観光に最適でした。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (3, 3, 1, 2, 'スタッフが親切で気持ちよく滞在できました。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (4, 4, 1, 3, '駅から近く便利でした。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (5, 5, 1, 4, '周辺の店が充実していて、楽しめました。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (6, 6, 1, 5, '手ごろで、コスパがよかったです。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (7, 7, 1, 1, '静かな環境でゆっくり休めました。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (8, 8, 1, 2, '施設内の設備が充実していｔました。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (9, 9, 1, 3, 'Wi-Fiが快適で助かりました。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (10, 10, 1, 4, '清潔感があり気持ちよく滞在できました。', '2023-04-01', '2023-04-02');
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at,  updated_at) VALUES (11, 11, 1, 3, '', '2023-04-01', '2023-04-02');

-- company テーブルにデータを挿入
INSERT IGNORE INTO company (
    id, 
    company_name, 
    website_url, 
    contact_email, 
    phone_number, 
    address, 
    services, 
    portfolio_url, 
    established_year, 
    technology_stack, 
    description, 
    created_at, 
    updated_at
) 
VALUES (
    1, 
    'Awesome App Studio', 
    'https://awesomeappstudio.com', 
    'info@awesomeappstudio.com', 
    '123-456-7890', 
    '123 Main Street, Tokyo', 
    'Webアプリ開発, モバイルアプリ開発', 
    'https://portfolio.awesomeappstudio.com', 
    2010, 
    'Java, Spring Boot, React, Node.js', 
    '私たちは高品質なアプリ開発を提供します。', 
    CURRENT_TIMESTAMP, 
    CURRENT_TIMESTAMP
);
=======
-- categories テーブルデータ挿入
INSERT INTO categories (id, name) VALUES
(1, '和食'),
(2, '洋食'),
(3, '中華'),
(4, 'カフェ'),
(5, 'その他')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- roles テーブルデータ挿入
INSERT INTO roles (id, name) VALUES
(1, 'ROLE_GENERAL'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_PREMIUM_USER')
ON DUPLICATE KEY UPDATE name = VALUES(name);


-- users テーブルにデータを挿入
-- users テーブルにデータを挿入
INSERT IGNORE INTO users (
    id, name, furigana, postal_code, address, phone_number, email, password, 
    role_id, enabled, is_premium, is_paid, customer_id, created_at, updated_at
) VALUES 
(1, 'admin', 'アドミン', '000-0000', '東京都千代田区丸の内1-1', '090-0000-0000', 'admin@example.com', 'password', 
 2, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(2, '侍 太郎', 'サムライ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(3, '侍 花子', 'サムライ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(4, '侍 義勝', 'サムライ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(5, '侍 幸美', 'サムライ サチミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachimi.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(6, '侍 雅', 'サムライ ミヤビ', '527-0209', '滋賀県東近江市佐目町X-XX-XX', '090-1234-5678', 'miyabi.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(7, '侍 正保', 'サムライ マサヤス', '989-1203', '宮城県柴田郡大河原町旭町X-XX-XX', '090-1234-5678', 'masayasu.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(8, '侍 真由美', 'サムライ マユミ', '951-8015', '新潟県新潟市松岡町X-XX-XX', '090-1234-5678', 'mayumi.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(9, '侍 安民', 'サムライ ヤスタミ', '241-0033', '神奈川県横浜市旭区今川町X-XX-XX', '090-1234-5678', 'yasutami.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(10, '侍 章緒', 'サムライ アキオ', '739-2103', '広島県東広島市高屋町宮領X-XX-XX', '090-1234-5678', 'akio.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(11, '侍 祐子', 'サムライ ユウコ', '601-0761', '京都府南丹市美山町高野X-XX-XX', '090-1234-5678', 'yuko.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(12, '侍 秋美', 'サムライ アキミ', '606-8235', '京都府京都市左京区田中西春菜町X-XX-XX', '090-1234-5678', 'akimi.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(13, '侍 信平', 'サムライ シンペイ', '673-1324', '兵庫県加東市新定X-XX-XX', '090-1234-5678', 'shinpei.samurai@example.com', 'password', 
 1, true, false, false, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(14, '侍 プレミアム', 'サムライ プレミアム', '102-0071', '東京都港区北青山2丁目X-XX', '080-1234-5678', 'premium.samurai@example.com', 'password', 
 3, true, true, true, 'cus_premium1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(15, '侍 裕美', 'サムライ ヒロミ', '150-0001', '東京都渋谷区神宮前X-XX', '080-2345-6789', 'hiromi.samurai@example.com', 'password', 
 3, true, true, true, 'cus_premium2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- user_roles テーブルにデータを挿入
INSERT IGNORE INTO user_roles (user_id, role_id) 
VALUES
(1, 2), -- 管理者 (admin) に ROLE_ADMIN を割り当て
(2, 1), -- 一般ユーザー (侍 太郎) に ROLE_GENERAL を割り当て
(3, 1), -- 一般ユーザー (侍 花子) に ROLE_GENERAL を割り当て
(4, 1), -- 一般ユーザー (侍 義勝) に ROLE_GENERAL を割り当て
(5, 1), -- 一般ユーザー (侍 幸美) に ROLE_GENERAL を割り当て
(6, 1), -- 一般ユーザー (侍 雅) に ROLE_GENERAL を割り当て
(7, 1), -- 一般ユーザー (侍 正保) に ROLE_GENERAL を割り当て
(8, 1), -- 一般ユーザー (侍 真由美) に ROLE_GENERAL を割り当て
(9, 1), -- 一般ユーザー (侍 安民) に ROLE_GENERAL を割り当て
(10, 1), -- 一般ユーザー (侍 章緒) に ROLE_GENERAL を割り当て
(11, 1), -- 一般ユーザー (侍 祐子) に ROLE_GENERAL を割り当て
(12, 1), -- 一般ユーザー (侍 秋美) に ROLE_GENERAL を割り当て
(13, 1), -- 一般ユーザー (侍 信平) に ROLE_GENERAL を割り当て
(14, 3), -- プレミアムユーザー (侍 プレミアム) に ROLE_PREMIUM_USER を割り当て
(15, 3); -- プレミアムユーザー (侍 裕美) に ROLE_PREMIUM_USER を割り当て

-- shop テーブルにデータを挿入
INSERT IGNORE INTO shop (category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
VALUES
(1, 'SAMURAIのもり', 'house01.jpg', '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ゆったりとお食事できます。', 3000, 6000, '11:00:00', '21:00:00', 'WEDNESDAY', '073-0145', '北海道砂川市西五条南X-XX-XX', '012-345-678'),
(1, '居酒屋 SAMURAI', 'house02.jpg', '最寄り駅から徒歩10分。数多いメニューでファミリーでもお食事いただけます。', 2000, 6000, '17:00:00', '23:00:00', 'MONDAY', '030-0945', '青森県青森市桜川X-XX-XX', '012-345-678'),
(1, 'SAMURAI屋', 'house03.jpg', '最寄り駅から徒歩10分。土佐料理店。皿鉢料理をはじめ土佐の地元料理が楽しめます。', 3000, 8000, '11:00:00', '22:00:00', 'TUESDAY', '029-5618', '岩手県和賀郡西和賀町沢内両沢X-XX-XX', '012-345-678'),
(1, 'SAMURAI座衛門', 'house04.jpg', '最寄り駅から徒歩10分。肉飯の店。ローストビーフ丼は絶品です。', 1000, 3000, '11:00:00', '21:00:00', 'TUESDAY', '989-0555', '宮城県刈田郡七ヶ宿町滝ノ上X-XX-XX', '012-345-678'),
(4, 'SAMURAIcafe', 'house05.jpg', '自然豊かで閑静な場所にあります。ゆったりとした時間を過ごしていただけます。', 1000, 3000, '08:00:00', '20:00:00', 'WEDNESDAY', '018-2661', '秋田県山本郡八峰町八森浜田X-XX-XX', '012-345-678'),
(2, 'レストラン SAMURAI', 'house06.jpg', '最寄り駅から徒歩10分。カジュアルレストランの店。', 3000, 8000, '10:00:00', '23:00:00', 'TUESDAY', '999-6708', '山形県酒田市泉興野X-XX-XX', '012-345-678'),
(3, 'SAMURAI天津', 'house07.jpg', '自然豊かで閑静な場所。本場の中国料理を楽しめます。', 5000, 10000, '12:00:00', '22:00:00', 'MONDAY', '969-5147', '福島県会津若松市大戸町芦牧X-XX-XX', '012-345-678'),
(1, 'SAMURAIラーメン', 'house08.jpg', '路地裏の隠れ店。おすすめは豚骨ラーメン。', 1000, 3000, '11:00:00', '00:00:00', 'SUNDAY', '310-0021', '茨城県水戸市南町X-XX-XX', '012-345-678'),
(1, 'SAMURAI鳥や', 'house09.jpg', '放し飼いの地鶏が楽しめるお店。水炊きもおすすめです。', 3000, 10000, '18:00:00', '23:00:00', 'WEDNESDAY', '323-1101', '栃木県下都賀郡藤岡町大前X-XX-XX', '012-345-678'),
(1, 'シーサイドSAMURAI', 'house10.jpg', '海なし県でも魚介料理を提供しています。', 5000, 10000, '12:00:00', '21:00:00', 'MONDAY', '370-0806', '群馬県高崎市上和田町X-XX-XX', '012-345-678'),
(2, 'イタ飯SAMURAI', 'house11.jpg', '本格的イタリアン料理。三ツ星レストラン出身シェフの料理。', 6000, 20000, '11:00:00', '21:00:00', 'THURSDAY', '344-0125', '埼玉県春日部市飯沼X-XX-XX', '012-345-678'),
(2, 'ヴィラSAMURAI', 'house12.jpg', '自然豊かでゆったりとお食事できます。', 3000, 10000, '11:00:00', '22:00:00', 'WEDNESDAY', '272-0011', '千葉県市川市高谷新町X-XX-XX', '012-345-678'),
(2, 'SAMURAIパレス', 'house13.jpg', 'ウェディングやパーティーに最適です。', 8000, 20000, '10:00:00', '20:00:00', 'WEDNESDAY', '130-0023', '東京都墨田区立川X-XX-XX', '012-345-678'),
(2, 'ロッジ SAMURAI', 'house14.jpg', '自然豊かで閑静な場所にあります。', 3000, 15000, '11:00:00', '21:00:00', 'SUNDAY', '240-0006', '神奈川県横浜市保土ヶ谷区星川X-XX-XX', '012-345-678'),
(2, 'SAMURAI館', 'house15.jpg', 'スペイン料理が楽しめます。ワインもおすすめです。', 5000, 10000, '12:00:00', '22:00:00', 'THURSDAY', '950-0201', '新潟県新潟市駒込X-XX-XX', '012-345-678'),
(2, 'SAMURAI山荘', 'house16.jpg', '自然豊かで山小屋風の建物で楽しめます。', 3000, 6000, '08:00:00', '19:00:00', 'WEDNESDAY', '939-8155', '富山県富山市江本X-XX-XX', '012-345-678'),
(1, 'SAMURAIの家', 'house17.jpg', '家庭的な雰囲気で一品料理が並びます。', 3000, 8000, '18:00:00', '01:00:00', 'MONDAY', '929-0111', '石川県能美市吉原町X-XX-XX', '012-345-678'),
(2, 'SAMURAIポルト', 'house18.jpg', 'ポルトガル料理の店。素朴な家庭料理を楽しめます。', 8000, 15000, '17:00:00', '23:00:00', 'MONDAY', '910-2354', '福井県福井市東天田町X-XX-XX', '012-345-678'),
(2, 'SAMURAIブラジル', 'house19.jpg', 'ブラジル料理店。シェラスコやフェイジョアーダが人気。', 3000, 10000, '18:00:00', '01:00:00', 'WEDNESDAY', '403-0003', '山梨県富士吉田市大明見X-XX-XX', '012-345-678'),
(2, 'SAMURAI軒', 'house20.jpg', 'コロッケやハンバーグなど人気メニューが目白押し。', 3000, 10000, '08:00:00', '21:00:00', 'FRIDAY', '395-0017', '長野県飯田市東新町X-XX-XX', '012-345-678');



-- verification_tokens テーブルデータ挿入
INSERT IGNORE INTO verification_tokens (user_id, token) 
VALUES
(1, 'admin-token'), -- 管理者トークン
(2, 'taro-token'), -- 一般ユーザー (侍 太郎) のトークン
(3, 'hanako-token'), -- 一般ユーザー (侍 花子) のトークン
(4, 'yoshikatsu-token'), -- 一般ユーザー (侍 義勝) のトークン
(5, 'sachimi-token'), -- 一般ユーザー (侍 幸美) のトークン
(6, 'miyabi-token'), -- 一般ユーザー (侍 雅) のトークン
(7, 'masayasu-token'), -- 一般ユーザー (侍 正保) のトークン
(8, 'mayumi-token'), -- 一般ユーザー (侍 真由美) のトークン
(9, 'yasutami-token'), -- 一般ユーザー (侍 安民) のトークン
(10, 'akio-token'), -- 一般ユーザー (侍 章緒) のトークン
(11, 'yuko-token'), -- 一般ユーザー (侍 祐子) のトークン
(12, 'akimi-token'), -- 一般ユーザー (侍 秋美) のトークン
(13, 'shinpei-token'), -- 一般ユーザー (侍 信平) のトークン
(14, 'premium-token'), -- プレミアムユーザー (侍 プレミアム) のトークン
(15, 'hiromi-token'); -- プレミアムユーザー (侍 裕美) のトークン


-- reservations テーブルにデータを挿入
-- reservations テーブルにデータを挿入
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservations_date, reservation_time, number_of_people, created_at, updated_at) 
VALUES
(1, 1, 2, '2023-04-01', '18:00:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, 2, '2023-04-01', '19:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 3, 2, '2023-04-01', '20:30:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 4, 3, '2023-04-02', '19:00:00', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 5, 3, '2023-04-03', '09:30:00', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 6, 4, '2023-04-04', '10:00:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 7, 4, '2023-04-05', '14:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 8, 5, '2023-04-06', '23:00:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 9, 5, '2023-04-07', '21:00:00', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 10, 6, '2023-04-08', '13:00:00', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 11, 6, '2023-04-09', '15:00:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 12, 7, '2023-04-10', '16:00:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 13, 7, '2023-04-11', '17:30:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, 14, 14, '2023-04-12', '18:00:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- プレミアムユーザーの予約
(15, 15, 15, '2023-04-13', '19:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- プレミアムユーザーの予約
(16, 1, 3, '2023-05-01', '17:00:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(17, 2, 4, '2023-05-02', '18:30:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(18, 3, 5, '2023-05-03', '19:00:00', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, 4, 6, '2023-05-04', '20:00:00', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(20, 5, 7, '2023-05-05', '21:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(21, 6, 8, '2023-05-06', '19:30:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(22, 7, 9, '2023-05-07', '20:30:00', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(23, 8, 10, '2023-05-08', '18:00:00', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(24, 9, 11, '2023-05-09', '19:00:00', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(25, 10, 12, '2023-05-10', '20:00:00', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- reviews テーブルにデータを挿入
-- reviews テーブルにデータを挿入
INSERT IGNORE INTO reviews (id, user_id, shop_id, evaluation, review_comment, created_at, updated_at) 
VALUES
(1, 1, 1, 5, 'きれいで快適に過ごせました。', '2023-04-01 10:00:00', '2023-04-02 10:00:00'),
(2, 2, 1, 4, 'アクセスがよく、観光に最適でした。', '2023-04-01 11:00:00', '2023-04-02 11:00:00'),
(3, 3, 1, 3, 'スタッフが親切で気持ちよく滞在できました。', '2023-04-01 12:00:00', '2023-04-02 12:00:00'),
(4, 4, 1, 4, '駅から近く便利でした。', '2023-04-01 13:00:00', '2023-04-02 13:00:00'),
(5, 5, 1, 5, '周辺の店が充実していて、楽しめました。', '2023-04-01 14:00:00', '2023-04-02 14:00:00'),
(6, 6, 1, 3, '手ごろで、コスパがよかったです。', '2023-04-01 15:00:00', '2023-04-02 15:00:00'),
(7, 7, 1, 4, '静かな環境でゆっくり休めました。', '2023-04-01 16:00:00', '2023-04-02 16:00:00'),
(8, 8, 1, 5, '施設内の設備が充実していました。', '2023-04-01 17:00:00', '2023-04-02 17:00:00'),
(9, 9, 1, 4, 'Wi-Fiが快適で助かりました。', '2023-04-01 18:00:00', '2023-04-02 18:00:00'),
(10, 10, 1, 5, '清潔感があり気持ちよく滞在できました。', '2023-04-01 19:00:00', '2023-04-02 19:00:00'),
(11, 11, 1, 3, 'フレンドリーなスタッフで安心できました。', '2023-04-01 20:00:00', '2023-04-02 20:00:00'),
(12, 14, 2, 5, '料理が素晴らしい！また利用したいです。', '2023-04-02 12:00:00', '2023-04-03 12:00:00'),
(13, 15, 3, 4, '中華料理が本格的で満足しました。', '2023-04-02 14:00:00', '2023-04-03 14:00:00'),
(14, 2, 4, 5, '肉料理が美味しかったです。', '2023-05-01 10:00:00', '2023-05-02 10:00:00'),
(15, 3, 5, 4, 'カフェの雰囲気が素晴らしかったです。', '2023-05-02 11:00:00', '2023-05-03 11:00:00'),
(16, 4, 6, 3, '期待以上ではなかった。', '2023-05-03 12:00:00', '2023-05-04 12:00:00'),
(17, 5, 7, 5, '家族全員が満足しました。', '2023-05-04 13:00:00', '2023-05-05 13:00:00'),
(18, 6, 8, 4, 'コーヒーが美味しかったです。', '2023-05-05 14:00:00', '2023-05-06 14:00:00'),
(19, 7, 9, 5, '最高のランチ体験でした。', '2023-05-06 15:00:00', '2023-05-07 15:00:00'),
(20, 8, 10, 4, 'シェフの腕前がすごい。', '2023-05-07 16:00:00', '2023-05-08 16:00:00'),
(21, 9, 11, 3, '普通の体験でした。', '2023-05-08 17:00:00', '2023-05-09 17:00:00'),
(22, 10, 12, 4, 'デザートが美味しかった。', '2023-05-09 18:00:00', '2023-05-10 18:00:00'),
(23, 11, 13, 5, 'スタッフが親切でした。', '2023-05-10 19:00:00', '2023-05-11 19:00:00'),
(24, 12, 14, 4, 'コストパフォーマンスが良かった。', '2023-05-11 20:00:00', '2023-05-12 20:00:00'),
(25, 13, 15, 5, '感動的な料理でした。', '2023-05-12 21:00:00', '2023-05-13 21:00:00');



-- company テーブルにデータを挿入
-- company テーブルにデータを挿入
INSERT IGNORE INTO company (
    id, 
    company_name, 
    website_url, 
    contact_email, 
    phone_number, 
    address, 
    services, 
    portfolio_url, 
    established_year, 
    technology_stack, 
    description, 
    created_at, 
    updated_at
) 
VALUES
(1, 'Awesome App Studio', 'https://awesomeappstudio.com', 'info@awesomeappstudio.com', '123-456-7890', 
 '123 Main Street, Tokyo', 'Webアプリ開発, モバイルアプリ開発', 
 'https://portfolio.awesomeappstudio.com', 2010, 
 'Java, Spring Boot, React, Node.js', 
 '私たちは高品質なアプリ開発を提供します。', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
