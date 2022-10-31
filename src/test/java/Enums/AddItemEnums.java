package Enums;

public enum AddItemEnums {
    SEARCH("twotabsearchtextbox"),
    SEARCHBUTTON("nav-search-submit-button"),
    MOBILECOLOUR("selection"),
    MOBILE("Redmi 9A Sport"),
    MOBILESIZE("a-text-left a-size-base"),
    QUESTION("5v3fge-m2aifc-49rfbq-uws09q"),
    ADDTOCART("submit.add-to-cart"),
    VERIFY("Added to Cart"),
    SHOPPINGCART("a-button a-button-base attach-button-large attach-cart-button");
    private String name;
    private AddItemEnums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}
