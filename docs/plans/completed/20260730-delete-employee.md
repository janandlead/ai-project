# Delete Employee

## Overview

Add a REST endpoint to delete an employee by ID. Existing employees return `204 No Content`; missing IDs return `404 Not Found` using the service's existing error pattern.

## Context

- `EmployeeController` exposes `/rest/employees`.
- `EmployeeService` owns employee persistence operations.
- `EmployeeRepository` is a Spring Data JPA repository for `Employee`.
- No delete endpoint or delete-specific tests currently exist.

## Development Approach

- Testing approach: regular (code first, then tests).
- Keep the change focused and preserve existing create/update behavior.
- Complete and run tests before considering the feature complete.

## Solution Overview

Add `EmployeeService.delete(Long)` with an existence check followed by `deleteById`. Expose it through `@DeleteMapping("/{id}")` and return `204 No Content` on success.

## Implementation Steps

### Task 1: Add employee deletion API and service behavior

**Files:**
- Modify: `src/main/java/com/ai/controller/EmployeeController.java`
- Modify: `src/main/java/com/ai/service/EmployeeService.java`
- Create: `src/test/java/com/ai/service/EmployeeServiceTest.java`

- [x] add the service delete operation with existing-employee and not-found handling
- [x] add `DELETE /rest/employees/{id}` with a `204` response
- [x] test successful deletion
- [x] test missing employee behavior
- [x] run the Maven test suite

### Task 2: Verify acceptance criteria

- [x] verify the endpoint path and status codes
- [x] run the full test suite

## Post-Completion

Manual verification: send `DELETE /rest/employees/{id}` against the running application for an existing and a missing ID.
