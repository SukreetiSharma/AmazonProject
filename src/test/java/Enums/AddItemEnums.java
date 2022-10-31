package Enums;

public enum AddItemEnums {
    SEARCH("twotabsearchtextbox"),
    SEARCHBUTTON("nav-search-submit-button");
    private String name;
    private AddItemEnums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}
