package org.technical.test.utility;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.technical.test.api.common.BaseObject;
import org.technical.test.api.exceptions.BadRequestException;

import java.util.List;

public class Utility {

    public static void validarJSON(BaseObject objeto) throws BadRequestException {
        Validator validator = new Validator();

        // It's validate the JSON received with OVal annotation
        List<ConstraintViolation> violations = validator.validate(objeto);
        String errorMessage = "";

        for (ConstraintViolation cv : violations) {
            errorMessage = cv.getMessage().concat(" [");
            if (cv.getCauses() != null) {
                for (ConstraintViolation cv2 : cv.getCauses()) {
                    errorMessage = errorMessage.concat(cv2.getMessage()).concat(";");
                }
            }
            errorMessage.concat("]");
        }

        if (violations.size() > 0) {
            throw new BadRequestException(errorMessage);
        }
    }

}
