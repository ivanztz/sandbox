import com.iz.sandbox.dto.ObjectDataRequest
import com.iz.sandbox.service.validation.Violation

fun validateObject(dto: ObjectDataRequest): List<Violation> {

    var violations = ArrayList<Violation>()

    if (dto.stringField.length > 20) {
        val violation: Violation = Violation()
        violation.validationName = "string_size_check"
        violation.description = "String length must be lover than 20"
        violations.add(violation)
    }

    return violations;
}
