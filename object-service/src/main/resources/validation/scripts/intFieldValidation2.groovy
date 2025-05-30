import com.iz.sandbox.object.dto.ObjectDataRequest
import com.iz.sandbox.service.validation.Violation

List<Violation> validateObject(ObjectDataRequest dto) {
    List<Violation> violations = new ArrayList<Violation>()

    if (dto.intField < 0) {
        Violation violation = new Violation()
        violation.validationName = "int_size_check"
        violation.description = "Int size must be greater than 0"
        violations.add(violation)
    }

    return violations
}