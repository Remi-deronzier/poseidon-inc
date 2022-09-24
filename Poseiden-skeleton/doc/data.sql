
CREATE TABLE bid_list (
  bid_list_id tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity DOUBLE NOT NULL,
  ask_quantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bid_list_date TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (bid_list_id)
);

CREATE TABLE trade (
  trade_id tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buy_quantity DOUBLE NOT NULL,
  sell_quantity DOUBLE,
  buy_price DOUBLE ,
  sell_price DOUBLE,
  trade_date TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (trade_id)
);

CREATE TABLE curve_point (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  curve_id  tinyint NOT NULL,
  as_of_date TIMESTAMP,
  term  DOUBLE NOT NULL,
  value  DOUBLE NOT NULL,
  creation_date TIMESTAMP,

  PRIMARY KEY (Id)
);

CREATE TABLE rating (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  moodys_rating VARCHAR(125) NOT NULL,
  sandprating VARCHAR(125) NOT NULL,
  fitch_rating VARCHAR(125) NOT NULL,
  order_number tinyint NOT NULL,

  PRIMARY KEY (Id)
);

CREATE TABLE rule_name (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  name  VARCHAR(125) NOT NULL,
  description  VARCHAR(125) NOT NULL,
  json  VARCHAR(125) NOT NULL,
  template  VARCHAR(512) NOT NULL,
  sql_str  VARCHAR(125) NOT NULL,
  sql_part  VARCHAR(125) NOT NULL,

  PRIMARY KEY (Id)
);

CREATE TABLE users (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(125) NOT NULL,
  password VARCHAR(125) NOT NULL,
  full_name VARCHAR(125) NOT NULL,
  role VARCHAR(125) NOT NULL,

  PRIMARY KEY (Id),
  UNIQUE (user_name)
);

insert into Users(full_name, user_name, password, role) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ROLE_ADMIN");
insert into Users(full_name, user_name, password, role) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ROLE_USER");
insert into Users(full_name, user_name, password, role) values("Remi DERONZIER", "remi", "$2a$10$PvuWH6/lRxKiqqic4IalI.BJuL3BdUH5fsh/2nKmr3Yvi9MIejEqO", "ROLE_ADMIN");
insert into Users(full_name, user_name, password, role) values("Thomas DERONZIER", "thomas", "$2a$10$/.xmSR6WXl7Qf5KZkt6KquXA/9SCGIwvKNmvCTk629ZFg7ZmHxCAu", "ROLE_USER");