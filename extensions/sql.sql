DROP TABLE IF EXISTS `play_address`;
DROP TABLE IF EXISTS `movie`;

CREATE TABLE `movie` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `name` TEXT default NULL COMMENT '影片名称',
  `title_tip` TEXT default NULL COMMENT '更新至201908220',
  `type` TEXT default NULL COMMENT '类型：国产剧',
  `tag` TEXT default NULL COMMENT '标签：搞笑$少女$推理$竞技',
  `cover_url` TEXT default NULL COMMENT '封面',
  `score` FLOAT(6,2) default NULL COMMENT '评分',
  `score_total` FLOAT(6,2) default NULL COMMENT '总评分数',
  `score_num` int(11) default NULL COMMENT '评分次数',
  `alias` TEXT default NULL COMMENT '别名',
  `director` TEXT default NULL COMMENT '导演',
  `actors` TEXT default NULL COMMENT '主演',
  `language` TEXT default NULL COMMENT '语言',
  `region` TEXT default NULL COMMENT '地区',
  `release_time` TEXT default NULL COMMENT '上映时间',
  `length` TEXT default NULL COMMENT '片长',
  `update_time` BIGINT(13) default NULL COMMENT '更新时间10位',
  `views_total` int(11) default NULL COMMENT '总播放量',
  `views_today` int(11) default NULL COMMENT '今日播放量',
  `summary` TEXT default NULL COMMENT '剧情介绍',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `play_address` (
  `id`         int(11) NOT NULL auto_increment COMMENT '编号',
  `movie_id`   int(11) default NULL COMMENT '影片编号',
  `media_type` TEXT    default NULL COMMENT '播放类型：ckm3u8',
  `url`        TEXT    default NULL COMMENT '资源地址',
  `title`      TEXT    default NULL COMMENT '第01集',
  `invalid`    int(11) default NULL COMMENT '失效',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`movie_id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;






# alter table `play_address` add constraint foreign key(movie_id) references movie(id);

