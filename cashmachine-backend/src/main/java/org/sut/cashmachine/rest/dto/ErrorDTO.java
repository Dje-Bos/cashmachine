package org.sut.cashmachine.rest.dto;

public class ErrorDTO {
    private String description;
    private String error;

    public ErrorDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorDTO(String description, String error) {
        this.description = description;
        this.error = error;
    }
}
