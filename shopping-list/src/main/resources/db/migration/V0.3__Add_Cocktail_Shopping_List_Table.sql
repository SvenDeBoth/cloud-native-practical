create table SHOPPING_LIST_COCKTAILS (
                               COCKTAILS_ID UUID ,
                               SHOPPING_LIST_ENTITY_ID UUID,

                                   CONSTRAINT FK_COCKTAIL
                                   FOREIGN KEY(COCKTAILS_ID)
                                   REFERENCES COCKTAIL(ID),
                               CONSTRAINT FK_SHOPPINGLIST
                                   FOREIGN KEY(SHOPPING_LIST_ENTITY_ID)
                                       REFERENCES SHOPPING_LIST(ID)
);