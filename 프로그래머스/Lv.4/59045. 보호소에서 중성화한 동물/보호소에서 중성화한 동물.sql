-- 코드를 입력하세요
SELECT INS.ANIMAL_ID, INS.ANIMAL_TYPE, INS.NAME
FROM ANIMAL_INS INS
JOIN ANIMAL_OUTS OUTS
ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
WHERE LEFT(INS.SEX_UPON_INTAKE,6) = 'Intact' AND LEFT(OUTS.SEX_UPON_OUTCOME,6) <> 'Intact'
ORDER BY INS.ANIMAL_ID;