package com.huntercodexs.codexstester.unitary;

import com.huntercodexs.codexstester.external.ExternalRequest1xx;

public abstract class AbstractUnitary extends ExternalRequest1xx {

    protected AbstractUnitary(String target) {
        super(target);
    }

}