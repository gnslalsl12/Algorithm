-- 코드를 입력하세요
SELECT OUTS.ANIMAL_ID AS ANIMAL_ID, OUTS.NAME AS NAME
FROM ANIMAL_OUTS OUTS
LEFT JOIN ANIMAL_INS INS
ON OUTS.ANIMAL_ID = INS.ANIMAL_ID
WHERE INS.DATETIME IS NULL
ORDER BY OUTS.ANIMAL_ID;