CREATE TABLE musicgame ( 
	id        	int(11) AUTO_INCREMENT COMMENT 'pk'  NOT NULL,
	ques      	text COMMENT '질문'  NULL,
	ans1      	varchar(200) COMMENT '문답1'  NULL,
	ans2      	varchar(200) COMMENT '문답2'  NULL,
	ans3      	varchar(200) COMMENT '문답3'  NULL,
	ans4      	varchar(200) COMMENT '문답4'  NULL,
	correct   	varchar(200) COMMENT '정답'  NULL,
	name      	varchar(200) COMMENT '이름'  NULL,
	music_file	varchar(200) COMMENT '음악파일'  NULL,
	img_file  	varchar(200) COMMENT '이미지파일'  NULL,
	use_yn    	varchar(10) COMMENT '사용여부'  NULL,
	update_dt 	datetime COMMENT '수정일'  NULL,
	create_dt 	datetime COMMENT '생성일'  NULL 
	)
COMMENT = '음악 퀴즈' 
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(16, 'Who was the first drummer of the Beatles?', 'Pete Best', 'Ringo Starr', 'Travis Barker', 'Buddy Rich', 'Pete Best', 'firstBeatlesDrummer', '', '', 'y', '2020-09-22 15:32:14.0', '2020-09-22 15:32:14.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(17, 'What city is synonymous with grunge?', 'New York', 'Los Angeles', 'Seattle', 'London', 'Seattle', 'grunge', '', '', 'y', '2020-09-22 15:32:41.0', '2020-09-22 15:32:41.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(18, 'What sport was Bob Marley''s skill considered to be at a professional level?', 'Cricket', 'Soccer', 'Tennis', 'Basketball', 'Soccer', 'bobMarley', '', '', 'y', '2020-09-22 15:33:01.0', '2020-09-22 15:33:01.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(19, 'Who is one famous musician that died on the day the music died?', 'Don McLean', 'Prince', 'Amy Winehouse', 'Buddy Holly', 'Buddy Holly', 'musicDied', '', '', 'y', '2020-09-22 15:33:27.0', '2020-09-22 15:33:27.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(20, 'Who is not a left-handed guitarist?', 'Kurt Cobain', 'Paul McCartney', 'Slash', 'Jimi Hendrix', 'Slash', 'leftHanded', '', '', 'y', '2020-09-22 15:33:52.0', '2020-09-22 15:33:52.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(21, 'What famous song''s bass line was sampled for Shaggy''s song ''Angel''?', 'Queen - Under Pressure', 'The Steve Miller Band - The Joker', 'The Seinfeld Intro', 'Motörhead - Ace of Spades', 'The Steve Miller Band - The Joker', 'angel', '', '', 'y', '2020-09-22 15:34:22.0', '2020-09-22 15:34:22.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(22, 'In what city were the Rolling Stones formed?', 'Liverpool', 'Manchester', 'Bristol', 'London', 'London', 'rollingStones', '', '', 'y', '2020-09-22 15:34:42.0', '2020-09-22 15:34:42.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(23, 'Who was the first artist to win a Grammy without a record contract?', 'Taylor Swift', 'Chance the Rapper', 'Amy Winehouse', 'Beyonce', 'Chance the Rapper', 'grammy', '', '', 'y', '2020-09-22 15:35:12.0', '2020-09-22 15:35:12.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(24, 'What was the first music video played on MTV when it launched on August 1, 1981?', 'The Buggles - Video Killed The Radio Star', 'Sheena Easton - 9 to 5', 'Rick Springfield - Jessie''s Girl', 'Hall and Oates - Private Eyes', 'The Buggles - Video Killed The Radio Star', 'mtv', '', '', 'y', '2020-09-22 15:35:49.0', '2020-09-22 15:35:49.0')
GO
INSERT INTO musicgame(id, ques, ans1, ans2, ans3, ans4, correct, name, music_file, img_file, use_yn, update_dt, create_dt)
  VALUES(25, 'What is the name of the beat that is featured in all reggaeton songs?', 'Paradiddle', 'Train Beat', 'Money Beat', 'Dem Bow', 'Dem Bow', 'beat', '', '', 'y', '2020-09-22 15:36:13.0', '2020-09-22 15:36:13.0')
GO
