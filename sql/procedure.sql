use b1db;
DROP PROCEDURE IF EXISTS sumAndMediane;
DELIMITER $
CREATE PROCEDURE sumAndMediane()
BEGIN
    select sum(RandInteger) from strings as sumInteger;
    SELECT AVG(RandReal) as medianReal
	FROM (
	SELECT d.RandReal, @rownum:=@rownum+1 as 'row_number', @total_rows:=@rownum
	FROM strings d, (SELECT @rownum:=0) r
	WHERE d.RandReal is NOT NULL
	ORDER BY d.RandReal
	) as dd
	WHERE dd.row_number IN ( FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2) );
END $
DELIMITER ;