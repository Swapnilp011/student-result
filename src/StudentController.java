package src;
@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody Student student) {
    try {
        studentService.register(student);
        return ResponseEntity.ok(Map.of("message", "Registration successful!"));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(Map.of("message", "Username already exists."));
    }
}