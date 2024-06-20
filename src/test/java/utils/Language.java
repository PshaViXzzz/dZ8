package utils;

public enum Language {
    RU("""
          Бронирование отелей по всему миру
          с оплатой в России без комиссии!
          """),

    EN("Book hotels in Russia and worldwide!");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
