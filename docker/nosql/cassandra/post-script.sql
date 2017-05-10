CREATE KEYSPACE apache_ignite
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

USE apache_ignite;

CREATE TABLE PAYMENT (

  id           bigint PRIMARY KEY,
  amount       decimal,
  purpose      text,
  creationDate timestamp,
  status       text

);