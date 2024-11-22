-- categoriesテーブルにデータを挿入
INSERT IGNORE INTO categories (id, category_name) VALUES (1, '和食');
INSERT IGNORE INTO categories (id, category_name) VALUES (2, '洋食');
INSERT IGNORE INTO categories (id, category_name) VALUES (3, '中華');
INSERT IGNORE INTO categories (id, category_name) VALUES (4, 'カフェ');
INSERT IGNORE INTO categories (id, category_name) VALUES (5, 'その他');

-- shopテーブルにデータを挿入
INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 1, 1, 'SAMURAIの宿', 'house01.jpg', '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ゆったりとお食事できます。', 3000, 6000, '11:00:00', '21:00:00', 'WEDNESDAY', '073-0145', '北海道砂川市西五条南X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 1);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 2, 1, '居酒屋 SAMURAI', 'house02.jpg', '最寄り駅から徒歩10分。数多いメニューでファミリーでもお食事いただけます。', 2000, 6000, '17:00:00', '23:00:00', 'MONDAY', '030-0945', '青森県青森市桜川X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 2);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 3, 1, 'SAMURAI屋', 'house03.jpg', '最寄り駅から徒歩10分。土佐料理店。皿鉢料理をはじめ土佐の地元料理が楽しめます。', 3000, 8000, '11:00:00', '22:00:00', 'TUESDAY', '029-5618', '岩手県和賀郡西和賀町沢内両沢X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 3);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 4, 1, 'SAMURAI座衛門', 'house04.jpg', '最寄り駅から徒歩10分。肉飯の店。ローストビーフ丼は絶品です。', 1000, 3000, '11:00:00', '21:00:00', 'TUESDAY', '989-0555', '宮城県刈田郡七ヶ宿町滝ノ上X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 4);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 5, 4, 'SAMURAIcafe', 'house05.jpg', '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ゆったりとした時間を過ごしていただけます。', 1000, 3000, '08:00:00', '20:00:00', 'WEDNESDAY', '018-2661', '秋田県山本郡八峰町八森浜田X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 5);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 6, 2, 'レストラン SAMURAI', 'house06.jpg', '最寄り駅から徒歩10分。カジュアルレストランの店。ファミリーでもお一人様でも。', 3000, 8000, '10:00:00', '23:00:00','TUESDAY', '999-6708', '山形県酒田市泉興野X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 6);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 7, 3, 'SAMURAI天津', 'house07.jpg', '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。本場の中国料理を楽しんでいただけます。', 5000, 10000, '12:00:00', '22:00:00', 'MONDAY',  '969-5147', '福島県会津若松市大戸町芦牧X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 7);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 8, 1, 'SAMURAIラーメン', 'house08.jpg', '最寄り駅から徒歩10分。路地裏の隠れ店的な場所にあります。おすすめは豚骨ラーメンです。', 1000, 3000, '11:00:00', '00:00:00', 'SUNDAY', '310-0021', '茨城県水戸市南町X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 8);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 9, 1, 'SAMURAI鳥や', 'house09.jpg', '最寄り駅から徒歩10分。放し飼いの地鶏が楽しめるお店。水炊きもおすすめです。', 3000, 10000, '18:00:00', '23:00:00', 'WEDNESDAY', '323-1101', '栃木県下都賀郡藤岡町大前X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 9);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 10, 1, 'シーサイドSAMURAI', 'house10.jpg', '最寄り駅から徒歩10分。海なし県なのにシーサイド。魚介料理を提供しています。', 5000, 10000, '12:00:00', '21:00:00', 'MONDAY', '370-0806', '群馬県高崎市上和田町X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 10);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 11, 2, 'イタ飯SAMURAI', 'house11.jpg', '最寄り駅から徒歩10分。本格的イタリアン料理のお店。イタリアの三ツ星レストラン出身のシェフの料理ご賞味あれ。',6000, 20000, '11:00:00', '21:00:00', 'THURSDAY', '344-0125', '埼玉県春日部市飯沼X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 11);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 12, 2,  'ヴィラSAMURAI', 'house12.jpg', '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ゆったりとお食事できます。', 3000, 10000, '11:00:00', '22:00:00', 'WEDNESDAY', '272-0011', '千葉県市川市高谷新町X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 12);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 13, 2,  'SAMURAIパレス', 'house13.jpg', '最寄り駅から徒歩10分。ウェディングレストランのお店。ウエディング、パーティーなど宴会にご利用ください。', 8000, 20000, '10:00:00', '20:00:00', 'WEDNESDAY', '130-0023', '東京都墨田区立川X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 13);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 14, 2, 'ロッジ SAMURAI', 'house14.jpg', '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ゆったりとお食事できます。', 3000, 15000, '11:00:00', '21:00:00', 'SUNDAY', '240-0006', '神奈川県横浜市保土ヶ谷区星川X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 14);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 15, 2, 'SAMURAI館', 'house15.jpg', '最寄り駅から徒歩10分。スペイン料理が楽しめます。ワインもおすすめ。', 5000, 10000, '12:00:00', '22:00:00', 'THURSDAY',  '950-0201', '新潟県新潟市駒込X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 15);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 16, 2, 'SAMURAI山荘', 'house16.jpg', '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。山小屋風の建物でゆったりとお食事できます。', 3000, 6000, '08:00:00', '19:00:00', 'WEDNESDAY', '939-8155', '富山県富山市江本X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 16);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 17, 1, 'SAMURAIの家', 'house17.jpg', '最寄り駅から徒歩10分。おもわずただいまと言いたくなるような家庭的な雰囲気の店。一品料理が数多く並んでいます。', 3000, 8000, '18:00:00', '01:00:00', 'MONDAY', '929-0111', '石川県能美市吉原町X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 17);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 18, 2, 'SAMURAIPOR', 'house18.jpg', '最寄り駅から徒歩10分。ポルトガル料理の店。素朴で優しいポルトガル家庭料理が楽しめます。', 8000, 15000, '17:00:00', '23:00:00', 'MONDAY', '910-2354', '福井県福井市東天田町X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 18);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 19, 2, 'SAMURAIBrazil', 'house19.jpg', '最寄り駅から徒歩10分。ブラジル料理の店。人気のシェラスコや国民食フェイジョアーダなど代表的グルメから珍グルメまで。', 3000, 10000, '18:00:00', '01:00:00', 'WEDNESDAY', '403-0003', '山梨県富士吉田市大明見X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 19);

INSERT INTO shop (id, category_id, shop_name, image_name, description, price_upper, price_lower, hours_open, hours_close, closed_day, postal_code, address, phone_number)
SELECT 20, 2, 'SAMURAI軒', 'house20.jpg', '最寄り駅から徒歩10分。コロッケやオニオンスープ、ハンバーグなど人気メニュー目白押し。ランチタイムは行列覚悟で。', 3000, 10000, '08:00:00', '21:00:00', 'FRIDAY', '395-0017', '長野県飯田市東新町X-XX-XX', '012-345-678'
WHERE NOT EXISTS (SELECT 1 FROM shop WHERE id = 20);

-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');


-- usersテーブル
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (1, '侍 太郎', 'サムライ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (2, '侍 花子', 'サムライ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (3, '侍 義勝', 'サムライ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (4, '侍 幸美', 'サムライ サチミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachimi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (5, '侍 雅', 'サムライ ミヤビ', '527-0209', '滋賀県東近江市佐目町X-XX-XX', '090-1234-5678', 'miyabi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (6, '侍 正保', 'サムライ マサヤス', '989-1203', '宮城県柴田郡大河原町旭町X-XX-XX', '090-1234-5678', 'masayasu.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (7, '侍 真由美', 'サムライ マユミ', '951-8015', '新潟県新潟市松岡町X-XX-XX', '090-1234-5678', 'mayumi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (8, '侍 安民', 'サムライ ヤスタミ', '241-0033', '神奈川県横浜市旭区今川町X-XX-XX', '090-1234-5678', 'yasutami.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (9, '侍 章緒', 'サムライ アキオ', '739-2103', '広島県東広島市高屋町宮領X-XX-XX', '090-1234-5678', 'akio.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (10, '侍 祐子', 'サムライ ユウコ', '601-0761', '京都府南丹市美山町高野X-XX-XX', '090-1234-5678', 'yuko.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (11, '侍 秋美', 'サムライ アキミ', '606-8235', '京都府京都市左京区田中西春菜町X-XX-XX', '090-1234-5678', 'akimi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (12, '侍 信平', 'サムライ シンペイ', '673-1324', '兵庫県加東市新定X-XX-XX', '090-1234-5678', 'shinpei.samurai@example.com', 'password', 1, false);


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



-- reservationsテーブル
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