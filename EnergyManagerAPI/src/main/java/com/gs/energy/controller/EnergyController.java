package com.gs.energy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/energy")
public class EnergyController {

    private final Map<Integer, Map<String, Object>> records = new HashMap<>();
    private int idCounter = 1;

    // 📦 GET - lista todos os registros
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        return ResponseEntity.ok(new ArrayList<>(records.values()));
    }

    // ➕ POST - adiciona um novo registro
    @PostMapping
    public ResponseEntity<Map<String, Object>> addRecord(@RequestBody Map<String, Object> record) {
        record.put("id", idCounter++);
        records.put((Integer) record.get("id"), record);
        return ResponseEntity.ok(record);
    }

    // 🧾 GET - busca um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable int id) {
        Map<String, Object> record = records.get(id);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }

    // ✏️ PUT - atualiza um registro
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable int id, @RequestBody Map<String, Object> record) {
        if (!records.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        record.put("id", id);
        records.put(id, record);
        return ResponseEntity.ok(record);
    }

    // ❌ DELETE - remove um registro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        records.remove(id);
        return ResponseEntity.noContent().build();
    }
}
