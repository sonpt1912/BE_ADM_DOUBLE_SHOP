package com.example.be_adm_double_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/doubleshop/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/index")
    public ResponseEntity<Page<NhanVien>> getAll(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<NhanVien> nhanVienPage = nhanVienService.findAll(pageable);
        return ResponseEntity.ok().body(nhanVienPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhanVien> getById(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        NhanVien nhanVien = nhanVienService.findById(id);
        return ResponseEntity.ok().body(nhanVien);
    }

    @PostMapping("/add")
    public ResponseEntity<NhanVien> addNhanVien(@RequestBody @Valid NhanVien nhanVien) {
        NhanVien savedNhanVien = nhanVienService.save(nhanVien);
        return ResponseEntity.ok().body(savedNhanVien);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NhanVien> updateNhanVien(@PathVariable("id") UUID id, @RequestBody @Valid NhanVien nhanVien) throws ResourceNotFoundException {
        NhanVien existingNhanVien = nhanVienService.findById(id);
        existingNhanVien.setTen(nhanVien.getTen());
        existingNhanVien.setChucVu(nhanVien.getChucVu());
        existingNhanVien.setNgaySinh(nhanVien.getNgaySinh());
        // Set other properties of NhanVien as needed

        NhanVien updatedNhanVien = nhanVienService.updateNhanVien(id, existingNhanVien);
        return ResponseEntity.ok().body(updatedNhanVien);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable("id") UUID id) {
        nhanVienService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<NhanVien>> searchNhanVien(@RequestParam("keyword") String keyword) {
        List<NhanVien> nhanVienList = nhanVienService.searchNhanVien(keyword);
        return ResponseEntity.ok().body(nhanVienList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<NhanVien>> getSortedNhanVienList() {
        List<NhanVien> sortedNhanVienList = nhanVienService.sapXep();
        return ResponseEntity.ok().body(sortedNhanVienList);
    }

    @GetMapping
    public ResponseEntity<List<NhanVien>> getAll() {
        return ResponseEntity.ok().body(nhanVienService.getAll());
    }
}

