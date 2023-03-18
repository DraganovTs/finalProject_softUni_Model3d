package com.homecode.library.model.view;

public class CustomerCreditsView {

    private Long id;
    private int credits;

    public CustomerCreditsView() {
    }

    public Long getId() {
        return id;
    }

    public CustomerCreditsView setId(Long id) {
        this.id = id;
        return this;
    }

    public int getCredits() {
        return credits;
    }

    public CustomerCreditsView setCredits(int credits) {
        this.credits = credits;
        return this;
    }
}
