# iresources-202001
springcloud项目

MYSQL创建序列
1.序列表
DROP TABLE IF EXISTS sequence_table;
CREATE TABLE sequence_table (
table_name VARCHAR(50) NOT NULL,
current_value INT NOT NULL,
increment INT NOT NULL DEFAULT 1,
PRIMARY KEY (table_name)
)ENGINE=InnoDB;

添加记录；

table_name                    current_value              increment 

testSeq　　|     0                            |                  1


2.创建函数
在创建函数之前先设置

SET GLOBAL log_bin_trust_function_creators = 1;

在创建函数currval
CREATE FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN
DECLARE value int;
set value=0;
SELECT current_value INTO value FROM sequence_table where table_name=seq_name;
return value;
END 

创建函数 nextval
CREATE FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN
UPDATE sequence_table SET current_value = current_value + increment where table_name=seq_name ;
RETURN currval(seq_name);
END 

创建函数 setval

CREATE FUNCTION `setval`(seq_name VARCHAR(50), value INTEGER) RETURNS int(11)
BEGIN
UPDATE sequence_table
SET current_value = value  where table_name=seq_name;
RETURN currval(seq_name);
END 

创建函数 setvallist  (针对多条数据获取序列)
CREATE FUNCTION `setvallist`(seq_name VARCHAR(50), value INTEGER) RETURNS int(11)
BEGIN
UPDATE sequence_table
SET current_value =current_value+value  where table_name=seq_name;
RETURN currval(seq_name);
END 

创建完毕之后：我们用sql语句 select nextval（‘testSeq’）就会返回 1 了！

select setval('testSeq',10)  返回43

在次select nextval（‘testSeq’）就会返回 44 了！
