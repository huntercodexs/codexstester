package codexstester.core.unitary;

import codexstester.core.external.ExternalRequest1xx;

public abstract class AbstractUnitary extends ExternalRequest1xx {

    protected AbstractUnitary(String target) {
        super(target);
    }

}