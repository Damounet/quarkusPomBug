package com.toto.tdng.datachecker.common.model.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class FunctionalException extends Exception {

    protected List<String> errorMessageList = new ArrayList<>();

    public FunctionalException(String errorMessage) {
        super(errorMessage);
    }

    public FunctionalException(String errorMessage, List<String> invalidFiles) {
        super(errorMessage);
        this.errorMessageList = invalidFiles;
    }

    public FunctionalException(String errorMessage, String invalidFile) {
        super(errorMessage);
        this.errorMessageList = new ArrayList<>();
        this.errorMessageList.add(invalidFile);

    }

}
