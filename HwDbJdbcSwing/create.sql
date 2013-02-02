DROP TABLE IF EXISTS users;
CREATE TABLE  users (
  id bigint(20) NOT NULL auto_increment,
  name varchar(45) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
