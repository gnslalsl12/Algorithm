SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_ID IN (
SELECT TREE.ITEM_ID   #RARE등급의 아이템이 부모 아이템인 자식 아이템들의 번호들
FROM ITEM_TREE TREE
JOIN ITEM_INFO INFO
ON TREE.PARENT_ITEM_ID = INFO.ITEM_ID
WHERE INFO.RARITY = 'RARE'
)
ORDER BY ITEM_ID DESC