create table COCKTAIL_SHOPPING_LIST (
                               COCKTAIL_ID UUID ,
                               SHOPPING_LIST_ID UUID,

                                   CONSTRAINT FK_COCKTAIL
                                   FOREIGN KEY(COCKTAIL_ID)
                                   REFERENCES COCKTAIL(ID),
                               CONSTRAINT FK_SHOPPINGLIST
                                   FOREIGN KEY(SHOPPING_LIST_ID)
                                       REFERENCES SHOPPING_LIST(ID)
);