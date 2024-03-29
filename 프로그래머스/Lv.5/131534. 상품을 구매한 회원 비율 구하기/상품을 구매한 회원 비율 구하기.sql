-- 코드를 입력하세요
SELECT YEAR(OS.SALES_DATE) AS YEAR, MONTH(OS.SALES_DATE) AS MONTH, COUNT(DISTINCT UI.USER_ID) AS PURCHASED_USERS, ROUND(COUNT(DISTINCT UI.USER_ID)/(SELECT COUNT(USER_ID) FROM USER_INFO WHERE YEAR(JOINED) = 2021),1) AS PURCHASED_RATIO
FROM USER_INFO UI
JOIN ONLINE_SALE OS
ON UI.USER_ID = OS.USER_ID
WHERE OS.SALES_DATE IS NOT NULL AND YEAR(UI.JOINED) = 2021
GROUP BY 1, 2
ORDER BY 1, 2