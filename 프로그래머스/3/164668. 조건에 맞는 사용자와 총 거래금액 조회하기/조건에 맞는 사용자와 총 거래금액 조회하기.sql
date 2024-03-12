-- 코드를 입력하세요
SELECT U.USER_ID, U.NICKNAME, B.SALES
FROM USED_GOODS_USER U
JOIN (
    SELECT WRITER_ID, SUM(PRICE) SALES
    FROM USED_GOODS_BOARD
    WHERE STATUS = 'DONE'
    GROUP BY WRITER_ID
    HAVING SALES >= 700000
) B
ON U.USER_ID = B.WRITER_ID
ORDER BY B.SALES