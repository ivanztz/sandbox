import com.iz.sandbox.dto.ObjectDataRequest
import com.iz.sandbox.service.validation.Violation

fun validateObject(dto: ObjectDataRequest): List<Violation> {

    var violations = ArrayList<Violation>()

    if (dto.intField > 10) {
        val violation: Violation = Violation()
        violation.validationName = "int_size_check"
        violation.description = "Int size must be lover than 10"
        violations.add(violation)
    }

    return violations;
}
