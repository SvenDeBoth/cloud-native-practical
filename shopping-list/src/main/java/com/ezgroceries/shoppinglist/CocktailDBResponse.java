package com.ezgroceries.shoppinglist;

import java.util.Arrays;
import java.util.List;

public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        return "CocktailDBResponse{" +
                "drinks=" + drinks +
                '}';
    }

    public static class DrinkResource {
        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;
        private String strIngredient5;
        private String strIngredient6;
        private String strIngredient7;

        public String getIdDrink() {
            return idDrink;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public String getStrIngredient1() {
            return strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public List<String> getIngredientsList()
        {
            return Arrays.asList(strIngredient1, strIngredient2);

        }
        public String getStrIngredient3() {
            return strIngredient3;
        }

        public String getStrIngredient4() {
            return strIngredient4;
        }

        public String getStrIngredient5() {
            return strIngredient5;
        }

        public String getStrIngredient6() {
            return strIngredient6;
        }

        public String getStrIngredient7() {
            return strIngredient7;
        }

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public void setStrDrink(String strDrink) {
            this.strDrink = strDrink;
        }

        public void setStrGlass(String strGlass) {
            this.strGlass = strGlass;
        }

        public void setStrInstructions(String strInstructions) {
            this.strInstructions = strInstructions;
        }

        public void setStrDrinkThumb(String strDrinkThumb) {
            this.strDrinkThumb = strDrinkThumb;
        }

        public void setStrIngredient1(String strIngredient1) {
            this.strIngredient1 = strIngredient1;
        }

        public void setStrIngredient2(String strIngredient2) {
            this.strIngredient2 = strIngredient2;
        }

        public void setStrIngredient3(String strIngredient3) {
            this.strIngredient3 = strIngredient3;
        }

        public void setStrIngredient4(String strIngredient4) {
            this.strIngredient4 = strIngredient4;
        }

        public void setStrIngredient5(String strIngredient5) {
            this.strIngredient5 = strIngredient5;
        }

        public void setStrIngredient6(String strIngredient6) {
            this.strIngredient6 = strIngredient6;
        }

        public void setStrIngredient7(String strIngredient7) {
            this.strIngredient7 = strIngredient7;
        }

        @Override
        public String toString() {
            return "DrinkResource{" +
                    "idDrink='" + idDrink + '\'' +
                    ", strDrink='" + strDrink + '\'' +
                    ", strGlass='" + strGlass + '\'' +
                    ", strInstructions='" + strInstructions + '\'' +
                    ", strDrinkThumb='" + strDrinkThumb + '\'' +
                    ", strIngredient1='" + strIngredient1 + '\'' +
                    ", strIngredient2='" + strIngredient2 + '\'' +
                    ", strIngredient3='" + strIngredient3 + '\'' +
                    ", strIngredient4='" + strIngredient4 + '\'' +
                    ", strIngredient5='" + strIngredient5 + '\'' +
                    ", strIngredient6='" + strIngredient6 + '\'' +
                    ", strIngredient7='" + strIngredient7 + '\'' +
                    '}';
        }
    }
}